import java.util.HashMap;

public class RegularGrammar {
    private static String[] G ;

    public static String[] getG() {
        return G;
    }

    public static void setG(String[] g) {
        G = g;
    }

    public static char[] getVn() {
        return Vn;
    }

    public static void setVn(char[] vn) {
        Vn = vn;
    }

    public static char[] getVt() {
        return Vt;
    }

    public static void setVt(char[] vt) {
        Vt = vt;
    }

    public static HashMap<Character, String[]> getP() {
        return p;
    }

    public static void setP(HashMap<Character, String[]> p) {
        RegularGrammar.p = p;
    }

    private static char[] Vn ;
    private static char[] Vt ;
    private static HashMap<Character, String[]> p = new HashMap<>();

}
