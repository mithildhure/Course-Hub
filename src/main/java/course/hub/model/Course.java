package course.hub.model;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer courseId;
	
	@Column(nullable = false)
	private String courseName;
	
	@Column(nullable = false)
	private String description;
	
	@Column(nullable = false)
	private Integer duration;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Category category;
	
	@CreationTimestamp
	@Column(updatable = false)
	private LocalDateTime courseAdded;
	
	@UpdateTimestamp
	private LocalDateTime courseLastUpdated;
	
//	Instructor Mapping
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "instructor_id")
	private User instructor;
	
//	Student Mapping
	@ManyToMany(mappedBy = "enrolled")
	private List<User> students;
	
}
