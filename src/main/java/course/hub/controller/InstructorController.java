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
import course.hub.service.InstructorService;

@RestController
@RequestMapping("/instructor")
public class InstructorController {

	@Autowired
	private InstructorService service;
	
//	@PostMapping("/register")
//	public ResponseEntity<User> registerInstructor(@RequestBody UserRegisterDto dto) {
//		return service.addInstructor(dto); 
//	}
//	
//	@PutMapping("/update")
//	public ResponseEntity<User> updateInstructorDetails(@RequestParam Integer id, @RequestBody UserDetailsUpdateDto dto){
//		return service.updateInstructorDetails(id, dto);
//	}
//	
//	@DeleteMapping("/delete")
//	public ResponseEntity<User> deleteInstructor(@RequestParam Integer id){
//		return service.deleteInstructor(id);
//	}	
}
