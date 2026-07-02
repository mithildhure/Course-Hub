package course.hub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import course.hub.dto.CourseDto;
import course.hub.dto.UserDetailsUpdateDto;
import course.hub.model.Course;
import course.hub.model.User;
import course.hub.service.InstructorService;

@RestController
@RequestMapping("/instructor")
public class InstructorController {

	@Autowired
	private InstructorService service;
	
//	LATER ADD AUTHENTICATION
	@GetMapping("/dashboard/{instructorId}")
	public ResponseEntity<List<Course>> dashboard(@PathVariable Integer instructorId){
		return new ResponseEntity<List<Course>>(service.getInstructorCourses(instructorId), HttpStatus.OK);
	}
	
	@PutMapping("/update/{instructorId}")
	public ResponseEntity<User> updateInstructorDetails(@PathVariable Integer instructorId, @RequestBody UserDetailsUpdateDto dto){
		return new ResponseEntity<User>(service.updateInstructorDetails(instructorId, dto), HttpStatus.OK);
	}
	
	@PostMapping("/course/add/{instructorId}")
	public ResponseEntity<Course> addCourse(@PathVariable Integer instructorId, @RequestBody CourseDto dto){
		return new ResponseEntity<Course>(service.addCourse(instructorId, dto), HttpStatus.OK);
	}
	
	@PutMapping("/course/update/{instructorId}/{courseId}")
	public ResponseEntity<Course> updateCourse(@PathVariable Integer instructorId, @PathVariable Integer courseId, @RequestBody CourseDto dto){
		return new ResponseEntity<Course>(service.updateCourseDetails(instructorId, courseId, dto), HttpStatus.OK);
	}
	
	@DeleteMapping("/course/delete/{instructorId}/{courseId}")
	public ResponseEntity<Course> deleteCourse(@PathVariable Integer instructorId, @PathVariable Integer courseId){
		service.deleteCourse(instructorId, courseId);
		return new ResponseEntity<Course>(HttpStatus.OK);
	}
	
}
