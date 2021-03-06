package de.uni_stuttgart.est.project.Services;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

/**
 * 
 * @author MikeAshi
 *
 */
public class db {
	private static Session session;
	private static Session testSession;

	/**
	 * 
	 * getnewTestSession load a new database to memory return a new Session every
	 * time
	 * 
	 * @return newSession
	 */
	public static Session getnewTestSession() {
		ServiceRegistry serviceRegistryObj = new StandardServiceRegistryBuilder().configure("hibernatetest.cfg.xml")
				.build();
		Metadata metadata = new MetadataSources(serviceRegistryObj).getMetadataBuilder().build();
		SessionFactory testSessionFactory = metadata.getSessionFactoryBuilder().build();
		testSession = testSessionFactory.openSession();
		return testSession;
	}

	/**
	 * 
	 * getSession connect to database return the same session every time
	 * 
	 * @return Session
	 */
	public static Session getSession() {
		if (session == null) {
			try {
				ServiceRegistry serviceRegistryObj = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml")
						.build();
				Metadata metadata = new MetadataSources(serviceRegistryObj).getMetadataBuilder().build();
				SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
				session = sessionFactory.openSession();
			} catch (Exception e) {
				session = getTestSession();
				System.out.println("Could not Connect to H2 Database !!");
				System.out.println("Running in Test mode !!!");
			}
		}
		return session;
	}

	/**
	 * getTestSession load database to memory return the same Session every time
	 * 
	 * @return Session
	 */
	public static Session getTestSession() {
		if (testSession == null) {
			ServiceRegistry serviceRegistryObj = new StandardServiceRegistryBuilder().configure("hibernatetest.cfg.xml")
					.build();
			Metadata metadata = new MetadataSources(serviceRegistryObj).getMetadataBuilder().build();
			SessionFactory testSessionFactory = metadata.getSessionFactoryBuilder().build();
			testSession = testSessionFactory.openSession();
		}
		return testSession;
	}
}
