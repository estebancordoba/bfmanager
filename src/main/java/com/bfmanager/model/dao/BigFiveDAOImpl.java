package com.bfmanager.model.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.bfmanager.model.hibernate.BfDimensions;
import com.bfmanager.model.hibernate.BfNormalization;
import com.bfmanager.model.hibernate.BfResults;
import com.bfmanager.model.hibernate.BfValueQuestions;
import com.bfmanager.model.hibernate.Questions;

public class BigFiveDAOImpl implements BigFiveDAO, Serializable {

	private static final long serialVersionUID = 1L;
	private SessionFactory sessionFactory;
	
	//Dimensions
	@Override
	public void saveBfDimension(BfDimensions bf_dimension) {
		Session session = null;
		Transaction tx = null;

		try {

			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.persist(bf_dimension);
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
	public void updateBfDimension(BfDimensions bf_dimension) {
		Session session = null;
		Transaction tx = null;

		try {

			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.update(bf_dimension);
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
	public void removeBfDimension(BfDimensions bf_dimension) {
		Session session = null;
		Transaction tx = null;

		try {

			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			BfDimensions rmv = session.load(BfDimensions.class, bf_dimension.getIdBfDimension());
			session.delete(rmv);
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
	public BfDimensions searchBfDimensionxId(Integer id) {
		Session session = null;

		try {
			session = sessionFactory.openSession();
			return (BfDimensions) session.createQuery("FROM BfDimensions WHERE id_bf_dimension = :id").setParameter("id", id).getSingleResult();
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
	public BfDimensions firstDimension() {
		Session session = null;

		try {
			session = sessionFactory.openSession();
			return (BfDimensions) session.createQuery("FROM BfDimensions").setMaxResults(1).getSingleResult();
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
	public List<BfDimensions> getBfDimensions() {
		Session session = null;

		try {
			session = sessionFactory.openSession();
			return (List<BfDimensions>) session.createQuery("FROM BfDimensions").getResultList();
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
	public List<BfDimensions> getDimensionsxIdSurvey(Integer id_survey) {
		Session session = null;

		try {
			session = sessionFactory.openSession();
			return (List<BfDimensions>) session.createQuery("FROM BfDimensions WHERE id_survey=:id_survey")
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
	
	//Normalization
	@Override
	public void saveBfNormalization(BfNormalization bf_normalization) {
		Session session = null;
		Transaction tx = null;

		try {

			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.persist(bf_normalization);
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
	public void updateBfNormalization(BfNormalization bf_normalization) {
		Session session = null;
		Transaction tx = null;

		try {

			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.update(bf_normalization);
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
	public void removeBfNormalization(BfNormalization bf_normalization) {
		Session session = null;
		Transaction tx = null;

		try {

			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			BfNormalization rmv = session.load(BfNormalization.class, bf_normalization.getIdBfNormalization());
			session.delete(rmv);
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
	public BfNormalization searchBfNormalizationxId(Integer id) {
		Session session = null;

		try {
			session = sessionFactory.openSession();
			return (BfNormalization) session.createQuery("FROM BfNormalization WHERE id_bf_normalization = :id").setParameter("id", id).getSingleResult();
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
	public BfNormalization searchBfNormalizationxIdSurvey(Integer id_survey) {
		Session session = null;

		try {
			session = sessionFactory.openSession();
			return (BfNormalization) session.createQuery("FROM BfNormalization WHERE id_survey = :id")
					.setParameter("id", id_survey)
					.getSingleResult();
		} catch (Exception e) {
			return null;
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
				session = null;
			}
		}
	}
	
	@Override
	public BfNormalization getBfNormalizationxIdSurvey(Integer id) {
		Session session = null;

		try {
			session = sessionFactory.openSession();
			return (BfNormalization) session.createQuery("FROM BfNormalization WHERE id_survey = :id").setParameter("id", id).getSingleResult();
		} catch (Exception e) {
			//e.printStackTrace();
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
	public List<BfNormalization> getBfNormalization() {
		Session session = null;

		try {
			session = sessionFactory.openSession();
			return (List<BfNormalization>) session.createQuery("FROM BfNormalization").getResultList();
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
	
	//ValueQuestions
	@Override
	public void saveBfValueQuestion(BfValueQuestions bf_value_question) {
		Session session = null;
		Transaction tx = null;

		try {

			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.persist(bf_value_question);
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
	public void updateBfValueQuestion(BfValueQuestions bf_value_question) {
		Session session = null;
		Transaction tx = null;

		try {

			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.update(bf_value_question);
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
	public void removeBfValueQuestion(BfValueQuestions bf_value_question) {
		Session session = null;
		Transaction tx = null;

		try {

			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			BfValueQuestions rmv = session.load(BfValueQuestions.class, bf_value_question.getIdBfValueQuestion());
			session.delete(rmv);
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
	public BfValueQuestions searchBfValueQuestionxId(Integer id) {
		Session session = null;

		try {
			session = sessionFactory.openSession();
			return (BfValueQuestions) session.createQuery("FROM BfValueQuestions WHERE id_bf_value_question = :id").setParameter("id", id).getSingleResult();
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
	public BfValueQuestions firstValueQuestion() {
		Session session = null;

		try {
			session = sessionFactory.openSession();
			return (BfValueQuestions) session.createQuery("FROM BfValueQuestions").setMaxResults(1).getSingleResult();
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
	public List<BfValueQuestions> getBfValueQuestions() {
		Session session = null;

		try {
			session = sessionFactory.openSession();
			return (List<BfValueQuestions>) session.createQuery("FROM BfValueQuestions").getResultList();
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
	public List<BfValueQuestions> getValueQuestionsxIdDimension(Integer id_dimension) {
		Session session = null;

		try {
			session = sessionFactory.openSession();
			return (List<BfValueQuestions>) session.createQuery("FROM BfValueQuestions WHERE id_bf_dimension=:id_dimension")
					.setParameter("id_dimension", id_dimension)
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
	public List<Questions> getQuestionsxIdSurvey(Integer id_survey) {
		Session session = null;

		try {
			session = sessionFactory.openSession();
			return (List<Questions>) session.createQuery("FROM Questions WHERE survey=:id_survey AND type=4")
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
	
	//Results
	@Override
	public void saveBfResult(BfResults bf_result) {
		Session session = null;
		Transaction tx = null;

		try {

			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.persist(bf_result);
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
	public void updateBfResult(BfResults bf_result) {
		Session session = null;
		Transaction tx = null;

		try {

			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.update(bf_result);
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
	public void removeBfResult(BfResults bf_result) {
		Session session = null;
		Transaction tx = null;

		try {

			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			BfResults rmv = session.load(BfResults.class, bf_result.getIdBfResult());
			session.delete(rmv);
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
	public BfResults searchBfResultxId(Integer id) {
		Session session = null;

		try {
			session = sessionFactory.openSession();
			return (BfResults) session.createQuery("FROM BfResults WHERE id_bf_result = :id").setParameter("id", id).getSingleResult();
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
	public List<BfResults> getBfResults() {
		Session session = null;

		try {
			session = sessionFactory.openSession();
			return (List<BfResults>) session.createQuery("FROM BfResults").getResultList();
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
	public List<BfResults> getBfResultsxIdSurveyIdGroup(Integer id_survey,Integer id_group){
		Session session = null;

		try {
			session = sessionFactory.openSession();
			return (List<BfResults>) session.createQuery("FROM BfResults WHERE id_survey=:id_survey AND id_group=:id_group")
					.setParameter("id_survey", id_survey)					
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
	public List<BfResults> getBfResultsxIdUserIdGroup(Integer id_user, Integer id_survey, Integer id_group){
		Session session = null;

		try {
			session = sessionFactory.openSession();
			return (List<BfResults>) session.createQuery("FROM BfResults WHERE id_user=:id_user AND id_survey=:id_survey AND id_group=:id_group")
					.setParameter("id_user", id_user)					
					.setParameter("id_survey", id_survey)
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

	@Override
	public BfResults getBfResultxIdUserIdDimIdGroup(Integer id_user, Integer id_dim, Integer id_group){
		Session session = null;

		try {
			session = sessionFactory.openSession();
			return (BfResults) session.createQuery("FROM BfResults WHERE id_user=:id_user AND id_bf_dimension=:id_dim AND id_group=:id_group")
					.setParameter("id_user", id_user)					
					.setParameter("id_dim", id_dim)
					.setParameter("id_group", id_group)
					.getSingleResult();
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
	
	//Otros
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}