package com.bfmanager.model.hibernate;
// Generated 18/10/2017 10:25:02 PM by Hibernate Tools 5.2.3.Final

/**
 * SurveyGroup generated by hbm2java
 */
public class SurveyGroup implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer idSurveyGroup;
	private Integer idGroup;
	private Integer idSurvey;

	public SurveyGroup() {
	}

	public SurveyGroup(Integer idGroup, Integer idSurvey) {
		this.idGroup = idGroup;
		this.idSurvey = idSurvey;
	}

	public Integer getIdSurveyGroup() {
		return this.idSurveyGroup;
	}

	public void setIdSurveyGroup(Integer idSurveyGroup) {
		this.idSurveyGroup = idSurveyGroup;
	}

	public Integer getIdGroup() {
		return this.idGroup;
	}

	public void setIdGroup(Integer idGroup) {
		this.idGroup = idGroup;
	}

	public Integer getIdSurvey() {
		return this.idSurvey;
	}

	public void setIdSurvey(Integer idSurvey) {
		this.idSurvey = idSurvey;
	}

}