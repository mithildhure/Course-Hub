package course.hub.dto;

import course.hub.model.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseDto {

	private String courseName;
	private String description;
	private Integer duration;
	private Category category;
	
}
