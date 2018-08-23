package com.bfmanager.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.bfmanager.model.dao.UsersDAO;
import com.bfmanager.model.hibernate.Users;
import com.bfmanager.model.hibernate.UsersType;

@Transactional(readOnly = true)
public class UsersServiceImpl implements UsersService, Serializable {

	private static final long serialVersionUID = 1L;
	private UsersDAO usersDAO;
		
	@Override
	public List<Users> getUsers() {
		return usersDAO.getUsers();
	}
	@Override
	public List<Users> getUsersManager() {
		return usersDAO.getUsersManager();
	}
	@Override
	public List<Users> getUsersUser() {
		return usersDAO.getUsersUser();
	}
	@Override
	public void saveUser(Users user) {
		usersDAO.saveUser(user);
		
	}
	@Override
	public void updateUser(Users user) {
		usersDAO.updateUser(user);
		
	}
	@Override
	public void removeUser(Users user) {
		usersDAO.removeUser(user);
		
	}
	@Override
	public Users login(Users user) {
		return usersDAO.login(user);
	}
	@Override
	public List<UsersType> getUserTypes() {
		return usersDAO.getUserTypes();
	}
	@Override
	public UsersType getUserTypeXId(int id) {
		return usersDAO.getUserTypeXId(id);
	}
	
	@Override
	public Users getUserXId(int id) {
		return usersDAO.getUserXId(id);
	}
	
	@Override
	public Users getUserXUser(String user) {
		return usersDAO.getUserXUser(user);
	}
	
	@Override
	public Users firstUser() {
		return usersDAO.firstUser();
	}
	
	public void setUsersDAO(UsersDAO usersDAO) {
		this.usersDAO = usersDAO;
	}

}
