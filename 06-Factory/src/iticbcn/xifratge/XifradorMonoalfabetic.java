package iticbcn.xifratge;
import java.util.*;

public class XifradorMonoalfabetic implements Xifrador {
    char[] alfabet = "AÀÁBCÇDEÉÈFGHIÍÌÏJKLMNÑOÓÒPQRSTUÚÙÜVWXYZ".toCharArray();
    private char[] alfabetPermutat;

    public XifradorMonoalfabetic() {
        permutaAlfabet(this.alfabet);
    }

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

    @Override 
    public TextXifrat xifra(String cadena, String clau) throws ClauNoSuportada {
        if (clau != null) {
            throw new ClauNoSuportada("Xifratge monoalfabètic no suporta clau");
        }
        
        String resultat = "";

        for (int i = 0; i < cadena.length(); i++) {
            char c = cadena.charAt(i);
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
                        resultat += Character.toLowerCase(alfabetPermutat[j]);
                        break;
                    }
                }
            } else {
                resultat += c;
            }
        }
        return new TextXifrat(resultat.getBytes());
    }

    @Override 
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        if (clau != null) {
            throw new ClauNoSuportada("Xifratge monoalfabètic no suporta clau");
        }

        String cadena = xifrat.toString(); 
        String resultat = "";

        for (int i = 0; i < cadena.length(); i++) {
            char c = cadena.charAt(i);
            
            if (Character.isUpperCase(c)) {
                for (int j = 0; j < alfabetPermutat.length; j++) {
                    if (c == alfabetPermutat[j]) {
                        resultat += alfabet[j];
                        break;
                    }
                }
            }
            else if (Character.isLowerCase(c)) {
                for (int j = 0; j < alfabetPermutat.length; j++) {
                    if (Character.toUpperCase(c) == alfabetPermutat[j]) {
                        resultat += Character.toLowerCase(alfabet[j]);
                        break;
                    }
                }
            } else {
                resultat += c;
            }
        }
        return resultat;
    }
}