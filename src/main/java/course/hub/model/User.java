package course.hub.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
	
	
	
}
