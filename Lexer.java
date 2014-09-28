/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mp2;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aldnav
 */
public class Lexer {
    
    Parser parser;
    Path filePath;
    List<String> lines;
    List<ForLoop> loops;
    double loopCounter;
    
    public Lexer (Path inputFilePath) {
        parser = new Parser();
        filePath = inputFilePath;
        Charset charset = Charset.forName("UTF-8");
        try {
            lines = Files.readAllLines(this.filePath, charset);
            loops = new ArrayList<ForLoop>();
            loopCounter = 1.0;
        } catch (IOException ex) {
            Logger.getLogger(Lexer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void analyze() {
        // identify the loops
//        Stack loopStack = new Stack();
        ForLoop alphaLoop;
//        Boolean foundLoopFlag = false;
        
        for (String line: lines) {
            ArrayList<Parser.Token> tokens = parser.parse(line);
            for (Parser.Token token: tokens) {
                if (token.type == Parser.TokenType.FOR) {
                    alphaLoop = new ForLoop(loopCounter);                    
                    alphaLoop.initialStatement = extractStatement(line, 0);
                    alphaLoop.booleanExpression = extractStatement(line, 1);
                    alphaLoop.incrementStatement = extractStatement(line, 2);
                    loops.add(alphaLoop);
                    loopCounter += 1.0;
                }
            }
        }
        
        // Display for loops
        for (ForLoop loop: loops)
            System.out.println(loop);        
    }
    
    private String extractStatement(String line, int order) {
        // order
        // 0 - initial statement
        // 1 - boolean expression
        // 2 - increment statement
        if (order > 2)
            return "";
        return line.split(";")[order].replace("for(", "").replace("){", "");
    }
    
    public class ForLoop {
        double id = 0.0;
        String initialStatement = "";
        String booleanExpression = "";
        String incrementStatement = "";
        String bodyStatements = "";
        List<ForLoop> innerLoops;
        
        public ForLoop() {
            innerLoops = new ArrayList<ForLoop>();
        }
        
        public ForLoop(double _id) {
            id = _id;
            innerLoops = new ArrayList<ForLoop>();
        }

        @Override
        public String toString() {
            return "ForLoop{" + "id=" + id + ", initialStatement=" + initialStatement + ", booleanExpression=" + booleanExpression + ", incrementStatement=" + incrementStatement + ", bodyStatements=" + bodyStatements + ", innerLoops=" + innerLoops + '}';
        }
        
    }
    
    public static void main(String[] args) {
        Path filePath = Paths.get(System.getProperty("user.dir") + "/src/mp2", "input.txt");
        Lexer lexer = new Lexer(filePath);
        lexer.analyze();
    }
    
}
