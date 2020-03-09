public class Main {
    public static String[][] dfa = new String[100][4];
    public static int k=0;
    public static String[][] createMatrix(String[] Vn, String[] Vt,String S, String F, String[] P){
        String[][] matrix = new String[4][4];
        for (int i= 0 ; i< 4; i++){
            for (int j = 0; j< 4; j++){
                matrix[i][j] = "0";
            }
        }
        matrix[0][0] = " "; matrix[0][1] = " ";
        for(int i = 1; i<4; i++){
            matrix[i][1] = Vn[i-1];
            if(Vn[i-1].equals(F))
                matrix[i][0] = "F";
            else if(Vn[i-1].equals(S))
                matrix[i][0] = "B";
        }
        for(int j = 2; j<4;j++){
            matrix[0][j]=Vt[j-2];
        }
        for(int i=0;i<6;i++){
            int n,m;
            switch (P[i].charAt(3)) {
                case '0':
                    n = 1;
                    break;
                case '1':
                    n = 2;
                    break;
                case '2':
                    n = 3;
                    break;
                default:
                    n = 0;
            }
            //      System.out.println("length 5");
            switch (P[i].charAt(5)) {
                case 'a':
                    m = 2;
                    break;
                case 'b':
                    m = 3;
                    break;
                default:
                    m = 0;
            }
            //         System.out.println("n = " + n);
            //         System.out.println("m = " +m);
            if(matrix[n][m] == "0")
                matrix[n][m] = "q" + P[i].charAt(11);
            else
                matrix[n][m] += "q" + P[i].charAt(11);
        }
        return matrix;
    }
    public static void input(String[][] matrix,int k){
        String from = dfa[k][1];
        while (from != null){
            //         System.out.println("from = " + from);
            String q = "";
            if(from.length() == 2){
                q = from;
                from = null;
            } else{
                q = from.substring(0,2);
                from = from.substring(2);
            }
            //        System.out.println("q = " + q);

            int n = 0;
            switch (q) {
                case "q0":
                    n = 1;
                    break;
                case "q1":
                    n = 2;
                    break;
                case "q2":
                    n = 3;
                    break;
                default:
                    n = 0;
            }
            //        System.out.println("n = " + n);
            if(matrix[n][2] != "0"){
                if(dfa[k][2] == null){
                    dfa[k][2] = matrix[n][2];
                }else if (!dfa[k][2].contains(matrix[n][2]))
                    dfa[k][2] += matrix[n][2];}
            if(matrix[n][3] != "0"){
                if(dfa[k][3] == null){
                    dfa[k][3] = matrix[n][3];
                }else if (!dfa[k][3].contains(matrix[n][3]))
                    dfa[k][3] += matrix[n][3];}
            //        System.out.println("dfa[2] = " + dfa[k][2]);
            //        System.out.println("dfa[3] = " + dfa[k][3]);
        }
    }

    public static boolean contains(String str, char c){
        for(int i=0;i< str.length();i++){
            if(str.charAt(i) == c){
                return true;
            }
        }
        return false;
    }

    public static boolean exist(String main, String verif){
        boolean exist = false;
        int count = 0;
        System.out.println("main "+ main);
        for (int i=1; i <= main.length(); i=i+2){
            //          System.out.println("main length " + main.length());
            if(contains(verif,main.charAt(i))) {
                count++;
            }
     //       System.out.println("i = " + i + " char "+ main.charAt(i) + " verif " + verif +" count " + count);
        }
        if(count == main.length()/2)
            return true;
        else return false;
    }

    public static boolean existc(char c,String str){
        for(int i=0; i<str.length();i++){
            if(str.charAt(i)==c)
                return true;
        }
        return false;
    }
    public static boolean exists(String main, int n){
        boolean exist = false;
        int i = 0;
        int count = 0;
        while (i<n-1){
            if(main.length() == 2)
            System.out.println("compare with " + dfa[i][1]);
            if(main.length() != dfa[i][1].length()){
                i++;
                if(main.length() == 2)
                System.out.println("no length");}
            else {
                if(main.length() == 2)
                System.out.println("length " + main.length());
                count = 0;
                for (int j=0; j < main.length();j++){
                    if(existc(main.charAt(j),dfa[i][1])){
                        count++;
                        if(main.length() == 2)
                            System.out.println("current count " + count);
                    }
                }
                if(count==main.length())
                    return true;
                else i++;
            }
        }
        return exist;
    }


    public static void constructdfa(String[][] matrix){
        int i =0;
        //заполнение первой строки
        while(i<2){
            for(int j =0;j<4;j++){
                dfa[k][j] = matrix[i][j];
            }
            k++;
            i++;}
        i=2; k=1;
        //i для второго столбика, k для третьего и четвертого
        while (true){
      //      System.out.println("dfa[k][2] " + dfa[k][2]);
            if(dfa[k][2] == null)
                break;
            // если из второго столбика не существует добавляем строку
            if(dfa[k][2] != "0"){
        if(exists(dfa[k][2], i) == false){
     ///       System.out.println("dont");
             dfa[i][1] = dfa[k][2];
            System.out.println("dfa new " + dfa[i][1]);
             input(matrix,i);
            System.out.println(dfa[i][1] + " " + dfa[i][2] + " " + dfa[i][3]);
             i++; } }
      //      System.out.println("i2 = " + i);
            // если из третьего столбика не существует добавляем строку
            System.out.println("dfa[k][3] " + dfa[k][3]);
            if(dfa[k][3] != "0"){
                System.out.println("im here");
        if(exists(dfa[k][3], i) == false){
                dfa[i][1] = dfa[k][3];
                input(matrix,i);
                i++; }
            else System.out.println("here");
            }
            System.out.println(dfa[k][1] + " " + dfa[k][2] + " " + dfa[k][3]);
            k++;
            System.out.println("k = " + k);
        }
    }

    public static void main(String[] args) {
        String[] Q = {"q0","q1","q2"};
        String[] L = {"a","b"};
        String F = "q2";
        String S = "q0";
        String[] rules ={ "s(q0,a) = q1",
                "s(q0,a) = q0",
                "s(q1,b) = q1",
                "s(q1,a) = q2",
                "s(q2,b) = q2",
                "s(q2,a) = q0"};
        String[][] matrix = createMatrix(Q,L,S,F,rules);
        System.out.println("NFA : ");
        for(int i = 0; i< 4; i++){
            for(int j = 0; j< 4; j++){
                System.out.print(" " + matrix[i][j] + " ");
                System.out.print("|");
            }
            System.out.println();
            System.out.println("-------------------");
        }
        constructdfa(matrix);
        System.out.println("DFA : ");
        for(int i = 0; i< 7; i++){
            for(int j = 0; j< 4; j++){
                System.out.print(" " + dfa[i][j] + " ");
                System.out.print("|");
            }
            System.out.println();
            System.out.println("-------------------");
        }
    }
}