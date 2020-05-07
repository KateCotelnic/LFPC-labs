import java.util.HashMap;
import java.util.Map;

public class ChomskyNormalForm {
    public static void printGrammar(RegularGrammar rg){
        System.out.print("G = {");
        int i;
        for(i = 0; i<rg.getG().length;i++){
            if(rg.getG()[i]!= null){
                if(i==rg.getG().length-1|| rg.getG()[i+1] == null){
                    System.out.print(rg.getG()[i]);
                }else
                    System.out.print(rg.getG()[i] + ", ");}
        }
        System.out.print("}\nVn = {" );

        for(i = 0; i<rg.getVn().length;i++){
            if(rg.getVn()[i] != 0 ){
                if(i==rg.getVn().length-1|| rg.getVn()[i+1] == 0) {
                    System.out.print(rg.getVn()[i]);
                }else
                    System.out.print(rg.getVn()[i] + ", ");}
        }
        System.out.print("}\nVt = {" );

        for(i = 0; i<rg.getVt().length;i++){
            if(rg.getVt()[i] != 0 ){
                if(i==rg.getVt().length-1|| rg.getVt()[i+1] == 0) {
                    System.out.print(rg.getVt()[i]);
                }else
            System.out.print(rg.getVt()[i] + ", ");}
        }
        System.out.println("}\nP = { " );

        //  System.out.println(Arrays.asList(rg.getP()));
        for (Map.Entry me : rg.getP().entrySet()) {
            System.out.print(me.getKey() + " -> ");
            for(String s:rg.getP().get(me.getKey())){
                if(s!= null){
                System.out.print(s + " | ");}
            }
            System.out.println();
        }
        System.out.println("}");
    }

    public static void toGrammar(char[] a, String[][] b){
        RegularGrammar chomsky = new RegularGrammar();
        String[] G = {"Vn", "Vt", "P", "S"};
        char[] Vn = a;
        char[] Vt = {'a', 'b'};
        HashMap<Character, String[]> p = new HashMap<>();
        int i=0;
        while (a[i]!=0){
            int k=0; int m=0; String[] str = new String[10];
            while (b[k][i]!=null){
                str[m] = b[k][i];
                k++;
                m++;
            }
            p.put(a[i],str);
            i++;
        }
        chomsky.setG(G);
        chomsky.setVn(Vn);
        chomsky.setVt(Vt);
        chomsky.setP(p);
            printGrammar(chomsky);
    }
    public static void eliminateEpsilon(char[] a, String[][] b) {
        for (int i = 0; i < b.length; i++) {
            for (int k = 0; k < b[i].length; k++) {
                if (b[i][k] == "e") {
                    ////delete e
                    for (int j = 1; j < b.length; j++) {
                        b[j - 1][k] = b[j][k];
                    }
                    ////adding
                    for (int j = 0; j < b.length; j++) {
                        for (int l = 0; l < b[i].length; l++) {
                            //System.out.println(a[k]);
                            String str = "";
                            if(b[j][l]!=null && b[j][l].contains("" + a[k])) {
                                for(int index=0;index<b[j][l].length();index++){
                                    if(b[j][l].charAt(index) != a[k]) {
                                        str += b[j][l].charAt(index);
                                    }}
                                int m=0;
                                while(b[m][l] != null)
                                    m++;
                          //      System.out.println(m);
                                b[m][l] = str;
                          //      System.out.println(str);
                            }

                        }
                    }
                }
            }
        }
    }

    public static void check(String[][] b){
        for(int i = 0;i<b.length;i++){
            for(int k=0; k< b[i].length;k++){
                if(b[i][k]!=null) {
                    int p = i - 1;
                    while (p >= 0) {
        //                System.out.println(k + ". " + i + " with " + p);
                        if (b[i][k] == b[p][k]) {
     //                       System.out.println("true");
                            int n = i + 1;
                            while (n < b.length) {
                                b[n - 1][k] = b[n][k];
                            }
                        }
                        p--;
                    }
                }
            }
        }
    }

