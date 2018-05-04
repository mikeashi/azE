package de.uni_stuttgart.est.project.Services;

import org.apache.commons.codec.digest.DigestUtils;

public class md5 {
	public static String enc(String password) {
		String md5Hex = DigestUtils.md5Hex(password);
		return md5Hex;	
	}
}
