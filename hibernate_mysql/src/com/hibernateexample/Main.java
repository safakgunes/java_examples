package com.hibernateexample;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class Main {

	public static void main(String[] args) {

		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Employees.class)
				.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			
			//Create Query
			/*
			 * List<Employees> emps =
			 * session.createQuery("from Employees c where c.gender='M'  ").getResultList();
			 * 
			 * for (Employees employees : emps) {
			 * System.out.println(employees.getFirstName() + " " + employees.getLastName());
			 * }
			 */
			
			//Insert
//			Employees employee = new Employees();
//			employee.setFirstName("Safak");
//			employee.setLastName("Gunes");
//			employee.setBirthDate("1998-05-27");
//			employee.setGender("M");
//			employee.setHireDate("2020-12-17");
//	        session.save(employee);
			
			
			
			//Update
//			
//			Employees employee = session.get(Employees.class, 0) ;
//			System.out.println(employee.getFirstName());
//			
			
			//Delete
//	
//			Employees employee = session.get(Employees.class, 0) ;
//			session.delete(employee);
			
			session.getTransaction().commit();
			
			
			
		}
			
		finally {
				factory.close();
			}
		}
	


		
	}


