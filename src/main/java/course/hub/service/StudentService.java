package course.hub.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import course.hub.dto.UserDetailsUpdateDto;
import course.hub.model.Course;
import course.hub.model.User;
import course.hub.repo.UserRepo;

@Service
public class StudentService {

	@Autowired
	private UserRepo repo;
	
	public List<Course> showStudentEnrolledCourses(Integer id){
		User user = repo.findById(id).orElseThrow(()-> new RuntimeException("Student Not Found"));
		return user.getEnrolled();
	}
	
	public User updateProfile(Integer id, UserDetailsUpdateDto dto){
		User user = repo.findById(id).orElseThrow(() -> new RuntimeException("User Not Found"));
			user.setName(dto.getName());
			user.setEmail(dto.getEmail());
			user.setUsername(dto.getUsername());
			user.setPassword(dto.getPassword());
			return repo.save(user);
	}
	
}