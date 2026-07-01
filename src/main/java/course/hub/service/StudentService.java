package course.hub.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import course.hub.dto.UserDetailsUpdateDto;
import course.hub.dto.UserRegisterDto;
import course.hub.model.User;
import course.hub.repo.UserRepo;

@Service
public class StudentService {

	@Autowired
	private UserRepo repo;
	
	public ResponseEntity<User> addStudent(UserRegisterDto dto){
		User user = new User();
		user.setName(dto.getName());
		user.setEmail(dto.getEmail());
		user.setUsername(dto.getUsername());
		user.setPassword(dto.getPassword());
		user.setRole(dto.getRole());
		return new ResponseEntity<User>(repo.save(user), HttpStatus.OK);
	}
	
	public ResponseEntity<User> updateStudentDetails(Integer id, UserDetailsUpdateDto dto){
		Optional<User> opt = repo.findById(id);
		if (opt.isPresent()) {
			User user = new User();
			user.setName(dto.getName());
			user.setEmail(dto.getEmail());
			user.setUsername(dto.getUsername());
			user.setPassword(dto.getPassword());
			return new ResponseEntity<User>(repo.save(user), HttpStatus.OK);
		}else {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
	}
	
	public ResponseEntity<User> deleteStudent(Integer id){
		Optional<User> opt = repo.findById(id);
		if (opt.isPresent()) {
			repo.deleteById(id);
			return new ResponseEntity<User>(HttpStatus.OK);
		}else {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
	}
	
}
