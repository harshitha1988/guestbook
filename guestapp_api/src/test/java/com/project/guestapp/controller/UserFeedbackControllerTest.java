package com.project.guestapp.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

import com.project.guestapp.model.Feedback;
import com.project.guestapp.model.RestResponse;
import com.project.guestapp.model.User;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UserFeedbackControllerTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void testFeedbackList() throws Exception {
		List<Feedback> feedbacks = this.restTemplate.getForObject("http://localhost:" + port + "/feedback", List.class);
		assertNotNull(feedbacks);
	}

	@Test
	public void testCreateFeedback() throws Exception {
		Feedback postBody = new Feedback();
		postBody.setComment("This is junit test");
		postBody.setUser(new User("junit user"));
		ResponseEntity<RestResponse> response = this.restTemplate
				.postForEntity("http://localhost:" + port + "/feedback/create", postBody, RestResponse.class);

		Assertions.assertNotNull(response);
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

	}

	@Test
	public void testCreateFeedbackFail() throws Exception {
		Feedback postBody = new Feedback();
		postBody.setComment(null);
		postBody.setUser(new User("junit user"));
		ResponseEntity<RestResponse> response = this.restTemplate
				.postForEntity("http://localhost:" + port + "/feedback/create", postBody, RestResponse.class);

		Assertions.assertNotNull(response);
		Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}

	@Test
	public void testLikeFeedback() throws Exception{
		Feedback[] feedbacks = this.restTemplate.getForObject("http://localhost:" + port + "/feedback", Feedback[].class);
		Feedback feedback = feedbacks[0];
		RequestEntity request = RequestEntity.put(new URI("http://localhost:" + port + "/feedback/like/" + feedback.getFeedbackId())).accept(MediaType.APPLICATION_JSON)
				.build();
		ResponseEntity<RestResponse> response = restTemplate.exchange(request, RestResponse.class);

		Assertions.assertNotNull(response);
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
	}
}
