public class Rot13 {
    char[] minuscules = {'a', 'á', 'à', 'b', 'c', 'ç', 'd', 'e', 'é', 'è', 'f', 'g', 'h', 'i', 'í', 'ì', 'ï', 'j', 'k', 'l', 'm', 'n', 'ñ', 'o', 'ó', 'ò', 'p', 'q', 'r', 's', 't', 'u', 'ú', 'ù', 'ü', 'v', 'w', 'x', 'y', 'z'};
    char[] majuscules = {'A', 'Á', 'À', 'B', 'C', 'Ç', 'D', 'E', 'É', 'È', 'F', 'G', 'H', 'I', 'Í', 'Ì', 'Ï', 'J', 'K', 'L', 'M', 'N', 'Ñ', 'O', 'Ó', 'Ò', 'P', 'Q', 'R', 'S', 'T', 'U', 'Ú', 'Ù', 'Ü', 'V', 'W', 'X', 'Y', 'Z'};
    public static void main(String[] args) {
        Rot13 rot = new Rot13();
        
        System.out.println("Xifrat");
        System.out.println("---------");
        System.out.println("ABC                   => " + rot.xifraRot13("ABC"));
        System.out.println("XYZ                   => " + rot.xifraRot13("XYZ"));
        System.out.println("Hola, Mr. calçot      => " + rot.xifraRot13("Hola, Mr. calçot"));
        System.out.println("Perdó, per tu què és? => " + rot.xifraRot13("Perdó, per tu què és?"));

        System.out.println("\nDesxifrat");
        System.out.println("---------");
        System.out.println("IÏJ                   => " + rot.desxifraRot13("IÏJ"));
        System.out.println("FGH                   => " + rot.desxifraRot13("FGH"));
        System.out.println("Òwúi, Ùá. jiúkwb      => " + rot.desxifraRot13("Òwúi, Ùá. jiúkwb"));
        System.out.println("Zmálx, zmá bc acñ nà? => " + rot.desxifraRot13("Zmálx, zmá bc acñ nà?"));
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
