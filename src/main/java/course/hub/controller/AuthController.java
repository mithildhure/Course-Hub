package course.hub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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
	public ResponseEntity<User> registerUser(@RequestBody UserRegisterDto dto){
		return new ResponseEntity<User>(service.register(dto),HttpStatus.OK);
	}
	
	@PostMapping("/login")
	public ResponseEntity<User> loginUser(UserLoginDto dto){
		service.login(dto);
		return new ResponseEntity<User>(HttpStatus.OK);
	}
	
//	Pagination 
	@GetMapping("/home")
	public ResponseEntity<Page<Course>> home(@RequestParam(defaultValue = "0") Integer pageNumber, @RequestParam(defaultValue = "10", required = false) Integer pageSize) {
		return new ResponseEntity<Page<Course>>(service.homePageCourses(pageNumber, pageSize), HttpStatus.OK);
	}

}
