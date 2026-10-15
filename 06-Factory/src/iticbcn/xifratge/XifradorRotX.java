package iticbcn.xifratge;

public class XifradorRotX implements Xifrador {
    char[] minuscules = {'a', 'á', 'à', 'b', 'c', 'ç', 'd', 'e', 'é', 'è', 'f', 'g', 'h', 'i', 'í', 'ì', 'ï', 'j', 'k', 'l', 'm', 'n', 'ñ', 'o', 'ó', 'ò', 'p', 'q', 'r', 's', 't', 'u', 'ú', 'ù', 'ü', 'v', 'w', 'x', 'y', 'z'};
    char[] majuscules = {'A', 'Á', 'À', 'B', 'C', 'Ç', 'D', 'E', 'É', 'È', 'F', 'G', 'H', 'I', 'Í', 'Ì', 'Ï', 'J', 'K', 'L', 'M', 'N', 'Ñ', 'O', 'Ó', 'Ò', 'P', 'Q', 'R', 'S', 'T', 'U', 'Ú', 'Ù', 'Ü', 'V', 'W', 'X', 'Y', 'Z'};

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        int desplacament;
        try {
            desplacament = Integer.parseInt(clau);
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("La clau de RotX ha de ser un sencer");
        }

        desplacament = desplacament % majuscules.length;
        if (desplacament < 0) {
            desplacament += majuscules.length;
        }

        String resultat = "";

        for (int i = 0; i < msg.length(); i++) {
            char c = msg.charAt(i);
            
            if (Character.isUpperCase(c)) {
                for (int j = 0; j < majuscules.length; j++) {
                    if (majuscules[j] == c) {
                        int novaPosicio = (j + desplacament) % majuscules.length;
                        resultat += majuscules[novaPosicio];
                        break;
                    }
                }
            } 
            else if (Character.isLowerCase(c)){
                for (int j = 0; j < minuscules.length; j++) {
                    if (minuscules[j] == c) {
                        int novaPosicio = (j + desplacament) % minuscules.length;
                        resultat += minuscules[novaPosicio];
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
        int desplacament;
        try {
            desplacament = Integer.parseInt(clau);
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("La clau de RotX ha de ser un sencer");
        }

        desplacament = desplacament % majuscules.length;
        if (desplacament < 0) {
            desplacament += majuscules.length;
        }

        String msgXifrat = xifrat.toString();
        String resultat = "";

        for (int i = 0; i < msgXifrat.length(); i++) {
            char c = msgXifrat.charAt(i);
            
            if (Character.isUpperCase(c)) {
                for (int j = 0; j < majuscules.length; j++) {
                    if (majuscules[j] == c) {
                        // Ahora la resta es 100% segura
                        int novaPosicio = (j - desplacament + majuscules.length) % majuscules.length;
                        resultat += majuscules[novaPosicio];
                        break;
                    }
                }
            } 
            else if (Character.isLowerCase(c)){
                for (int j = 0; j < minuscules.length; j++) {
                    if (minuscules[j] == c) {
                        // Ahora la resta es 100% segura
                        int novaPosicio = (j - desplacament + minuscules.length) % minuscules.length;
                        resultat += minuscules[novaPosicio];
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