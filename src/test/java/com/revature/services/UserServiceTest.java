package com.revature.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import com.revature.models.NewUserTemplate;
import com.revature.models.User;
import com.revature.repositories.UserDAO;
import com.revature.repositories.UserDAOImpl;

public class UserServiceTest {
	
	UserDAO uDAOMock = mock(UserDAOImpl.class);
	UserService usMock = new UserService(uDAOMock);
	
	@Test
	public void testConfirmLoginNull() {
		when(uDAOMock.findByName("test")).thenReturn(null);
		assertEquals(null, usMock.confirmLogin("test", "password"));
	}
	@Test
	public void testConfirmLoginNotNull() {
		User u = new User();
		u.setPassword("password");
		when(uDAOMock.findByName("test")).thenReturn(u);
		assertEquals(u, usMock.confirmLogin("test", "password"));
	}
	@Test
	public void testConfirmLoginNotPassword() {
		User u = new User();
		u.setPassword("word");
		when(uDAOMock.findByName("test")).thenReturn(u);
		assertEquals(null, usMock.confirmLogin("test", "password"));
	}
	
	@Test
	public void testNewUser(){
		NewUserTemplate nut = new NewUserTemplate();
		nut.name = "ttest";
		nut.email = "tt@gotya.com";
		nut.firstName = "testy";
		nut.lastName = "ttd";
		nut.password = "help";
		
		User u = new User();
		
		when(uDAOMock.insertUser(u)).thenReturn(true);
		assertFalse(usMock.newUser(nut));

	}
	
	@Test
	public void testNewUserTrue(){
		NewUserTemplate nut = new NewUserTemplate();
		nut.name = "ttest";
		nut.email = "tt@gotya.com";
		nut.firstName = "testy";
		nut.lastName = "ttd";
		nut.password = "help";
		
		User u = new User(nut.name, String.valueOf(nut.password.hashCode()), nut.firstName, nut.lastName, nut.email, 1);
		
		when(uDAOMock.insertUser(u)).thenReturn(true);
		assertTrue(usMock.newUser(nut));

	}

}
