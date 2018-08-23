package com.bfmanager.service;

import java.io.Serializable;
import java.util.List;

import com.bfmanager.model.dao.QuestionsAllDAO;
import com.bfmanager.model.hibernate.DropDown;
import com.bfmanager.model.hibernate.MultipleChoice;

public class QuestionsAllServiceImpl implements QuestionsAllService, Serializable{
	
	private static final long serialVersionUID = 1L;
	private QuestionsAllDAO questionsAllDAO;
	
	//MultipleChoice
	@Override
	public void saveMultipleChoice(MultipleChoice multipleChoice) {
		questionsAllDAO.saveMultipleChoice(multipleChoice);
	}
	@Override
	public void updateMultipleChoice(MultipleChoice multipleChoice) {
		questionsAllDAO.updateMultipleChoice(multipleChoice);
	}
	@Override
	public void removeMultipleChoice(MultipleChoice multipleChoice) {
		questionsAllDAO.removeMultipleChoice(multipleChoice);
	}
	@Override
	public MultipleChoice searchMCxId(Long id_multipleChoice) {
		return questionsAllDAO.searchMCxId(id_multipleChoice);
	}
	@Override
	public MultipleChoice firstMultipleChoice() {
		return questionsAllDAO.firstMultipleChoice();
	}
	@Override
	public List<MultipleChoice> getMultipleChoice() {
		return questionsAllDAO.getMultipleChoice();
	}
	@Override
	public List<MultipleChoice> getMultipleChoiceQuestion(Long id_question) {
		return questionsAllDAO.getMultipleChoiceQuestion(id_question);
	}
	
	//DropDown
	@Override
	public void saveDropDown(DropDown dropDown) {
		questionsAllDAO.saveDropDown(dropDown);
	}
	@Override
	public void updateDropDown(DropDown dropDown) {
		questionsAllDAO.updateDropDown(dropDown);
	}
	@Override
	public void removeDropDown(DropDown dropDown) {
		questionsAllDAO.removeDropDown(dropDown);
	}
	@Override
	public DropDown searchDDxId(Long id_dropDown) {
		return questionsAllDAO.searchDDxId(id_dropDown);
	}
	@Override
	public DropDown firstDropDown() {
		return questionsAllDAO.firstDropDown();
	}
	@Override
	public List<DropDown> getDropDown() {
		return questionsAllDAO.getDropDown();
	}
	@Override
	public List<DropDown> getDropDownQuestion(Long id_question) {
		return questionsAllDAO.getDropDownQuestion(id_question);
	}
	
	public QuestionsAllDAO getQuestionsAllDAO() {
		return questionsAllDAO;
	}
	public void setQuestionsAllDAO(QuestionsAllDAO questionsAllDAO) {
		this.questionsAllDAO = questionsAllDAO;
	}
}