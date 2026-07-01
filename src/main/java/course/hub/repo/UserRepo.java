package course.hub.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import course.hub.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer>{

	boolean existsByUsername(String username);
	
	boolean existsByEmail(String email);
	
}
