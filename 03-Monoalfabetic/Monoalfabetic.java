import java.util.*;

public class Monoalfabetic {
    char[] alfabet = "AÀÁBCÇDEÉÈFGHIÍÌÏJKLMNÑOÓÒPQRSTUÚÙÜVWXYZ".toCharArray();
    private char[] alfabetPermutat;

    public char[] permutaAlfabet(char[] alfabet) {
        List <Character> llistaAlfabet = new ArrayList<>();

        for (char c : alfabet) {
            llistaAlfabet.add(c);
        }
        Collections.shuffle(llistaAlfabet);
        
        char[] permutat = new char[alfabet.length];

        for (int i = 0; i < llistaAlfabet.size(); i++) {
            char c = llistaAlfabet.get(i);
            permutat[i] = c;
        }
        this.alfabetPermutat = permutat;
        return permutat;
    }

    public String xifraMonoAlfa(String cadena) {
        String resultat = "";

        for (int i = 0; i < cadena.length(); i++) {
            char c = cadena.charAt(i);
            if (Character.isUpperCase(c)) {
                for (int j = 0; j < alfabet.length; j++) {
                    if (c == alfabet[j]) {
                        resultat+=alfabetPermutat[j];
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
    
    public static void main(String[] args) {

    }
}
