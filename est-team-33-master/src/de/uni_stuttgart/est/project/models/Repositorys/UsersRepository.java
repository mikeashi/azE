package de.uni_stuttgart.est.project.models.Repositorys;

import java.util.List;

import org.hibernate.Session;

import de.uni_stuttgart.est.project.Services.db;
import de.uni_stuttgart.est.project.Services.md5;
import de.uni_stuttgart.est.project.models.User;

/**
 * 
 * @author MikeAshi
 *
 */
public class UsersRepository {
	public static Session session = db.getSession();

	/**
	 * 
	 * @param accessLevel
	 * @param userName
	 * @param firstName
	 * @param lastName
	 * @param password
	 */
	public static void add(int accessLevel, String userName, String firstName, String lastName, String password) {
		session.beginTransaction();
		// TODO can not add new user with userName that is already used
		User user = new User(accessLevel, userName, firstName, lastName, md5.enc(password));
		session.save(user);
		session.getTransaction().commit();
	}

	/**
	 * 
	 * @param user
	 */
	public static void add(User user) {
		session.beginTransaction();
		session.save(user);
		session.getTransaction().commit();
	}

	/**
	 * 
	 * @return List<User>
	 */
	public static List<User> getAll() {
		session.beginTransaction();
		List<User> users = session
				.createNativeQuery("Select * from users", de.uni_stuttgart.est.project.models.User.class)
				.getResultList();
		session.getTransaction().commit();
		return users;
	}
}
