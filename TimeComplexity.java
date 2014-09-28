/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mp2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aldnav
 */
public class TimeComplexity {
    private String inFilename;
    private String netbeansPath = System.getProperty("user.dir") + "/src/mp2/";
    private Reader reader;
    
    public TimeComplexity(String infileName){
        this.inFilename = infileName;
        try {
            this.reader = new Reader(this.netbeansPath + this.inFilename);
        } catch (Exception ex) {
            Logger.getLogger(TimeComplexity.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void displayLog() {
        try {
            System.out.println(this.reader.getLinesAsString());
        } catch (Exception ex) {
            Logger.getLogger(TimeComplexity.class.getName()).log(Level.SEVERE, null, ex);
        }
    };
    
    class Token {
        public static final int assignment = 0;
        public static final int arithmetic = 1;
        // loop dissect
        public static final int forloop = 2;
        public static final int variabledeclaration = 3;
        public static final int condition = 4;
        public int type;
        public List tokens;
    }
    
    class Parser {
        
        public void parse(String s) {
            
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
    
    public static void main(String[] args) {
        String inputFile = "input.txt";
        
        TimeComplexity tc = new TimeComplexity(inputFile);
        tc.displayLog();
    }
}
