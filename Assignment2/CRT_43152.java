/*
Name: Vedant Puranik
Class: TE-9
Roll No: 43152
Batch: R9
Assignment 2: Chinese Remainder Theorem implementation in Java
*/

import java.util.*;
import java.lang.*;

public class CRT_43152
{
    static int a1,a2,a3,m1,m2,m3,M1,M2,M3;
    static int y1,y2,y3;
    static int M,x;

    public static int find_y(int M, int m)
    {
        int value;
        int y = 1; //serves as multiplier for mod value to find y

        while(true) //Loops till mod of M*y and m is 1
        {
            value = M * y;
            if((value%m)==1) //Break when mod is 1
                break;
            y++;
        }
        return y;
    }

    public static void take_input()
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a1 => ");
        a1 = sc.nextInt();
        System.out.print("Enter m1 => ");
        m1 = sc.nextInt();
        System.out.print("Enter a2 => ");
        a2 = sc.nextInt();
        System.out.print("Enter m2 => ");
        m2 = sc.nextInt();
        System.out.print("Enter a3 => ");
        a3 = sc.nextInt();
        System.out.print("Enter m3 => ");
        m3 = sc.nextInt();
        sc.close();
    }

    public static void main(String[] args)
    {
        take_input(); //Function to take input values for a1,a2,a3,m1,m2,m3
        System.out.println();
        System.out.println("Given problem: ");
        System.out.println("x = " + a1 + "(mod " + m1 + ")");
        System.out.println("x = " + a2 + "(mod " + m2 + ")");
        System.out.println("x = " + a3 + "(mod " + m3 + ")");
        System.out.println();

        M = m1*m2*m3; //Calcluate M
        
        //Calculate values of M1,M2,M3 as per formula
        M1 = M/m1; 
        M2 = M/m2;
        M3 = M/m3;

        //Function call to calculate values of y1,y2,y3
        y1 = find_y(M1,m1);
        y2 = find_y(M2,m2);
        y3 = find_y(M3,m3);

        System.out.println("M = m1*m2*m3 = " + M);
        System.out.println("M1 = M/m1 = " + M1);
        System.out.println("M2 = M/m2 = " + M2);
        System.out.println("M3 = M/m3 = " + M3);
        System.out.println();
        System.out.println("y1 is => " + y1);
        System.out.println("y2 is => " + y2);
        System.out.println("y3 is => " + y3);

        //Final value calculation of x
        x = (a1*M1*y1) + (a2*M2*y2) + (a3*M3*y3);
        x = x%M;

        System.out.println();
        System.out.println("x = " + x);
    }
}

/*
OUTPUT

Enter a1 => 1
Enter m1 => 5
Enter a2 => 1
Enter m2 => 7
Enter a3 => 3
Enter m3 => 11

Given problem:
x = 1(mod 5)
x = 1(mod 7)
x = 3(mod 11)

M = m1*m2*m3 = 385
M1 = M/m1 = 77
M2 = M/m2 = 55
M3 = M/m3 = 35

y1 is => 3
y2 is => 6
y3 is => 6

x = 36
*/