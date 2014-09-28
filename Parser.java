package mp2;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Parser {
    public static enum TokenType {
        
        TYPE("byte|short|int|long|float|double|boolean|char"),        
        FOR("for"),
        ASSIGNMENTOPERATORADV("[-|+|*|/|%|<<|>>|&|^|\\|]="),
        ASSIGNMENTOPERATOR("="),
        BINARYOPERATOR("[-|+|*|/|%]"),
        EQUALITYOPERATOR("==|!=|>|>=|<|<="),
        CONDITIONALOPERATOR("&&|\\|\\|?:"),
        VARIABLE("[a-zA-Z][a-zA-Z0-9_]*"),
        NUMBER("[0-9]+"),
        WHITESPACE("[ \t\f\r\n]+"),
        OPENBRACES("\\{"),
        CLOSEBRACES("\\}"),
        OPENPARENTHESIS("\\("),
        CLOSEPARENTHESIS("\\)"),
        DELIMITER(";");

        public final String pattern;

        private TokenType(String pattern) {
            this.pattern = pattern;
        }
    }

    public static class Token {
        public TokenType type;
        public int lineStart;
        public String data;

        public Token(TokenType type, String data, int start) {
            this.type = type;
            this.data = data;
            this.lineStart = start;
        }

        @Override
        public String toString() {
//            return String.format("%4d %-8s %-8s", lineStart, data, type.name());
            return String.format("%-8s %-8s", data, type.name());
        }
    }

    public ArrayList<Token> parse(String input) {
        // Store tokens here
        ArrayList<Token> tokens = new ArrayList<Token>();

        // Auto-compile pattern string buffering
        StringBuffer tokenPatternsBuffer = new StringBuffer();
        for (TokenType tokenType : TokenType.values())
            tokenPatternsBuffer.append(String.format("|(?<%s>%s)", tokenType.name(), tokenType.pattern));
        Pattern tokenPatterns = Pattern.compile(new String(tokenPatternsBuffer.substring(1)));

        // Look for matches and store to tokens
        Matcher matcher = tokenPatterns.matcher(input);
        while (matcher.find()) {
            if (matcher.group(TokenType.TYPE.name()) != null) {
                tokens.add(new Token(TokenType.TYPE, matcher.group(TokenType.TYPE.name()), matcher.start()));
                continue;
            } else if (matcher.group(TokenType.FOR.name()) != null) {
                tokens.add(new Token(TokenType.FOR, matcher.group(TokenType.FOR.name()), matcher.start()));
                continue;
            } else if (matcher.group(TokenType.ASSIGNMENTOPERATORADV.name()) != null) {
                tokens.add(new Token(TokenType.ASSIGNMENTOPERATORADV, matcher.group(TokenType.ASSIGNMENTOPERATORADV.name()), matcher.start()));
                continue;
            } else if (matcher.group(TokenType.ASSIGNMENTOPERATOR.name()) != null) {
                tokens.add(new Token(TokenType.ASSIGNMENTOPERATOR, matcher.group(TokenType.ASSIGNMENTOPERATOR.name()), matcher.start()));
                continue;
            } else if (matcher.group(TokenType.BINARYOPERATOR.name()) != null) {
                tokens.add(new Token(TokenType.BINARYOPERATOR, matcher.group(TokenType.BINARYOPERATOR.name()), matcher.start()));
                continue;
            } else if (matcher.group(TokenType.EQUALITYOPERATOR.name()) != null) {
                tokens.add(new Token(TokenType.EQUALITYOPERATOR, matcher.group(TokenType.EQUALITYOPERATOR.name()), matcher.start()));
                continue;
            } else if (matcher.group(TokenType.CONDITIONALOPERATOR.name()) != null) {
                tokens.add(new Token(TokenType.CONDITIONALOPERATOR, matcher.group(TokenType.CONDITIONALOPERATOR.name()), matcher.start()));
                continue;
            } else if (matcher.group(TokenType.VARIABLE.name()) != null) {
                tokens.add(new Token(TokenType.VARIABLE, matcher.group(TokenType.VARIABLE.name()), matcher.start()));
                continue;
            } else if (matcher.group(TokenType.NUMBER.name()) != null) {
                tokens.add(new Token(TokenType.NUMBER, matcher.group(TokenType.NUMBER.name()), matcher.start()));
                continue;
            } else if (matcher.group(TokenType.OPENBRACES.name()) != null) {
                tokens.add(new Token(TokenType.OPENBRACES, matcher.group(TokenType.OPENBRACES.name()), matcher.start()));
                continue;
            } else if (matcher.group(TokenType.CLOSEBRACES.name()) != null) {
                tokens.add(new Token(TokenType.CLOSEBRACES, matcher.group(TokenType.CLOSEBRACES.name()), matcher.start()));
                continue;
            } else if (matcher.group(TokenType.OPENPARENTHESIS.name()) != null) {
                tokens.add(new Token(TokenType.OPENPARENTHESIS, matcher.group(TokenType.OPENPARENTHESIS.name()), matcher.start()));
                continue;
            } else if (matcher.group(TokenType.CLOSEPARENTHESIS.name()) != null) {
                tokens.add(new Token(TokenType.CLOSEPARENTHESIS, matcher.group(TokenType.CLOSEPARENTHESIS.name()), matcher.start()));
                continue;
            } else if (matcher.group(TokenType.WHITESPACE.name()) != null) {
                continue;
            } else if (matcher.group(TokenType.DELIMITER.name()) != null)
                tokens.add(new Token(TokenType.DELIMITER, matcher.group(TokenType.DELIMITER.name()), matcher.start()));
                continue;
        }

        return tokens;
    }
    
    public static class For {
        ArrayList<Object> signature;
        ArrayList<Object> content;
    }

    public static void main(String[] args) {
        
        String input = "for(int i=1; i<n-3; i*=2){\n" +
                        "    sum = i;\n" +
                        "    sum = sum + 2;\n" +
                        "    sum += 9;\n" +
                        "}";
        int indentation = 0;
        
//        ArrayList<Token> tokens = parse(input);
//        for (Token token : tokens){
//            System.out.println(token);
//        }
    }
}