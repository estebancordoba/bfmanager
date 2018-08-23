package com.bfmanager.model.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.bfmanager.model.hibernate.Surveys;
import com.bfmanager.model.hibernate.SurveysType;
import com.bfmanager.model.hibernate.Users;

public class SurveysDAOImpl implements SurveysDAO, Serializable{

	private static final long serialVersionUID = 1L;
	private SessionFactory sessionFactory;
	
	@Override
	public void saveSurvey(Surveys survey) {
		Session session = null;
		Transaction tx = null;

		try {

			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			//session.persist(survey);
			session.save(survey);
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
	public void updateSurvey(Surveys survey) {
		Session session = null;
		Transaction tx = null;

		try {

			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.update(survey);
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
	public void removeSurvey(Surveys survey) {
		Session session = null;
		Transaction tx = null;

		try {

			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			Surveys su = session.load(Surveys.class, survey.getIdSurvey());
			session.delete(su);
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
	public Surveys searchxId(Integer id_survey) {
		Session session = null;

		try {
			session = sessionFactory.openSession();
			return (Surveys) session.createQuery("FROM Surveys WHERE 	id_survey = :id_survey").setParameter("id_survey", id_survey).getSingleResult();
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
	public Surveys firstSurvey() {
		Session session = null;

		try {
			session = sessionFactory.openSession();
			return (Surveys) session.createQuery("FROM Surveys").setMaxResults(1).getSingleResult();
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
	public List<Surveys> getSurveys() {
		Session session = null;

		try {
			session = sessionFactory.openSession();
			//return (List<Surveys>) session.createQuery("FROM Surveys inner join SurveyUser WHERE id_user=2").getResultList();
			return (List<Surveys>) session.createQuery("FROM Surveys").getResultList();
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
	public List<Surveys> getSurveysFree() {
		Session session = null;

		try {
			session = sessionFactory.openSession();
			return (List<Surveys>) session.createQuery("FROM Surveys WHERE free=1 AND activated=1").getResultList();
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
	public List<Surveys> getSurveysBFI() {
		Session session = null;

		try {
			session = sessionFactory.openSession();
			return (List<Surveys>) session.createQuery("FROM Surveys WHERE survey_type=2").getResultList();
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
	public List<Surveys> getSurveysxIdManager(Integer id) {
		Session session = null;

		try {
			session = sessionFactory.openSession();
			return (List<Surveys>) session.createQuery("FROM Surveys WHERE user_owner=:id")
					.setParameter("id", id)
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
	public List<Surveys> getSurveysBFIxIdManager(Integer id) {
		Session session = null;

		try {
			session = sessionFactory.openSession();
			return (List<Surveys>) session.createQuery("FROM Surveys WHERE user_owner=:id AND survey_type=2")
					.setParameter("id", id)
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

	@Override
	public Users getUserXId(int id) {
		Session session = null;

		try {
			session = sessionFactory.openSession();
			return (Users) session.createQuery("FROM Users WHERE id_user = :id").setParameter("id", id).getSingleResult();
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
	public List<SurveysType> getSurveysType() {

		Session session = null;

		try {
			session = sessionFactory.openSession();
			return (List<SurveysType>) session.createQuery("FROM SurveysType").getResultList();
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
	
	public SurveysType getSurveyTypeXId(int id) {
		Session session = null;

		try {
			session = sessionFactory.openSession();
			return (SurveysType) session.createQuery("FROM SurveysType WHERE id_survey_type = :id").setParameter("id", id).getSingleResult();
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
	
}
