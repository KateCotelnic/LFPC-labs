import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws IOException {
        String str = new String(Files.readAllBytes(Paths.get("language.txt")), StandardCharsets.UTF_8);
//        System.out.println(str);
        String s = str.replaceAll(System.getProperty("line.separator"), " ");
//        System.out.println(s);
        Lexer lexer = new Lexer(s);
        Tokens tokens = new Tokens(Lexer.createTokens()[0], Lexer.createTokens()[1]);
        Parser parser = new Parser(tokens);
        ParseTree parseTree = new ParseTree(Parser.createParseTree());
    }
}