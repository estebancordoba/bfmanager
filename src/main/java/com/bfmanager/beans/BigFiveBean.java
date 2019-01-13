package com.bfmanager.beans;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.bfmanager.model.hibernate.BfDimensions;
import com.bfmanager.model.hibernate.BfNormalization;
import com.bfmanager.model.hibernate.BfResults;
import com.bfmanager.model.hibernate.BfValueQuestions;
import com.bfmanager.model.hibernate.Groups;
import com.bfmanager.model.hibernate.Questions;
import com.bfmanager.model.hibernate.Surveys;
import com.bfmanager.model.hibernate.Users;
import com.bfmanager.service.BigFiveService;
import com.bfmanager.service.QuestionsService;
import com.bfmanager.service.SurveysService;
import com.bfmanager.service.UsersService;
import com.bfmanager.util.JavaScriptUtil;
import com.bfmanager.util.MsgUtil;

@ManagedBean(name = "bigFiveBean")
@SessionScoped
public class BigFiveBean implements Serializable {

	private static final long serialVersionUID = 1L;	
	
	@ManagedProperty(value = "#{BigFiveServiceBean}")
	BigFiveService bigFiveService;
	@ManagedProperty(value = "#{SurveysServiceBean}")
	SurveysService surveysService;
	@ManagedProperty(value = "#{QuestionsServiceBean}")
	QuestionsService questionsService;
	@ManagedProperty(value = "#{UsersBean}")
	UsersService usersService;
	
	private BfDimensions bf_dimension;
	private BfNormalization bf_normalization;
	private BfValueQuestions bf_value_question;
	private BfResults bf_result;
	private Users user_chart;
	
	private boolean editarDimension = false;
	private boolean editarNormalization = false;
	private boolean editarValueQuestion = false;
	private boolean editarResult = false;
	
	private List<BfDimensions> selectedBfDimensions;
	private List<BfValueQuestions> selectedBfValueQuestions;	
	
	private Surveys survey;
	
	private StreamedContent fileDownload;
	
	//Constructor
	public BigFiveBean() {		
		cancelarDimension();
		cancelarNormalization();
		cancelarValueQuestion();
		cancelarResult();
	}
	
	//Getters and Setters
	public BfDimensions getBf_dimension() {
		return bf_dimension;
	}

	public void setBf_dimension(BfDimensions bf_dimension) {
		this.bf_dimension = bf_dimension;
	}

	public BfNormalization getBf_normalization() {
		return bf_normalization;
	}

	public void setBf_normalization(BfNormalization bf_normalization) {
		this.bf_normalization = bf_normalization;
	}

	public BfValueQuestions getBf_value_question() {
		return bf_value_question;
	}

	public void setBf_value_question(BfValueQuestions bf_value_question) {
		this.bf_value_question = bf_value_question;
	}

	public BfResults getBf_result() {
		return bf_result;
	}

	public void setBf_result(BfResults bf_result) {
		this.bf_result = bf_result;
	}

	public Users getUser_chart() {
		return user_chart;
	}

	public void setUser_chart(Users user_chart) {
		this.user_chart = user_chart;
	}
	
	public List<BfDimensions> getSelectedBfDimensions() {
		return selectedBfDimensions;
	}

	public void setSelectedBfDimensions(List<BfDimensions> selectedBfDimensions) {
		this.selectedBfDimensions = selectedBfDimensions;
	}

	public List<BfValueQuestions> getSelectedBfValueQuestions() {
		return selectedBfValueQuestions;
	}

	public void setSelectedBfValueQuestions(List<BfValueQuestions> selectedBfValueQuestions) {
		this.selectedBfValueQuestions = selectedBfValueQuestions;
	}
	
	public StreamedContent getFileDownload() {
		return fileDownload;
	}

	//Services
	public void setBigFiveService(BigFiveService bigFiveService) {
		this.bigFiveService = bigFiveService;
	}
	
	public void setSurveysService(SurveysService surveysService) {
		this.surveysService = surveysService;
	}
	
	public void setQuestionsService(QuestionsService questionsService) {
		this.questionsService = questionsService;
	}
	public void setUsersService(UsersService usersService) {
		this.usersService = usersService;
	}
	
