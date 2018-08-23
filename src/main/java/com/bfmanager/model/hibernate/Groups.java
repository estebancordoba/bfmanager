package com.bfmanager.model.hibernate;
// Generated 18/10/2017 10:25:02 PM by Hibernate Tools 5.2.3.Final

/**
 * Groups generated by hbm2java
 */
public class Groups implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer idGroup;
	private String name;
	private String description;

	public Groups() {
	}

	public Groups(String name) {
		this.name = name;
	}

	public Groups(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public Integer getIdGroup() {
		return this.idGroup;
	}

	public void setIdGroup(Integer idGroup) {
		this.idGroup = idGroup;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}