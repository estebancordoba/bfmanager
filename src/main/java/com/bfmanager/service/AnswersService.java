package com.bfmanager.service;

import java.util.List;

import com.bfmanager.model.hibernate.Answers;

public interface AnswersService {
	public void saveAnswer(Answers answer);
	
	public void updateAnswer(Answers answer);
	
	public void removeAnswer(Answers answer);
	
	public Answers searchAWxId(Long id_answer);

	public List<Answers> getAnswer();
	
	public Answers getAnswerxIdSurveyIdGroupxIdUser(Integer id_survey, Integer id_group, Integer id_user);
	
	public List<Answers> getAnswerxIdSurveyIdGroupShow(Integer id_survey, Integer id_group, List<Long> lst_questionsM);
	
	public List<Answers> getAnswerQuestion(Long id_question);
	
	public List<Answers> getAnswerQuestionGroup(Long id_question, Integer id_group);
	
	public List<Answers> getAnswersSurvey(Integer id_survey);
	
	public List<Answers> getAnswersSurveyUserGroup(Integer id_user,Integer id_survey, Integer id_group);	
}
