package burrows;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.*;

public class Burrows {
    
    /**
     * My own toString method for a StringBuffer to a String
     * @param buffer - supplied StringBuffer
     * @return String representation of the StringBuffer
     */
    public static String stringanate(StringBuffer buffer) {
        String string = "";
        for(int i = 0; i < buffer.length(); i++)
            string = string + buffer.charAt(i);
        return string;
    }
    
    /**
     * Creates the Cyclic array of the input text.
     * @param str - String from the entire input text
     * @return - Array containing the different permutations of the input text
     */
    public static String[] formCyclicArray(String str) {
        int n = str.length();
        String[] cyclic_array = new String[n];
        StringBuffer rotated = new StringBuffer(str);
        cyclic_array[0] = str;
        for(int i = 1; i < n; i++) {
            rotated.insert(0,rotated.charAt(n-1));
            rotated.deleteCharAt(n);;
            cyclic_array[i] = stringanate(rotated);
        }
        return cyclic_array;
    }
    
    /**
     * Sorts an array using Collections.sort(list) in alphabetical order.
     * @param array - Array to be sorted
     * @return - Sorted array in alphabetical order
     */
    public static String[] sort(String[] array) {
        ArrayList<String> list = new ArrayList<String>();
        String[] sorted = new String[array.length];
        for(int i = 0; i < array.length; i++)
            list.add(array[i]);
        Collections.sort(list);
        for(int j = 0; j < array.length; j++)
            sorted[j] = list.get(j);
        return sorted;
    }
    
    /**
     * Gathers the Burrows-Wheeler Transform from the Sorted Array
     * @param sorted - Sorted array from the input text
     * @return - An array that contains the characters of the Burrows-Wheeler transform.
     */
    public static char[] bwTransform(String[] sorted) {
        char[] bwt = new char[sorted.length];
        int lastDigit = sorted.length - 1;
        StringBuffer temp;
        for(int i = 0; i < sorted.length; i++) {
            temp = new StringBuffer(sorted[i].toString());
            bwt[i] = temp.charAt(lastDigit);
        }
        return bwt;
    }
    
    /**
     * Move to front method to help with encoding the BWT.
     * @param array - An ArrayList that is used to help move the characters in the BWT to the front when encoding.
     * @param pos - Position of the moved character initially. 
     * @return - Returns the array with the moved character.
     */
    public static ArrayList<Character> moveFront(ArrayList<Character> array, int pos) {
        array.add(0, array.get(pos));
        array.remove(pos + 1);
        return array;
    }
    
    /**
     * Encodes the BWT using the ASCII for a great compression.
     * @param trans - the BWT array
     * @return - The array that contains the encoded characters.
     */
    public static int[] encode(char[] trans) { 
        int[] encoded = new int[trans.length];
        ArrayList<Character> ascii = new ArrayList<Character>();
        for(int i = 0; i < 256; i++)
            ascii.add((char)i);
        for(int j = 0; j < trans.length; j++) {
            int encNum = ascii.indexOf(trans[j]);
            encoded[j] = encNum;
            ascii = moveFront(ascii, encNum);
        }
        return encoded;
    }
    
    /**
     * Used in the decompression process
     * @param sorted - The sorted array of the input text.
     * @param bwt - the BWT character array.
     * @return - An integer array containing the integers needed to decompress.
     */
    public static int[] decodeInts(String[] sorted, char[] bwt) {
        int sort_length = sorted.length;
        int next = 0;
        ArrayList<Character> bwt_list = new ArrayList<Character>();
        for(int x = 0; x < sort_length; x++)
            bwt_list.add(bwt[x]);
        ArrayList<Integer> deco = new ArrayList<Integer>();
        int[] decod = new int[sort_length];
        for(int i = 0; i < sort_length; i++){
            char temp = sorted[i].charAt(0);
            for(int j = 0; j < sort_length; j++) {
                if (temp == bwt_list.get(j) && !deco.contains(j)) {
                    next = j;
                    break;
                }
                else
                    continue;
            }
            deco.add(next);       
        }
        for(int p = 0; p < sort_length; p++)
            decod[p] = deco.get(p);
        return decod;
    }
    
