package com.example.test.gitplugin;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;

class DesEncrypter {
    private Cipher _dcipher;
    private Cipher _ecipher;

    public DesEncrypter(SecretKey key) throws Exception {
        try {
            String des = "DES";
            _ecipher = Cipher.getInstance(des);
            _dcipher = Cipher.getInstance(des);

            _ecipher.init(Cipher.ENCRYPT_MODE, key);
            _dcipher.init(Cipher.DECRYPT_MODE, key);

        } catch (javax.crypto.NoSuchPaddingException e) {
            throw new Exception(e);
        } catch (java.security.NoSuchAlgorithmException e) {
            throw new Exception(e);
        } catch (java.security.InvalidKeyException e) {
            throw new Exception(e);
        }
    }

    public String decrypt(String str) throws Exception {
        try {
            byte[] dec = Base64.decodeBase64(str.getBytes("ASCII"));
            byte[] utf8 = _dcipher.doFinal(dec);

            return new String(utf8, "UTF8");

        } catch (javax.crypto.BadPaddingException e) {
            throw new Exception(e);
        } catch (IllegalBlockSizeException e) {
            throw new Exception(e);
        } catch (UnsupportedEncodingException e) {
            throw new Exception(e);
        }
    }

    public String encrypt(String str) throws Exception {
        try {
            byte[] utf8 = str.getBytes("UTF8");
            byte[] enc = _ecipher.doFinal(utf8);

            return new String(Base64.encodeBase64(enc), "ASCII");

        } catch (javax.crypto.BadPaddingException e) {
            throw new Exception(e);
        } catch (IllegalBlockSizeException e) {
            throw new Exception(e);
        } catch (UnsupportedEncodingException e) {
            throw new Exception(e);
        }
    }

}


public class DecrypterTest {
    private static final SecretKey GL_SECRET_KEY = new SecretKeySpec(new byte[] { 10, 20, 30, 40, 50, 60, 70, 80 },
            "DES");

    public static String decrypt(String value) throws Exception {
        DesEncrypter desEncrypter = new DesEncrypter(GL_SECRET_KEY);
        return desEncrypter.decrypt(value);
    }

    public static String encrypt(String value) throws Exception {
        DesEncrypter desEncrypter = new DesEncrypter(GL_SECRET_KEY);
        return desEncrypter.encrypt(value);
    }

    public static void main(String[] args) throws Exception {

//        String decrypted = decrypt("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");
//        System.out.println(decrypted);

        String encrypted = encrypt("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");
        System.out.println(encrypted);
    }
}
