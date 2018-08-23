package com.bfmanager.model.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.bfmanager.model.hibernate.Groups;
import com.bfmanager.model.hibernate.SurveyGroup;
import com.bfmanager.model.hibernate.UserGroup;

public class GroupsDAOImpl implements GroupsDAO, Serializable {

	private static final long serialVersionUID = 1L;
	private SessionFactory sessionFactory;
	
	//Groups
	@Override
	public void saveGroup(Groups group) {
		Session session = null;
		Transaction tx = null;

		try {

			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.persist(group);
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
	public void updateGroup(Groups group) {
		Session session = null;
		Transaction tx = null;

		try {

			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.update(group);
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
	public void removeGroup(Groups group) {
		Session session = null;
		Transaction tx = null;

		try {

			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			Groups aw = session.load(Groups.class, group.getIdGroup());
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
	public Groups searchGroupxId(Integer id_group) {
		Session session = null;

		try {
			session = sessionFactory.openSession();
			return (Groups) session.createQuery("FROM Groups WHERE id_group = :id_group").setParameter("id_group", id_group).getSingleResult();
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
	public Groups firstGroup() {
		Session session = null;

		try {
			session = sessionFactory.openSession();
			return (Groups) session.createQuery("FROM Groups").setMaxResults(1).getSingleResult();			
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
	public List<Groups> getGroups() {
		Session session = null;

		try {
			session = sessionFactory.openSession();
			return (List<Groups>) session.createQuery("FROM Groups").getResultList();
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
	
	//SurveyGroup
	@Override
	public void saveSurveyGroup(SurveyGroup survey_group) {
		Session session = null;
		Transaction tx = null;

		try {

			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.persist(survey_group);
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
	public void updateSurveyGroup(SurveyGroup survey_group) {
		Session session = null;
		Transaction tx = null;

		try {

			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.update(survey_group);
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
	public void removeSurveyGroup(SurveyGroup survey_group) {
		Session session = null;
		Transaction tx = null;

		try {

			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			SurveyGroup aw = session.load(SurveyGroup.class, survey_group.getIdSurveyGroup());
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
	public SurveyGroup searchSurveyGroupxId(Integer id_survey_group) {
		Session session = null;

		try {
			session = sessionFactory.openSession();
			return (SurveyGroup) session.createQuery("FROM SurveyGroup WHERE id_survey_group = :id_survey_group").setParameter("id_survey_group", id_survey_group).getSingleResult();
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
	public List<SurveyGroup> getSurveyGroup() {
		Session session = null;

		try {
			session = sessionFactory.openSession();
			return (List<SurveyGroup>) session.createQuery("FROM SurveyGroup").getResultList();
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
	public List<SurveyGroup> getSurveyGroupsxIdSurvey(Integer id_survey) {
		Session session = null;

		try {
			session = sessionFactory.openSession();
			return (List<SurveyGroup>) session.createQuery("FROM SurveyGroup WHERE id_survey=:id_survey")					
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
	public List<SurveyGroup> getSurveyGroupxLstGroups(List<Integer> lstGroups){
		Session session = null;

		try {
			session = sessionFactory.openSession();
			return (List<SurveyGroup>) session.createQuery("FROM SurveyGroup WHERE id_group IN (:lstGroups)")					
					.setParameterList("lstGroups", lstGroups)
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
	
	//UserGroup
	@Override
	public void saveUserGroup(UserGroup user_group) {
		Session session = null;
		Transaction tx = null;

		try {

			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.persist(user_group);
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
	public void updateUserGroup(UserGroup user_group) {
		Session session = null;
		Transaction tx = null;

		try {

			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.update(user_group);
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
	public void removeUserGroup(UserGroup user_group) {
		Session session = null;
		Transaction tx = null;

		try {

			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			UserGroup aw = session.load(UserGroup.class, user_group.getIdUserGroup());
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
	public UserGroup searchUserGroupxId(Integer id_user_group) {
		Session session = null;

		try {
			session = sessionFactory.openSession();
			return (UserGroup) session.createQuery("FROM UserGroup WHERE id_user_group = :id_user_group").setParameter("id_user_group", id_user_group).getSingleResult();
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
	public List<UserGroup> getUserGroup() {
		Session session = null;

		try {
			session = sessionFactory.openSession();
			return (List<UserGroup>) session.createQuery("FROM UserGroup").getResultList();
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
	public List<UserGroup> getUserGroupxIdGroup(Integer id_group) {
		Session session = null;

		try {
			session = sessionFactory.openSession();
			return (List<UserGroup>) session.createQuery("FROM UserGroup WHERE id_group=:id_group")
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
	public List<UserGroup> getUserGroupxIdUser(Integer id_user) {
		Session session = null;

		try {
			session = sessionFactory.openSession();
			return (List<UserGroup>) session.createQuery("FROM UserGroup WHERE id_user=:id_user")
					.setParameter("id_user", id_user)
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