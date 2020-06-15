package com.project.guestapp.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.project.guestapp.model.Feedback;
import com.project.guestapp.model.User;

@Repository
public class FeedbackRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public List<Feedback> findAllFeedback() {
		return jdbcTemplate.query("select "
				+ "fb.feedback_id, fb.guest_id , fb.comments, fb.likes, fb.created_on, gu.name as guest_name from "
				+ "feedback fb, guestuser gu where gu.guest_id = fb.guest_id ", new FeedbackRowMapper());
	}

	public Feedback findFeedback(int id) {
		return jdbcTemplate.queryForObject(
				"select " + "fb.feedback_id, fb.comments, fb.likes, fb.created_on, gu.name as guest_name from "
						+ "feedback fb, guestuser gu where gu.guest_id = fb.guest_id and fb.feedback_id = ?",
				new FeedbackRowMapper(), id);
	}

	public void saveFeedback(Feedback feedback) {
		jdbcTemplate.update(
				"insert into Feedback (guest_id,comments,likes,created_on, updated_on) "
						+ "values (?,?,?,CURRENT_TIMESTAMP,CURRENT_TIMESTAMP)",
				feedback.getUser().getUserId(), feedback.getComment(), feedback.getLikes());

	}

	public void updateFeedback(Feedback feedback) {
		jdbcTemplate.update("update feedback set likes = ?, updated_on = CURRENT_TIMESTAMP  where feedback_id= ?",
				feedback.getLikes(), feedback.getFeedbackId());
	}

	public void deleteFeedback(int fbId) {
		jdbcTemplate.update("delete from Feedback where feedback_id=?", fbId);
	}

}