package parsers;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public abstract class ParserUtils {
	
	protected static Scanner buildFileReader(String filename){
		//set up a file reader
		Scanner scan = null;
		try{
			scan = new Scanner(new File(filename));
		}catch(IOException e){
			System.err.println("Problem reading file `"+ filename +"'. Please check it exists");
			e.printStackTrace();
			System.exit(0);
		}
		
		return scan;
	}

}
