package token;

import state.StartState;
import state.TokenizerState;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Tokenizer {
    private final InputStream in;
    private boolean eof = false;
    private char curChar;
    private final List<Token> tokens = new ArrayList<>();
    private TokenizerState state = new StartState();

    public Tokenizer(InputStream in) throws IOException {
        this.in = in;
        this.nextChar();
    }

    private void nextChar() throws IOException {
        if (eof) return;
        int next = in.read();
        if (next == -1) {
            eof = true;
            curChar = '\0';
        } else {
            curChar = (char) next;
        }
    }

    public List<Token> parse() {
        return List.copyOf(tokens);
    }

    public void process() throws IOException {
        while (!state.isEOF()) {
            this.state = state.consume(curChar, tokens);
            this.nextChar();
        }
    }
    public static List<Token> tokenize(final InputStream inputStream) throws IOException {
        final Tokenizer tokenizer = new Tokenizer(inputStream);
        tokenizer.process();
        return tokenizer.parse();
    }
}
