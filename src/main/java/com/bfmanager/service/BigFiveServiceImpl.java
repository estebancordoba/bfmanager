package com.bfmanager.service;

import java.io.Serializable;
import java.util.List;

import com.bfmanager.model.dao.BigFiveDAO;
import com.bfmanager.model.hibernate.BfDimensions;
import com.bfmanager.model.hibernate.BfNormalization;
import com.bfmanager.model.hibernate.BfResults;
import com.bfmanager.model.hibernate.BfValueQuestions;
import com.bfmanager.model.hibernate.Questions;

public class BigFiveServiceImpl  implements BigFiveService, Serializable{
	
	private static final long serialVersionUID = 1L;
	private BigFiveDAO bigFiveDAO;
	
	//Dimensions	
	@Override
	public void saveBfDimension(BfDimensions bf_dimension) {
		bigFiveDAO.saveBfDimension(bf_dimension);
	}
	@Override
	public void updateBfDimension(BfDimensions bf_dimension) {
		bigFiveDAO.updateBfDimension(bf_dimension);
	}
	@Override
	public void removeBfDimension(BfDimensions bf_dimension) {
		bigFiveDAO.removeBfDimension(bf_dimension);
	}
	@Override
	public BfDimensions searchBfDimensionxId(Integer id) {		
		return bigFiveDAO.searchBfDimensionxId(id);
	}
	
	@Override
	public BfDimensions firstDimension() {		
		return bigFiveDAO.firstDimension();
	}
	@Override
	public List<BfDimensions> getBfDimensions() {		
		return bigFiveDAO.getBfDimensions();
	}
	@Override
	public List<BfDimensions> getDimensionsxIdSurvey(Integer id_survey) {		
		return bigFiveDAO.getDimensionsxIdSurvey(id_survey);
	}
	
	//Normalization
	@Override
	public void saveBfNormalization(BfNormalization bf_normalization) {
		bigFiveDAO.saveBfNormalization(bf_normalization);
	}
	@Override
	public void updateBfNormalization(BfNormalization bf_normalization) {
		bigFiveDAO.updateBfNormalization(bf_normalization);
	}
	@Override
	public void removeBfNormalization(BfNormalization bf_normalization) {
		bigFiveDAO.removeBfNormalization(bf_normalization);
	}
	@Override
	public BfNormalization searchBfNormalizationxId(Integer id) {
		return bigFiveDAO.searchBfNormalizationxId(id);
	}
	@Override
	public BfNormalization searchBfNormalizationxIdSurvey(Integer id_survey) {
		return bigFiveDAO.searchBfNormalizationxIdSurvey(id_survey);
	}
	@Override
	public BfNormalization getBfNormalizationxIdSurvey(Integer id) {
		return bigFiveDAO.getBfNormalizationxIdSurvey(id);
	}
	@Override
	public List<BfNormalization> getBfNormalization() {
		return bigFiveDAO.getBfNormalization();
	}
	
	//ValueQuestions
	@Override
	public void saveBfValueQuestion(BfValueQuestions bf_value_question) {
		bigFiveDAO.saveBfValueQuestion(bf_value_question);
	}
	@Override
	public void updateBfValueQuestion(BfValueQuestions bf_value_question) {
		bigFiveDAO.updateBfValueQuestion(bf_value_question);
	}
	@Override
	public void removeBfValueQuestion(BfValueQuestions bf_value_question) {
		bigFiveDAO.removeBfValueQuestion(bf_value_question);
	}
	@Override
	public BfValueQuestions searchBfValueQuestionxId(Integer id) {
		return bigFiveDAO.searchBfValueQuestionxId(id);
	}
	@Override
	public BfValueQuestions firstValueQuestion() {
		return bigFiveDAO.firstValueQuestion();
	}
	@Override
	public List<BfValueQuestions> getBfValueQuestions() {
		return bigFiveDAO.getBfValueQuestions();
	}
	
	@Override
	public List<BfValueQuestions> getValueQuestionsxIdDimension(Integer id_dimension) {
		return bigFiveDAO.getValueQuestionsxIdDimension(id_dimension);
	}
	
	@Override
	public List<Questions> getQuestionsxIdSurvey(Integer id_survey) {
		return bigFiveDAO.getQuestionsxIdSurvey(id_survey);
	}
	
	//Results
	@Override
	public void saveBfResult(BfResults bf_result) {
		bigFiveDAO.saveBfResult(bf_result);
	}
	@Override
	public void updateBfResult(BfResults bf_result) {
		bigFiveDAO.updateBfResult(bf_result);
	}
	@Override
	public void removeBfResult(BfResults bf_result) {
		bigFiveDAO.removeBfResult(bf_result);
	}
	@Override
	public BfResults searchBfResultxId(Integer id) {
		return bigFiveDAO.searchBfResultxId(id);
	}
	@Override
	public List<BfResults> getBfResults() {
		return bigFiveDAO.getBfResults();
	}
	
	@Override
	public List<BfResults> getBfResultsxIdSurveyIdGroup(Integer id_survey,Integer id_group){
		return bigFiveDAO.getBfResultsxIdSurveyIdGroup(id_survey, id_group);
	}
	
	@Override
	public List<BfResults> getBfResultsxIdUserIdGroup(Integer id_user, Integer id_survey, Integer id_group){
		return bigFiveDAO.getBfResultsxIdUserIdGroup(id_user, id_survey, id_group);
	}
	
	@Override
	public BfResults getBfResultxIdUserIdDimIdGroup(Integer id_user, Integer id_dim, Integer id_group){
		return bigFiveDAO.getBfResultxIdUserIdDimIdGroup(id_user, id_dim,id_group);
	}
	
	//Otros
	public BigFiveDAO getBigFiveDAO() {
		return bigFiveDAO;
	}
	public void setBigFiveDAO(BigFiveDAO bigFiveDAO) {
		this.bigFiveDAO = bigFiveDAO;
	}
}