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
public class InstructorService {
	
	@Autowired
	private UserRepo repo;
	
	public ResponseEntity<User> addInstructor(UserRegisterDto dto){
		if (repo.existsByUsername(dto.getUsername()) || repo.existsByEmail(dto.getEmail())) {
			throw new RuntimeException("Username or Email already Registered!");
		} else {
			User user = new User();
			user.setName(dto.getName());
			user.setPassword(dto.getPassword());
			user.setUsername(dto.getUsername());
			user.setEmail(dto.getEmail());
			user.setRole(dto.getRole().INSTRUCTOR);
			return new ResponseEntity<User>(repo.save(user), HttpStatus.OK);
		}
		
	}
	
	public ResponseEntity<User> removeInstructor(Integer id){
		Optional<User> opt = repo.findById(id);
		if (opt.isPresent()) {
			User user = opt.get();
			repo.delete(user);
			return new ResponseEntity<User>(HttpStatus.OK);
		}else {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
	}
	
	public ResponseEntity<User> updateInstructorDetails(Integer id, UserDetailsUpdateDto dto){
		Optional<User> opt = repo.findById(id);
		if (opt.isPresent()) {
			User user = opt.get();
			user.setName(dto.getName());
			user.setEmail(dto.getEmail());
			user.setPassword(dto.getPassword());
			user.setUsername(dto.getUsername());
			return new ResponseEntity<User>(repo.save(user), HttpStatus.OK);
		}else {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
	}
	
	public ResponseEntity<User> deleteInstructor(Integer id){
		Optional<User> opt = repo.findById(id);
		if (opt.isPresent()) {
			repo.deleteById(id);
			return new ResponseEntity<User>(HttpStatus.OK);
		}else {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
	}
	
}
