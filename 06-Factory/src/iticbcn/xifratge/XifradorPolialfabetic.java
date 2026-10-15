package iticbcn.xifratge;

import java.util.*;

public class XifradorPolialfabetic implements Xifrador {
    char[] alfabet = "AÀÁBCÇDEÉÈFGHIÍÌÏJKLMNÑOÓÒPQRSTUÚÙÜVWXYZ".toCharArray();
    char[] alfabetPermutat;
    Random generador;

    public void initRandom(long clau) {
        generador = new Random(clau);
    }

    public void permutaAlfabet() {
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

    @Override 
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        long clauLong;
        try {
            clauLong = Long.parseLong(clau);
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("La clau per xifrat Polialfabètic ha de ser un String convertible a long");
        }
        
        initRandom(clauLong);
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
        return new TextXifrat(resultat.getBytes());
    }

    @Override 
     public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        long clauLong;
        try {
            clauLong = Long.parseLong(clau);
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("La clau per xifrat Polialfabètic ha de ser un String convertible a long");
        }

        initRandom(clauLong);
        String msgXifrat = xifrat.toString();
        String resultat = "";

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
}