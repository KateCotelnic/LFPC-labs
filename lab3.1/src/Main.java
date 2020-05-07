import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        //////// v 29 /////////
        String[] G = {"Vn", "Vt", "P", "S"};
        char[] Vn = {'S', 'A', 'B', 'C', 'D'};
        char[] Vt = {'a', 'b'};
        HashMap<Character, String[]> p = new HashMap<>(){{
            put('S', new String[]{"aB", "DA"});
            put('A', new String[]{"a", "BD", "aDADB"});
            put('B', new String[]{"b", "ASB"});
            put('D', new String[]{"e", "BA"});
            put('C', new String[]{"BA"});
        }};

        RegularGrammar rg = new RegularGrammar();
        rg.setG(G);
        rg.setVn(Vn);
        rg.setVt(Vt);
        rg.setP(p);
    //    System.out.println("Regular Grammar:");
    //    ChomskyNormalForm.printGrammar(rg);
        ChomskyNormalForm.obtainChomsky(rg);
    }
}
