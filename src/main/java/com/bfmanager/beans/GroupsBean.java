package com.bfmanager.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.bfmanager.model.hibernate.Groups;
import com.bfmanager.model.hibernate.SurveyGroup;
import com.bfmanager.model.hibernate.Surveys;
import com.bfmanager.model.hibernate.UserGroup;
import com.bfmanager.service.GroupsService;
import com.bfmanager.service.SurveysService;
import com.bfmanager.util.MsgUtil;

@ManagedBean(name = "groupsBean")
@SessionScoped
public class GroupsBean implements Serializable {

	private static final long serialVersionUID = 1L;	
	
	@ManagedProperty(value = "#{GroupsServiceBean}")
	GroupsService groupsService;
	@ManagedProperty(value = "#{SurveysServiceBean}")
	SurveysService surveysService;
	
	private Groups group;
	private SurveyGroup survey_group;
	private UserGroup user_group;
	private Integer[] usersS;
	private Integer[] groupsS;
	
	private List<SurveyGroup> selectedSurveyGroup;
	private List<Groups> selectedGroups;
	private List<UserGroup> selectedUserGroup;
	
	private boolean editarGroup = false;
	
	//Constructor
	public GroupsBean() {		
		cancelarGroup();
		cancelarUserGroup();
		cancelarSurveyGroup();
	}
	
	//Getters and Setters
	public Groups getGroup() {
		return group;
	}

	public void setGroup(Groups group) {
		this.group = group;
	}

	public SurveyGroup getSurvey_group() {
		return survey_group;
	}

	public void setSurvey_group(SurveyGroup survey_group) {
		this.survey_group = survey_group;
	}

	public UserGroup getUser_group() {
		return user_group;
	}

	public void setUser_group(UserGroup user_group) {
		this.user_group = user_group;
	}	
	
	public Integer[] getUsersS() {
		return usersS;
	}

	public void setUsersS(Integer[] usersS) {
		this.usersS = usersS;
	}
	
	public Integer[] getGroupsS() {
		return groupsS;
	}

	public void setGroupsS(Integer[] groupsS) {
		this.groupsS = groupsS;
	}
	
	public List<SurveyGroup> getSelectedSurveyGroup() {
		return selectedSurveyGroup;
	}

	public void setSelectedSurveyGroup(List<SurveyGroup> selectedSurveyGroup) {
		this.selectedSurveyGroup = selectedSurveyGroup;
	}	
	
	public List<Groups> getSelectedGroups() {
		return selectedGroups;
	}

	public void setSelectedGroups(List<Groups> selectedGroups) {
		this.selectedGroups = selectedGroups;
	}

	public List<UserGroup> getSelectedUserGroup() {
		return selectedUserGroup;
	}

	public void setSelectedUserGroup(List<UserGroup> selectedUserGroup) {
		this.selectedUserGroup = selectedUserGroup;
	}
	
	//Services
	public void setGroupsService(GroupsService groupsService) {
		this.groupsService = groupsService;
	}
	public void setSurveysService(SurveysService surveysService) {
		this.surveysService = surveysService;
	}
	
