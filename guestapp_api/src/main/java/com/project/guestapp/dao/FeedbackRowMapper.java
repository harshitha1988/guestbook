package com.project.guestapp.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import org.springframework.jdbc.core.RowMapper;

import com.project.guestapp.model.Feedback;
import com.project.guestapp.model.User;

public class FeedbackRowMapper implements RowMapper<Feedback>{

	@Override
	public Feedback mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Feedback feedback = new Feedback();
		feedback.setFeedbackId(rs.getInt("feedback_id"));
		feedback.setUser(new User(rs.getString("guest_name")));
		feedback.setComment(rs.getString("comments"));
		feedback.setLikes(rs.getInt("likes"));
		feedback.setUnLikes(rs.getInt("unlikes"));
		feedback.setCreateOn(rs.getTimestamp("created_on").toLocalDateTime());
		
		return feedback;
	}

}
