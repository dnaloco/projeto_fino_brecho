package br.arthur.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;

public class PasswordUtil {
	
	public static boolean checkPassword(String pass, String salt, String hash) {
		String hashTest = generateHash(pass, salt);
		
		if (hashTest.equals(hash)) {
			return true;
		}
		
		return false;
	}
	
	public static String generateSalt() {
		return RandomStringUtils.randomAscii(22);
	}
	
	public static String generateHash(String pass, String salt) {
		String hash = DigestUtils.md5Hex(pass + salt);
		
		for(int i = 0; i < 1000; i += 1) {
			hash = DigestUtils.md5Hex(hash);
		}
		
		return hash;
	}
}