	//Groups
	public void guardarGroup() {
		if (!editarGroup) {
			try {
				groupsService.saveGroup(group);
				this.cancelarGroup();
				MsgUtil.msgInfo("Exito!", "Grupo guardado correctamente");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				groupsService.updateGroup(group);
				this.cancelarGroup();
				MsgUtil.msgInfo("Exito!", "Grupo actualizado");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	
	public void firstGroup() {
		group = groupsService.firstGroup();		
	}

	public void actualizarGroup(Integer id_group) {
		group = groupsService.searchGroupxId(id_group);
		editarGroup = true;
	}

	public void eliminarGroup(Integer id_group) {		
		try {
			Groups eliminar = groupsService.searchGroupxId(id_group);		
			groupsService.removeGroup(eliminar);						
			MsgUtil.msgInfo("Exito!", "Grupo eliminado correctamente");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void eliminarVariosGrupos(){				
		try {	
			for (Groups eliminar : selectedGroups) {	
				groupsService.removeGroup(eliminar);
			}
			MsgUtil.msgInfo("Exito!", "Registros eliminados correctamente.");
		} catch (Exception e) {
			MsgUtil.msgError("Error!", "Ocurrio un error al intentar eliminar los registros.");
			e.printStackTrace();
		}		
		selectedGroups=null;
	}
	
	public List<Groups> getGroups() {
		return groupsService.getGroups();
	}
	
	public List<Groups> getGroupsAvailable(Integer id_survey) {		
		List<Groups> gruposDisponibles = groupsService.getGroups();
		
		List<SurveyGroup> gruposAgregados = groupsService.getSurveyGroupsxIdSurvey(id_survey);
				
		for (SurveyGroup groupA : gruposAgregados) {
			
			ArrayList<Groups> borrar = new ArrayList<Groups>();
			
			for(int i=0;i<gruposDisponibles.size();i++)
				if(gruposDisponibles.get(i).getIdGroup()==groupA.getIdGroup()) 
					borrar.add(gruposDisponibles.get(i));
			
			gruposDisponibles.removeAll(borrar);			
		}
		
		return gruposDisponibles;
	}
	
	public Groups getGroupxId(Integer id_group) {
		return groupsService.searchGroupxId(id_group);
	}
	
	public void prepararGroup(Integer id_group) {
		cancelarGroup();
		group = groupsService.searchGroupxId(id_group);
	}

	public void cancelarGroup() {
		group = new Groups();
		editarGroup = false;
		selectedGroups=null;
	}
	
	//UserGroup
	public void guardarUsersxGroup(Integer id_group) {
		for (Integer user_id : usersS) {			
			try {
				user_group.setIdUser(user_id);
				user_group.setIdGroup(id_group);
				groupsService.saveUserGroup(user_group);
				this.cancelarUserGroup();
				MsgUtil.msgInfo("Exito!", "Usuario asignado correctamente");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void guardarUserGroup(Integer id_group) {		
		try {
			user_group.setIdGroup(id_group);
			groupsService.saveUserGroup(user_group);
			this.cancelarUserGroup();
			MsgUtil.msgInfo("Exito!", "Usuario asignado correctamente");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void eliminarUserGroup(Integer id_user_group) {		
		try {
			UserGroup eliminar = groupsService.searchUserGroupxId(id_user_group);		
			groupsService.removeUserGroup(eliminar);						
			MsgUtil.msgInfo("Exito!", "Usuario asignado eliminado correctamente");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void eliminarVariosUserGroup(){				
		try {	
			for (UserGroup eliminar : selectedUserGroup) {	
				groupsService.removeUserGroup(eliminar);
			}
			MsgUtil.msgInfo("Exito!", "Registros eliminados correctamente.");
		} catch (Exception e) {
			MsgUtil.msgError("Error!", "Ocurrio un error al intentar eliminar los registros.");
			e.printStackTrace();
		}		
		selectedUserGroup=null;
	}
	
	public List<UserGroup> getUserGroupxIdGroup(Integer id_group) {
		return groupsService.getUserGroupxIdGroup(id_group);
	}
		
	public void cancelarUserGroup() {
		user_group = new UserGroup();
		usersS = null;		
		selectedUserGroup=null;
	}
	
	//SurveyGroup
	public void guardarSurveyGroup(Integer id_survey) {		
		try {
			survey_group.setIdSurvey(id_survey);
			groupsService.saveSurveyGroup(survey_group);
			this.cancelarSurveyGroup();
			MsgUtil.msgInfo("Exito!", "Grupo asignado correctamente");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void guardarSurveyxGroup(Integer id_survey) {
		for (Integer id_group : groupsS) {
			try {
				survey_group.setIdSurvey(id_survey);
				survey_group.setIdGroup(id_group);				
				groupsService.saveSurveyGroup(survey_group);
				this.cancelarSurveyGroup();
				MsgUtil.msgInfo("Exito!", "Grupo asignado correctamente");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}		
	}

	public void eliminarSurveyGroup(Integer id_survey_group) {		
		try {
			SurveyGroup eliminar = groupsService.searchSurveyGroupxId(id_survey_group);		
			groupsService.removeSurveyGroup(eliminar);						
			MsgUtil.msgInfo("Exito!", "Grupo asignado eliminado correctamente");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void eliminarVariosSurveyGroup(){				
		try {	
			for (SurveyGroup eliminar : selectedSurveyGroup) {	
				groupsService.removeSurveyGroup(eliminar);
			}
			MsgUtil.msgInfo("Exito!", "Registros eliminados correctamente.");
		} catch (Exception e) {
			MsgUtil.msgError("Error!", "Ocurrio un error al intentar eliminar los registros.");
			e.printStackTrace();
		}		
		selectedSurveyGroup=null;
	}
	
	public List<SurveyGroup> getSurveyGroupsxIdSurvey(Integer id_survey) {
		return groupsService.getSurveyGroupsxIdSurvey(id_survey);
	}
	
	public List<SurveyGroup> getSurveysGroupsxIdUser(Integer id_user) {
		List<UserGroup> lstUserGroup = groupsService.getUserGroupxIdUser(id_user);	
		
		List<Integer> lstGroupsUser=new ArrayList<Integer>();
		
		for (UserGroup userGroup : lstUserGroup) {			
			lstGroupsUser.add(userGroup.getIdGroup());
		}
		
		if(lstGroupsUser.isEmpty()) return null;
		else{			
			List<SurveyGroup> lstSG_All = groupsService.getSurveyGroupxLstGroups(lstGroupsUser);
			
			ArrayList<SurveyGroup> borrarSurveyGroup = new ArrayList<SurveyGroup>();////			 
			
			for(int i=0;i<lstSG_All.size();i++){		
				Surveys su = surveysService.searchxId(lstSG_All.get(i).getIdSurvey());				
				if(!su.getActivated())
					borrarSurveyGroup.add(lstSG_All.get(i));					
			}
			
			lstSG_All.removeAll(borrarSurveyGroup);
			
			return lstSG_All;
		}
	}
	
	public void cancelarSurveyGroup() {
		survey_group = new SurveyGroup();
		groupsS = null;
		selectedSurveyGroup=null;
	}
	
}
