package com.bfmanager.service;

import java.util.List;

import com.bfmanager.model.hibernate.Questions;
import com.bfmanager.model.hibernate.QuestionsType;

public interface QuestionsService {
	public void saveQuestion(Questions question);
	
	public void updateQuestion(Questions question);
	
	public void removeQuestion(Questions question);
	
	public Questions searchxId(Long id_question);
	
	public Questions firstQuestion();

	public List<Questions> getQuestions();
	
	public List<Questions> getQuestionsSurvey(Integer id_survey);
	
	public List<Questions> getQuestionsSurveyStats(Integer id_survey);
	
	public List<Questions> getQuestionsSurveyResults(Integer id_survey);
	
	// QuestionType
	
	public List<QuestionsType> getQuestionsType();
	
	public QuestionsType getQuestionTypeXId(int id);
}
