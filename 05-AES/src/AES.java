package src;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AES {
    public static final String ALGORISME_XIFRAT = "AES";
    public static final String ALGORISME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";

    private static final int MIDA_IV = 16;
    private static byte[] iv = new byte[MIDA_IV];
    private static final String CLAU = "374647sj";

    public static IvParameterSpec generaIV() {
        byte[] iv = new byte[MIDA_IV];
        new SecureRandom().nextBytes(iv);
        return new IvParameterSpec(iv);
    }

    public static IvParameterSpec extreureIV(byte[] paquetXifrat) {
        byte[] iv = Arrays.copyOfRange(paquetXifrat, 0, MIDA_IV);
        return new IvParameterSpec(iv);
    }



    public static byte[] xifraAES(String msg, String clau) throws Exception {
        byte[] bytes = msg.getBytes(StandardCharsets.UTF_8);
        IvParameterSpec ivSpec = generaIV();

        MessageDigest md = MessageDigest.getInstance(ALGORISME_HASH);
        byte[] clauHash = md.digest(clau.getBytes(StandardCharsets.UTF_8));
        byte[] clau16Bytes = Arrays.copyOf(clauHash, MIDA_IV);
        
        //Crear clau AES
        SecretKeySpec secretKey = new SecretKeySpec(clau16Bytes, ALGORISME_XIFRAT);

        //Inicialitzar el Cipher i encriptar
        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec);
        byte[] msgXifrat = cipher.doFinal(bytes);

        //empaquetar el bytes i els bytes xifrats
        byte[] paquetFinal = new byte[MIDA_IV + msgXifrat.length];
        System.arraycopy(ivSpec.getIV(), 0, paquetFinal, 0, MIDA_IV);
        System.arraycopy(msgXifrat, 0, paquetFinal, MIDA_IV, msgXifrat.length);

        return paquetFinal;
    }

    public static String desxifraAES(byte[] bMsgXifrat, String clau) throws Exception {
        IvParameterSpec ivSpec = extreureIV(bMsgXifrat);
        byte[] msgXifrat = Arrays.copyOfRange(bMsgXifrat, MIDA_IV, bMsgXifrat.length);
        
        MessageDigest md = MessageDigest.getInstance(ALGORISME_HASH);
        byte[] clauHash = md.digest(clau.getBytes(StandardCharsets.UTF_8));
        byte[] clau16Bytes = Arrays.copyOf(clauHash, MIDA_IV);

        SecretKeySpec secretKey = new SecretKeySpec(clau16Bytes, ALGORISME_XIFRAT);

        //configurar el cipher en mode desencriptar (DECRYPT_MODE)
        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);
        
        //Desxifrar els bytes
        byte[] bytesDesxifrats = cipher.doFinal(msgXifrat);

        return new String(bytesDesxifrats, StandardCharsets.UTF_8);
    }

    public static void main(String[] args) {
        String msgs[] = {"Lorem ipsum dicet",
            "Hola Andrés cómo está tu cuñado",
            "Àgora ïlla Ôtto"};

        for (int i = 0; i < msgs.length; i++) {
            String msg = msgs[i];

            byte[] bXifrats = null;
            String desxifrat = "";
            
            try {
                bXifrats = xifraAES (msg, CLAU);
                desxifrat = desxifraAES(bXifrats, CLAU);
            } catch (Exception e) {
                System.err.println("Error de xifrat: " + e.getLocalizedMessage());
            }
            System.out.println("------------------");
            System.out.println("Msg: " + msg);
            System.out.println("Enc: " + new String(bXifrats));
            System.out.println("DEC: " + desxifrat);
        }
    }
}
