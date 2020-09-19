package de.uni_stuttgart.est.project.ControllersTest;

import static org.junit.Assert.assertEquals;

import org.hibernate.Session;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import de.uni_stuttgart.est.project.Controllers.LoginSystemController;
import de.uni_stuttgart.est.project.Services.db;
import de.uni_stuttgart.est.project.models.Repositorys.UsersRepository;

class LoginControllerTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		LoginSystemController.session = db.getTestSession();
		UsersRepository.session = db.getTestSession();
		UsersRepository.add(0,"testusername","testfirstname","testlastname","pass");
		UsersRepository.add(1,"admin","adminFirstname","adminLastname","12345");
 		Session session = LoginSystemController.session;
 		session.beginTransaction();
 		session.getTransaction().commit();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		LoginSystemController.session = db.getSession();
		UsersRepository.session = db.getSession();
	}
	@Test
	void test_Login() {
		String Username = LoginSystemController.login("testusername", "pass");
		assertEquals("testusername",Username);
		String Username2 = LoginSystemController.login("testusername", "notTheCorrectpass");
		assertEquals(null,Username2);
		String Username3 = LoginSystemController.login("notauser", "notTheCorrectpass");
		assertEquals(null,Username3);
	}
	@Test
	void test_isAdmin() {
		LoginSystemController.login("testusername", "pass");
		assertEquals(false,LoginSystemController.currentUser.isAdmin());
		LoginSystemController.login("admin", "12345");
		assertEquals(true,LoginSystemController.currentUser.isAdmin());
	}
	
}
