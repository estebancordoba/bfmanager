package com.bfmanager.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.bfmanager.model.hibernate.Answers;
import com.bfmanager.model.hibernate.Questions;
import com.bfmanager.service.AnswersService;
import com.bfmanager.service.QuestionsService;
import com.bfmanager.util.MsgUtil;

@ManagedBean(name = "answersBean")
@SessionScoped
public class AnswersBean implements Serializable {

	private static final long serialVersionUID = 1L;	
	
	@ManagedProperty(value = "#{AnswersServiceBean}")
	AnswersService answersService;
	@ManagedProperty(value = "#{QuestionsServiceBean}")
	QuestionsService questionsService;
	
	private Answers answer;
	private Integer id_group;
	
	public AnswersBean() {
		answer = new Answers();		
	}

	public AnswersService getAnswersService() {
		return answersService;
	}

	public void setAnswersService(AnswersService answersService) {
		this.answersService = answersService;
	}
	
	public void setQuestionsService(QuestionsService questionsService) {
		this.questionsService = questionsService;
	}

	public Answers getAnswer() {
		return answer;
	}

	public void setAnswer(Answers answer) {
		this.answer = answer;
	}
	
	public Integer getId_group() {
		return id_group;
	}

	public void setId_group(Integer id_group) {
		this.id_group = id_group;
	}
	
	public List<Answers> getAnswerQuestion(Long id_question) {		
		return answersService.getAnswerQuestion(id_question);		
	}
	
	public List<Answers> getAnswersSurvey(Integer id_survey) {		
		return answersService.getAnswersSurvey(id_survey);		
	}
	
	public List<Answers> getAnswerxIdSurveyIdGroup(Integer id_survey, Integer id_group){
		List<Questions> questionsMostrar = questionsService.getQuestionsSurveyResults(id_survey);
		
		List<Long> lst_questionsM=new ArrayList<Long>();
		
		for (Questions question : questionsMostrar) {
			lst_questionsM.add(question.getIdQuestion());
		}
		
		if(lst_questionsM.isEmpty()) return null;
		else{
			return answersService.getAnswerxIdSurveyIdGroupShow(id_survey, id_group,lst_questionsM);
		}
	}
	
	public Boolean getSurveyAnswered(Boolean survey_only_once,Integer id_user,Integer id_survey, Integer id_group){		
		if(survey_only_once){			
			Answers answered = answersService.getAnswerxIdSurveyIdGroupxIdUser(id_survey, id_group, id_user);
			
			return answered==null?false:true;			
		}		
		return false;
	}
	
	public void guardar() {					
		try {
			answersService.saveAnswer(answer);			
			MsgUtil.msgInfo("Exito!", "Respuesta(s) guardada()s correctamente");
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	public void eliminar(Long id_aw) {	
		try {
			Answers aw_eliminar = answersService.searchAWxId(id_aw);
			answersService.removeAnswer(aw_eliminar);			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void eliminarAnswersUserGroup(Integer id_user, Integer id_survey, Integer id_group) {
		
		
		List<Answers> lstAnswers = answersService.getAnswersSurveyUserGroup(id_user, id_survey, id_group);
		
		if(!lstAnswers.isEmpty()){
			for (Answers answer : lstAnswers) {
				eliminar(answer.getIdAnswer());
			}
			MsgUtil.msgInfo("Exito!", "Respuesta(s) eliminada(s) correctamente.");
		}		
	}
}
