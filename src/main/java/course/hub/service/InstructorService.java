package course.hub.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import course.hub.dto.CourseDto;
import course.hub.dto.UserDetailsUpdateDto;
import course.hub.model.Course;
import course.hub.model.User;
import course.hub.repo.CourseRepo;
import course.hub.repo.UserRepo;

@Service
public class InstructorService {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CourseRepo courseRepo;
	
//	dashboard
	public List<Course> getInstructorCourses(Integer instructorId){
		User user = userRepo.findById(instructorId).orElseThrow(()-> new RuntimeException("Instructor Not Found"));
		return user.getCourses();
	}
	
//	Update profile
	public User updateInstructorDetails(Integer instructorId, UserDetailsUpdateDto dto){
		Optional<User> opt = userRepo.findById(instructorId);
		if (opt.isPresent()) {
			User user = opt.get();
			user.setName(dto.getName());
			user.setEmail(dto.getEmail());
			user.setPassword(dto.getPassword());
			user.setUsername(dto.getUsername());
			return userRepo.save(user);
		}else {
			return null;
		}
	}
	
//	add instructor course
	public Course addCourse(Integer instructorId, CourseDto dto) {
		User user = userRepo.findById(instructorId).orElseThrow(()->new RuntimeException("Instructor Not Found"));
		Course course = new Course();
		course.setCourseName(dto.getCourseName());
		course.setDescription(dto.getDescription());
		course.setDuration(dto.getDuration());
		course.setCategory(dto.getCategory());
		course.setInstructor(user);
		return courseRepo.save(course);
	}
	
//	update instructor course
	public Course updateCourseDetails(Integer instructorId ,Integer courseId ,CourseDto dto) {
		Course course = courseRepo.findById(courseId).orElseThrow(()->new RuntimeException("Course Not Found"));
		if (course.getInstructor().getId().equals(instructorId)) {
			course.setCourseName(dto.getCourseName());
			course.setDescription(dto.getDescription());
			course.setDuration(dto.getDuration());
			course.setCategory(dto.getCategory());
			return courseRepo.save(course);
		}else {
			return null;
		}
	}
	
//	delete instructor course
//	next time will be using cascade probably
	public String deleteCourse(Integer instructorId, Integer courseId) {
		Course course = courseRepo.findById(courseId).orElseThrow(()->new RuntimeException("Course Not Found"));
		if (course.getInstructor().getId().equals(instructorId)) {
			for(User student : course.getStudents()) {
				student.getEnrolled().remove(course);
			}
			course.getStudents().clear();
			courseRepo.delete(course);
			return "course deleted!";
		}else {
			return "course not found!";
		}
	}
	
}
