package de.uni_stuttgart.est.project.RepositorysTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.hibernate.Session;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import de.uni_stuttgart.est.project.Services.db;
import de.uni_stuttgart.est.project.Services.md5;
import de.uni_stuttgart.est.project.models.User;
import de.uni_stuttgart.est.project.models.Repositorys.UsersRepository;

class UserRepositoryTest {
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		UsersRepository.session = db.getnewTestSession();	
	}
	@BeforeEach
	void setUp() throws Exception {
		UsersRepository.session = db.getnewTestSession();	
	}
	
	@Test
	void test_addUser() {	
		UsersRepository.add(0,"testusername","testfirstname","testlastname","pass");
 		Session session = UsersRepository.session;
 		session.beginTransaction();
 		session.getTransaction().commit();
 		assertEquals("testusername",session.get(User.class, "testusername").getUserName());
 		assertEquals("testfirstname",session.get(User.class, "testusername").getFirstName());
 		assertEquals("testlastname",session.get(User.class, "testusername").getLastName());
 		assertEquals("1a1dc91c907325c69271ddf0c944bc72",session.get(User.class, "testusername").getPassword());
 		 		
	}
	@Test
	void test_addUserFromObj() {
		User user = new User();
		user.setAccessLevel(0);
		user.setFirstName("testfirstname");
		user.setLastName("testlastname");
		user.setPassword(md5.enc("pass"));
		user.setUserName("testusername");
		UsersRepository.add(user);
 		Session session = UsersRepository.session;
 		session.beginTransaction();
 		session.getTransaction().commit();
 		assertEquals("testusername",session.get(User.class, "testusername").getUserName());
 		assertEquals("1a1dc91c907325c69271ddf0c944bc72",session.get(User.class, "testusername").getPassword());	
	}
	@Test
	void test_getAll() {
		UsersRepository.session = db.getnewTestSession();	
		UsersRepository.add(0,"testusername","testfirstname","testlastname","pass");		
		List<User> users = UsersRepository.getAll();
 		assertEquals("testusername",users.get(0).getUserName());
 		assertEquals("1a1dc91c907325c69271ddf0c944bc72",users.get(0).getPassword());	
	}
	
	
	@AfterAll
	static void tearDownAfterClass() throws Exception {
		UsersRepository.session = db.getSession();
	}
}
