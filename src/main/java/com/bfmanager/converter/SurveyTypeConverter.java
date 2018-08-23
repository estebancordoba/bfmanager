package com.bfmanager.converter;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import com.bfmanager.model.hibernate.SurveysType;
import com.bfmanager.service.SurveysService;

@ManagedBean(name = "surveyTypeConverter")
@RequestScoped
public class SurveyTypeConverter implements Converter{
	@ManagedProperty(value = "#{SurveysServiceBean}")
	SurveysService surveysService;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {

		try {

			if (value != null && value.trim().length() > 0) {
				return surveysService.getSurveyTypeXId(Integer.parseInt(value));
			} else {
				return null;
			}

		} catch (Exception e) {
			FacesMessage msg = new FacesMessage("Error!", "Tipo cuestionario no valido");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ConverterException(msg);			
		}

	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {

		if (value != null) {
			return String.valueOf(((SurveysType) value).getIdSurveyType());
		} else {
			return null;
		}

	}

	public void setSurveysService(SurveysService surveysService) {
		this.surveysService = surveysService;
	}
}
