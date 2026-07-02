package course.hub.model;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user_details")
@Getter
@Setter
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(unique = true, nullable = false)
	private String username;
	
	@Column(unique = true, nullable = false)
	private String email;
	
	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Role role;
	
	@CreationTimestamp
	@Column(updatable = false)
	private LocalDateTime creationTime;
	
	@UpdateTimestamp
	private LocalDateTime updateTime;
	
//	Instructor Mapping
	@OneToMany(mappedBy = "instructor")
	@JsonIgnore
	private List<Course> courses;
	
//	Student Mapping
	@ManyToMany
	@JoinTable(name = "students_enrolled",joinColumns = @JoinColumn(name = "student_id"),
			   inverseJoinColumns = @JoinColumn(name = "course_id"))
	private List<Course> enrolled;
	
}
