public class Tokens {
    String[] tokens;
    String[] identifiers;

    public Tokens(String[] tokens, String[] identifiers) {
        this.tokens = tokens;
        this.identifiers = identifiers;
        print();
    }

    public String[] getTokens() {
        return tokens;
    }

    public void setTokens(String[] tokens) {
        this.tokens = tokens;
    }

    public String[] getIdentifiers() {
        return identifiers;
    }

    public void setIdentifiers(String[] identifiers) {
        this.identifiers = identifiers;
    }
    public void print(){
        System.out.println("Tokens:");
        for (int i = 0; i < this.tokens.length; i++) {
            System.out.print(this.tokens[i] + " - " + this.identifiers[i] + " | ");
        }
        System.out.println("\n");
    }
}
