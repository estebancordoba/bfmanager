package com.bfmanager.service;

import java.io.Serializable;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

import com.bfmanager.model.dao.QuestionsDAO;
import com.bfmanager.model.hibernate.Questions;
import com.bfmanager.model.hibernate.QuestionsType;

@Transactional(readOnly = true)
public class QuestionsServiceImpl implements QuestionsService, Serializable{
	
	private static final long serialVersionUID = 1L;
	private QuestionsDAO questionsDAO;

	@Override
	public void saveQuestion(Questions question) {
		questionsDAO.saveQuestion(question);		
	}

	@Override
	public void updateQuestion(Questions question) {
		questionsDAO.updateQuestion(question);
	}

	@Override
	public void removeQuestion(Questions question) {
		questionsDAO.removeQuestion(question);
	}

	@Override
	public Questions searchxId(Long id_question) {
		return questionsDAO.searchxId(id_question);
	}
	
	@Override
	public Questions firstQuestion() {
		return questionsDAO.firstQuestion();
	}

	@Override
	public List<Questions> getQuestions() {
		return questionsDAO.getQuestions();
	}
	
	@Override
	public List<Questions> getQuestionsSurvey(Integer id_survey) {
		return questionsDAO.getQuestionsSurvey(id_survey);
	}
	
	@Override
	public List<Questions> getQuestionsSurveyStats(Integer id_survey) {
		return questionsDAO.getQuestionsSurveyStats(id_survey);
	}
	
	@Override
	public List<Questions> getQuestionsSurveyResults(Integer id_survey) {
		return questionsDAO.getQuestionsSurveyResults(id_survey);
	}

	@Override
	public List<QuestionsType> getQuestionsType() {
		return questionsDAO.getQuestionsType();
	}
	
	@Override
	public QuestionsType getQuestionTypeXId(int id) {
		return questionsDAO.getQuestionTypeXId(id);
	}
	
	public void setQuestionsDAO(QuestionsDAO QuestionsDAO) {
		this.questionsDAO = QuestionsDAO;
	}
}
