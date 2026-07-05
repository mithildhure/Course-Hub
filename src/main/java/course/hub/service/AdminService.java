package course.hub.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import course.hub.dto.CourseDto;
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
	
//	Delete Student
	public String deleteStudent(Integer id) {
		Optional<User> opt = userRepo.findById(id);
		if (opt.isPresent()) {
			userRepo.deleteById(id);
			return "Student deleted";
		}else {
			throw new RuntimeException("Student not found");
		}
	}
	
//	Update Instructor
	public User updateInstructor(Integer instructorId, UserDetailsUpdateDto dto) {
		Optional<User> opt = userRepo.findById(instructorId);
		if (opt.isPresent()) {
			User user = opt.get();
			user.setName(dto.getName());
			user.setEmail(dto.getEmail());
			user.setUsername(dto.getUsername());
			user.setPassword(dto.getPassword());
			return userRepo.save(user);
		}else {
			throw new RuntimeException("Instructor not found");
		}
	}
	
//	Update Course
	public Course updateCourse(Integer id, CourseDto dto) {
		Optional<Course> opt = courseRepo.findById(id);
		if(opt.isPresent()) {
			Course c = opt.get();
			c.setCourseName(dto.getCourseName());
			c.setDescription(dto.getDescription());
			c.setDuration(dto.getDuration());
			c.setCategory(dto.getCategory());
			return courseRepo.save(c);
		}else {
			throw new RuntimeException("Course not found");
		}
	}
	
//	Course Delete
	public String deleteCourse(Integer courseId) {
		Optional<Course> opt = courseRepo.findById(courseId);
		if (opt.isPresent()) {
			courseRepo.deleteById(courseId);
			return "course deleted";
		}else {
			throw new RuntimeException("Course not found");
		}
	}
	
	
}
