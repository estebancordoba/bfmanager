package com.bfmanager.converter;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import com.bfmanager.beans.LanguageBean;
import com.bfmanager.model.hibernate.QuestionsType;
import com.bfmanager.service.QuestionsService;

@ManagedBean(name = "questionTypeConverter")
@RequestScoped
public class QuestionTypeConverter implements Converter{
	@ManagedProperty(value = "#{QuestionsServiceBean}")
	QuestionsService questionsService;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {

		try {

			if (value != null && value.trim().length() > 0) {
				return questionsService.getQuestionTypeXId(Integer.parseInt(value));
			} else {
				return null;
			}

		} catch (Exception e) {
			FacesMessage msg = new FacesMessage(LanguageBean.obtenerMensaje("error"), LanguageBean.obtenerMensaje("invalid_user"));
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ConverterException(msg);			
		}

	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {

		if (value != null) {
			return String.valueOf(((QuestionsType) value).getIdQuestionType());
		} else {
			return null;
		}

	}

	public void setQuestionsService(QuestionsService questionsService) {
		this.questionsService = questionsService;
	}
}

