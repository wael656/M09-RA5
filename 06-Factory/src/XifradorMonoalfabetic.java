import java.util.*;

public class XifradorMonoalfabetic {
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
        return resultat;
    }

    public String desxifraMonoAlfa(String cadena) {
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
        
    public static void main(String[] args) {
        XifradorMonoalfabetic mono = new XifradorMonoalfabetic();
        mono.permutaAlfabet(mono.alfabet);
        System.out.println("Sortida esperada\n");
        
        for (char c : mono.alfabet) {
            System.out.print(c + " ");
        }
        System.out.println();
        
        for (char c : mono.alfabetPermutat) {
            System.out.print(c + " ");
        }
        System.out.println("\n");

        String[] cadenes = {
            "Test 01 àrbritre, coixí, Perímetre",
            "Test 02 Taüll, DÍA, año",
            "Test 03 Peça, Òrrius, Bòvila"
        };

        String[] cadenesXifrades = new String[cadenes.length];

        System.out.println("Xifratge:\n");
        for (int i = 0; i < cadenes.length; i++) {
            cadenesXifrades[i] = mono.xifraMonoAlfa(cadenes[i]);
            System.out.println(cadenes[i] + " -> " + cadenesXifrades[i]);
        }

        System.out.println("\nDesxifratge:\n");
        for (int i = 0; i < cadenesXifrades.length; i++) {
            String textDesxifrat = mono.desxifraMonoAlfa(cadenesXifrades[i]);
            System.out.println(cadenesXifrades[i] + " -> " + textDesxifrat);
        }
    }
}