package com.project.guestapp.dao;

import java.sql.Types;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.project.guestapp.model.User;

/**
 * Repository class to hold the database operations related to Use
 * 
 * @author Harshitha
 *
 */
@Repository
public class UserRepository {

	 private static final Logger LOG = LoggerFactory.getLogger(UserRepository.class);  
	
	 @Autowired
	 JdbcTemplate jdbcTemplate;
	 
	 public User findUserByName(String name) {                //method to check if a user exists when a feedback is created
		 User user = null;
		 try {
			 user = jdbcTemplate.queryForObject("select name as userName, guest_id as userId from guestuser where name= ?", new Object[] {name}, new BeanPropertyRowMapper<User>(User.class));
		 } catch (EmptyResultDataAccessException e) {
			 // if not found then there is no such user, need to create new user
			 LOG.error("User not found " + name);
		 }
		 
		 return user;
	 }
	 
	 public User createUser(User user) {
		 KeyHolder primaryKey = new GeneratedKeyHolder();              //auto-generates an id for every database entry 
		 
		 PreparedStatementCreatorFactory factory = new PreparedStatementCreatorFactory(
				 "insert into guestuser(name, created_on, updated_on) values (? , CURRENT_TIMESTAMP, CURRENT_TIMESTAMP) ", Types.CHAR);
		 factory.setReturnGeneratedKeys(true);
		 PreparedStatementCreator preparedStatement = factory.newPreparedStatementCreator(new String[] {user.getName()});
		 
		 jdbcTemplate.update(preparedStatement, primaryKey);
		 user.setUserId(primaryKey.getKey().intValue());
		 return user;
	 }
}
