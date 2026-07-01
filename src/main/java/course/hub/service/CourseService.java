package course.hub.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import course.hub.dto.CourseDto;
import course.hub.model.Course;
import course.hub.model.Role;
import course.hub.model.User;
import course.hub.repo.CourseRepo;
import course.hub.repo.UserRepo;

@Service
public class CourseService {

	@Autowired
	private CourseRepo courseRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	public String addCourse(Integer instructorId, CourseDto dto) {
		Optional<User> opt = userRepo.findById(instructorId);
		if (opt.isPresent()) {
			User user = opt.get();
			if (user.getRole() == Role.INSTRUCTOR) {				
				Course course = new Course();
				course.setCourseName(dto.getCourseName());
				course.setDescription(dto.getDescription());
				course.setDuration(dto.getDuration());
				course.setCategory(dto.getCategory());
				course.setInstructor(user);
				courseRepo.save(course);
				return "Course added!";
			} else {
				return "Failed to Add Course";
			}
		}else {
			return "Failed to add course";
		}
	}
	
	public String updateCourse(Integer courseId, CourseDto dto) {
		Optional<Course> opt = courseRepo.findById(courseId);
		if (opt.isPresent()) {
			Course course = opt.get();
			course.setCourseName(dto.getCourseName());
			course.setDescription(dto.getDescription());
			course.setDuration(dto.getDuration());
			course.setCategory(dto.getCategory());
			courseRepo.save(course);
			return "course updated";
		}else {
			return "course not found";
		}
	}
	
	public String deleteCourse(Integer courseId) {
		Optional<Course> opt = courseRepo.findById(courseId);
		if (opt.isPresent()) {
			courseRepo.deleteById(courseId);
			return "course deleted";
		}else {
			return "course not found";
		}
	}
	
	
}
