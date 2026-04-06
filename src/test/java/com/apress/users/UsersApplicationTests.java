package com.apress.users;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@SuppressWarnings("unchecked")
class UsersApplicationTests {

	@Value("${local.server.port}")
	private int port;

	private final String BASE_URL = "http://localhost:";
	private final String USERS_PATH = "/users";

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void indexPageShouldReturnHeaderOneContent() {
		assertThat(this.restTemplate.getForObject(BASE_URL + port, String.class))
				.contains("Simple Users Rest Application");
	}

	@Test
	public void usersEndPointShouldReturnCollectionWithTwoUsers() {
        Collection<User> response = restTemplate.getForObject(BASE_URL + port + USERS_PATH, Collection.class);
		assertThat(response.size()).isEqualTo(2);
	}

	@Test
	public void userEndPointFindByUserShouldReturnUser() {
		User user = this.restTemplate.getForObject(BASE_URL + port + USERS_PATH + "/ximena@email.com", User.class);
		assertThat(user).isNotNull();
		assertThat(user.getEmail()).isEqualTo("ximena@email.com");
	}

	@Test
	@DirtiesContext
	public void userEndPointPostNewUserShouldReturnUser() {
		final var user = new User("dummy@email.com", "Dummy");
		final User response = restTemplate.postForObject(BASE_URL + port + USERS_PATH, user, User.class);

		assertThat(response).isNotNull();
		assertThat(response.getEmail()).isEqualTo(user.getEmail());

		Collection<User> users = restTemplate.getForObject(BASE_URL + port + USERS_PATH, Collection.class);
		assertThat(users.size()).isEqualTo(3);
	}

	@Test
	@DirtiesContext
	public void userEndPointDeleteUserShouldReturnVoid() {
		restTemplate.delete(BASE_URL + port + USERS_PATH + "/norma@email.com");
		Collection<User> users = restTemplate.getForObject(BASE_URL + port + USERS_PATH, Collection.class);
		assertThat(users.size()).isEqualTo(1);
	}
}
