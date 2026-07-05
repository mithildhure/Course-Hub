package course.hub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import course.hub.dto.CourseDto;
import course.hub.dto.UserDetailsUpdateDto;
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
	
	@PutMapping("/student/update/{studentId}")
	public ResponseEntity<User> updateStudent(@PathVariable Integer studentId, @RequestBody UserDetailsUpdateDto dto){
		return new ResponseEntity<User>(service.updateStudent(studentId, dto), HttpStatus.OK);
	}
	
	@DeleteMapping("/student/delete/{studentId}")
	public ResponseEntity<String> deleteStudent(@PathVariable Integer studentId){
		return new ResponseEntity<String>(service.deleteStudent(studentId), HttpStatus.OK);
	}
	
	@PutMapping("/instructor/update/{instructorId}")
	public ResponseEntity<User> updateInstructor(@PathVariable Integer instructorId, @RequestBody UserDetailsUpdateDto dto){
		return new ResponseEntity<User>(service.updateInstructor(instructorId, dto), HttpStatus.OK);
	}
	
	@DeleteMapping("/instructor/delete/{instructorId}")
	public ResponseEntity<String> deleteInstructor(@PathVariable Integer instructorId){
		return new ResponseEntity<String>(service.deleteInstructor(instructorId), HttpStatus.OK);
	}
	
	@PutMapping("/course/update/{courseId}")
	public ResponseEntity<Course> updateCourse(@PathVariable Integer courseId, @RequestBody CourseDto dto){
		return new ResponseEntity<Course>(service.updateCourse(courseId, dto), HttpStatus.OK);
	}
	
	@DeleteMapping("/course/delete/{courseId}")
	public ResponseEntity<String> deleteCourse(@PathVariable Integer courseId){
		return new ResponseEntity<String>(service.deleteCourse(courseId), HttpStatus.OK);
	}
	
}
