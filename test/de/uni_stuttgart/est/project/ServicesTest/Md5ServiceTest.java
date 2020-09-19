package de.uni_stuttgart.est.project.ServicesTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import de.uni_stuttgart.est.project.Services.md5;


public class Md5ServiceTest {

	@Test
	void test_md5_enc() {
		String password = "1234567890";
		String hash ="e807f1fcf82d132f9bb018ca6738a19f";
		assertEquals(hash, md5.enc(password));
	}
	@Test
	void test_md5_enc2() {
		String password = "üצה";
		String hash ="e8920e096dcd2c06275d16490d4131a1";
		assertEquals(hash, md5.enc(password));
	}

}
