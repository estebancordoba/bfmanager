package com.bfmanager.beans;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.primefaces.event.FileUploadEvent;

import com.bfmanager.model.hibernate.BfDimensions;
import com.bfmanager.model.hibernate.BfNormalization;
import com.bfmanager.model.hibernate.BfValueQuestions;
import com.bfmanager.model.hibernate.Surveys;
import com.bfmanager.model.hibernate.SurveysType;
import com.bfmanager.service.BigFiveService;
import com.bfmanager.service.SurveysService;
import com.bfmanager.util.ImagesUtils;
import com.bfmanager.util.MsgUtil;

@ManagedBean(name = "surveysBean")
@SessionScoped
public class SurveysBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<Surveys> filterSurveys;
	private boolean editar = false;
	private String uImagen;

	@ManagedProperty(value = "#{SurveysServiceBean}")
	SurveysService surveysService;
	@ManagedProperty(value = "#{BigFiveServiceBean}")
	BigFiveService bigFiveService;
	
	private Surveys survey;
	
	private List<Surveys> selectedSurveys;

	public SurveysBean() {
		survey = new Surveys();
	}
	
	public void renovar() {
		survey = new Surveys();
		editar = false;
		uImagen=null;
	}
	
	public Surveys getSurvey() {
		return survey;
	}

	public void setSurvey(Surveys survey) {
		this.survey = survey;
	}
	
	public String getuImagen() {
		return uImagen;
	}

	public void setuImagen(String uImagen) {
		this.uImagen = uImagen;
	}
	
	public List<Surveys> getSurveys() {
		return surveysService.getSurveys();
	}

	public List<Surveys> getSelectedSurveys() {
		return selectedSurveys;
	}

	public void setSelectedSurveys(List<Surveys> selectedSurveys) {
		this.selectedSurveys = selectedSurveys;
	}
	
	public List<Surveys> getSurveysFree() {
		return surveysService.getSurveysFree();
	}
	
	public List<Surveys> getSurveysBFI() {
		return surveysService.getSurveysBFI();
	}
	
	public List<Surveys> getSurveysxIdManager(Integer id) {
		return surveysService.getSurveysxIdManager(id);
	}
	
	public List<Surveys> getSurveysBFIxIdManager(Integer id) {
		return surveysService.getSurveysBFIxIdManager(id);
	}
			
	public void setSurveysService(SurveysService surveysService) {
		this.surveysService = surveysService;
	}
	
	public void setBigFiveService(BigFiveService bigFiveService) {
		this.bigFiveService = bigFiveService;
	}
	
	public List<SurveysType> getSurveyTypes() {
		return surveysService.getSurveysType();
	}

	public void guardar() {
		if (!editar) {
			try {				
				surveysService.saveSurvey(survey);
				this.cancelar();
				MsgUtil.msgInfo("Exito!", "Cuestionario guardado correctamente.");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				surveysService.updateSurvey(survey);
				this.cancelar();
				MsgUtil.msgInfo("Exito!", "Cuestionario actualizado");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public void actualizar(Integer id_s) {
		survey = surveysService.searchxId(id_s);
		ImagesUtils iu= new ImagesUtils();
		uImagen=iu.imgTemp(survey.getBanner(), "img_"+survey.getIdSurvey());		
		editar = true;
	}

	public void eliminar(Integer id_s) {		
		try {
			Surveys survey_eliminar = surveysService.searchxId(id_s);			
			surveysService.removeSurvey(survey_eliminar);
			MsgUtil.msgInfo("Exito!", "Cuestionario eliminado correctamente");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void eliminarSurveys() {		
		try {	
			for (Surveys eliminar : selectedSurveys) {	
				surveysService.removeSurvey(eliminar);
			}
			MsgUtil.msgInfo("Exito!", "Registros eliminados correctamente.");
		} catch (Exception e) {
			MsgUtil.msgError("Error!", "Ocurrio un error al intentar eliminar los registros.");
			e.printStackTrace();
		}		
		selectedSurveys=null;
	}
	
	public void changeActivatedSurvey(Integer id_survey) {
		try {
			Surveys su = surveysService.searchxId(id_survey);
			Boolean activado=su.getActivated();
			Boolean activar=true;
			
			if(!activado && su.getSurveyType().getIdSurveyType()==2){//BigFive
				BfNormalization nm = bigFiveService.searchBfNormalizationxIdSurvey(id_survey);				
				if(nm==null) activar=false;
				else{
					List<BfDimensions> dm = bigFiveService.getDimensionsxIdSurvey(id_survey);					
					if(dm==null || dm.isEmpty()) activar=false;
					else{
						List<BfValueQuestions> pg = bigFiveService.getValueQuestionsxIdDimension(dm.get(0).getIdBfDimension());						
						if(pg==null || pg.isEmpty()) activar=false;
					}					
				}
			}
			
			if(activar){//Normal
				su.setActivated(!activado);			
				surveysService.updateSurvey(su);	
				
				if(activado) MsgUtil.msgInfo("Exito!", "Cuestionario desactivado");
				else MsgUtil.msgInfo("Exito!", "Cuestionario activado");
			}
			else{
				MsgUtil.msgWarning("No es posible activar el Cuestionario "+su.getTitle()+"!", "Se deben configurar todos los parametros para el cuestionario de tipo BigFive");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Surveys getSurveyxId(Integer id_survey){
		return surveysService.searchxId(id_survey);
	}
	
	public void firstSurvey(){
		survey = surveysService.firstSurvey();
	}

	public void cancelar() {
		survey = new Surveys();
		editar = false;
		selectedSurveys=null;
	}
	
	public List<Surveys> getFilterSurveys() {
		return filterSurveys;
	}

	public void setFilterSurveys(List<Surveys> filterSurveys) {
		this.filterSurveys = filterSurveys;
	}
	
	public void asignarSurvey(Integer id_s) {		
		survey = surveysService.searchxId(id_s);
		//FacesContext context = FacesContext.getCurrentInstance();
		//QuestionsBean qb = context.getApplication().evaluateExpressionGet(context, "#{questionBean}", QuestionsBean.class);
		
		//qb.setSurvey(survey);
	}	
		
	public void subirImagen(FileUploadEvent event) {
		try {
			survey.setBanner(event.getFile().getContents());
			ImagesUtils iu= new ImagesUtils();
			uImagen=iu.imgTemp(event.getFile().getContents(), event.getFile().getFileName());
		} catch (Exception e) {
			System.err.print("ERROR AL SUBIR: "+e);
		}
	}
}
