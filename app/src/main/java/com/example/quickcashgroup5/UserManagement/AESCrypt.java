package com.example.quickcashgroup5.UserManagement;

import android.util.Base64;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * Class for encrypting the password
 */

public class AESCrypt {
    private static final String ALGORITHM = "AES";
    private static final String KEY = "1Hbfh667adfDEJ78";

    /**
     * Method that encrypts the password
     *
     * @param value
     * @return
     * @throws Exception
     */
    public static String encrypt(String value) throws Exception {
        Key key = generateKey();
        Cipher cipher = Cipher.getInstance(AESCrypt.ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedByteValue = cipher.doFinal(value.getBytes("utf-8"));
        String encryptedValue64 = Base64.encodeToString(encryptedByteValue, Base64.DEFAULT);
        return encryptedValue64;

    }

    /**
     * Method that decrypts the password
     *
     * @param value
     * @return
     * @throws Exception
     */
    public static String decrypt(String value) throws Exception {
        Key key = generateKey();
        Cipher cipher = Cipher.getInstance(AESCrypt.ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decryptedValue64 = Base64.decode(value, Base64.DEFAULT);
        byte[] decryptedByteValue = cipher.doFinal(decryptedValue64);
        String decryptedValue = new String(decryptedByteValue, "utf-8");
        return decryptedValue;

    }

    /**
     * Method that generates key for encryption
     *
     * @return
     * @throws Exception
     */
    private static Key generateKey() throws Exception {
        Key key = new SecretKeySpec(AESCrypt.KEY.getBytes(), AESCrypt.ALGORITHM);
        return key;
    }
}