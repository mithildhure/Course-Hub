package course.hub.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import course.hub.model.Role;
import course.hub.model.User;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepo extends JpaRepository<User, Integer>{

	boolean existsByUsername(String username);
	
	boolean existsByEmail(String email);
	
	Optional<User> findByUsername(String username);
	
	List<User> findByRole(Role role);
	
}
