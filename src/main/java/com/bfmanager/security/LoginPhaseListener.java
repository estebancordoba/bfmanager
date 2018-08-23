package com.bfmanager.security;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpServletResponse;

import com.bfmanager.util.FacesUtils;

public class LoginPhaseListener implements PhaseListener {
	
	private static final long serialVersionUID = 1L;

	@Override
	public void afterPhase(PhaseEvent arg0) {	
				
		FacesContext facesContext = arg0.getFacesContext();
		HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
		
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", -1);
		
		String[] paginasPermitidasAdmin = { "adminUsers.xhtml",
											"bfDimensions.xhtml",
											"bfValueQuestions.xhtml",
											"bigFive.xhtml",
											"dashboard.xhtml",
											"dropDown.xhtml",
											"managerGroups.xhtml",											
											"multipleChoice.xhtml",
											"preview.xhtml",
											"questions.xhtml",
											"responses.xhtml",
											"responsesBFI.xhtml",
											"results.xhtml",
											"statisticsSurvey.xhtml",
											"suggestions.xhtml",
											"surveys.xhtml",
											"adminGroups.xhtml",
											"assignGroups.xhtml",
											"backup.xhtml",
											"profile.xhtml"};
		
		String[] paginasPermitidasManager = { "dashboard.xhtml",
											  "assignGroups.xhtml",
											  "preview.xhtml",
											  "results.xhtml",
											  "statisticsSurvey.xhtml",
											  "surveys.xhtml",
											  "profile.xhtml",
											  "bigFive.xhtml",
											  "responsesBFI.xhtml",
											  "responses.xhtml",
											  "bfDimensions.xhtml",
											  "bfValueQuestions.xhtml"};
		
		String[] paginasPermitidasUsers = {	"users.xhtml",
											"answers.xhtml",
											"profile.xhtml"};
		
		String[] paginasLibres = {"answers.xhtml"};
		
		String[] paginasError = {"access.xhtml",
								 "error.xhtml",
								 "notfound.xhtml",
								 "request.xhtml"};
		
		FacesContext fc = arg0.getFacesContext();
		String paginaActual = fc.getViewRoot().getViewId();//Pagina Actual
		NavigationHandler nh = fc.getApplication().getNavigationHandler();
		
		//System.out.println("paginaActual: "+paginaActual+"- paginaLogin: "+paginaActual.lastIndexOf("index.xhtml"));
		boolean paginaLogin=paginaActual.lastIndexOf("index.xhtml") > -1 ? true : false;

		if(paginaActual != null){
			boolean permitido = false;
			
			for(String pagina : paginasError){						
				permitido = paginaActual.lastIndexOf(pagina) > -1 ? true : false;						
				if(permitido) return;
			}
			
			
			if(estaLogueado()){
				int tipoUsuario;
				tipoUsuario=FacesUtils.getUsuarioLogueado().getUserType().getIdUserType();				
				
				if(tipoUsuario==1){
					for(String pagina : paginasPermitidasAdmin){						
						permitido = paginaActual.lastIndexOf("admin/"+pagina) > -1 ? true : false;						
						if(permitido) break;
					}
				}
				if(tipoUsuario==2){
					for(String pagina : paginasPermitidasManager){						
						permitido = paginaActual.lastIndexOf("manager/"+pagina) > -1 ? true : false;						
						if(permitido) break;
					}
				}
				if(tipoUsuario==3){
					for(String pagina : paginasPermitidasUsers){						
						permitido = paginaActual.lastIndexOf("user/"+pagina) > -1 ? true : false;						
						if(permitido) break;
					}
				}
				//permitido=true;////Eliminar
				//Redireccionamiento a la pagina de Login
				if(!permitido && !paginaLogin) nh.handleNavigation(fc, null, "pretty:login");
			}			
			else{
				for(String pagina : paginasLibres){
					permitido = paginaActual.lastIndexOf("guest/"+pagina) > -1 ? true : false;
					if(permitido) break;
				}
				//permitido=true;///////////Eliminar
			}		

			//Redireccionamiento a la pagina de Login
			if(!estaLogueado() && !paginaLogin && !permitido){
				nh.handleNavigation(fc, null, "pretty:login");
			}
		}
	}

	@Override
	public void beforePhase(PhaseEvent arg0) {}

	@Override
	public PhaseId getPhaseId() {		
		return PhaseId.RESTORE_VIEW;
	}
	
	private boolean estaLogueado(){
		return FacesUtils.existeUsuarioLogueado();
	}

}
