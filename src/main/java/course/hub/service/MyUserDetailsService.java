package course.hub.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import course.hub.model.User;
import course.hub.repo.UserRepo;

@Service
public class MyUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepo repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		Optional<User> opt = repo.findByUsername(username);
		if(opt.isPresent()) {
			User u = opt.get();
			UserDetails userDetails = org.springframework.security.core.userdetails.User.builder().username(u.getUsername())
																		.password(u.getPassword())
																		.roles(u.getRole().toString())
																		.build();
			return userDetails;
			
		}else {
			throw new UsernameNotFoundException("Username not found");
		}
		
	}
	
}
