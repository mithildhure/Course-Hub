package course.hub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import course.hub.model.Course;
import course.hub.model.User;
import course.hub.service.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService service;
	
	@GetMapping("/students")
	public ResponseEntity<List<User>> studentDashboard(){
		return new ResponseEntity<List<User>>(service.getAllStudents(), HttpStatus.OK);
	}
	
	@GetMapping("/instructors")
	public ResponseEntity<List<User>> instructorDashboard(){
		return new ResponseEntity<List<User>>(service.getAllInstructor(), HttpStatus.OK);
	}
	
	@GetMapping("/courses")
	public ResponseEntity<List<Course>> courseDashboard(){
		return new ResponseEntity<List<Course>>(service.getAllCourse(), HttpStatus.OK);
	}
	
}
