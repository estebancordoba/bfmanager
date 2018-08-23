package com.bfmanager.service;

import java.io.Serializable;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import com.bfmanager.model.dao.SurveysDAO;
import com.bfmanager.model.hibernate.Surveys;
import com.bfmanager.model.hibernate.SurveysType;
import com.bfmanager.model.hibernate.Users;

@Transactional(readOnly = true)
public class SurveysServiceImpl implements SurveysService, Serializable{
	
	private static final long serialVersionUID = 1L;
	private SurveysDAO surveysDAO;

	@Override
	public void saveSurvey(Surveys survey) {
		surveysDAO.saveSurvey(survey);		
	}

	@Override
	public void updateSurvey(Surveys survey) {
		surveysDAO.updateSurvey(survey);
	}

	@Override
	public void removeSurvey(Surveys survey) {
		surveysDAO.removeSurvey(survey);
	}

	@Override
	public Surveys searchxId(Integer id_survey) {
		return surveysDAO.searchxId(id_survey);
	}
	
	@Override
	public Surveys firstSurvey() {
		return surveysDAO.firstSurvey();
	}

	@Override
	public List<Surveys> getSurveys() {
		return surveysDAO.getSurveys();
	}
	
	@Override
	public List<Surveys> getSurveysFree() {
		return surveysDAO.getSurveysFree();
	}
	
	@Override
	public List<Surveys> getSurveysBFI() {
		return surveysDAO.getSurveysBFI();
	}
	
	@Override
	public List<Surveys> getSurveysxIdManager(Integer id) {
		return surveysDAO.getSurveysxIdManager(id);
	}
	
	@Override
	public List<Surveys> getSurveysBFIxIdManager(Integer id) {
		return surveysDAO.getSurveysBFIxIdManager(id);
	}
	
	public void setSurveysDAO(SurveysDAO surveysDAO) {
		this.surveysDAO = surveysDAO;
	}

	@Override
	public Users getUserXId(int id) {
		return surveysDAO.getUserXId(id);
	}
	
	@Override
	public List<SurveysType> getSurveysType() {
		return surveysDAO.getSurveysType();
	}
	
	@Override
	public SurveysType getSurveyTypeXId(int id) {
		return surveysDAO.getSurveyTypeXId(id);
	}
	
}
