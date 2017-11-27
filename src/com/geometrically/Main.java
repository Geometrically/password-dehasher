
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
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static String enteredHash;

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        final String dir = System.getProperty("user.dir");
        //Scanner for encrypted password
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Hash(SHA-256): ");
        enteredHash =scanner.nextLine();
        //File Reading
        FileReader fr = new FileReader(dir + "\\src\\dictionary.txt");
        BufferedReader in = new BufferedReader(fr);
        String str;
        int encryptIndex = 0;

        List<String> list = new ArrayList<String>();
        while((str = in.readLine()) != null){
            //add strings
            list.add(str);
        }
        String[] stringArr = list.toArray(new String[0]);
        System.out.println( "Things read:" + Arrays.toString(stringArr));

        //Encryption
        List<String> encryptList = new ArrayList<String>();
        for(String s : stringArr){
            //encrypt into encrypted arrays
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(s.getBytes(StandardCharsets.UTF_8));

            encryptList.add(DatatypeConverter.printHexBinary(hash));

        }
        String[] encryptArr = encryptList.toArray(new String[0]);

        System.out.println("Encrypted Strings:" + Arrays.toString(encryptArr));
        //Add Correspondence to array values
        for(int i=0;i<encryptArr.length;i++){
            if(encryptArr[i].equals(enteredHash)){
                encryptIndex = i;
                break;
            }
        }
        System.out.println("Deencrypted Hash: " + stringArr[encryptIndex]);
    }
}

