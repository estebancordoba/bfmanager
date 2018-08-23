package com.bfmanager.model.dao;

import java.util.List;

import com.bfmanager.model.hibernate.DropDown;
import com.bfmanager.model.hibernate.MultipleChoice;

public interface QuestionsAllDAO {
	//MultipleChoice
	public void saveMultipleChoice(MultipleChoice multipleChoice);
	
	public void updateMultipleChoice(MultipleChoice multipleChoice);
	
	public void removeMultipleChoice(MultipleChoice multipleChoice);
	
	public MultipleChoice searchMCxId(Long id_multipleChoice);
	
	public MultipleChoice firstMultipleChoice();

	public List<MultipleChoice> getMultipleChoice();
	
	public List<MultipleChoice> getMultipleChoiceQuestion(Long id_question);
	
	//DropDown
	public void saveDropDown(DropDown dropDown);
	
	public void updateDropDown(DropDown dropDown);
	
	public void removeDropDown(DropDown dropDown);
	
	public DropDown searchDDxId(Long id_dropDown);
	
	public DropDown firstDropDown();

	public List<DropDown> getDropDown();
	
	public List<DropDown> getDropDownQuestion(Long id_question);
	
}
