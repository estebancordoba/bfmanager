package com.bfmanager.model.dao;

import java.util.List;

import com.bfmanager.model.hibernate.BfDimensions;
import com.bfmanager.model.hibernate.BfNormalization;
import com.bfmanager.model.hibernate.BfResults;
import com.bfmanager.model.hibernate.BfValueQuestions;
import com.bfmanager.model.hibernate.Questions;

public interface BigFiveDAO {
	//Dimensions
	public void saveBfDimension(BfDimensions bf_dimension);
	
	public void updateBfDimension(BfDimensions bf_dimension);
	
	public void removeBfDimension(BfDimensions bf_dimension);
	
	public BfDimensions searchBfDimensionxId(Integer id);
	
	public BfDimensions firstDimension();

	public List<BfDimensions> getBfDimensions();
	
	public List<BfDimensions> getDimensionsxIdSurvey(Integer id_survey);
	
	//Normalization
	public void saveBfNormalization(BfNormalization bf_normalization);
	
	public void updateBfNormalization(BfNormalization bf_normalization);
	
	public void removeBfNormalization(BfNormalization bf_normalization);
	
	public BfNormalization searchBfNormalizationxId(Integer id);
	
	public BfNormalization searchBfNormalizationxIdSurvey(Integer id_survey);
	
	public BfNormalization getBfNormalizationxIdSurvey(Integer id);

	public List<BfNormalization> getBfNormalization();
	
	//ValueQuestions
	public void saveBfValueQuestion(BfValueQuestions bf_value_question);
	
	public void updateBfValueQuestion(BfValueQuestions bf_value_question);
	
	public void removeBfValueQuestion(BfValueQuestions bf_value_question);
	
	public BfValueQuestions searchBfValueQuestionxId(Integer id);
	
	public BfValueQuestions firstValueQuestion();

	public List<BfValueQuestions> getBfValueQuestions();
	
	public List<BfValueQuestions> getValueQuestionsxIdDimension(Integer id_dimension);
	
	public List<Questions> getQuestionsxIdSurvey(Integer id_survey);
	
	//Results
	public void saveBfResult(BfResults bf_result);
	
	public void updateBfResult(BfResults bf_result);
	
	public void removeBfResult(BfResults bf_result);
	
	public BfResults searchBfResultxId(Integer id);

	public List<BfResults> getBfResults();
	
	public List<BfResults> getBfResultsxIdSurveyIdGroup(Integer id_survey,Integer id_group);
	
	public List<BfResults> getBfResultsxIdUserIdGroup(Integer id_user, Integer id_survey , Integer id_group);
	
	public BfResults getBfResultxIdUserIdDimIdGroup(Integer id_user, Integer id_dim, Integer id_group);
		
}
