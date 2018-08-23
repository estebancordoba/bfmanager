package com.bfmanager.model.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.bfmanager.model.hibernate.Answers;

public class AnswersDAOImpl implements AnswersDAO, Serializable {

	private static final long serialVersionUID = 1L;
	private SessionFactory sessionFactory;
	
	@Override
	public void saveAnswer(Answers answer) {
		Session session = null;
		Transaction tx = null;

		try {

			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.persist(answer);
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
	public void updateAnswer(Answers answer) {
		Session session = null;
		Transaction tx = null;

		try {

			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.update(answer);
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
	public void removeAnswer(Answers answer) {
		Session session = null;
		Transaction tx = null;

		try {

			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			Answers aw = session.load(Answers.class, answer.getIdAnswer());
			session.delete(aw);
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
	public Answers searchAWxId(Long id_answer) {
		Session session = null;

		try {
			session = sessionFactory.openSession();
			return (Answers) session.createQuery("FROM Answers WHERE id_answer = :idAnswer").setParameter("idAnswer", id_answer).getSingleResult();
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
	public List<Answers> getAnswer() {
		Session session = null;

		try {
			session = sessionFactory.openSession();
			return (List<Answers>) session.createQuery("FROM Answers").getResultList();
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
	public Answers getAnswerxIdSurveyIdGroupxIdUser(Integer id_survey, Integer id_group, Integer id_user) {
		Session session = null;

		try {
			session = sessionFactory.openSession();
			return (Answers) session.createQuery("FROM Answers WHERE id_group=:id_group AND id_survey=:id_survey AND user=:id_user")
					.setParameter("id_survey", id_survey)
					.setParameter("id_group", id_group)
					.setParameter("id_user", id_user)
					.setMaxResults(1).getSingleResult();			
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
	public List<Answers> getAnswerxIdSurveyIdGroupShow(Integer id_survey, Integer id_group, List<Long> lst_questionsM) {
		Session session = null;

		try {
			session = sessionFactory.openSession();
			/*
			 return (List<Answers>) session.createQuery("FROM Answers WHERE id_group=:id_group AND id_survey=:id_survey")
					.setParameter("id_survey", id_survey)
					.setParameter("id_group", id_group)
					.getResultList();
					*/
			return (List<Answers>) session.createQuery("FROM Answers WHERE id_group=:id_group AND id_survey=:id_survey AND question IN (:lst_questionsM)")
					.setParameter("id_survey", id_survey)
					.setParameter("id_group", id_group)
					.setParameterList("lst_questionsM", lst_questionsM)
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
	public List<Answers> getAnswerQuestion(Long id_question) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			return (List<Answers>) session.createQuery("FROM Answers WHERE question = :question")
					.setParameter("question", id_question)
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
	public List<Answers> getAnswerQuestionGroup(Long id_question, Integer id_group) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			return (List<Answers>) session.createQuery("FROM Answers WHERE question = :question AND id_group=:id_group")
					.setParameter("question", id_question)
					.setParameter("id_group", id_group)
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
	public List<Answers> getAnswersSurvey(Integer id_survey) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			return (List<Answers>) session.createQuery("FROM Answers WHERE id_survey = :id_survey")
					.setParameter("id_survey", id_survey)
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
	public List<Answers> getAnswersSurveyUserGroup(Integer id_user,Integer id_survey, Integer id_group) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			return (List<Answers>) session.createQuery("FROM Answers WHERE id_survey=:id_survey AND user=:id_user AND id_group=:id_group")
					.setParameter("id_survey", id_survey)
					.setParameter("id_user", id_user)
					.setParameter("id_group", id_group)
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
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}