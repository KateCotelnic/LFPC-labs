public class Lexer {

    private static String input;

    public Lexer(String input) {
        this.input = input;
//        this.tokens.setIdentifiers(createTokens()[1]);
//        this.tokens.setTokens(createTokens()[0]);
    }

    private static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    static String[][] createTokens(){

        String[] word = new String[45];
        String[] ident = new String[45];
        word[0] = "main";
        word[1] = "{";
        word[2] = "}";
        int i = 0, k = 0;
        String news;
        String[][] array = new String[2][word.length];
        try {
            int n = input.length();
//            System.out.println("n = " + n);
            while (i < input.length() - 4) {
                news = "";
                while (input.charAt(i) == ' ' || input.charAt(i) == '\n')
                    i++;
                if (input.charAt(i) == '(') {
                    word[k] = "" + input.charAt(i);
                    k++;
                    i++;
                } else if (input.charAt(i) == '{' || input.charAt(i) == '}' || input.charAt(i) == ';') {
                    word[k] = "" + input.charAt(i);
                    k++;
                    i++;
                }
                if (input.charAt(i + 1) == ',' || (input.charAt(i + 1) == ')')) {
                    word[k] = "" + input.charAt(i);
                    k++;
                    i++;
                    word[k] = "" + input.charAt(i);
                    k++;
                    i++;
                } else if (input.charAt(i + 1) == ';') {
                    word[k] = "" + input.charAt(i);
                    k++;
                    i++;
                    word[k] = "" + input.charAt(i);
                    k++;
                    i++;
                } else {
                    while (input.charAt(i) == ' ')
                        i++;
                    while (input.charAt(i) != ' ') {
                        news += input.charAt(i);
                        i++;
                    }
                    word[k] = news;
                    while (input.charAt(i) == ' ')
                        i++;
                    k++;
//            System.out.println(news);
//            System.out.println(i);
//            System.out.println("here " + this.input.charAt(i));
                }
            }
            word[k] = "" + input.charAt(i);
            word[k + 1] = "" + input.charAt(i + 2);

            String newfnc = "";
            for (i = 0; i < word.length; i++) {
                if (word[i].equals("main")) {
                    ident[i] = "keyword";
                } else if (word[i].equals("=")) {
                    ident[i] = "assignment";
                } else if (word[i].equals("fnc")) {
                    ident[i] = "keyword";
                    ident[i + 1] = "function";
                    newfnc = word[i + 1];
                    i++;
                } else if (word[i].equals(newfnc)) {
                    ident[i] = "function";
                } else if (word[i].equals("(") || word[i].equals(")")) {
                    ident[i] = "parameter separator";
                } else if (word[i].equals("{") || word[i].equals("}")) {
                    ident[i] = "function separator";
                } else if (word[i].equals(",") || word[i].equals(";")) {
                    ident[i] = "separator";
                } else if (word[i].equals("+") || word[i].equals("-") || word[i].equals("*") || word[i].equals("/")) {
                    ident[i] = "math operator";
                } else if (word[i].equals("return") || word[i].equals("print") || word[i].equals("scan")) {
                    ident[i] = "function";
                } else if (isNumeric(word[i])) {
                    ident[i] = "number";
                } else {
                    ident[i] = "identifier";
                }
            }
            for (i = 0; i < word.length; i++) {
//                System.out.println(i + ": " + word[i] + "  -  " + ident[i] + ".");
                array[0][i] = word[i];
                array[1][i] = ident[i];
            }
            return array;
//            this.tokens.setTokens(word);
//            for(i=0;i<this.tokens.getTokens().length;i++){
//                System.out.print(this.tokens.getTokens()[i] + " ");
//            }
//            this.tokens.setIdentifiers(ident);
        }
        catch (NullPointerException e){
            System.out.println("sorry");
        }
        return array;
    }
}