    public static void eliminateUnitProduction(char[] a, String[][] b){
        for (int i = 0; i < b.length; i++) {
            for (int k = 0; k < b[i].length; k++) {
                if (b[i][k]!= null && b[i][k].length()==1 && b[i][k].charAt(0)>64 && b[i][k].charAt(0)<91){
                    ////delete
                    char A = b[i][k].charAt(0);
             //       System.out.println(i + " "+k + " " + A);

                    for (int j = i+1; j <= b.length-1; j++) {
                        b[j - 1][k] = b[j][k];
                    }
//                    ////adding
                    int j;
                    for (j = 0; j < a.length; j++) {
                        if(a[j]==A)
                            break;
                    }
      //              System.out.println(j);
                    for(int n=0;n<b.length;n++){
                        String str = b[n][j];
                        int p=0;
                        while (b[p][k]!= null)
                            p++;
                        b[p][k] = str;
                    }
                }
            }
        }
        check(b);
        for (int i = 0; i < b.length; i++) {
            for (int k = 0; k < b[i].length; k++) {
                if (b[i][k] != null && b[i][k].length() == 1 && b[i][k].charAt(0) > 64 && b[i][k].charAt(0) < 91) {
    //                System.out.println(b[i][k] + " " + i + " " + k);
                    ////delete
                    int j=i+1;
                    while (j < b.length) {
   //                     System.out.println(j + ". "+b[j-1][k]+ " to "+b[j][k]);
                        b[j - 1][k] = b[j][k];
                        j++;
                    }
                }
            }
        }
    }

    public static void eliminateInaccessible(char[] a, String[][] b, RegularGrammar rg) {
        int n = 0; char c = 0;
        while (n < rg.getVn().length) {
            c = rg.getVn()[n];
        //    System.out.println(c);
            int ver = 0;
            while (ver == 0) {
                for (int i = 0; i < b.length; i++) {
                    for (int k = 0; k < b[i].length; k++) {
                        //          System.out.println(c + "   " + b[i][k]);
                        if (b[i][k] != null && b[i][k].contains("" + c)) {
                            ver = 1;
                            break;
                            //   n++;
                            //     System.out.println("yes");
                        }
                        //else if (i == b.length - 1 && k == b[i].length - 1) {
                        //  System.out.println("no");
                    }
                }
            } n++;
            if(n==3)
                break;
        }
            int i;
            c = 'C';
            for(i =0; i< a.length;i++){
                if(a[i]==c)
                    break;
            }
     //   System.out.println(c);
      //  System.out.println(i);
        int k;
            for(k = i+1; k< a.length;k++){
                a[k-1] = a[k];
            }
            a[k-1] = Character.MIN_VALUE;
                for (int l = 0; l < b.length; l ++) {
                    for (k = i+1; k <b[i].length; k++) {
                        b[l][k-1] = b[l][k];
                    } b[l][k-1] = null;
                }
            }

    public static void eliminateNonProductive(char[] a, String[][] b, RegularGrammar rg){
        char[] prod = new char[10]; int m=0;
        for (int i = 0; i < b.length; i ++) {
            for (int k = 0; k < b[i].length; k++) {
                if(b[i][k]!=null && b[i][k].length()==1 && b[i][k].charAt(0)>96 && b[i][k].charAt(0)<123){
                            prod[m] = a[k];
       //             System.out.println(prod[m]);
                            m++;
                        }
                    }
                }
        String str = null;
        for(int i=0;i<prod.length;i++){
            str += prod[i];
        }
        for(int i=0;i<a.length;i++){
            if(!str.contains(""+a[i])){
                for(int k =0;k<b.length;k++){
                    if(b[k][i]!=null && str.contains(b[k][i].subSequence(0,1))
                            && str.contains(b[k][i].subSequence(1,b[k][i].length()))){
                        prod[m]=a[i];
                    }
                }
            }
        } int k = 0;
        for(int i=0;i<a.length;i++){
            for(int j=0;j<prod.length;j++){
                if(a[i]==prod[j]){
                    k++;
                    break;
                }
            }
        }
        if(k!=a.length-1){
            for(int i=k+1;i<a.length;i++){
                a[i-1]=a[i];
                for(int j=0;j<b.length;j++){
                    b[j][i-1] = b[j][i];
                }
            }
        }
    }

