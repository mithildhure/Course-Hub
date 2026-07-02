package course.hub.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import course.hub.dto.UserDetailsUpdateDto;
import course.hub.model.Course;
import course.hub.model.User;
import course.hub.repo.CourseRepo;
import course.hub.repo.UserRepo;
import jakarta.transaction.Transactional;

@Service
public class StudentService {

	@Autowired
	private UserRepo repo;
	
	@Autowired
	private CourseRepo courseRepo;
	
	public List<Course> showStudentEnrolledCourses(Integer id){
		User user = repo.findById(id).orElseThrow(()-> new RuntimeException("Student Not Found"));
		return user.getEnrolled();
	}
	
	public User updateProfile(Integer id, UserDetailsUpdateDto dto){
		User user = repo.findById(id).orElseThrow(() -> new RuntimeException("User Not Found"));
			user.setName(dto.getName());
			user.setEmail(dto.getEmail());
			user.setUsername(dto.getUsername());
			user.setPassword(dto.getPassword());
			return repo.save(user);
	}
	
//	Should have transactional i feel like
	
	@Transactional
	public Course enrollCourse(Integer studentId, Integer courseId) {
		Course course = courseRepo.findById(courseId).orElseThrow(()-> new RuntimeException("Course not found"));
		User student = repo.findById(studentId).orElseThrow(()-> new RuntimeException("User Not Found"));
		if (student.getEnrolled().contains(course)) {
			throw new RuntimeException("Already Enrolled!");
		}
		student.getEnrolled().add(course);
		repo.save(student);
		return course;
	}
	
}