    /**
     * The method used to decode the encoded array after compression is complete.
     * @param bwt - the BWT array
     * @param next - the array received from the method decodeInts
     * @param key - the position in the sorted array of the original text.
     * @return - String that contains the original text drawn from the input file.
     */
    public static String decode(char[] bwt, int[] next, int key) {
        StringBuffer decoded_buffer = new StringBuffer();
        int x = key;
        for(int i = 0; i < bwt.length; i++) {
            x = next[x];
            decoded_buffer.append(bwt[x]);
        }
        return stringanate(decoded_buffer);
    }
    
    /**
     * Used to find the original text in the sorted array of the cyclic texts.
     * @param sarray - the sorted array.
     * @param text - the original text drawn from input file.
     * @return - the position in the Sorted array of original text.
     */
    public static int originalTextKey(String[] sarray, String text) {
        int key = -1;
        for(int i = 0; i < text.length(); i++) {
            if(sarray[i].equals(text))
                key = i;
        }
        return key;
    }
    
    /**
     * Method used to read the file and draw the text from it
     * @param file - the file to be opened and drawn from
     * @return - A string representation of the input text from the file.
     */
    public static String readFile(File file) {
        StringBuffer string = new StringBuffer();
        try {
            FileInputStream fis = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(fis);
            Reader read = new InputStreamReader(bis);
            BufferedReader buff = new BufferedReader(read);
            int c = 0;
            while(c != -1) {
                c = buff.read();
                string.append((char)c);
            }
            fis.close();
            bis.close();
            read.close();
            buff.close();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        string.setCharAt(string.length()-1, ' ');
        return string.toString();
    }
    
    /**
     * Main method using the methods to produce the Burrows-Wheeler Compression
     * @param args - Will be used to supply the input file.
     */
    public static void main(String[] args) {
        File input = new File(args[0]);
        try {
            PrintWriter out = new PrintWriter(new FileWriter("output.txt"));
            int text_key;
            String file_text, decodedString;
            String[] cyclicArray, suffixArray;
            char[] transform;
            int[] encoded, nextInts;
            file_text = readFile(input);
            cyclicArray = new String[file_text.length()];
            for(int i = 0; i < file_text.length(); i++)
                cyclicArray[i] = file_text;
            cyclicArray = formCyclicArray(file_text);
            out.println("Cycled Array:");
            out.println("--------------");
            for(int i = 0; i < file_text.length(); i++)
                out.println(cyclicArray[i]);
            suffixArray = sort(cyclicArray);
            text_key = originalTextKey(suffixArray, file_text);
            out.println();
            out.println();
            out.println("Suffix Array:");
            out.println("--------------");
            for(int j = 0; j < file_text.length(); j++)
            out.println(suffixArray[j]);
            transform = bwTransform(suffixArray);
            out.println();
            out.println("BWT: ");
            for(int x = 0; x < file_text.length(); x++)
                out.print(transform[x]);
            out.println();
            out.println();
            encoded = encode(transform);
            out.print("Encoded Array: {");
            for(int p = 0; p < file_text.length(); p++) {
                if( p != file_text.length() - 1)
                    out.print(encoded[p]+", ");
                else
                    out.print(encoded[p]);
            }    
            out.print("}");
            out.println();
            out.println();
            nextInts = decodeInts(suffixArray, transform);
            out.print("Next: {");
            for(int h = 0; h < file_text.length(); h++) {
                if( h != file_text.length() - 1)
                    out.print(nextInts[h]+", ");
                else
                    out.print(nextInts[h]);
            }    
            out.print("}");
            out.println();
            out.println();
            decodedString = decode(transform, nextInts, text_key);
            out.println("Decoded String: ");
            out.println(decodedString);
            out.close();
        }
        catch(IOException e) {
            e.printStackTrace();
        }          
    }    
}
