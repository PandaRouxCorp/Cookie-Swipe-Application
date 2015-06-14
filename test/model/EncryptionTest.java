package model;

import org.junit.Test;

public class EncryptionTest {
	
	@Test
	public void encryptTest() {
		Encryption e = new Encryption();
		try {
			System.out.println(e.encrypt("panda"));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
