package course.hub.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import course.hub.dto.UserLoginDto;
import course.hub.dto.UserRegisterDto;
import course.hub.model.Course;
import course.hub.model.User;
import course.hub.repo.CourseRepo;
import course.hub.repo.UserRepo;

@Service
public class AuthService {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CourseRepo courseRepo;
	
	public User register(UserRegisterDto dto) {
		if (userRepo.existsByUsername(dto.getUsername()) || userRepo.existsByEmail(dto.getEmail())) {
			throw new RuntimeException("Username Or Email Already Registered");
		} else {
			User user = new User();
			user.setName(dto.getName());
			user.setEmail(dto.getEmail());
			user.setUsername(dto.getUsername());
			user.setPassword(dto.getPassword());
			user.setRole(dto.getRole());
			userRepo.save(user);
			return user;
		}
	}
	
	public String login(UserLoginDto dto) {
		Optional<User> opt = userRepo.findByUsername(dto.getUsername());
		if (opt.isPresent()) {
			User u = opt.get();
			if (u.getPassword() == dto.getPassword()) {
//				redirect to homepage
				return "login succesfull";
			}else {
				return "wrong password";
			}
		}else {
			return "username doesnt exists!";
		}
	}
	
//	Pagination Required
//	public Page<Course> homePageCourses(){
//		
//		
//		
//		 
//	}
	
}
