package de.uni_stuttgart.est.project.Controllers;

import org.hibernate.Session;

import de.uni_stuttgart.est.project.Services.db;
import de.uni_stuttgart.est.project.Services.md5;
import de.uni_stuttgart.est.project.models.User;

public class LoginSystemController {
	public static User currentUser = null;
	public static Session session = db.getSession();

	public static String login(String username, String password) {
		session.beginTransaction();
		try {
			User user = session.get(User.class, username);
			if (md5.enc(password).equals(user.getPassword())) {
				session.getTransaction().commit();
				currentUser = user;
				return user.getUserName();
			}
		} catch (Exception e) {
			session.getTransaction().commit();
			return null;
		}
		session.getTransaction().commit();
		return null;
	}
}
