package com.bfmanager.test.integration;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.bfmanager.model.dao.RatingsDAO;
import com.bfmanager.model.dao.UsersDAO;
import com.bfmanager.model.hibernate.Ratings;
import com.bfmanager.model.hibernate.Users;
import com.bfmanager.model.hibernate.UsersType;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/test-context.xml" })
public class TestDAO {

 	@Autowired
	private UsersDAO userDAO;
	@Autowired
	private RatingsDAO ratingsDAO;

 	@Before
	public void setUp() {
	}

 	@Test
	@Transactional
    @Rollback(true)
	public void testUsersDAO() {
		List<Users> users;

 		users = userDAO.getUsers();		
		Assert.assertNotNull(users);

 		Users user= userDAO.firstUser();
		Assert.assertNotNull(user);

 		if(user != null){		
			Users user_aux= userDAO.getUserXUser(user.getUser());
			Assert.assertEquals(user, user_aux);

 			user_aux= userDAO.getUserXId(user.getIdUser());
			Assert.assertEquals(user, user_aux);

 			user_aux= userDAO.getUserXId(user.getIdUser());
			Assert.assertEquals(user, user_aux);
		}	

 		List<UsersType> user_type = userDAO.getUserTypes();
		Assert.assertNotNull(user_type);
	}

 	@Test
	@Transactional
    @Rollback(true)
	public void testRatingsDAO() {
		List<Ratings> ratings;

 		ratings = ratingsDAO.getRatings();		
		Assert.assertNotNull(ratings);
	}
}