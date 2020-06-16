package com.project.guestapp.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.guestapp.dao.FeedbackRepository;
import com.project.guestapp.dao.UserRepository;
import com.project.guestapp.model.Feedback;
import com.project.guestapp.model.User;

/**
 * Service class to cover business logic related to feeddback 
 *  
 * @author Harshitha
 */
@Service
public class FeedbackService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FeedbackService.class);

	@Autowired
	private FeedbackRepository feedbackRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Transactional
	public List<Feedback> getAllFeedback() {
		return this.feedbackRepository.findAllFeedback();
	}
	
	@Transactional
	public void saveFeedback(Feedback feedback) {
		LOGGER.info("Saving feedback " + feedback);
		User userWithId = this.userRepository.findUserByName(feedback.getUser().getName());
		if(userWithId == null) {
		   userWithId = this.userRepository.createUser(feedback.getUser());	
		}
		feedback.setUser(userWithId);
	    this.feedbackRepository.saveFeedback(feedback);
		
	}
	
	@Transactional
	public void addLike(int feedbackId) {
		 LOGGER.info("Liking feedback " + feedbackId);
		 Feedback feedback = this.feedbackRepository.findFeedback(feedbackId);
		 feedback.setLikes(feedback.getLikes() + 1);                            //increments the likes 
		 this.feedbackRepository.updateFeedback(feedback);
	}
	
	@Transactional
	public void unLike(int feedbackId) {
		LOGGER.info("UnLiking feedback " + feedbackId);
		Feedback feedback = this.feedbackRepository.findFeedback(feedbackId);
		feedback.setUnLikes(feedback.getUnLikes() + 1);
		this.feedbackRepository.updateFeedback(feedback);                       //increments the unlikes 
	}
	
	@Transactional
	public void deleteFeedback(int feedbackId) {
		LOGGER.info("Deleting feedback " + feedbackId);
		this.feedbackRepository.deleteFeedback(feedbackId);
	}
	
}
