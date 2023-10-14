package info.ashutosh.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import info.ashutosh.domain.MyUser;

@Service
public class MyUserService {

	@Autowired
	private MyUserRepository userRepository;

	public List<MyUser> getAllUsers() {
		return userRepository.findAll();
	}

	public Optional<MyUser> getUserById(Long id) {
		return userRepository.findById(id);
	}

	public MyUser createUser(MyUser user) {
		return userRepository.save(user);
	}

	public MyUser updateUser(Long id, MyUser updatedUser) {
		if (userRepository.existsById(id)) {
			updatedUser.setId(id);
			return userRepository.save(updatedUser);
		}
		return null;
	}

	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}
}
