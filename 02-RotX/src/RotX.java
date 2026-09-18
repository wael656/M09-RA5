public class RotX {
    char[] minuscules = {'a', 'á', 'à', 'b', 'c', 'ç', 'd', 'e', 'é', 'è', 'f', 'g', 'h', 'i', 'í', 'ì', 'ï', 'j', 'k', 'l', 'm', 'n', 'ñ', 'o', 'ó', 'ò', 'p', 'q', 'r', 's', 't', 'u', 'ú', 'ù', 'ü', 'v', 'w', 'x', 'y', 'z'};
    char[] majuscules = {'A', 'Á', 'À', 'B', 'C', 'Ç', 'D', 'E', 'É', 'È', 'F', 'G', 'H', 'I', 'Í', 'Ì', 'Ï', 'J', 'K', 'L', 'M', 'N', 'Ñ', 'O', 'Ó', 'Ò', 'P', 'Q', 'R', 'S', 'T', 'U', 'Ú', 'Ù', 'Ü', 'V', 'W', 'X', 'Y', 'Z'};
    public static void main(String[] args) {
        RotX rot = new RotX();
        
        int[] desplacaments = {0, 2, 4, 6};
        String[] cadenesXifrar = {"ABC", "XYZ", "Hola, Mr. calçot", "Perdó, per tu què és?"};
        String[] cadenesDesxifrar = {"ABC", "ZAÁ", "Ïqoc, Óú. écoèqü", "Úiüht, úiü wx ùxì ív?"};
        System.out.println("Xifrat\n-------");

        for (int i = 0; i < cadenesXifrar.length; i++) {
            System.out.println("(" + desplacaments[i] + ")-" + cadenesXifrar[i] + "=>" + rot.xifraRotX(cadenesXifrar[i], desplacaments[i]));

        }

        System.out.println("\nDesxifrat\n---------");
        for (int i = 0; i < cadenesDesxifrar.length; i++) {
            System.out.println("(" + desplacaments[i] + ") " + cadenesDesxifrar[i] + " => " + rot.desxifraRotX(cadenesDesxifrar[i], desplacaments[i]));
        }
        
        System.out.println("\nForça Bruta\n-----------");
        rot.forcaBrutaRotX("Úiüht, úiü wx ùxì ív?");
    }

    public String xifraRotX(String cadena, int desplaçament) {
        String resultat = "";

        for (int i = 0; i < cadena.length(); i++) {
            char c = cadena.charAt(i);
            
            if (Character.isUpperCase(c)) {
                for (int j = 0; j < majuscules.length; j++) {
                    if (majuscules[j] == c) {
                        int novaPosicio = (j + desplaçament) % majuscules.length;
                        resultat = resultat + majuscules[novaPosicio];
                        break;
                    }
                }
            } 
            else if (Character.isLowerCase(c)){
                for (int j = 0; j < minuscules.length; j++) {
                    if (minuscules[j] == c) {
                        int novaPosicio = (j + desplaçament) % minuscules.length;
                        resultat = resultat + minuscules[novaPosicio];
                        break;
                    }
                }
            } else {
                resultat = resultat + c;
            }
        }
        return resultat;
    }

    public String desxifraRotX(String cadena, int desplaçament) {
        String resultat = "";

        for (int i = 0; i < cadena.length(); i++) {
            char c = cadena.charAt(i);
            
            if (Character.isUpperCase(c)) {
                for (int j = 0; j < majuscules.length; j++) {
                    if (majuscules[j] == c) {
                        int novaPosicio = (j - desplaçament + majuscules.length) % majuscules.length;
                        resultat = resultat + majuscules[novaPosicio];
                        break;
                    }
                }
            } 
            else if (Character.isLowerCase(c)){
                for (int j = 0; j < minuscules.length; j++) {
                    if (minuscules[j] == c) {
                        int novaPosicio = (j - desplaçament + minuscules.length) % minuscules.length;
                        resultat = resultat + minuscules[novaPosicio];
                        break;
                    }
                }
            } else {
                resultat = resultat + c;
            }
        }
        return resultat;
    }
    
    public void forcaBrutaRotX(String cadenaXifrada) {
        System.out.println("Missatge xifrat: " + cadenaXifrada);
        System.out.println("----------");

        for (int i = 0; i < minuscules.length; i++) {
            String intent = desxifraRotX(cadenaXifrada, i);
            System.out.println("(" + i + ") ->" + intent);
        }
    }
}