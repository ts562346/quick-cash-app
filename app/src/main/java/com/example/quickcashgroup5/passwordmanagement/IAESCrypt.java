package com.example.quickcashgroup5.passwordmanagement;

/**
 * Interface for the AESCrypt class
 */
public interface IAESCrypt {
    /**
     * Encrypt Strings
     *
     * @param value
     * @return
     * @throws Exception
     */
    String encrypt(String value) throws Exception;

    /**
     * Decrypt Strings
     *
     * @param value
     * @return
     * @throws Exception
     */
    String decrypt(String value) throws Exception;
}
