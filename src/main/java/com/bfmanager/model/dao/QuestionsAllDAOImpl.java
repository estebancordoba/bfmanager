package com.bfmanager.model.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.bfmanager.model.hibernate.DropDown;
import com.bfmanager.model.hibernate.MultipleChoice;

public class QuestionsAllDAOImpl implements QuestionsAllDAO, Serializable {

	private static final long serialVersionUID = 1L;
	private SessionFactory sessionFactory;
	
	//MultipleChoice
	@Override
	public void saveMultipleChoice(MultipleChoice multipleChoice) {
		Session session = null;
		Transaction tx = null;

		try {

			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.persist(multipleChoice);
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
	public void updateMultipleChoice(MultipleChoice multipleChoice) {
		Session session = null;
		Transaction tx = null;

		try {

			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.update(multipleChoice);
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
	public void removeMultipleChoice(MultipleChoice multipleChoice) {
		Session session = null;
		Transaction tx = null;

		try {

			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			MultipleChoice qu = session.load(MultipleChoice.class, multipleChoice.getIdMultipleChoice());
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
	public MultipleChoice searchMCxId(Long id_multipleChoice) {
		Session session = null;

		try {
			session = sessionFactory.openSession();
			return (MultipleChoice) session.createQuery("FROM MultipleChoice WHERE 	id_multiple_choice = :id_multipleChoice").setParameter("id_multipleChoice", id_multipleChoice).getSingleResult();
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
	public MultipleChoice firstMultipleChoice() {
		Session session = null;

		try {
			session = sessionFactory.openSession();
			return (MultipleChoice) session.createQuery("FROM MultipleChoice").setMaxResults(1).getSingleResult();
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
	public List<MultipleChoice> getMultipleChoice() {
		Session session = null;

		try {
			session = sessionFactory.openSession();
			return (List<MultipleChoice>) session.createQuery("FROM MultipleChoice").getResultList();
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
	public List<MultipleChoice> getMultipleChoiceQuestion(Long id_question) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			return (List<MultipleChoice>) session.createQuery("FROM MultipleChoice WHERE question = :id_question")
					.setParameter("id_question", id_question)
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
	
	//DropDown
	@Override
	public void saveDropDown(DropDown dropDown) {
		Session session = null;
		Transaction tx = null;

		try {

			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.persist(dropDown);
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
	public void updateDropDown(DropDown dropDown) {
		Session session = null;
		Transaction tx = null;

		try {

			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.update(dropDown);
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
	public void removeDropDown(DropDown dropDown) {
		Session session = null;
		Transaction tx = null;

		try {

			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			DropDown qu = session.load(DropDown.class, dropDown.getIdDropDown());
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
	public DropDown searchDDxId(Long id_dropDown) {
		Session session = null;

		try {
			session = sessionFactory.openSession();
			return (DropDown) session.createQuery("FROM DropDown WHERE 	id_drop_down = :id_dropDown").setParameter("id_dropDown", id_dropDown).getSingleResult();
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
	public DropDown firstDropDown() {
		Session session = null;

		try {
			session = sessionFactory.openSession();
			return (DropDown) session.createQuery("FROM DropDown").setMaxResults(1).getSingleResult();
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
	public List<DropDown> getDropDown() {
		Session session = null;

		try {
			session = sessionFactory.openSession();
			return (List<DropDown>) session.createQuery("FROM DropDown").getResultList();
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
	public List<DropDown> getDropDownQuestion(Long id_question) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			return (List<DropDown>) session.createQuery("FROM DropDown WHERE question = :id_question")
					.setParameter("id_question", id_question)
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
