public class Parser {
    private static Tokens tokens;

    public Parser(Tokens tokens) {
        this.tokens = tokens;
    }

    public static String[][] createParseTree(){
        String[] parents = tokens.getTokens();
        String[] identif = tokens.getIdentifiers();
        int n = parents.length;
        String[][] children = new String[5][n];

        if(!identif[0].equals("keyword")){
            System.out.println("Error: the program should begin with a keyword");
        }
        int k;
        for(int i = 1; i<parents.length;i++){
            if(identif[i-1].equals("keyword")){
                if(!(identif[i].equals("function") || identif[i].equals("function separator"))) {
                    System.out.println("Error: function or function separator is expected");
                }
                else{
                    k=0;
                    while (children[k][i-1]!=null)
                        k++;
                    children[k][i-1] = parents[i];
                }
            }
            else if(identif[i-1].equals("function")){
                if(!(identif[i].equals("parameter separator") || identif[i].equals("function separator"))) {
                    System.out.println("Error: parameter or function separator is expected");
                }
                else{
                    k=0;
                    while (children[k][i-1]!=null)
                        k++;
                    children[k][i-1] = parents[i];
                }
            }
            else if(identif[i-1].equals("parameter separator")){
                if(!(identif[i].equals("identifier") || identif[i].equals("function separator") || identif[i].equals("separator"))) {
                    System.out.println("Error: identifier or function separator or separator is expected");
                }
                else if (identif[i].equals("identifier")){
                    k=0;
                    while (children[k][i-1]!=null)
                        k++;
                    children[k][i-1] = parents[i];
                }
                else if (identif[i].equals("function separator")){
                    int j=i;
                    while (!identif[j].equals("function"))
                        j--;
                    k=0;
                    while (children[k][j]!=null)
                        k++;
                    children[k][j] = parents[i];
                }
            }
            else if(identif[i-1].equals("identifier")){
                if(!(identif[i].equals("assignment") || identif[i].equals("math operator") || identif[i].equals("separator") || identif[i].equals("parameter separator"))) {
                    System.out.println("Error: assignment or math operator or separator or parameter separator is expected");
                }
                else if (identif[i].equals("parameter separator")){
                    int j=i-1;
                    while (!identif[j].equals("parameter separator"))
                        j--;
                    k=0;
                    while (children[k][j]!=null)
                        k++;
                    children[k][j] = parents[i];
                }
                else if (identif[i].equals("assignment")){
                    int j=i-1;
                    while (!identif[j].equals("function separator"))
                        j--;
                    k=0;
                    while (children[k][j]!=null)
                        k++;
                    children[k][j] = parents[i];
                }
            }
            if(identif[i].equals("assignment")){
                if(!(identif[i-1].equals("identifier") && (identif[i+1].equals("identifier") || identif[i+1].equals("number") || identif[i+1].equals("function")))) {
                    System.out.println("Error: assignment of identifier to math operator or identifier or number is expected");
                }
                else {
                    k=0;
                    while (children[k][i]!=null)
                        k++;
                    children[k][i] = parents[i-1];
                    if(((identif[i+1].equals("identifier") || identif[i+1].equals("number")) && identif[i+2].equals("separator")) || identif[i+1].equals("function")) {
                        k = 0;
                        while (children[k][i] != null)
                            k++;
                        children[k][i] = parents[i+1];
                    }
                }
            }
            else if(identif[i].equals("math operator")){
                if(!((identif[i-1].equals("identifier") || identif[i-1].equals("number")) && (identif[i+1].equals("identifier") || identif[i+1].equals("number") || identif[i+1].equals("function")))) {
                    System.out.println("Error: math operator between identifiers or numbers or functions is expected");
                }
                else {
                    int j=i;
                    while (!identif[j].equals("assignment"))
                        j--;
                    k=0;
                    while (children[k][j]!=null)
                        k++;
                    children[k][j] = parents[i];
                    k = 0;
                    while (children[k][i] != null)
                        k++;
                    children[k][i] = parents[i-1];
                    k = 0;
                    while (children[k][i] != null)
                        k++;
                    children[k][i] = parents[i+1];
                }
            }
            else if(identif[i-1].equals("separator")){
                if(!(identif[i].equals("identifier") || identif[i].equals("function") || identif[i].equals("function separator"))) {
                    System.out.println("Error: assignment or math operator or function separator after separator is expected");
                }
                else if (identif[i].equals("function separator")){
                    int j=i-1;
                    while (!identif[j].equals("function separator"))
                        j--;
                    k=0;
                    while (children[k][j]!=null)
                        k++;
                    children[k][j] = parents[i];
                }
                else if (identif[i].equals("function")){
                    int j=i-1;
                    while (!identif[j].equals("function separator"))
                        j--;
                    k=0;
                    while (children[k][j]!=null)
                        k++;
                    children[k][j] = parents[i];
                }
                else if(!identif[i+1].equals("assignment")) {
                    int j = i - 1;
                    while (!(identif[j].equals("parameter separator") || identif[j].equals("assignment") || identif[j].equals("function separator")))
                        j--;
                    k = 0;
                    while (children[k][j] != null)
                        k++;
                    children[k][j] = parents[i];
                }
            }
        }

        int i,count =0;
        for(i=0;i<n;i++){
            if(children[0][i] == null){
                count++;
            }
        }
//        System.out.println("count = " + count);

        for(int m = 0;m<count;m++){
            for (i = 0; i < n; i++) {
                if (children[0][i] == null) {
//                System.out.println(i);
                    for (k = i + 1; k < n; k++) {
                        parents[k - 1] = parents[k];
                    }
                    for (k = i + 1; k < n; k++) {
                        for (int j = 0; j < children.length; j++) {
                            children[j][k - 1] = children[j][k];
                        }
                    }
                }
            }
        }
        count = parents.length-count;
        System.out.println(count);
        for(i=count;i<parents.length;i++)
            parents[i]=null;

//        for(i=0;i<parents.length;i++) {
//            System.out.print(i+ ". "+parents[i] + "   ");
//        }
//        System.out.println();
//        for(i=0;i<n;i++) {
//            System.out.print("_________");
//        }
//        System.out.println();
//        for (i=0;i<children.length;i++){
//            for (int j=0;j<n;j++){
//                System.out.print(j+ ". " +children[i][j] + "   ");
//            }
//            System.out.println();
//        }

        String[][] a = new String[children.length+1][count];
        i=0;
        while (parents[i]!=null){
            a[0][i] = parents[i];
            i++;
        }
        for (i=1;i<children.length+1;i++){
            for(int j=0;j<count;j++){
                a[i][j] = children[i-1][j];
            }
        }
        return a;
    }
}