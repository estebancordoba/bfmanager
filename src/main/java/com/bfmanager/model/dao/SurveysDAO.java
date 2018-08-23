package com.bfmanager.model.dao;

import java.util.List;

import com.bfmanager.model.hibernate.Surveys;
import com.bfmanager.model.hibernate.SurveysType;
import com.bfmanager.model.hibernate.Users;

public interface SurveysDAO {	
	public void saveSurvey(Surveys survey);
	
	public void updateSurvey(Surveys survey);
	
	public void removeSurvey(Surveys survey);
	
	public Surveys searchxId(Integer id_survey);
	
	public Surveys firstSurvey();

	public List<Surveys> getSurveys();
	
	public List<Surveys> getSurveysFree();
	
	public List<Surveys> getSurveysBFI();
	
	public List<Surveys> getSurveysxIdManager(Integer id);
	
	public List<Surveys> getSurveysBFIxIdManager(Integer id);
		
	public Users getUserXId(int id);
	
	public List<SurveysType> getSurveysType();
	
	public SurveysType getSurveyTypeXId(int id);
			
}
