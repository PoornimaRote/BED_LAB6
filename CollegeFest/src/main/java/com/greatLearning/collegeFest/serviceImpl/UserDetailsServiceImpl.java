package com.greatLearning.collegeFest.serviceImpl;

//import java.util.*;
import com.greatLearning.collegeFest.entity.User;
import com.greatLearning.collegeFest.repository.UserRepository;
import com.greatLearning.collegeFest.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private final UserRepository userRepository;

	@Autowired
	public UserDetailsServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<User> userOptional = userRepository.findByUsername(username);
//        
//        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
//        
//        return new MyUserDetails(user);
//    }
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

		return new MyUserDetails(user);
	}

}
