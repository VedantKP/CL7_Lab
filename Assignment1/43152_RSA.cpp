/*
Name: Vedant Puranik
Class: BE-9
Roll No: 43152
Batch: R9
Assignment 1: RSA Algorithm in C++
*/

#include<iostream>
#include<math.h>
using namespace std;

int gcd(int x, int y) 
{
   int remain;
   while(1) 
   {
      remain=x%y;
      if(remain==0)
        return y;
      x=y;
      y=remain;
   }
}

int main() 
{
   double p=23,q=7; //Taking two prime numbers p and q
   double n=p*q; //Calculating n
   double value;
   double phi=(p-1)*(q-1); //Calculating phi
   double e=2; //e is the encryption key
   
   while(e<phi) {
      value = gcd(phi,e); //Find GCD of the two numbers
      if(value==1) break;
      else e++; //Increment e till e and phi are co-prime
   }
   
   //Compute value of d as per formula
   double dtemp=1/e;
   double d=fmod(dtemp,phi);
   
   double message=85; //Plaintext input

   cout<<"\nOriginal Message => "<<message;
   cout<<"\n\np is => "<<p;
   cout<<"\nq is => "<<q;
   cout<<"\nn is => "<<n;
   cout<<"\nphi is => "<<phi;

   double c=pow(message,e); //encrypt
   double m=pow(c,d); //decrypt
   c=fmod(c,n);
   m=fmod(m,n);

   cout<<"\n\nValue of e is => "<<e;
   cout<<"\nValue of d is => "<<d;
   cout<<"\n\nEncrypted Message => "<<c;
   cout<<"\nDecrypted Message => "<<m;
   return 0;
}

/*
OUTPUT

Original Message => 85                                                                                                          
                                                                                                                                
p is => 23                                                                                                                      
q is => 7                                                                                                                       
n is => 161                                                                                                                     
phi is => 132                                                                                                                   
                                                                                                                                
Value of e is => 5                                                                                                              
Value of d is => 0.2                                                                                                            
                                                                                                                                
Encrypted Message => 29                                                                                                         
Decrypted Message => 85 

*/