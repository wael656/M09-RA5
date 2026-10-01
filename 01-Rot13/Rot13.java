public class Rot13 {
    char[] minuscules = {'a', 'á', 'à', 'b', 'c', 'ç', 'd', 'e', 'é', 'è', 'f', 'g', 'h', 'i', 'í', 'ì', 'ï', 'j', 'k', 'l', 'm', 'n', 'ñ', 'o', 'ó', 'ò', 'p', 'q', 'r', 's', 't', 'u', 'ú', 'ù', 'ü', 'v', 'w', 'x', 'y', 'z'};
    char[] majuscules = {'A', 'Á', 'À', 'B', 'C', 'Ç', 'D', 'E', 'É', 'È', 'F', 'G', 'H', 'I', 'Í', 'Ì', 'Ï', 'J', 'K', 'L', 'M', 'N', 'Ñ', 'O', 'Ó', 'Ò', 'P', 'Q', 'R', 'S', 'T', 'U', 'Ú', 'Ù', 'Ü', 'V', 'W', 'X', 'Y', 'Z'};
    public static void main(String[] args) {
        Rot13 rot = new Rot13();

        String[] cadenesXifrar = {"ABC", "XYZ", "Hola, Mr. calçot", "Perdó, per tu què és?"};
        String[] cadenesDesxifrar = {"IÏJ", "FGH", "Òwúi, Ùá. jiúkwb", "Zmálx, zmá bc acñ nà?"};
        System.out.println("Xifrat\n-------");

        for (int i = 0; i < cadenesXifrar.length; i++) {
            System.out.println(cadenesXifrar[i] + " => " + rot.xifraRot13(cadenesXifrar[i]));

        }

        System.out.println("\nDesxifrat\n---------");
        for (int i = 0; i < cadenesDesxifrar.length; i++) {
            System.out.println(cadenesDesxifrar[i] + " => " + rot.desxifraRot13(cadenesDesxifrar[i]));
        }
    }

    public String xifraRot13(String cadena) {
        String resultat = "";

        for (int i = 0; i < cadena.length(); i++) {
            char c = cadena.charAt(i);
            
            if (Character.isUpperCase(c)) {
                for (int j = 0; j < majuscules.length; j++) {
                    if (majuscules[j] == c) {
                        int novaPosicio = (j + 13) % majuscules.length;
                        resultat = resultat + majuscules[novaPosicio];
                        break;
                    }
                }
            } 
            else if (Character.isLowerCase(c)){
                for (int j = 0; j < minuscules.length; j++) {
                    if (minuscules[j] == c) {
                        int novaPosicio = (j + 13) % minuscules.length;
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

    public String desxifraRot13(String cadena) {
        String resultat = "";

        for (int i = 0; i < cadena.length(); i++) {
            char c = cadena.charAt(i);
            
            if (Character.isUpperCase(c)) {
                for (int j = 0; j < majuscules.length; j++) {
                    if (majuscules[j] == c) {
                        int novaPosicio = (j - 13 + majuscules.length) % majuscules.length;
                        resultat = resultat + majuscules[novaPosicio];
                        break;
                    }
                }
            } 
            else if (Character.isLowerCase(c)){
                for (int j = 0; j < minuscules.length; j++) {
                    if (minuscules[j] == c) {
                        int novaPosicio = (j - 13 + minuscules.length) % minuscules.length;
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
}