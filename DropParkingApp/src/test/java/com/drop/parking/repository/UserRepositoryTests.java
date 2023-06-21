package com.drop.parking.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.drop.parking.entity.User;

/**
 * Test for UserRepository
 * 
 * @author Avantika Tyagi
 *
 */
@ExtendWith(MockitoExtension.class)
public class UserRepositoryTests {

	@Mock
	private UserRepository userRepository;

	private List<User> users;
	private User user;

	@BeforeEach
	void setup() {
		users = new ArrayList<>();

		user = new User(1L, "jack", "jack123", "jack@mail.com");
		User user2 = new User(2L, "mike", "mike123", "mike@mail.com");

		users.add(user);
		users.add(user2);
	}

	/**
	 * Unit Test for findByUserName
	 */
	@Test
	void findByUserName() {
		when(userRepository.findByUserName("jack")).thenReturn(user);
		assertThat(userRepository.findByUserName("jack")).isEqualTo(user);
		verify(userRepository).findByUserName("jack");
	}

	@AfterEach
	void tearDown() {
		verifyNoMoreInteractions(userRepository);
	}

}
