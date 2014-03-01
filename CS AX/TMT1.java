import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Random;


public class TMT1 {
	public static final String plaintext = "0x5265";
	public static final int N = 1024;
	public static final int L = 1024;
	public static final int T = 65536;
	
	public static void main(String[] args) {
		if(args.length < 1){
			System.err.println("Usage: TMT1 <output_file_name>");
		}
	
		//prepare output file
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(args[0], "UTF-8");
		} catch (FileNotFoundException | UnsupportedEncodingException e1) {
			e1.printStackTrace();
			System.exit(1);
		}
		
		//build table
		Random rand = new Random();
		int p = Hex16.convert(plaintext);
		//calculate chains
		for(int i = 0; i < N; i++){
			int x0 = rand.nextInt(T +1);
			int c = 0; //cipher text
			int k = x0; //previous cipher (used as key)
			for(int j = 0; j < L; j++){
				c = Coder.encrypt(k, p);
				k= c;
			}
			//add an X_l, X0 pair (a chain) to the table
			writer.println(c + " " + x0);
			
		}
		
		//close resources
		writer.close();
		
		

	}

}
