package de.uni_stuttgart.est.project.Services;

import org.apache.commons.codec.digest.DigestUtils;
/**
 * 
 * @author MikeAshi
 *
 */
public class md5 {
	/**
	 * 
	 * @param password
	 * @return String
	 */
	public static String enc(String password) {
		String md5Hex = DigestUtils.md5Hex(password);
		return md5Hex;	
	}
}
