public class Lexer {

    private String input;
    private Tokens tokens;

    public Lexer(String input) {
        this.input = input;
        createTokens();
    }

    public Tokens getTokens() {
        return tokens;
    }

    public void setTokens(Tokens tokens) {
        this.tokens = tokens;
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

    private void createTokens(){

        String[] word = new String[43];
        String[] ident = new String[43];
        word[0] = "main";
        word[1] = "{";
        word[2] = "}";
        int i = 0, k = 0;
        String news;
        try {
            int n = this.input.length();
//            System.out.println("n = " + n);
            while (i < this.input.length() - 4) {
                news = "";
                while (this.input.charAt(i) == ' ' || this.input.charAt(i) == '\n')
                    i++;
                if (this.input.charAt(i) == '(') {
                    word[k] = "" + this.input.charAt(i);
                    k++;
                    i++;
                } else if (this.input.charAt(i) == '{' || this.input.charAt(i) == '}' || this.input.charAt(i) == ';') {
                    word[k] = "" + this.input.charAt(i);
                    k++;
                    i++;
                }
                if (this.input.charAt(i + 1) == ',' || (this.input.charAt(i + 1) == ')')) {
                    word[k] = "" + this.input.charAt(i);
                    k++;
                    i++;
                    word[k] = "" + this.input.charAt(i);
                    k++;
                    i++;
                } else if (this.input.charAt(i + 1) == ';') {
                    word[k] = "" + this.input.charAt(i);
                    k++;
                    i++;
                    word[k] = "" + this.input.charAt(i);
                    k++;
                    i++;
                } else {
                    while (this.input.charAt(i) == ' ')
                        i++;
                    while (this.input.charAt(i) != ' ') {
                        news += this.input.charAt(i);
                        i++;
                    }
                    word[k] = news;
                    while (this.input.charAt(i) == ' ')
                        i++;
                    k++;
//            System.out.println(news);
//            System.out.println(i);
//            System.out.println("here " + this.input.charAt(i));
                }
            }
            word[k] = "" + this.input.charAt(i);
            word[k + 1] = "" + this.input.charAt(i + 2);

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
                System.out.println(i + ": " + word[i] + "  -  " + ident[i] + ".");
            }
            Tokens tk = null;
            tk.setTokens(word);
            tk.setIdentifiers(ident);
            this.tokens = tk;
        }
        catch (NullPointerException e){
            System.out.println("sorry");
        }
    }

}