    public static void toChomsky(char[] a, String[][] b){
        for(int i=0;i<b.length;i++){
            for(int k=0;k<b[i].length;k++){
                if(b[i][k]!=null && !(b[i][k].length()==1 || (b[i][k].length()==2 && b[i][k].charAt(0)>64 && b[i][k].charAt(0)<91 && b[i][k].charAt(1)>64 && b[i][k].charAt(1)<91))){

                    if(b[i][k].length()==5 && Character.isLowerCase(b[i][k].charAt(0))&& Character.isUpperCase(b[i][k].charAt(1)) && Character.isUpperCase(b[i][k].charAt(2)) && Character.isUpperCase(b[i][k].charAt(3)) && Character.isUpperCase(b[i][k].charAt(4))){
                        int j=i;
                        String str = b[j][k];
                        while (b[j][k]!=null){
                 //           System.out.println(b[j][k] +" to "+b[j+1][k]);
                            b[j][k] = b[j+1][k];
                            j++;
                        }
                        j--;
                  //      System.out.println(j);
                        int m=90; int l=0; String s = "";
                        while (l<a.length){
                            s+=a[l];
                            l++;
                        }
                        while (m>65){
                            if(!(s.contains("" + (char)m) || s.contains("" + (char)(m-1)) || s.contains("" + (char)(m-2)) || s.contains("" + (char)(m-3)))){
               //                 System.out.println("new tring: "+s + " doent has "+(char)m);
                                break;
                            }
                            m--;
                        }
                        int n= m-1, x = m-2, y = m-3;
                      //  System.out.println("\n new: \n str: " + str +"  m= " + (char)m + "   m-3= " + (char)(m-3));
                        b[j][k] = "" + (char)m + (char)n;
                        l = 0;

                        while (a[l]!=0)
                            l++;
                        int lx = l+2, ly = l+3, ln = l+1;
                        a[l] = (char)m;
                        b[0][l] = "" + str.charAt(0);
                        a[ln] = (char)n;
                        b[0][ln] = "" + (char)y + (char)x;
                        a[lx] = (char)x;
                        b[0][lx] = "" + str.charAt(3) + str.charAt(4);
                        a[ly] = (char)y;
                        b[0][ly] = "" + str.charAt(1) + str.charAt(2);
                    }
                    if(b[i][k].length()==2 && b[i][k].charAt(0)>96 && b[i][k].charAt(0)<123 && b[i][k].charAt(1)>64 && b[i][k].charAt(1)<91){
                        int j=i;
                        String str = b[j][k];
                        while (b[j][k]!=null){
                            b[j][k] = b[j+1][k];
                            j++;
                        }
                        j--;
                        int m=65; int l=0;
                        while (a[l] == m){
                            m++; l++;
                        }
                        char c = (char) m;
                        b[j][k] = "" + c + str.charAt(1);
                        while (a[l]!=0)
                            l++;
                        a[l] = c;
                        b[0][l] = "" + str.charAt(0);
                    }
                    if(b[i][k].length()==3 && Character.isUpperCase(b[i][k].charAt(0))&& Character.isUpperCase(b[i][k].charAt(1)) && Character.isUpperCase(b[i][k].charAt(2))){
                        int j=i;
                        String str = b[j][k];
                        while (b[j][k]!=null){
                            b[j][k] = b[j+1][k];
                            j++;
                        }
                        j--;
                        int m=65; int l=0; String s = "";
                        while (l<a.length){
                            s+=a[l];
                            l++;
                        }
                        while (m<91){
                            if(!s.contains("" + (char)m))
                                break;
                                m++;
                        }
                        b[j][k] = "" + str.charAt(0) + (char)m;
                        l = 0;
                        while (a[l]!=0)
                            l++;
                        a[l] = (char)m;
                        b[0][l] = "" + str.charAt(1) + str.charAt(2);
                    }
                    if(b[i][k].length()==3 && Character.isLowerCase(b[i][k].charAt(0))&& Character.isUpperCase(b[i][k].charAt(1)) && Character.isUpperCase(b[i][k].charAt(2))){
                        int j=i;
                        String str = b[j][k];
                        while (b[j][k]!=null){
                            b[j][k] = b[j+1][k];
                            j++;
                        }
                        j--;
                        int m=65; int l=0; String s = "";
                        while (l<a.length){
                            s+=a[l];
                            l++;
                        }
                        while (m<91){
                            if(!s.contains("" + (char)m))
                                break;
                            m++;
                        }
                        int n = m+1;
                        b[j][k] = "" + (char)n + (char)m;
                        l = 0;
                        while (a[l]!=0)
                            l++;
                        a[l] = (char)m;
                        b[0][l+1] = "" + str.charAt(0);
                        a[l+1] = (char)n;
                        b[0][l] = "" + str.charAt(1) + str.charAt(2);
                    }
                }
                }
            }
        }

