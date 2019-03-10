package com.bfmanager.beans;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.Properties;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

@ManagedBean(name = "languageBean")
@SessionScoped
public class LanguageBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String language;
	private static Properties archivoProperties;
	
	public String getLanguage() {
		System.out.println("LANGUAGE GET: "+language);
		return language;
	}

	public void setLanguage(String language) {
		System.out.println("LANGUAGE SET: "+language);
		this.language = language;
	}
	
	public LanguageBean(){
		language="es";
		archivoProperties = new Properties();
		cargarArchivoLanguage();
	}
	
	public void cambioIdioma(){
		cargarArchivoLanguage();				
	}
	
	private void cargarArchivoLanguage(){
		 try {
			ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();		
			String path = servletContext.getRealPath("") + File.separatorChar
					+ "WEB-INF" + File.separatorChar + "classes" + File.separatorChar + "com"+ File.separatorChar + "bfmanager"+ File.separatorChar + "languages"
					+ File.separatorChar + "messages_"+language+".properties";
						
			archivoProperties.load(new FileInputStream(path));
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String obtenerMensaje(String name){
		String message = archivoProperties.getProperty(name);
		if(message == null) return name;			
		return message;
	}
}
