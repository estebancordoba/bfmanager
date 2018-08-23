package com.bfmanager.beans;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.faces.application.NavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UISelectItem;
import javax.faces.context.FacesContext;

import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.message.Message;
import org.primefaces.component.outputlabel.OutputLabel;
import org.primefaces.component.panel.Panel;
import org.primefaces.component.panelgrid.PanelGrid;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.primefaces.component.selectoneradio.SelectOneRadio;
import org.primefaces.context.RequestContext;

import com.bfmanager.model.hibernate.Answers;
import com.bfmanager.model.hibernate.BfDimensions;
import com.bfmanager.model.hibernate.BfNormalization;
import com.bfmanager.model.hibernate.BfResults;
import com.bfmanager.model.hibernate.BfValueQuestions;
import com.bfmanager.model.hibernate.DropDown;
import com.bfmanager.model.hibernate.MultipleChoice;
import com.bfmanager.model.hibernate.Questions;
import com.bfmanager.model.hibernate.Surveys;
import com.bfmanager.model.hibernate.Users;
import com.bfmanager.service.AnswersService;
import com.bfmanager.service.BigFiveService;
import com.bfmanager.service.QuestionsAllService;
import com.bfmanager.service.QuestionsService;
import com.bfmanager.service.SurveysService;
import com.bfmanager.util.MsgUtil;
import com.bfmanager.util.ImagesUtils;
import com.bfmanager.util.JavaScriptUtil;

