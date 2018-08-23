package com.bfmanager.service;

import java.io.Serializable;
import java.util.List;

import com.bfmanager.model.dao.AnswersDAO;
import com.bfmanager.model.hibernate.Answers;

public class AnswersServiceImpl  implements AnswersService, Serializable{
	
	private static final long serialVersionUID = 1L;
	private AnswersDAO answersDAO;
	
	@Override
	public void saveAnswer(Answers answer) {
		answersDAO.saveAnswer(answer);
	}
	@Override
	public void updateAnswer(Answers answer) {
		answersDAO.updateAnswer(answer);
	}
	@Override
	public void removeAnswer(Answers answer) {
		answersDAO.removeAnswer(answer);
	}
	@Override
	public Answers searchAWxId(Long id_answer) {
		return answersDAO.searchAWxId(id_answer);
	}
	@Override
	public List<Answers> getAnswer() {
		return answersDAO.getAnswer();
	}
	@Override
	public Answers getAnswerxIdSurveyIdGroupxIdUser(Integer id_survey, Integer id_group, Integer id_user) {
		return answersDAO.getAnswerxIdSurveyIdGroupxIdUser(id_survey, id_group, id_user);
	}
	@Override
	public List<Answers> getAnswerxIdSurveyIdGroupShow(Integer id_survey, Integer id_group,List<Long> lst_questionsM) {
		return answersDAO.getAnswerxIdSurveyIdGroupShow(id_survey, id_group,lst_questionsM);
	}
	@Override
	public List<Answers> getAnswerQuestion(Long id_question) {
		return answersDAO.getAnswerQuestion(id_question);
	}
	@Override
	public List<Answers> getAnswerQuestionGroup(Long id_question, Integer id_group) {
		return answersDAO.getAnswerQuestionGroup(id_question,id_group);
	}
	@Override
	public List<Answers> getAnswersSurvey(Integer id_survey) {
		return answersDAO.getAnswersSurvey(id_survey);
	}	
	@Override
	public List<Answers> getAnswersSurveyUserGroup(Integer id_user,Integer id_survey, Integer id_group) {
		return answersDAO.getAnswersSurveyUserGroup(id_user, id_survey, id_group);
	}	
	
	public AnswersDAO getAnswersDAO() {
		return answersDAO;
	}
	public void setAnswersDAO(AnswersDAO answersDAO) {
		this.answersDAO = answersDAO;
	}
}
