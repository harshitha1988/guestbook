package com.project.guestapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.guestapp.model.Feedback;
import com.project.guestapp.model.RestResponse;
import com.project.guestapp.model.User;
import com.project.guestapp.service.FeedbackService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserFeedbackController {

	@Autowired
	private FeedbackService feedbackService;

	@GetMapping("/feedback")
	public ResponseEntity<List<Feedback>> getFeedbacks() {
		List<Feedback> feedbacks = this.feedbackService.getAllFeedback();
		ResponseEntity<List<Feedback>> response = ResponseEntity.status(HttpStatus.OK).body(feedbacks);
		return response;
	}

	@PostMapping("/feedback/create")
	public ResponseEntity<RestResponse> postFeedback(@RequestBody Feedback feedback) {

		ResponseEntity<RestResponse> response;

		if (feedback == null 
				|| (feedback.getComment() == null || feedback.getComment().isEmpty())
				|| (feedback.getUser() == null || feedback.getUser().getName() == null || feedback.getUser().getName().isEmpty())) 
		{
			response = ResponseEntity.badRequest().body(new RestResponse("comment or user name incomplete"));

		} else {
			Feedback requiredInfo = new Feedback();

			requiredInfo.setComment(feedback.getComment());
			requiredInfo.setUser(new User(feedback.getUser().getName()));

			this.feedbackService.saveFeedback(requiredInfo);

			response = ResponseEntity.ok().body(new RestResponse("Created feedback"));
		}

		return response;
	}

	@PutMapping("/feedback/like/{feedbackId}")
	public ResponseEntity<RestResponse> addLike(@PathVariable Integer feedbackId) {
		ResponseEntity<RestResponse> response;
		if(feedbackId == null) {
			response = ResponseEntity.badRequest().body(new RestResponse("feedback id is missing"));
		} else {
			this.feedbackService.addLike(feedbackId);
			response = ResponseEntity.ok().body(new RestResponse("Feedback liked"));
		}
		
		return response;
	}
	
	@PutMapping("/feedback/unlike/{feedbackId}")
	public ResponseEntity<RestResponse> unLike(@PathVariable Integer feedbackId) {
		ResponseEntity<RestResponse> response;
		if(feedbackId == null) {
			response = ResponseEntity.badRequest().body(new RestResponse("feedback id is missing"));
		} else {
			this.feedbackService.unLike(feedbackId);
			response = ResponseEntity.ok().body(new RestResponse("Feedback unliked"));
		}
		
		return response;
	}
	
	@DeleteMapping("/feedback/delete/{feedbackId}")
	public ResponseEntity<RestResponse> deleteFeedback(@PathVariable Integer feedbackId) {
		ResponseEntity<RestResponse> response;
		if(feedbackId == null) {
			response = ResponseEntity.badRequest().body(new RestResponse("feedback id is missing"));
		} else {
			this.feedbackService.deleteFeedback(feedbackId);
			response = ResponseEntity.ok().body(new RestResponse("Feedback deleted"));
		}
		return response;
	}

}
