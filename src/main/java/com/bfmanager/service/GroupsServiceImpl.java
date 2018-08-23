package com.bfmanager.service;

import java.io.Serializable;
import java.util.List;

import com.bfmanager.model.dao.GroupsDAO;
import com.bfmanager.model.hibernate.Groups;
import com.bfmanager.model.hibernate.SurveyGroup;
import com.bfmanager.model.hibernate.UserGroup;

public class GroupsServiceImpl  implements GroupsService, Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private GroupsDAO groupsDAO;
		
	//Groups
	@Override
	public void saveGroup(Groups group) {
		groupsDAO.saveGroup(group);
	}

	@Override
	public void updateGroup(Groups group) {
		groupsDAO.updateGroup(group);
	}

	@Override
	public void removeGroup(Groups group) {
		groupsDAO.removeGroup(group);
	}

	@Override
	public Groups searchGroupxId(Integer id_group) {		
		return groupsDAO.searchGroupxId(id_group);
	}
	
	@Override
	public Groups firstGroup() {		
		return groupsDAO.firstGroup();
	}

	@Override
	public List<Groups> getGroups() {		
		return groupsDAO.getGroups();
	}

	//SurveyGroup
	@Override
	public void saveSurveyGroup(SurveyGroup survey_group) {
		groupsDAO.saveSurveyGroup(survey_group);
	}

	@Override
	public void updateSurveyGroup(SurveyGroup survey_group) {
		groupsDAO.updateSurveyGroup(survey_group);
	}

	@Override
	public void removeSurveyGroup(SurveyGroup survey_group) {
		groupsDAO.removeSurveyGroup(survey_group);
	}

	@Override
	public SurveyGroup searchSurveyGroupxId(Integer id_survey_group) {
		return groupsDAO.searchSurveyGroupxId(id_survey_group);
	}

	@Override
	public List<SurveyGroup> getSurveyGroup() {
		return groupsDAO.getSurveyGroup();
	}
	
	@Override
	public List<SurveyGroup> getSurveyGroupsxIdSurvey(Integer id_survey) {
		return groupsDAO.getSurveyGroupsxIdSurvey(id_survey);
	}
	
	@Override
	public List<SurveyGroup> getSurveyGroupxLstGroups(List<Integer> lstGroups) {
		return groupsDAO.getSurveyGroupxLstGroups(lstGroups);
	}

	//UserGroup
	@Override
	public void saveUserGroup(UserGroup user_group) {
		groupsDAO.saveUserGroup(user_group);
	}

	@Override
	public void updateUserGroup(UserGroup user_group) {
		groupsDAO.updateUserGroup(user_group);
	}

	@Override
	public void removeUserGroup(UserGroup user_group) {
		groupsDAO.removeUserGroup(user_group);
	}

	@Override
	public UserGroup searchUserGroupxId(Integer id_user_group) {
		return groupsDAO.searchUserGroupxId(id_user_group);
	}

	@Override
	public List<UserGroup> getUserGroup() {
		return groupsDAO.getUserGroup();
	}
	
	@Override
	public List<UserGroup> getUserGroupxIdGroup(Integer id_group){
		return groupsDAO.getUserGroupxIdGroup(id_group);
	}
	
	@Override
	public List<UserGroup> getUserGroupxIdUser(Integer id_user){
		return groupsDAO.getUserGroupxIdUser(id_user);
	}
	
	//DAO
	public GroupsDAO getGroupsDAO() {
		return groupsDAO;
	}

	public void setGroupsDAO(GroupsDAO groupsDAO) {
		this.groupsDAO = groupsDAO;
	}
}
