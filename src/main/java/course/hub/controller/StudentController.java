package course.hub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import course.hub.dto.UserDetailsUpdateDto;
import course.hub.dto.UserRegisterDto;
import course.hub.model.User;
import course.hub.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentService service;
	
	@PostMapping("/register")
	public ResponseEntity<User> register(@RequestBody UserRegisterDto dto){
		return service.addStudent(dto);
	}
	
	@PutMapping("/update")
	public ResponseEntity<User> updateStudentDetails(@RequestParam Integer id, @RequestBody UserDetailsUpdateDto dto){
		return service.updateStudentDetails(id, dto);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<User> deleteStudent(@RequestParam Integer id){
		return service.deleteStudent(id);
	}
	
}
