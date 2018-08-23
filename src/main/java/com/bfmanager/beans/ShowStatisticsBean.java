package com.bfmanager.beans;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import org.primefaces.component.chart.Chart;
import org.primefaces.component.outputlabel.OutputLabel;
import org.primefaces.component.panel.Panel;
import org.primefaces.component.panelgrid.PanelGrid;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import com.bfmanager.model.hibernate.Answers;
import com.bfmanager.model.hibernate.DropDown;
import com.bfmanager.model.hibernate.MultipleChoice;
import com.bfmanager.model.hibernate.Questions;
import com.bfmanager.model.hibernate.Surveys;
import com.bfmanager.service.AnswersService;
import com.bfmanager.service.QuestionsAllService;
import com.bfmanager.service.QuestionsService;
import com.bfmanager.service.SurveysService;
 
@ManagedBean (name = "showStatisticsBean")
public class ShowStatisticsBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Panel panels;
	private BarChartModel barModel;
	private Integer id_group;
	private String name_group;
		
	//Propiedades Sevicios
	@ManagedProperty(value = "#{SurveysServiceBean}")
	SurveysService surveysService;
	@ManagedProperty(value = "#{QuestionsServiceBean}")
	QuestionsService questionsService;	
	@ManagedProperty(value = "#{QuestionsAllServiceBean}")
	QuestionsAllService questionsAllService;
	@ManagedProperty(value = "#{AnswersServiceBean}")
	AnswersService answersService;
	
	public ShowStatisticsBean(){
	}
	
	public Integer getId_group() {
		return id_group;
	}

	public void setId_group(Integer id_group) {
		this.id_group = id_group;
	}
 
    public Panel panels(Integer id_survey) { 
    	FacesContext fc = FacesContext.getCurrentInstance();    	
        panels = (Panel) fc.getApplication().createComponent("org.primefaces.component.Panel");
        
        Surveys survey = surveysService.searchxId(id_survey);                
        panels.setHeader(survey.getTitle()+" - "+name_group);    
        
        List<Questions> questions = questionsService.getQuestionsSurveyStats(survey.getIdSurvey());
        
        PanelGrid pGrid = new PanelGrid();
        pGrid.setColumns(3);
        pGrid.setLayout("grid");
        //pGrid.setColumnClasses("left,right");
       // pGrid.setStyle("width:100%");
        pGrid.setStyleClass("ui-panelgrid-blank form-group");
        for (Questions question : questions) { 
            Chart ch = new Chart();
                    	
        	if(question.getType().getIdQuestionType()==1){
        		Panel op = new Panel();
        		op.setHeader(questionsService.searchxId(question.getIdQuestion()).getTitle());
        		//op.setStyle("width:300px;height:300px;text-align:center;");        		
        		op.setStyle("text-align:center;");
        		
        		OutputLabel ol = new OutputLabel();        		
        		ol.setValue("NO EXISTE GRAFICA PARA ESTE TIPO DE PREGUNTA");
        		
        		op.getChildren().add(ol);        		
        		pGrid.getChildren().add(op);
        	}            
        	else if(question.getType().getIdQuestionType()==2){
        		ch.setType("bar");
        		//ch.setStyle("width:300px;height:300px");
        		
        		BarChartModel bM = new BarChartModel();        
        		bM.addSeries(getDataBarModelMC(question.getIdQuestion()));                 
        		bM.setTitle(questionsService.searchxId(question.getIdQuestion()).getTitle());
        		bM.setAnimate(true);
        		
        		/*bM.setLegendPosition("ne"); 
                Axis xAxis = bM.getAxis(AxisType.X);
                xAxis.setLabel("Opcion");
                Axis yAxis = bM.getAxis(AxisType.Y);
                yAxis.setLabel("Numero de Respuestas");
                yAxis.setMin(0);
                yAxis.setMax(200);*/
        		
        		ch.setModel(bM);
        		
        		pGrid.getChildren().add(ch);
        	}   
        	else if(question.getType().getIdQuestionType()==3){
        		ch.setType("bar");
        		//ch.setStyle("width:300px;height:300px");
        		
        		BarChartModel bM = new BarChartModel();
        		bM.addSeries(getDataBarModelDD(question.getIdQuestion()));                 
        		bM.setTitle(questionsService.searchxId(question.getIdQuestion()).getTitle());
        		bM.setAnimate(true);
        		
        		ch.setModel(bM);
        		
        		pGrid.getChildren().add(ch);
        	}
        	else if(question.getType().getIdQuestionType()==4){
        		ch.setType("bar");
        		//ch.setStyle("width:300px;height:300px");
        		
        		BarChartModel bM = new BarChartModel();        
        		bM.addSeries(getDataBarModelLS(question.getIdQuestion()));                 
        		bM.setTitle(questionsService.searchxId(question.getIdQuestion()).getTitle());
        		bM.setAnimate(true);
        		
        		ch.setModel(bM);
        		
        		pGrid.getChildren().add(ch);
        	} 
        }
        panels.getChildren().add(pGrid);
        
        return panels;
	}
    
    public Panel getPanels() {
		return this.panels;
	}
    
    public void setPanels(Panel panels) {
		this.panels = panels;
	}
 
    public BarChartModel getBarModel() {
        barModel = new BarChartModel();  
        ChartSeries seriesBarModel = new ChartSeries();
        seriesBarModel.set("ERROR", 0); 
        barModel.addSeries(seriesBarModel);
        return barModel;        
    } 
    
    private ChartSeries getDataBarModelMC(Long id_p){
    	ChartSeries seriesBarModel = new ChartSeries();
    	
    	List<MultipleChoice> mcBarModel = questionsAllService.getMultipleChoiceQuestion(id_p);
    	List<Answers> answersBarModel = answersService.getAnswerQuestionGroup(id_p,id_group);
    	
    	int[] sumMC=new int[mcBarModel.size()]; 
    	
    	for(int i=0;i<mcBarModel.size();i++){
    		sumMC[i]=0;
    		for(int j=0;j<answersBarModel.size();j++){
    			if(mcBarModel.get(i).getIdMultipleChoice()==Long.parseLong(answersBarModel.get(j).getAnswer())){
    				sumMC[i]=sumMC[i]+1;
    			}
    		}  
    		seriesBarModel.set(mcBarModel.get(i).getDescription(), sumMC[i]);
    	}
    	
      //  seriesBarModel.setLabel("Boys");
    	//seriesBarModel.set("2004", 120);     
        
        return seriesBarModel;
    }
    
    private ChartSeries getDataBarModelLS(Long id_p){
    	ChartSeries seriesBarModel = new ChartSeries();
    	    	
    	List<Answers> answersBarModel = answersService.getAnswerQuestionGroup(id_p,id_group);
    	
    	int[] sumMC=new int[5]; 
    	
    	for(int i=0;i<5;i++){
    		sumMC[i]=0;
    		for(int j=0;j<answersBarModel.size();j++){
    			if(Long.parseLong(answersBarModel.get(j).getAnswer())==(i+1)){
    				sumMC[i]=sumMC[i]+1;
    			}
    		}  
    		seriesBarModel.set((i+1), sumMC[i]);
    	}
    	
      //  seriesBarModel.setLabel("Boys");
    	//seriesBarModel.set("2004", 120);     
        
        return seriesBarModel;
    }
    
    private ChartSeries getDataBarModelDD(Long id_p){
    	ChartSeries seriesBarModel = new ChartSeries();
    	
    	List<DropDown> mcBarModel = questionsAllService.getDropDownQuestion(id_p);
    	List<Answers> answersBarModel = answersService.getAnswerQuestionGroup(id_p,id_group);
    	
    	int[] sumMC=new int[mcBarModel.size()]; 
    	
    	for(int i=0;i<mcBarModel.size();i++){
    		sumMC[i]=0;
    		for(int j=0;j<answersBarModel.size();j++){
    			if(mcBarModel.get(i).getIdDropDown()==Long.parseLong(answersBarModel.get(j).getAnswer())){
    				sumMC[i]=sumMC[i]+1;
    			}
    		}  
    		seriesBarModel.set(mcBarModel.get(i).getDescription(), sumMC[i]);
    	}
        
        return seriesBarModel;
    }
    
    //Funciones
    public void prepararDatos(Integer id_group, String name_group){
    	this.id_group=id_group;
    	this.name_group=name_group;
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
}