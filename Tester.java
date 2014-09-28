/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * NOTE:
 * PLEASE CHANGE 'netbeansPath' for issues about file reading
 * REMOVE package for package issues
 */
package mp2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Tester
 * Prep class for T(n) machine problem
 * Current submission includes parser
 * Todo: lexer and algebraic calculator
 * @author aldnav
 * @date Sep. 17, 2014
 */
public class Tester {

    private String inFilename;
    private String netbeansPath = System.getProperty("user.dir") + "/src/mp2/";
    private Reader reader;
    private Parser parser;

    public Tester(String infileName) {
        this.inFilename = infileName;
        this.parser = new Parser();
        try {
            this.reader = new Reader(this.netbeansPath + this.inFilename);
        } catch (Exception ex) {
            Logger.getLogger(Tester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void run() throws Exception {
        String code = this.reader.getLinesAsString();
        List<String> tokens = parser.parse(code);
        for (String token : tokens) {
            if (token.equals("for")) {
                System.out.println("");
            }
            System.out.println(token);
        }
    }

    class Parser {

        String delimiters = "[\t\f\r\n]|;|\\{|\\}|\\(|\\)";

        public List<String> parse(String line) {
            return trim(Arrays.asList(line.split(delimiters)));
        }

        private List<String> trim(List<String> lines) {
            List trimmed = new ArrayList();
            for (String line : lines) {
                if (line.length() > 0) {
                    trimmed.add(line.trim());
                }
            }
            return trimmed;
        }
    }

    class Reader {

        private BufferedReader in;

        public Reader() {
        }

        public Reader(String fname) throws Exception {
            try {
                in = new BufferedReader(new FileReader(fname));
                // Other code that might throw exceptions
            } catch (FileNotFoundException e) {
                System.err.println("Could not open " + fname);
                // Wasn't open, so don't close it
                throw e;
            } catch (Exception e) {
                // All other exceptions must close it
                try {
                    in.close();
                } catch (IOException e2) {
                    System.err.println("in.close() unsuccessful");
                }
                throw e; // Rethrow
            } finally {
                // Don't close it here!!!
            }
        }

        public String getLinesAsString() throws Exception {
            StringBuilder sb = new StringBuilder();
            String line = in.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = in.readLine();
            }
            return sb.toString();
        }

        public List getLinesAsList() throws Exception {
            List lines = new LinkedList();
            String line = in.readLine();
            while (line != null) {
                lines.add(line);
                line = in.readLine();
            }
            return lines;
        }
    }

    public static void main(String[] args) throws Exception {
        String inputFile = "input.txt";
        Tester tester = new Tester(inputFile);
        tester.run();
    }

}
