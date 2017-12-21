
/*
Copyright (c) 2017 Geometrically

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
package com.geometrically;

import javax.xml.bind.DatatypeConverter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Main {
    private static final String dir = System.getProperty("user.dir");

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Hash(SHA-256): ");
        String enteredHash =scanner.nextLine();
        long startTime = System.currentTimeMillis();
        boolean found = false;
        String pathName = dir + "\\" + args[0];
        try(BufferedReader in = new BufferedReader(new FileReader(pathName))) {
            String line;
            while ((line = in.readLine()) != null) {
                try {
                    MessageDigest digest = MessageDigest.getInstance("SHA-256");
                    byte[] hash1 = digest.digest(line.getBytes(StandardCharsets.UTF_8));
                    String encrypted = DatatypeConverter.printHexBinary(hash1);
                    String encrypted1 = null;
                    if(enteredHash.equals(encrypted)){
                        long endTime = System.currentTimeMillis();
                        System.out.println("Password:\"" + line + "\" ---> Program took " +(endTime - startTime) + " ms");
                        found = true;
                    }
                } catch (NoSuchAlgorithmException e){
                    e.printStackTrace();
                }
            }
        } catch(IOException e){
            e.printStackTrace();
        }
        if(!found){
            long endTime = System.currentTimeMillis();
            System.out.println("The password could not be found ---> Program took " +(endTime - startTime) + " ms");
        }
    }
}

