package com.bfmanager.model.hibernate;
// Generated 16/10/2017 02:04:00 AM by Hibernate Tools 5.2.3.Final

/**
 * BfValueQuestions generated by hbm2java
 */
public class BfValueQuestions implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer idBfValueQuestion;
	private Boolean addition;
	private Boolean reverse;
	private long idBfDimension;
	private long idQuestion;

	public BfValueQuestions() {
	}

	public BfValueQuestions(long idBfDimension, long idQuestion) {
		this.idBfDimension = idBfDimension;
		this.idQuestion = idQuestion;
	}

	public BfValueQuestions(Boolean addition, Boolean reverse, long idBfDimension, long idQuestion) {
		this.addition = addition;
		this.reverse = reverse;
		this.idBfDimension = idBfDimension;
		this.idQuestion = idQuestion;
	}

	public Integer getIdBfValueQuestion() {
		return this.idBfValueQuestion;
	}

	public void setIdBfValueQuestion(Integer idBfValueQuestion) {
		this.idBfValueQuestion = idBfValueQuestion;
	}

	public Boolean getAddition() {
		return this.addition;
	}

	public void setAddition(Boolean addition) {
		this.addition = addition;
	}

	public Boolean getReverse() {
		return this.reverse;
	}

	public void setReverse(Boolean reverse) {
		this.reverse = reverse;
	}

	public long getIdBfDimension() {
		return this.idBfDimension;
	}

	public void setIdBfDimension(long idBfDimension) {
		this.idBfDimension = idBfDimension;
	}

	public long getIdQuestion() {
		return this.idQuestion;
	}

	public void setIdQuestion(long idQuestion) {
		this.idQuestion = idQuestion;
	}

}