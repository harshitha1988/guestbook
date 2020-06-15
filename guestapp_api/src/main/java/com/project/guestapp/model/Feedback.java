package com.project.guestapp.model;

import java.time.LocalDateTime;

public class Feedback {

	private Integer feedbackId;

	private User user;

	private String comment;

	private int likes;
	
	private int unLikes;

	private LocalDateTime createOn;

	public Integer getFeedbackId() {
		return feedbackId;
	}

	public void setFeedbackId(Integer feedbackId) {
		this.feedbackId = feedbackId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LocalDateTime getCreateOn() {
		return createOn;
	}

	public void setCreateOn(LocalDateTime createOn) {
		this.createOn = createOn;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public int getUnLikes() {
		return unLikes;
	}

	public void setUnLikes(int unLikes) {
		this.unLikes = unLikes;
	}

	
}