        public static void optimization(char[] a, String[][] b){
        int[] quantity = new int[25];
        int k=0;
        while(a[k] != 0){
                int i=0;
                while (b[i][k]!=null){
                    i++;
                }
                quantity[k] = i;
                k++;
            }
        for(k=a.length-1;k>=0;k--){
            for(int l=k-1;l>=0;l--){
                if(a[l]!=0 && a[k]!=0 && quantity[k]==quantity[l]){
                    int i=0,count=0;
                    while(b[i][k]!= null){
                        if(b[i][k].equals(b[i][l])){
                            count++;
                        }
                            i++;
                    }
                    if(count==quantity[k]){
                        char ch = a[k];
                        for(int m=k+1;m< a.length;m++){
                            a[m-1]= a[m];
                            for(int n=0;n<b.length;n++){
                                b[n][m-1] = b[n][m];
                            }
                        }
                        for(int m=0;m<b.length;m++) {
                            for (int n = 0; n < b[m].length; n++) {
                                if(b[m][n]!=null){
                                for (int c = 0; c < b[m][n].length(); c++) {
                                    if (b[m][n].charAt(c) == ch) {
                         //               System.out.print(b[m][n] + " contains " + ch);
                                        b[m][n] = b[m][n].substring(0, c) + a[l] + b[m][n].substring(c + 1);
                       //                 System.out.println(" to  " + b[m][n]);
                                    }
                                }
                            }
                        }
                        }
                    }
                }
            }
        }
        }
    public static void obtainChomsky(RegularGrammar rg){
        int i =0; int k=0; char[] a = new char[25]; String[][] b = new String[15][26];
        for (Map.Entry me : rg.getP().entrySet()) {
            a[i] = (char) me.getKey();
            k=0;
            for(String s:rg.getP().get(me.getKey())) {
                b[k][i] = s;
                k++;
            }
            i++;
        }
        eliminateEpsilon(a,b);
        System.out.println("After elimination of epsilon:");
        toGrammar(a,b);
        eliminateUnitProduction(a, b);
        System.out.println("After elimination of unit-production:");
        toGrammar(a,b);
        eliminateInaccessible(a,b,rg);
        System.out.println("After elimination of inaccessible:");
        toGrammar(a,b);
        eliminateNonProductive(a,b,rg);
        System.out.println("After elimination of non-productive:");
        toGrammar(a,b);
        toChomsky(a,b);
        System.out.println("Chomsky Normal Form without optimization:");
        toGrammar(a,b);
        optimization(a,b);
        System.out.println("Chomsky Normal Form:");
        toGrammar(a,b);
    }
}
