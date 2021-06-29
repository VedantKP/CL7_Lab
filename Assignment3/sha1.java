/*
Name: Vedant Puranik
Class: BE-9
Roll No: 43152
Batch: R9
Assignment 3: SHA-1 algorithm in Java
*/

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class sha1 {
    public static String hash(String input){
        try {
            
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            
            byte[] digMess = md.digest(input.getBytes());
            
            BigInteger bi = new BigInteger(1,digMess);
            
            String hash = bi.toString(16);
    
            //while (hash.length() < 32) //Pad with 0s until 32 bits 
            //    hash = "0"+hash;
            return hash;
        }
        catch (NoSuchAlgorithmException e){
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a string => ");
        String str1 = sc.nextLine();
        System.out.println("Hash of "+str1+" is => "+hash(str1));
        sc.close();
    }
}

/*
OUTPUT

Enter a string => Vedant
Hash of Vedant is => d4e5d124a6074c097a9a4dd39a6197afd2f2736b

*/