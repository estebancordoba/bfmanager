package com.bfmanager.model.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.bfmanager.model.hibernate.Questions;
import com.bfmanager.model.hibernate.QuestionsType;

public class QuestionsDAOImpl implements QuestionsDAO, Serializable{

	private static final long serialVersionUID = 1L;
	private SessionFactory sessionFactory;
	
	@Override
	public void saveQuestion(Questions question) {
		Session session = null;
		Transaction tx = null;

		try {

			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.persist(question);
			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
				session = null;
			}
		}
	}

	@Override
	public void updateQuestion(Questions question) {
		Session session = null;
		Transaction tx = null;

		try {

			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.update(question);
			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
				session = null;
			}
		}
	}

	@Override
	public void removeQuestion(Questions question) {
		Session session = null;
		Transaction tx = null;

		try {

			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			Questions qu = session.load(Questions.class, question.getIdQuestion());
			session.delete(qu);
			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
				session = null;
			}
		}
	}

	@Override
	public Questions searchxId(Long id_question) {
		Session session = null;

		try {
			session = sessionFactory.openSession();
			return (Questions) session.createQuery("FROM Questions WHERE id_question = :id_question").setParameter("id_question", id_question).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
				session = null;
			}
		}
	}
	
	@Override
	public Questions firstQuestion() {
		Session session = null;

		try {
			session = sessionFactory.openSession();
			return (Questions) session.createQuery("FROM Questions").setMaxResults(1).getSingleResult();
		} catch (Exception e) {			
			return null;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
				session = null;
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Questions> getQuestions() {
		Session session = null;

		try {
			session = sessionFactory.openSession();
			return (List<Questions>) session.createQuery("FROM Questions").getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
				session = null;
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Questions> getQuestionsSurvey(Integer id_survey) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			return (List<Questions>) session.createQuery("FROM Questions WHERE survey = :survey")
					.setParameter("survey", id_survey)
					.getResultList();
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
				session = null;
			}
		}		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Questions> getQuestionsSurveyStats(Integer id_survey) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			return (List<Questions>) session.createQuery("FROM Questions WHERE survey = :survey AND showStats=1")
					.setParameter("survey", id_survey)
					.getResultList();
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
				session = null;
			}
		}		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Questions> getQuestionsSurveyResults(Integer id_survey) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			return (List<Questions>) session.createQuery("FROM Questions WHERE survey = :survey AND showResults=1")
					.setParameter("survey", id_survey)
					.getResultList();
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
				session = null;
			}
		}		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<QuestionsType> getQuestionsType() {

		Session session = null;

		try {
			session = sessionFactory.openSession();
			return (List<QuestionsType>) session.createQuery("FROM QuestionsType").getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
				session = null;
			}
		}
	}

	public QuestionsType getQuestionTypeXId(int id) {
		Session session = null;

		try {
			session = sessionFactory.openSession();
			return (QuestionsType) session.createQuery("FROM QuestionsType WHERE id_question_type = :id").setParameter("id", id).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
				session = null;
			}
		}
	}
			
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
