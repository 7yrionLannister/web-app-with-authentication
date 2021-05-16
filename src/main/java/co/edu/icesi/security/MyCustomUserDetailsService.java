package co.edu.icesi.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import co.edu.icesi.model.Userr;
import co.edu.icesi.repository.UserRepositoryI;

@Service
public class MyCustomUserDetailsService implements UserDetailsService {
	private UserRepositoryI userRepository;
	
	@Autowired
	public MyCustomUserDetailsService(UserRepositoryI userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Userr user = userRepository.findByUserName(username);
		if (user != null) {
			System.out.println("found");
			User.UserBuilder builder = User.withUsername(username).password(user.getUserPassword()).roles(user.getUserType().toString());
			return builder.build();
		} else {
			System.out.println("not found");
			throw new UsernameNotFoundException("User not found.");
		}
	}
}