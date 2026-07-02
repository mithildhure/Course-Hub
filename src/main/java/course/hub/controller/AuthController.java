package course.hub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import course.hub.dto.UserLoginDto;
import course.hub.dto.UserRegisterDto;
import course.hub.model.Course;
import course.hub.model.User;
import course.hub.service.AuthService;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class AuthController {

	@Autowired
	private AuthService service;
	
	@PostMapping("/register")
	public ResponseEntity<User> registerUser(UserRegisterDto dto){
		return new ResponseEntity<User>(service.register(dto),HttpStatus.OK);
	}
	
	@PostMapping("/login")
	public ResponseEntity<User> loginUser(UserLoginDto dto){
		service.login(dto);
		return new ResponseEntity<User>(HttpStatus.OK);
	}
	
	@GetMapping("/home")
	public ResponseEntity<List<Course>> home() {
		return new ResponseEntity<List<Course>>(service.homePageCourses(), HttpStatus.OK);
	}
	
	
}