@ManagedBean(name = "showSurveyBean")
@SessionScoped
public class ShowSurveyBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	//Propiedades Sevicios
	@ManagedProperty(value = "#{SurveysServiceBean}")
	SurveysService surveysService;
	@ManagedProperty(value = "#{QuestionsServiceBean}")
	QuestionsService questionsService;	
	@ManagedProperty(value = "#{QuestionsAllServiceBean}")
	QuestionsAllService questionsAllService;
	@ManagedProperty(value = "#{AnswersServiceBean}")
	AnswersService answersService;	
	@ManagedProperty(value = "#{BigFiveServiceBean}")
	BigFiveService bigFiveService;
	
	//Variables
    private Panel panels;
    private String instrucciones;
    private Surveys survey;
	private Integer id_group;
    
	//Constructores, Getters y Setters
	public ShowSurveyBean(){
		//RequestContext.getCurrentInstance().execute("alert('Aquí tu código Javascript');");
    }
	
	public Panel getPanels() {
		return this.panels;
	}
    
    public void setPanels(Panel panels) {
		this.panels = panels;
	}
    
    public Integer getId_group() {
		return id_group;
	}

	public void setId_group(Integer id_group) {
		this.id_group = id_group;
	}

	public Surveys getSurvey() {
		return survey;
	}

	public void setSurvey(Surveys survey) {
		this.survey = survey;
	}
    
    //Creacion del Panel
    public Panel panels(Integer id_survey) {
    	
    	FacesContext fc = FacesContext.getCurrentInstance();
        panels = (Panel) fc.getApplication().createComponent("org.primefaces.component.Panel");
        
        survey = surveysService.searchxId(id_survey);                
        panels.setHeader(survey.getTitle());
        
        OutputLabel des = new OutputLabel();
        des.setValue(survey.getDescription());
        des.setEscape(false);        
        panels.getChildren().add(des);
        
        OutputLabel ins = new OutputLabel();
        ins.setValue(survey.getInstructions());        
        ins.setEscape(false);        
        panels.getChildren().add(ins);
        
        instrucciones=survey.getInstructions();
        
        int numP=1;
        
        List<Questions> questions = questionsService.getQuestionsSurvey(survey.getIdSurvey());
        for (Questions question : questions) {        	
        	PanelGrid pan = new PanelGrid();        	
            pan.setColumns(4);
            pan.setLayout("grid");
            pan.setStyleClass("ui-panelgrid-blank form-group");
         
	       	if(question.getType().getIdQuestionType()==1){	       		
       			InputText inputtext = new InputText();
	       		inputtext.setPlaceholder(question.getHelp());
	       		inputtext.setRequired(question.getRequired());   
	
	       		inputtext.setId("st_"+question.getIdQuestion());
	       		
	       		inputtext.setRequiredMessage("Debe ingresar un dato");/////
	       		inputtext.setRequired(question.getRequired());/////
	       		
	       		OutputLabel ot = new OutputLabel();
	       		ot.setValue(numP+". "+question.getTitle());numP++;
	       		ot.setFor("st_"+question.getIdQuestion());
	       		
	       		Message ms = new Message();
	       		ms.setFor("st_"+question.getIdQuestion());
	       				       		
	       		pan.getChildren().add(ot);/////
	       		pan.getChildren().add(inputtext);
	       		pan.getChildren().add(ms);/////
	       	}            
	       	else if(question.getType().getIdQuestionType()==2){
	       		SelectOneRadio selectoneradio = new SelectOneRadio();
	       		
	       		List<MultipleChoice> questionsMC = questionsAllService.getMultipleChoiceQuestion(question.getIdQuestion());
	       		selectoneradio.setColumns(questionsMC.size());
	       		
	       		for (MultipleChoice qmc : questionsMC) {
                   UISelectItem opc = new UISelectItem();
                   opc.setItemLabel(qmc.getDescription());
                   opc.setItemValue(qmc.getIdMultipleChoice());
                   selectoneradio.getChildren().add(opc);
                }
	       		
	       		selectoneradio.setId("mc_"+question.getIdQuestion());
	       		
	       		selectoneradio.setRequiredMessage("Debe seleccionar una opcion");/////
	       		selectoneradio.setRequired(question.getRequired());/////
	       		
	       		OutputLabel ot = new OutputLabel();
	       		ot.setValue(numP+". "+question.getTitle());numP++;
	       		ot.setFor("mc_"+question.getIdQuestion());
	       		Message ms = new Message();
	       		ms.setFor("mc_"+question.getIdQuestion());
	       		
	       		pan.getChildren().add(ot);/////
	       		pan.getChildren().add(selectoneradio);
	       		pan.getChildren().add(ms);/////
	       	}   
	       	else if(question.getType().getIdQuestionType()==3){
	       		SelectOneMenu selectonemenu = new SelectOneMenu();        		        		
	       		
	       		List<DropDown> questionsDD = questionsAllService.getDropDownQuestion(question.getIdQuestion());
	       		     
	       		UISelectItem opcD = new UISelectItem();
	       		opcD.setItemLabel("Elegir");
	       		opcD.setNoSelectionOption(true);
	               selectonemenu.getChildren().add(opcD);
	       		for (DropDown qdd : questionsDD) {
	                   UISelectItem opc = new UISelectItem();
	                   opc.setItemLabel(qdd.getDescription());
	                   opc.setItemValue(qdd.getIdDropDown());
	                   selectonemenu.getChildren().add(opc);
	               }
	       		
	       		selectonemenu.setId("dd_"+question.getIdQuestion());
	       		
	       		selectonemenu.setRequiredMessage("Debe seleccionar una opcion");/////
	       		selectonemenu.setRequired(question.getRequired());/////
	       			       		
	       		OutputLabel ot = new OutputLabel();
	       		ot.setValue(numP+". "+question.getTitle());numP++;
	       		ot.setFor("dd_"+question.getIdQuestion());
	       		Message ms = new Message();
	       		ms.setFor("dd_"+question.getIdQuestion());
	       		
	       		pan.getChildren().add(ot);/////
	       		pan.getChildren().add(selectonemenu);
	       		pan.getChildren().add(ms);/////
	       	}         
	       	else if(question.getType().getIdQuestionType()==4){
	       		SelectOneRadio selectoneradio = new SelectOneRadio();
	       			       		
	       		selectoneradio.setColumns(5);
	       		
	       		for(int i=1;i<=5;i++){
	       			UISelectItem opc = new UISelectItem();
                    opc.setItemLabel(""+i);
                    opc.setItemValue(""+i);
                    selectoneradio.getChildren().add(opc);
	       		}
	       		
	       		selectoneradio.setId("ls_"+question.getIdQuestion());
	       		
	       		selectoneradio.setRequiredMessage("Debe seleccionar una opcion");/////
	       		selectoneradio.setRequired(question.getRequired());/////
	       			       		
	       		OutputLabel ot = new OutputLabel();
	       		ot.setValue(numP+". "+question.getTitle());numP++;
	       		ot.setFor("ls_"+question.getIdQuestion());
	       		Message ms = new Message();
	       		ms.setFor("ls_"+question.getIdQuestion());
	       		
	       		pan.getChildren().add(ot);/////
	       		pan.getChildren().add(selectoneradio);
	       		pan.getChildren().add(ms);/////
	       	}	 
            
            panels.getChildren().add(pan);
        }
        return panels;
	}
    
	//Guardar Respuestas
    public void saveAnswers(boolean invitado) {    	
    	try {
    		Map<Integer, Double> dimensionsMap = new HashMap<Integer, Double>();
    		
    		for(int i=2;i<panels.getChildren().size();i++){  //Desde 2 por los outputPanel de Descripcion e Instrucciones 
        		PanelGrid pa = (PanelGrid) panels.getChildren().get(i);        		
        		Answers answer = new Answers();  
        		
        		boolean pregunta=true;///// 
        		        		
        		if (pa.getChildren().get(1) instanceof InputText){
        			InputText it = (InputText) pa.getChildren().get(1);
        			        			
        			answer.setQuestion(Long.parseLong(it.getId().split("_")[1]));
        			
        			try {
        				answer.setAnswer(it.getValue().toString());
					} catch (Exception e) {
						answer.setAnswer("");
					}
        		} 
        		else if (pa.getChildren().get(1) instanceof SelectOneRadio){
        			SelectOneRadio or = (SelectOneRadio) pa.getChildren().get(1);
        			
        			answer.setQuestion(Long.parseLong(or.getId().split("_")[1]));
        			
        			try {
        				answer.setAnswer(or.getValue().toString());
					} catch (Exception e) {
						answer.setAnswer("");
					}
        		}
        		else if (pa.getChildren().get(1) instanceof SelectOneMenu){
        			SelectOneMenu om = (SelectOneMenu) pa.getChildren().get(1);        			
        			
        			answer.setQuestion(Long.parseLong(om.getId().split("_")[1]));        			
        			
        			try {
        				answer.setAnswer(om.getValue().toString());
					} catch (Exception e) {
						answer.setAnswer("");
					} 
        		}
        		else pregunta=false;/////       
        		
        		if(!invitado && pregunta && !answer.getAnswer().equals("")){/////
        			FacesContext context = FacesContext.getCurrentInstance();		
            		Users us = (Users) context.getExternalContext().getSessionMap().get("usuario");
            		
            		answer.setUser(us.getIdUser()); 
            		answer.setIdSurvey(survey.getIdSurvey());
            		answer.setIdGroup(id_group);            		
            		
            		answersService.saveAnswer(answer);
        		}
        		
        		if(survey.getSurveyType().getIdSurveyType()==2 && pregunta && !answer.getAnswer().equals("")){
        			List<BfDimensions> dimensions = bigFiveService.getDimensionsxIdSurvey(survey.getIdSurvey());
        			
        			for (BfDimensions dimension : dimensions) {
        				List<BfValueQuestions> valueQuestions = bigFiveService.getValueQuestionsxIdDimension(dimension.getIdBfDimension());
        				
        				for (BfValueQuestions valueQuestion : valueQuestions) {
							if(valueQuestion.getIdQuestion()==answer.getQuestion()){
								if(dimensionsMap.containsKey(dimension.getIdBfDimension())){
									Double valorAnt=dimensionsMap.get(dimension.getIdBfDimension());									
									if(valueQuestion.getAddition()){
										if(valueQuestion.getReverse()){
											dimensionsMap.put(dimension.getIdBfDimension(), valorAnt+(6.0-Double.parseDouble(answer.getAnswer())));
										}
										else{
											dimensionsMap.put(dimension.getIdBfDimension(), valorAnt+Double.parseDouble(answer.getAnswer()));
										}
									}
									else{
										if(valueQuestion.getReverse()){
											dimensionsMap.put(dimension.getIdBfDimension(), valorAnt-(6.0-Double.parseDouble(answer.getAnswer())));
										}
										else{
											dimensionsMap.put(dimension.getIdBfDimension(), valorAnt-Double.parseDouble(answer.getAnswer()));
										}
									}
								}
								else{
									if(valueQuestion.getAddition()){
										if(valueQuestion.getReverse()){
											dimensionsMap.put(dimension.getIdBfDimension(), (6.0-Double.parseDouble(answer.getAnswer())));
										}
										else{
											dimensionsMap.put(dimension.getIdBfDimension(), Double.parseDouble(answer.getAnswer()));
										}
									}
									else{
										if(valueQuestion.getReverse()){
											dimensionsMap.put(dimension.getIdBfDimension(), -(6.0-Double.parseDouble(answer.getAnswer())));
										}
										else{
											dimensionsMap.put(dimension.getIdBfDimension(), -Double.parseDouble(answer.getAnswer()));
										}
									}
								}								
								break;
							}
						}
					}
        			
        		}
        	}
    		
    		if(survey.getSurveyType().getIdSurveyType()==2 && !dimensionsMap.isEmpty()){
    			BfResults[] results = new BfResults[dimensionsMap.size()];
    			String[] dataChartD = new String[dimensionsMap.size()];
    			String[] labelsChartD = new String[dimensionsMap.size()];
    			
    			int cResult=0;
    			for (Entry<Integer, Double> dimension : dimensionsMap.entrySet()) {
    				BfDimensions dim = bigFiveService.searchBfDimensionxId(dimension.getKey());
    				BfNormalization norm = bigFiveService.searchBfNormalizationxIdSurvey(survey.getIdSurvey());
    				
    				Double sumaActual=dimension.getValue();    				
    				Double baseValue=(double) dim.getBaseValue();
    				Double maximum=(double) norm.getMaximum();
    				Double minimum=(double) norm.getMinimum();
    				Double divide=(double) dim.getDivide();
    				    				   
    				Double resultado=(baseValue+sumaActual)/divide;
    				
    				resultado=Math.rint(((resultado-minimum)/(maximum-minimum))*10000)/10000;
    				
    				results[cResult]=new BfResults();
    				results[cResult].setResult(resultado);
    				results[cResult].setIdBfDimension(dim.getIdBfDimension());
    				
    				labelsChartD[cResult]=dim.getName();
    				dataChartD[cResult]=resultado.toString();
    				
    				cResult++;        		    
        		}
    			
    			RequestContext context = RequestContext.getCurrentInstance();
    			JavaScriptUtil jsu = new JavaScriptUtil();
    			context.execute(jsu.radarChart("chart", labelsChartD, dataChartD));
    			
    			if(!invitado){
    				FacesContext fc = FacesContext.getCurrentInstance();		
            		Users us = (Users) fc.getExternalContext().getSessionMap().get("usuario");
            		
            		for(int i=0;i<results.length;i++){
            			results[i].setIdUser(us.getIdUser());   
            			results[i].setIdSurvey(survey.getIdSurvey());
            			results[i].setIdGroup(id_group);            			
                		bigFiveService.saveBfResult(results[i]);
            		}
    			}
    		}
    		
    		RequestContext context = RequestContext.getCurrentInstance();    		    		
    		context.execute("PF('dlgRes').show();");    		
    		
    		MsgUtil.msgInfo("Exito!", "Respuestas enviadas exitosamente.");
    		
		} catch (Exception e) {
			e.printStackTrace();
		}    	
	}
        
    //Funciones
    public String getInstrucciones() {
		return instrucciones;
	}
    
    public String colocarCopyright(Integer id_survey) {
    	Surveys survey = surveysService.searchxId(id_survey);    	
		return survey.getCopyright();		
	}
    
    public String colocarBanner(Integer id_survey) {
    	Surveys survey = surveysService.searchxId(id_survey);
    	byte[] banner=survey.getBanner();
    	ImagesUtils iu= new ImagesUtils();
		return iu.imgTemp(banner, "banner_"+id_survey);		
	}
    
    public void redireccionar(int tipo_r){
    	FacesContext fc=FacesContext.getCurrentInstance();
    	NavigationHandler nh = fc.getApplication().getNavigationHandler();
    	
    	if(tipo_r==0) nh.handleNavigation(fc, null, "pretty:users");
    	else if (tipo_r==1) nh.handleNavigation(fc, null, "pretty:login");    	
        /*try {        	
        	if(tipo_r==2){ fc.getExternalContext().redirect("pretty:users");//
        	else if(tipo_r==0) fc.getExternalContext().redirect("pretty:bigfive");//
        	else if(tipo_r==1) fc.getExternalContext().redirect("pretty:bfi");//        	
		} catch (IOException e) {			
			e.printStackTrace();
		}*/
    }
    
    public void preparaDatos(Integer id_survey,Integer id_group){
    	this.id_group=id_group;
    	survey = surveysService.searchxId(id_survey);
    }
    	
    //Services
    public void setSurveysService(SurveysService surveysService) {
		this.surveysService = surveysService;
	}	
	public void setQuestionsService(QuestionsService questionsService) {
		this.questionsService = questionsService;
	}	
	public void setQuestionsAllService(QuestionsAllService questionsAllService) {
		this.questionsAllService = questionsAllService;
	}
	public void setAnswersService(AnswersService answersService) {
		this.answersService = answersService;
	}
	public void setBigFiveService(BigFiveService bigFiveService) {
		this.bigFiveService = bigFiveService;
	}
}
