package course.hub.dto;

import course.hub.model.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegisterDto {

	private String name;	
	private String username;
	private String email;
	private String password;
	private Role role;
	
}
