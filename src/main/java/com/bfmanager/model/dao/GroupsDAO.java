package com.bfmanager.model.dao;

import java.util.List;

import com.bfmanager.model.hibernate.Groups;
import com.bfmanager.model.hibernate.SurveyGroup;
import com.bfmanager.model.hibernate.UserGroup;;

public interface GroupsDAO {

	//Groups
	public void saveGroup(Groups group);
	
	public void updateGroup(Groups group);
	
	public void removeGroup(Groups group);
	
	public Groups searchGroupxId(Integer id_group);
	
	public Groups firstGroup();

	public List<Groups> getGroups();
	
	//SurveyGroup
	public void saveSurveyGroup(SurveyGroup survey_group);
	
	public void updateSurveyGroup(SurveyGroup survey_group);
	
	public void removeSurveyGroup(SurveyGroup survey_group);
	
	public SurveyGroup searchSurveyGroupxId(Integer id_survey_group);

	public List<SurveyGroup> getSurveyGroup();
	
	public List<SurveyGroup> getSurveyGroupsxIdSurvey(Integer id_survey);
	
	public List<SurveyGroup> getSurveyGroupxLstGroups(List<Integer> lstGroups);
	
	//UserGroup
	public void saveUserGroup(UserGroup user_group);
	
	public void updateUserGroup(UserGroup user_group);
	
	public void removeUserGroup(UserGroup user_group);
	
	public UserGroup searchUserGroupxId(Integer id_user_group);

	public List<UserGroup> getUserGroup();
	
	public List<UserGroup> getUserGroupxIdGroup(Integer id_group);
	
	public List<UserGroup> getUserGroupxIdUser(Integer id_user);
}
