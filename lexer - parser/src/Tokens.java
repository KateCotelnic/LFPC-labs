public class Tokens {
    String[] tokens;
    String[] identifiers;

    public Tokens(String[] tokens, String[] identifiers) {
        this.tokens = tokens;
        this.identifiers = identifiers;
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
}
