package project1;

import java.util.Scanner;
public class BruteForce
{
    public static void main(String args[])
    {
        

        Scanner sc = new Scanner(System.in);
        int i,j,k=0,index,count=0; 
        /* i - outer 'for-loop' control variable'
         * j - inner 'for-loop' control variable'
         * k - index for genome's characters (during matching)
         * index - required position(s)
         * count - no. of matches
         */                
        String genome = "",pattern;
        boolean flag=true; //flag - checks if pattern is found in the genome
        
        //System.out.println("Enter the genome");
        try{genome=GenomeSearch.fastaConvert();}
        catch(Exception e) {
            System.out.println(e);
        }
        genome=genome.toUpperCase();
        System.out.println("Enter the search pattern");
        pattern=sc.next();
        long startTime = System.nanoTime();
        pattern=pattern.toUpperCase();
        System.out.print("Required positions are: ");
        for(i=0; i<genome.length()-pattern.length()+1; i++) // outer 'for-loop' to iterate through the genome
        {
            if(count==10)
             count = 0;
            k=i;
            index=i;
            for(j=0; j<pattern.length(); j++) // inner 'for-loop' to iterate through the pattern
            {
                if(pattern.charAt(j)!=genome.charAt(k)) //check for mismatches in the corresponding characters of pattern and genome
                {
                    flag=false;
                    break;
                }
                else 
                 flag=true;
                k++;
            }
            if(flag==true) // pattern is found
            {
                count++;
                System.out.print(index + " ");
                if(count==10) //Print only 10 positions per line (for readability)
                {
                    System.out.println();  
                }
            }
        }
        if(count==0) // no occurences of the pattern in the genome
        	System.out.print("NIL");
        System.out.println();
        long stopTime = System.nanoTime();
        System.out.println("Execution Time in nanoseconds : ");
        System.out.println(stopTime - startTime);
    }
}