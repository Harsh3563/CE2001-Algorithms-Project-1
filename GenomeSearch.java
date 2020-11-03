package project1;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class GenomeSearch {
	
	public static void search(String genome, String seq) {
		int sSize = seq.length(); //Find length of the sequence
		int i = 0; //index for Sequence
		int j = 0; //index for Genome
		int k = 0; //To enter a new line in the printing
		
		genome += "\0"; // Add a terminating char
		int prefix[] = new int[sSize];
		
		prefixTable(seq, sSize, prefix); //Creating the prefix table for the pattern
		
		System.out.println("Sequence found at Position: ");
		
		while(genome.charAt(j) != '\0') {
			if(genome.charAt(j) == seq.charAt(i)) {
				j++;
				i++;
				if(i == sSize) { //Sequence found
					System.out.print(j - i + ", ");
					k++;
					if (k == 10) { //Print only 10 positions per line (for readability)
						System.out.println();
						k = 0;
					}
					i = prefix[i-1]; //i - 1 because i is the size of the seq
					// So that there is no need to backtrack in Genome
				}
			}
			else { //Similar to in prefix table method
				if(i == 0) {
					j++; //Move on to next char in genome
				}
				else {
					i = prefix[i-1]; //Backtrack until common char found
				}
			}
		}
		System.out.println("No more Sequences found !!");
	}
	
	public static void prefixTable(String seq, int len, int prefix[]) {
		
		int pat = 0; // Size of the longest pattern
		
		//Start at 1 since should not match char 0 with itself
		int i = 1;
		while(i < len) {
			if(seq.charAt(i) == seq.charAt(pat)) {
				pat++;
				prefix[i] = pat;
				i++;
			}
			else {
				if(pat == 0) { //Move onto the next char in pattern
					i++;
				}
				else {
					pat = prefix[pat-1]; //Backtrack until common char found
				}
			}
		}
		
	}
	
	public static String inputSeq() { //Ask user to input in the sequence to find
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter the sequence to search for: ");
		String seq = sc.nextLine();
		sc.close();
		return seq;
	}
	
	public static String fastaConvert() throws FileNotFoundException { //Converting the fna file to a string
		System.out.println("Processing Genome, Please Wait...");	
		String text = "";
			try (Scanner sc = new Scanner(new File("Genome.fna"))) {
	            while (sc.hasNextLine()) {
	                text = text.concat(sc.nextLine().trim());
	            }
	        }
			return text;
	}
	
	public static void main(String []args) {
		String genome = "";
		try{genome = fastaConvert();}
		catch(Exception e) {System.out.println("File not found!!!"); }
		String seq = inputSeq();
		if (genome != "") { //to make sure genome is not empty
			long startTime = System.nanoTime();
			search(genome, seq);
			long stopTime = System.nanoTime();
	        System.out.println("Execution Time in nanoseconds : ");
	        System.out.println(stopTime - startTime);
		}
	}
}
