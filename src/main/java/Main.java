import token.Token;
import token.Tokenizer;
import visitors.CalcVisitor;
import visitors.ParserVisitor;
import visitors.PrintVisitor;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            throw new RuntimeException("Expected name of file, but: " + Arrays.toString(args) + "found");
        }
        final String fileName = args[0];
        File file = new File(fileName);

        try (InputStream in = new FileInputStream(file)) {
            final ParserVisitor parserVisitor = new ParserVisitor(Tokenizer.tokenize(in));
            final List<Token> tokensRev = parserVisitor.getPolish();
            final PrintVisitor printVisitor = new PrintVisitor(tokensRev, System.out);
            printVisitor.print();
            final CalcVisitor calcVisitor = new CalcVisitor(tokensRev);
            System.out.println(calcVisitor.calc());
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
