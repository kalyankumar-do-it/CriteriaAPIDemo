package com.techjs;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import com.techjs.entity.Accounts;

public class Demo
{
	public static void main(String[] args)
	{
		Accounts acc = new Accounts();
		//acc.setAccNo(1001);
		acc.setAccName("Kalyan");
		
		Configuration config = new Configuration().configure();
		config.addAnnotatedClass(Accounts.class);
		
		SessionFactory sf = config.buildSessionFactory();
		Session session = sf.openSession();
		
		Transaction ts = session.beginTransaction();
		//session.save(acc);
		
		EntityManagerFactory entityManagerFactory = session.getEntityManagerFactory();
		EntityManager entityManager = entityManagerFactory.createEntityManager();
	    
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Accounts> cq = cb.createQuery(Accounts.class);
	
	
		Root<Accounts> from = cq.from(Accounts.class);
		cq.select(from);
	
		TypedQuery<Accounts> q = entityManager.createQuery(cq);
		List<Accounts> allitems = q.getResultList();
		
		for (Accounts account : allitems)
		{
			System.out.println("Acc No :"+account.getAccNo()+" Acc Name :"+account.getAccName());
		}
//		
//		
//		Criteria criteria = session.createCriteria(Accounts.class);
//		criteria.add(Restrictions.eq("accName","Sandeep"));
//		
//		List<Accounts> accounts = (List<Accounts>)criteria.list();
//		
//		for (Accounts account : accounts) 
//		{
//			System.out.println(account.getAccNo()+" "+account.getAccName());
//		}
		
		ts.commit();
		
        session.close();
        sf.close();
        
	}
}
