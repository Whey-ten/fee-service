package fmfi.sbdemo;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class SbDemoApplicationTests {

	@LocalServerPort // injects actual port to the annotated field
	private int port;

	@Autowired // injects instance of TestRestTemplate to the annotated field
	private TestRestTemplate restTemplate;

	//@Test
	void givenNoSubject_whenHelloCalled_thenShouldReturnHelloWorld() {
		// given
		var url = "http://localhost:" + port + "/api/hello";

		// when
		var helloResult = this.restTemplate.getForObject(url, String.class);

		// then
		assertThat(helloResult).isEqualTo("Hello, World");
	}

	//@Test
	void givenSubjectTest_whenHelloCalled_thenShouldReturnHelloTest() {
		// given
		var url = "http://localhost:" + port + "/api/hello?subject=test";

		// when
		var helloResult = this.restTemplate.getForObject(url, String.class);

		// then
		assertThat(helloResult).isEqualTo("Hello, test");
	}
}