	//Dimensions
	public void guardarDimension() {
		if (!editarDimension) {
			try {
				bf_dimension.setIdSurvey(survey.getIdSurvey());
				
				bigFiveService.saveBfDimension(bf_dimension);
				this.cancelarDimension();
				MsgUtil.msgInfo(LanguageBean.obtenerMensaje("success"), LanguageBean.obtenerMensaje("dimension_saved_correctly"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				bigFiveService.updateBfDimension(bf_dimension);
				this.cancelarDimension();
				MsgUtil.msgInfo(LanguageBean.obtenerMensaje("success"), LanguageBean.obtenerMensaje("dimension_updated_correctly"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public void actualizarDimension(Integer id) {
		bf_dimension = bigFiveService.searchBfDimensionxId(id);		
		editarDimension = true;
	}
	public BfDimensions searchBfDimensionxId(Integer id){
		return bigFiveService.searchBfDimensionxId(id);
	}
	
	public void firstDimension(){
		bf_dimension = bigFiveService.firstDimension();
	}

	public void eliminarDimension(Integer id) {		
		try {
			BfDimensions eliminar = bigFiveService.searchBfDimensionxId(id);		
			bigFiveService.removeBfDimension(eliminar);						
			MsgUtil.msgInfo(LanguageBean.obtenerMensaje("success"), LanguageBean.obtenerMensaje("dimension_removed_correctly"));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void eliminarVariasDimensiones(){				
		try {	
			for (BfDimensions eliminar : selectedBfDimensions) {	
				bigFiveService.removeBfDimension(eliminar);
			}
			MsgUtil.msgInfo(LanguageBean.obtenerMensaje("success"), LanguageBean.obtenerMensaje("records_removed_correctly"));
		} catch (Exception e) {
			MsgUtil.msgError(LanguageBean.obtenerMensaje("error"), LanguageBean.obtenerMensaje("error_remove_records"));
			e.printStackTrace();
		}		
		selectedBfDimensions=null;
	}
	
	public List<BfDimensions> getBfDimensions() {
		return bigFiveService.getBfDimensions();
	}
	
	public List<BfDimensions> getDimensionsxIdSurvey(Integer id_survey) {		
		return bigFiveService.getDimensionsxIdSurvey(id_survey);
	}

	public void cancelarDimension() {
		bf_dimension = new BfDimensions();
		editarDimension = false;
	}
	
	public void prepararDimension(Integer id_survey) {
		cancelarDimension();
		asignarSurvey(id_survey);	
		selectedBfDimensions=null;
	}
	
	//Normalizations
	public void guardarNormalization() {
		if (!editarNormalization) {
			bf_normalization.setIdSurvey(survey.getIdSurvey());
			try {
				bigFiveService.saveBfNormalization(bf_normalization);
				this.cancelarNormalization();
				MsgUtil.msgInfo(LanguageBean.obtenerMensaje("success"), LanguageBean.obtenerMensaje("normalization_saved_correctly"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				bigFiveService.updateBfNormalization(bf_normalization);
				this.cancelarNormalization();
				MsgUtil.msgInfo(LanguageBean.obtenerMensaje("success"), LanguageBean.obtenerMensaje("normalization_updated_correctly"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public void actualizarNormalization(Integer id_survey) {
		bf_normalization = bigFiveService.getBfNormalizationxIdSurvey(id_survey);		
		if(bf_normalization==null){			
			cancelarNormalization(); 
			asignarSurvey(id_survey);
		}		
		else editarNormalization = true;		
	}

	public void eliminarNormalization(Integer id) {		
		try {
			BfNormalization eliminar = bigFiveService.searchBfNormalizationxId(id);		
			bigFiveService.removeBfNormalization(eliminar);						
			MsgUtil.msgInfo(LanguageBean.obtenerMensaje("success"), LanguageBean.obtenerMensaje("normalization_removed_correctly"));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public List<BfNormalization> getBfNormalization() {
		return bigFiveService.getBfNormalization();
	}
	
	public void cancelarNormalization() {
		bf_normalization = new BfNormalization();
		editarNormalization = false;
	}
	
	//ValueQuestions
	public void guardarValueQuestion() {
		if (!editarValueQuestion) {
			try {
				bigFiveService.saveBfValueQuestion(bf_value_question);
				this.cancelarValueQuestion();
				MsgUtil.msgInfo(LanguageBean.obtenerMensaje("success"), LanguageBean.obtenerMensaje("value_question_saved_correctly"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				bigFiveService.updateBfValueQuestion(bf_value_question);
				this.cancelarValueQuestion();
				MsgUtil.msgInfo(LanguageBean.obtenerMensaje("success"), LanguageBean.obtenerMensaje("value_question_updated_correctly"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public void actualizarValueQuestion(Integer id) {
		bf_value_question = bigFiveService.searchBfValueQuestionxId(id);		
		editarValueQuestion = true;
	}

	public void eliminarValueQuestion(Integer id) {		
		try {
			BfValueQuestions eliminar = bigFiveService.searchBfValueQuestionxId(id);		
			bigFiveService.removeBfValueQuestion(eliminar);						
			MsgUtil.msgInfo(LanguageBean.obtenerMensaje("success"), LanguageBean.obtenerMensaje("value_question_removed_correctly"));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void eliminarVariasValueQuestion(){				
		try {	
			for (BfValueQuestions eliminar : selectedBfValueQuestions) {	
				bigFiveService.removeBfValueQuestion(eliminar);
			}
			MsgUtil.msgInfo(LanguageBean.obtenerMensaje("success"), LanguageBean.obtenerMensaje("records_removed_correctly"));
		} catch (Exception e) {
			MsgUtil.msgError(LanguageBean.obtenerMensaje("error"), LanguageBean.obtenerMensaje("error_remove_records"));
			e.printStackTrace();
		}		
		selectedBfValueQuestions=null;
	}
	
	public void firstValueQuestion(){
		bf_value_question = bigFiveService.firstValueQuestion();
	}
	
	public List<BfValueQuestions> getBfValueQuestions() {
		return bigFiveService.getBfValueQuestions();
	}
	
	public List<BfValueQuestions> getValueQuestionsxIdDimension(Integer id_dimension) {
		return bigFiveService.getValueQuestionsxIdDimension(id_dimension);
	}
	
	public List<Questions> getQuestionsxIdSurvey(Integer id_survey) {
		return bigFiveService.getQuestionsxIdSurvey(id_survey);	
	}
	
	public List<Questions> getQuestionsxIdSurveyAvailable(Integer id_survey, Integer id_dimension) {
		List<Questions> preguntasDisponibles = bigFiveService.getQuestionsxIdSurvey(id_survey);
		List<BfValueQuestions> preguntasAgregadas = bigFiveService.getValueQuestionsxIdDimension(id_dimension);
		
		for (BfValueQuestions vQuestA : preguntasAgregadas) {
			ArrayList<Questions> borrar = new ArrayList<Questions>();
			
			for(int i=0;i<preguntasDisponibles.size();i++)
				if(preguntasDisponibles.get(i).getIdQuestion()==vQuestA.getIdQuestion()){					
					if(editarValueQuestion){
						if(preguntasDisponibles.get(i).getIdQuestion()!=bf_value_question.getIdQuestion()){							
							borrar.add(preguntasDisponibles.get(i));
						}							
					}
					else borrar.add(preguntasDisponibles.get(i));
				}					
			
			preguntasDisponibles.removeAll(borrar);			
		}
		
		return preguntasDisponibles;			
	}
	
	public String getQuestionTitle(Long id_question) {
		return questionsService.searchxId(id_question).getTitle();	
	}
	
	public void cancelarValueQuestion() {
		bf_value_question = new BfValueQuestions();
		editarValueQuestion = false;
	}
	
	public void prepararValueQuestion(Integer id_dimension) {
		cancelarValueQuestion();
		bf_value_question.setIdBfDimension(id_dimension);	
		selectedBfValueQuestions=null;
	}
	
	//Results
	public void guardarResult() {
		if (!editarResult) {
			try {
				bigFiveService.saveBfResult(bf_result);
				this.cancelarResult();
				MsgUtil.msgInfo(LanguageBean.obtenerMensaje("success"), LanguageBean.obtenerMensaje("result_saved_correctly"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				bigFiveService.updateBfResult(bf_result);
				this.cancelarResult();
				MsgUtil.msgInfo(LanguageBean.obtenerMensaje("success"), LanguageBean.obtenerMensaje("result_updated_correctly"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public void actualizarResult(Integer id) {
		bf_result = bigFiveService.searchBfResultxId(id);		
		editarResult = true;
	}

	public void eliminarResult(Integer id) {		
		try {
			BfResults eliminar = bigFiveService.searchBfResultxId(id);		
			bigFiveService.removeBfResult(eliminar);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void eliminarResultUser(Integer id_user, Integer id_survey, Integer id_group) {
		List<BfResults> lstResults = bigFiveService.getBfResultsxIdUserIdGroup(id_user,id_survey,id_group);
		
		if(!lstResults.isEmpty()){
			for (BfResults bfResult : lstResults) {
				eliminarResult(bfResult.getIdBfResult());
			}
			MsgUtil.msgInfo(LanguageBean.obtenerMensaje("success"), LanguageBean.obtenerMensaje("result_removed_correctly"));
		}
		
	}
	
	public List<BfResults> getBfResults() {
		return bigFiveService.getBfResults();
	}
	
	public List<Users> getUsersxIdSurveyIdGroup(Integer id_survey, Integer id_group) {
		List<BfResults> lstR = bigFiveService.getBfResultsxIdSurveyIdGroup(id_survey,id_group);
		
		TreeMap <Integer,Integer> mpU = new TreeMap<Integer,Integer>();
		
		List<Users> lstU = new ArrayList<Users>();
		
		for (BfResults result : lstR) {
			if(!mpU.containsKey(result.getIdUser())){
				mpU.put(result.getIdUser(),1);
				Users us = usersService.getUserXId(result.getIdUser());
				lstU.add(us);
			}
		}
		
		return lstU;
	}
	
	public BfResults getBfResultxIdUserIdDimIdGroup(Integer id_user, Integer id_dim, Integer id_group) {
		return bigFiveService.getBfResultxIdUserIdDimIdGroup(id_user,id_dim,id_group);
	}

	public void cancelarResult() {
		bf_result = new BfResults();
		editarResult = false;
	}
	
	//Funciones	
	public void asignarSurvey(Integer id) {		
		survey = surveysService.searchxId(id);
	}
	
	public void asignarDimension(Integer id) {		
		bf_dimension = bigFiveService.searchBfDimensionxId(id);
	}
	
	public void chartBigFive(Integer id_user, Integer id_survey, Integer id_group){
		this.user_chart=usersService.getUserXId(id_user);
		
		List<BfResults> lstRes = bigFiveService.getBfResultsxIdUserIdGroup(id_user, id_survey,id_group);
		
		String[] dataChartD = new String[lstRes.size()];
		String[] labelsChartD = new String[lstRes.size()];
		
		int cD=0;
		for (BfResults BfResult : lstRes) {
			BfDimensions bf_d = bigFiveService.searchBfDimensionxId((int) BfResult.getIdBfDimension());
			labelsChartD[cD]=bf_d.getName();
			dataChartD[cD]=""+BfResult.getResult();
			cD++;			
		}
		
		RequestContext context = RequestContext.getCurrentInstance();
		JavaScriptUtil jsu = new JavaScriptUtil();
		context.execute(jsu.radarChart("chart", labelsChartD, dataChartD));
	}
	
	public void exportarCW(Integer id_survey, Groups group){
		try{
			String str="group_name="+group.getName()+"\n";
			str+="group_description="+group.getDescription()+"\n";
			
			Integer id_group=group.getIdGroup();
			List<Users> usuarios_cw=getUsersxIdSurveyIdGroup(id_survey, id_group);
			List<BfDimensions> dimensiones_cw= getDimensionsxIdSurvey(id_survey);
			
			str+="characteristics_number="+dimensiones_cw.size()+"\n";
			
			for (BfDimensions dim : dimensiones_cw) str+=dim.getName()+";0;1"+"\n";									
			
			str+="members_number="+usuarios_cw.size()+"\n";
			str+="available_name=true\n";			
			
			for (Users user_cw : usuarios_cw) {
				String sU=user_cw.getIdUser()+";"+user_cw.getName()+" "+user_cw.getSurname();
				for (BfDimensions dim : dimensiones_cw) {				
					BfResults resultxUser = getBfResultxIdUserIdDimIdGroup(user_cw.getIdUser(), dim.getIdBfDimension(), id_group);
					sU+=";"+resultxUser.getResult();
				}
				str+=sU+"\n";
			}
			
			String excelFileName = "Groups_"+group.getName()+".csv";
		
	        InputStream stream = new ByteArrayInputStream(str.getBytes());
	        
	        fileDownload = new DefaultStreamedContent(stream, "application/csv", excelFileName);
	        
	        MsgUtil.msgInfo(LanguageBean.obtenerMensaje("success"), LanguageBean.obtenerMensaje("file_downloaded_correctly"));
	        
		}catch (Exception e) {
			MsgUtil.msgError(LanguageBean.obtenerMensaje("error"), LanguageBean.obtenerMensaje("error_downloading_file"));
		}
	}
}
