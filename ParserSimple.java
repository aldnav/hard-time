package mp2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParserSimple {
    
    String delimiters = "[\t\f\r\n]|;|\\{|\\}|\\(|\\)";
    
    public List<String> parse(String line) {
        return trim(Arrays.asList(line.split(delimiters)));
    }
    
    private List<String> trim(List<String> lines) {
        List trimmed = new ArrayList();
        for (String line : lines) {
            if (line.length() > 0)
                trimmed.add(line.trim());
        }
        return trimmed;
    }
    
    public static void main(String[] args) {
        String s = "for(int i=n; i>0; i--){\n" +
                   "    sum = i;\n" +
                   "    sum = sum + 2;\n" +
                   "    sum += 9;\n" +
                   "}";
        
        ParserSimple parser = new ParserSimple();        
        List<String> tokens = parser.parse(s);
        for (String token : tokens) {
            System.out.println(token);
        }
    }
    
}