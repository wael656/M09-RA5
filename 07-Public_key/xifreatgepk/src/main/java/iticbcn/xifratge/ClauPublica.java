package iticbcn.xifratge;

import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;

public class ClauPublica {

    public KeyPair generaParellClausRSA() throws Exception {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        return keyGen.generateKeyPair();
    }

    public byte[] xifraRSA(String msg, PublicKey clauPublica) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, clauPublica);

        byte[] msgBytes = msg.getBytes("UTF-8");
        return cipher.doFinal(msgBytes);
    }

    public String desxifraRSA(byte[] msgXifrat, PrivateKey ClauPrivada) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, ClauPrivada);

        byte[] bytesDesxifrats = cipher.doFinal(msgXifrat);

        return new String(bytesDesxifrats, StandardCharsets.UTF_8);
    }
}
