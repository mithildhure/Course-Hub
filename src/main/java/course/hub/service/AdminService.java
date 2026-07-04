package course.hub.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import course.hub.dto.UserDetailsUpdateDto;
import course.hub.model.Course;
import course.hub.model.Role;
import course.hub.model.User;
import course.hub.repo.CourseRepo;
import course.hub.repo.UserRepo;

@Service
public class AdminService {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CourseRepo courseRepo;
	
//	Student 
	public List<User> getAllStudents(){
		return userRepo.findByRole(Role.STUDENT);
	}
	
//	Admin
	public List<User> getAllInstructor(){
		return userRepo.findByRole(Role.INSTRUCTOR);
	}
	
//	Courses
	public List<Course> getAllCourse(){
		return courseRepo.findAll();
	}
	
//	Update Student
	public User updateStudent(Integer id, UserDetailsUpdateDto dto) {
		Optional<User> opt = userRepo.findById(id);
		if(opt.isPresent()) {
			User user = opt.get();
			user.setName(dto.getName());
			user.setEmail(dto.getEmail());
			user.setPassword(dto.getPassword());
			user.setUsername(dto.getUsername());
			return userRepo.save(user);
		}else {
			throw new RuntimeException("Student not found");
		}
	}
	
	
}
