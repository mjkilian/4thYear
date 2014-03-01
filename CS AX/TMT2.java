import java.io.File;
import java.io.IOException;
import java.util.Scanner;


public class TMT2 {
	public static final int N = 1024;
	public static final int L = 1024;
	public static final String plaintext = "0x5265";
	

	public static void main(String[] args) {
		if(args.length < 2){
			System.err.println("Usage: TMT2 <table-filename> <cipher-filename>");
			System.exit(1);
		}
		File tableFile = new File(args[0]);
		File cipherFile = new File(args[1]);
		
		//read in table
		Table table = new Table();
		Scanner scan = null;
		try{
			scan = new Scanner(tableFile);
			while(scan.hasNextLine()){
				String[] entry = scan.nextLine().split(" ");
				table.add(Integer.parseInt(entry[0]),
						Integer.parseInt(entry[1]));
			}
		}catch(IOException e){
			System.err.println("Could not read table file.");
			e.printStackTrace();
		}
		scan.close();
		
		//read cipher block
		String cipherBlock = "";
		try{
			scan = new Scanner(cipherFile);
			if(scan.hasNextLine()){
				cipherBlock = scan.nextLine();
			}
		}catch(IOException e){
			System.err.println("Could not read cipher block");
			e.printStackTrace();
		}
		
		int p = Hex16.convert(plaintext);
		int c = Hex16.convert(cipherBlock);
		int x0 = -1;
		int keyPos = -1;
		
		//check table
		for(int i = 0; i < L; i++){
			if(table.find(c) != -1){
				x0 = table.find(c);
				keyPos = i;
				break;
			}else{
				c = Coder.encrypt(c,p);
			}
		}
		
		if(keyPos == -1){
			System.out.println("Key is not in table");
		}
		
		//now have x0, can work through chain
		int key = x0;
		for(int i = 0; i < (L-keyPos-1) ; i++){
			c = Coder.encrypt(key,p );	
			key = c;
		}
		
		
		System.out.println("Key is : 0x" + Integer.toHexString(key));
		
		//check key is correct
		int decrypted = Coder.decrypt(key, Hex16.convert(cipherBlock));
		if(decrypted == p){
			System.out.println("Key found successfully!");
		}else{
			System.out.println("Key is wrong");
		}
		
	}

}
