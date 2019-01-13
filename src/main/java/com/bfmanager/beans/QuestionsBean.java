package com.bfmanager.beans;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.bfmanager.model.hibernate.Questions;
import com.bfmanager.model.hibernate.QuestionsType;
import com.bfmanager.service.QuestionsAllService;
import com.bfmanager.service.QuestionsService;
import com.bfmanager.util.MsgUtil;

@ManagedBean(name = "questionsBean")
@SessionScoped
public class QuestionsBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<Questions> filterQuestions;
	private boolean editar = false;
	private int identifiquer_survey;
	private List<Questions> selectedQuestions;

	@ManagedProperty(value = "#{QuestionsServiceBean}")
	QuestionsService questionsService;
	@ManagedProperty(value = "#{QuestionsAllServiceBean}")
	QuestionsAllService questionsAllService;	

	private Questions question;

	public QuestionsBean() {
		question = new Questions();
	}
		
	public Questions getQuestion() {
		return question;
	}

	public void setQuestion(Questions question) {
		this.question = question;
	}

	public List<Questions> getQuestions() {
		return questionsService.getQuestions();
	}

	public List<Questions> getSelectedQuestions() {
		return selectedQuestions;
	}

	public void setSelectedQuestions(List<Questions> selectedQuestions) {
		this.selectedQuestions = selectedQuestions;
	}
	
	public Questions getQuestionXId(Long id_question) {
		return questionsService.searchxId(id_question);
	}
	
	public List<Questions> getQuestionsSurvey(Integer id_survey) {		
		return questionsService.getQuestionsSurvey(id_survey);		
	}
	
	public List<QuestionsType> getQuestionsType() {
		return questionsService.getQuestionsType();
	}	

	public void setQuestionsService(QuestionsService questionsService) {
		this.questionsService = questionsService;
	}

	public void guardar() {				
		this.question.setSurvey(identifiquer_survey);		
		if (!editar) {
			try {
				questionsService.saveQuestion(question);
				this.cancelar();
				MsgUtil.msgInfo(LanguageBean.obtenerMensaje("success"), LanguageBean.obtenerMensaje("question_saved_correctly"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				questionsService.updateQuestion(question);
				this.cancelar();
				MsgUtil.msgInfo(LanguageBean.obtenerMensaje("success"), LanguageBean.obtenerMensaje("question_updated_correctly"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public void actualizar(Long id_s,Integer id_survey) {
		question = questionsService.searchxId(id_s);
		colocarIDSurvey(id_survey);
		editar = true;
	}

	public void eliminar(Long id_s) {		
		try {
			Questions question_eliminar = questionsService.searchxId(id_s);		
			questionsService.removeQuestion(question_eliminar);						
			MsgUtil.msgInfo(LanguageBean.obtenerMensaje("success"), LanguageBean.obtenerMensaje("question_removed_correctly"));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void eliminarQuestions(){				
		try {	
			for (Questions eliminar : selectedQuestions) {	
				questionsService.removeQuestion(eliminar);
			}
			MsgUtil.msgInfo(LanguageBean.obtenerMensaje("success"), LanguageBean.obtenerMensaje("records_removed_correctly"));
		} catch (Exception e) {
			MsgUtil.msgError(LanguageBean.obtenerMensaje("error"), LanguageBean.obtenerMensaje("error_remove_records"));
			e.printStackTrace();
		}		
		selectedQuestions=null;
	}

	public void cancelar() {
		question = new Questions();
		editar = false;
		selectedQuestions=null;
	}
	
	public void firstQuestion() {
		question = questionsService.firstQuestion();
	}
	
	public void asignarQuestion(Long id_s) {
		question = questionsService.searchxId(id_s);
	}	
	
	public void renovar(Integer id){
		question = new Questions();
		identifiquer_survey=id;		
		editar = false;		
	}
	
	public void colocarIDSurvey(Integer id){
		identifiquer_survey=id;		
	}

	public List<Questions> getFilterQuestions() {
		return filterQuestions;
	}

	public void setFilterQuestions(List<Questions> filterQuestions) {
		this.filterQuestions = filterQuestions;
	}
	
	public void setQuestionsAllService(QuestionsAllService questionsAllService) {
		this.questionsAllService = questionsAllService;
	}
}