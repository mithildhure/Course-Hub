package course.hub.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import course.hub.model.Course;

@Repository
public interface CourseRepo extends JpaRepository<Course, Integer>{

	
	
}
