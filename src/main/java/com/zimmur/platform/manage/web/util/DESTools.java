package com.zimmur.platform.manage.web.util;


import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class DESTools {
	static final String Key = "UI25ZIMMUR2016IM";
	public DESTools() {
	}

	private static final String AESTYPE = "AES/ECB/PKCS5Padding";
	/**
	 * 加密
	 * @param plainText
	 * @return
	 */
	public static String AES_Encrypt( String plainText) {
		byte[] encrypt = null;
		try {
			Key key = generateKey(Key);
			Cipher cipher = Cipher.getInstance(AESTYPE);
			cipher.init(Cipher.ENCRYPT_MODE, key);
			encrypt = cipher.doFinal(plainText.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new String(Base64.encodeBase64URLSafeString(encrypt));
	}
	/**
	 * 解密
	 * @param encryptData
	 * @return
	 */
	public static String AES_Decrypt(String encryptData) {
		byte[] decrypt = null;
		try {
			Key key = generateKey(Key);
			Cipher cipher = Cipher.getInstance(AESTYPE);
			cipher.init(Cipher.DECRYPT_MODE, key);
			decrypt = cipher.doFinal(Base64.decodeBase64(encryptData));
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(decrypt == null){
			return null;
		}
		return new String(decrypt).trim();
	}

	private static Key generateKey(String key) throws Exception {
		try {
			SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
			return keySpec;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

	public static void main(String[] args) {

		String plainText = "106585221545454652&&1464424617";
		
		
		String encText = AES_Encrypt(plainText);
		String decString = AES_Decrypt(encText);

		System.out.println(encText);
		System.out.println(decString);

	}

}
