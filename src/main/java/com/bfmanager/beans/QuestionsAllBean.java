package com.bfmanager.beans;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.bfmanager.model.hibernate.DropDown;
import com.bfmanager.model.hibernate.MultipleChoice;
import com.bfmanager.service.QuestionsAllService;
import com.bfmanager.util.MsgUtil;

@ManagedBean(name = "questionsAllBean")
@SessionScoped
public class QuestionsAllBean implements Serializable {

	private static final long serialVersionUID = 1L;	
	private boolean editar = false;
	private long identifiquer_question;	
	
	@ManagedProperty(value = "#{QuestionsAllServiceBean}")
	QuestionsAllService questionsAllService;
	
	private MultipleChoice multipleChoice;
	private DropDown dropDown;
	
	private List<MultipleChoice> selectedMultipleChoice;
	private List<DropDown> selectedDropDown;
	
	public QuestionsAllBean() {
		multipleChoice = new MultipleChoice();		
		dropDown = new DropDown();
			
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("validacionLS", 0);
	}

	//MultipleChoice
	public MultipleChoice getMultipleChoice() {
		return multipleChoice;
	}

	public void setMultipleChoice(MultipleChoice multipleChoice) {
		this.multipleChoice = multipleChoice;
	}

	public List<MultipleChoice> getMultipleChoiceAll() {
		return questionsAllService.getMultipleChoice();
	}
	
	public List<MultipleChoice> getMultipleChoiceQuestion(Long id_question) {		
		return questionsAllService.getMultipleChoiceQuestion(id_question);		
	}
	
	public List<MultipleChoice> getSelectedMultipleChoice() {
		return selectedMultipleChoice;
	}

	public void setSelectedMultipleChoice(List<MultipleChoice> selectedMultipleChoice) {
		this.selectedMultipleChoice = selectedMultipleChoice;
	}
	
	public void guardarMC() {
		this.multipleChoice.setQuestion(identifiquer_question);	
		if (!editar) {
			try {
				questionsAllService.saveMultipleChoice(multipleChoice);;
				this.cancelar();
				MsgUtil.msgInfo("Exito!", "Opcion guardada correctamente");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				questionsAllService.updateMultipleChoice(multipleChoice);
				this.cancelar();
				MsgUtil.msgInfo("Exito!", "Opcion actualizada");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void actualizarMC(Long id_s, Long id_q) {
		multipleChoice = questionsAllService.searchMCxId(id_s);
		colocarIDQuestion(id_q);
		editar = true;
	}

	public void eliminarMC(Long id_s) {	
		try {
			MultipleChoice s_eliminar = questionsAllService.searchMCxId(id_s);
			questionsAllService.removeMultipleChoice(s_eliminar);
			MsgUtil.msgInfo("Exito!", "Opcion eliminada correctamente");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void eliminarVariosMC(){				
		try {	
			for (MultipleChoice eliminar : selectedMultipleChoice) {	
				questionsAllService.removeMultipleChoice(eliminar);
			}
			MsgUtil.msgInfo("Exito!", "Registros eliminados correctamente.");
		} catch (Exception e) {
			MsgUtil.msgError("Error!", "Ocurrio un error al intentar eliminar los registros.");
			e.printStackTrace();
		}		
		selectedMultipleChoice=null;
	}
	
	public void firstMultipleChoice(){
		multipleChoice = questionsAllService.firstMultipleChoice();
	}
	
	public void renovarMC(Long id){
		multipleChoice = new MultipleChoice();
		editar = false;
		identifiquer_question=id;		
	}

	//DropDown
	public DropDown getDropDown() {
		return dropDown;
	}

	public void setDropDown(DropDown dropDown) {
		this.dropDown = dropDown;
	}

	public List<DropDown> getDropDownAll() {
		return questionsAllService.getDropDown();
	}
	
	public List<DropDown> getDropDownQuestion(Long id_question) {		
		return questionsAllService.getDropDownQuestion(id_question);		
	}
	
	public List<DropDown> getSelectedDropDown() {
		return selectedDropDown;
	}

	public void setSelectedDropDown(List<DropDown> selectedDropDown) {
		this.selectedDropDown = selectedDropDown;
	}
	
	public void guardarDD() {
		this.dropDown.setQuestion(identifiquer_question);	
		if (!editar) {
			try {
				questionsAllService.saveDropDown(dropDown);;
				this.cancelar();
				MsgUtil.msgInfo("Exito!", "Opcion guardada correctamente");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				questionsAllService.updateDropDown(dropDown);
				this.cancelar();
				MsgUtil.msgInfo("Exito!", "Opcion actualizada");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void actualizarDD(Long id_s, Long id_q) {
		dropDown = questionsAllService.searchDDxId(id_s);
		colocarIDQuestion(id_q);
		editar = true;
	}
	
	public void firstDropDown(){
		dropDown = questionsAllService.firstDropDown();
	}

	public void eliminarDD(Long id_s) {	
		try {
			DropDown s_eliminar = questionsAllService.searchDDxId(id_s);
			questionsAllService.removeDropDown(s_eliminar);
			MsgUtil.msgInfo("Exito!", "Opcion eliminada correctamente");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void eliminarVariosDD(){				
		try {	
			for (DropDown eliminar : selectedDropDown) {	
				questionsAllService.removeDropDown(eliminar);
			}
			MsgUtil.msgInfo("Exito!", "Registros eliminados correctamente.");
		} catch (Exception e) {
			MsgUtil.msgError("Error!", "Ocurrio un error al intentar eliminar los registros.");
			e.printStackTrace();
		}		
		selectedDropDown=null;
	}
	
	public void renovarDD(Long id){
		dropDown = new DropDown();
		editar = false;
		identifiquer_question=id;		
	}	
		
	//OTRAS
	public void cancelar() {
		multipleChoice = new MultipleChoice();
		dropDown = new DropDown();
		editar = false;
		selectedMultipleChoice=null;
		selectedDropDown=null;
	}
	
	public void colocarIDQuestion(Long id){		
		identifiquer_question=id;		
	}
	
	public void setQuestionsAllService(QuestionsAllService questionsAllService) {
		this.questionsAllService = questionsAllService;
	}
}