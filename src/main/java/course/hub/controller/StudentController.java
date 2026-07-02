package course.hub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import course.hub.dto.UserDetailsUpdateDto;
import course.hub.model.Course;
import course.hub.model.User;
import course.hub.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentService service;
	
//	View courses for dashboard
	@GetMapping("/dashboard/{id}")
	public ResponseEntity<List<Course>> dashboard(@PathVariable Integer id){
		return new ResponseEntity<List<Course>>(service.showStudentEnrolledCourses(id), HttpStatus.OK);
	}
	
//	LATER ADD AUTHENTICATION THRU JWT AND SECURITY
	@PutMapping("/update/{id}")
	public ResponseEntity<User> updateStudentDetails(@PathVariable Integer id, @RequestBody UserDetailsUpdateDto dto){
		return new ResponseEntity<User>(service.updateProfile(id, dto), HttpStatus.OK);
	}
	
	@PostMapping("/enroll/{studentId}/{courseId}")
	public ResponseEntity<Course> enrollCourse(@PathVariable Integer studentId, @PathVariable Integer courseId){
		return new ResponseEntity<Course>(service.enrollCourse(studentId, courseId), HttpStatus.OK);
	}
	
}
