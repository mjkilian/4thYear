import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class CTO {
	public static final int noKeysToTry = 65536;

	
	/**Assigns a score to a possible message based on the frequency of its component
	 * n-grams. 
	 * @param n
	 * @param table
	 * @param text
	 * @return
	 */
	public static double ngramScore(int n, Map<String,Double> table,String text){
		double score = 0.0;
		for(int i = 0; i + n < text.length() + 1; i++){
			String ngram = text.substring(i, i+n).toLowerCase();
			if(table.get(ngram) != null){
				score +=  Math.round(table.get(ngram) * Math.pow(10, n));
			}
		}
		return score;
	}
	
	/**Checks whether a piece of text is valid english*/
	public static boolean isEnglish(String text,Collection<String> charset){
		boolean valid = true;
		for(int i = 0; i < text.length(); i++){
			String c = Character.toString(text.charAt(i)).toLowerCase();
			if(!charset.contains(c)){	
				valid = false;
				break;
			}
			
		}
		return valid;
	}
	
	/**Builds a frequency table from a file*/
	public static Map<String,Double> buildFrequencyTable(String filename){
		Map<String,Double> table;
		File in;
		Scanner scan = null;
		
		table = new HashMap<String,Double>();
		try{
			in = new File(filename);
			scan = new Scanner(in);
		}catch(IOException e){
			System.err.println("File '" + filename+"' could not be found.");
			e.printStackTrace();
		}
		
		
		if(scan.hasNextLine()){
			String[] grams = scan.nextLine().split(" ");
			double i = 0;
			for(String ngram : grams){
				if(ngram.equals("SPC")){
					table.put(" ", grams.length-i);
				}else if(ngram.equals("ENT")){
					table.put("\n", grams.length-i);
				}else{
					table.put(ngram.toLowerCase(),grams.length-i);
				}
				i++;
			}
				
		}
		scan.close();
		return table;
	}
		
	public static String decryptToText(String[] blocks,int key){
		String output = "";
		for(String block: blocks){
			if(block == null){
				continue;
			}
			int	c = Hex16.convert(block);
			int	p = Coder.decrypt(key, c);
			String	decrypted = String.format("0x%04x", p);
			
			int	i = Hex16.convert(decrypted);
			int	c0 = i / 256;
			int	c1 = i % 256;
			output += Character.toString((char)c0);
			if (c1 != 0)
				output += Character.toString((char) c1);
		}
		return output;
	}
	public static void main(String[] args) {
		if(args.length < 2){
			System.err.println("Usage: CTO <cipher_filename> noBlocksToDecrypt");
		}
		
		//initialise frequency tables
		Map<String, Double> monograms;
		
		monograms = buildFrequencyTable("frequency-data/monograms-freq.txt");
		//bigrams = buildFrequencyTable("frequency-data/bigrams-freq.txt");
	
		//read in cipher text blocks
		File in;
		int noBlocksToDecrypt = Integer.parseInt(args[1]);
		Scanner scan = null;
		try{
			in = new File(args[0]);
			scan = new Scanner(in);
		}catch(IOException e){
			System.err.println("Cipher text file could not be read.");
			e.printStackTrace();
			System.exit(1);
		}
		
		String cipherTextBlocks[]  = new String[noBlocksToDecrypt];
		int noBlocksRead = 0;
		while(scan.hasNextLine() && noBlocksRead < noBlocksToDecrypt){
			cipherTextBlocks[noBlocksRead] = scan.nextLine();
			noBlocksRead++;
		}
		

	//main brute force loop
		String bestMatch = "";
		double bestScore = 0.0;
		int bestKey = -1;
		for(int k = 0; k < noKeysToTry ; k++){
			String p = decryptToText(cipherTextBlocks,k);
			double score = 0.0;
			if(!isEnglish(p,monograms.keySet())){
				continue;
			}
			//do ngram scores (monograms to trigrams)
			score +=ngramScore(1,monograms,p);
			//score +=ngramScore(2,bigrams,p);
			
			if (score > bestScore){
				bestScore = score;
				bestMatch = p;
				bestKey = k;
			}
		}
		System.out.println("Message: `" + bestMatch + "'");
		System.out.println("Key: 0x" + Integer.toHexString(bestKey));
		
	}

}
