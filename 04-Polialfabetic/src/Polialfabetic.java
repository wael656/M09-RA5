import java.util.*;

public class Polialfabetic {
    static char[] alfabet = "AÀÁBCÇDEÉÈFGHIÍÌÏJKLMNÑOÓÒPQRSTUÚÙÜVWXYZ".toCharArray();
    static char[] alfabetPermutat;
    private static int clauSecreta = 7876545;
    static Random generador;

    public static void initRandom(int clau) {
        generador = new Random(clau);
    }

    public static void permutaAlfabet() {
        List <Character> llistaAlfabet = new ArrayList<>();
        
        for (char c : alfabet) {
            llistaAlfabet.add(c);
        }
        Collections.shuffle(llistaAlfabet, generador);
         
        alfabetPermutat = new char[alfabet.length];

        for (int i = 0; i < alfabet.length; i++) {
            alfabetPermutat[i] = llistaAlfabet.get(i);
        }
    }

    public static String xifraPoliAlfa(String msg) {
        String resultat ="";

        for (int i = 0; i < msg.length(); i++) {
            char c = msg.charAt(i);
            permutaAlfabet();
            if (Character.isUpperCase(c)) {
                for (int j = 0; j < alfabet.length; j++) {
                    if (c == alfabet[j]) {
                        resultat += alfabetPermutat[j];
                        break;
                    }
                }
            } 
            else if (Character.isLowerCase(c)) {
                for (int j = 0; j < alfabet.length; j++) {
                    if (Character.toUpperCase(c) == alfabet[j]) {
                        resultat+=Character.toLowerCase(alfabetPermutat[j]);
                        break;
                    }
                }
            } else {
                resultat+=c;
            }
        }
        return resultat;
    }

     public static String desxifraPoliAlfa(String msgXifrat) {
        String resultat = "";;

        for (int i = 0; i < msgXifrat.length(); i++) {
            char c = msgXifrat.charAt(i);
            permutaAlfabet();
            if (Character.isUpperCase(c)) {
                for (int j = 0; j < alfabetPermutat.length; j++) {
                    if (c == alfabetPermutat[j]) {
                        resultat+=alfabet[j];
                        break;
                    }
                }
            }
            else if (Character.isLowerCase(c)) {
                for (int j = 0; j < alfabetPermutat.length; j++) {
                    if (Character.toUpperCase(c) == alfabetPermutat[j]) {
                        resultat+=Character.toLowerCase(alfabet[j]);
                        break;
                    }
                }
            } else {
                resultat+=c;
            }
        }
        return resultat;
    }
    
    
    public static void main(String[] args) {

        String msgs[] = {"Test 01 àrbritre, coixí, Perímetre",
                "Test 02 Taüll, DÍA, año", 
                "Test 03 Peça, Òrrius, Bòvila"};
        String msgsXifrats[] = new String[msgs.length];

        System.out.println("Xifratge:\n--------");
        for (int i = 0; i < msgs.length; i++) {
            initRandom(clauSecreta);
            msgsXifrats[i] = xifraPoliAlfa(msgs[i]);
            System.out.printf("%-34s -> %s%n", msgs[i], msgsXifrats[i]);
        }

        System.out.println("Desxifratge:\n--------");
        for (int i = 0; i < msgs.length; i++) {
            initRandom(clauSecreta);
            String msg = desxifraPoliAlfa(msgsXifrats[i]);
            System.out.printf("%-34s -> %s%n", msgsXifrats[i], msg);
        }

    }
    
}
