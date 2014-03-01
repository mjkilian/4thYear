import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class KPT{

    public static final int noPossibleKeys = 65536;

    public static void main(String[] args){
        String plainBlock = "";
        String cipherBlock = "";

        //read in cipher text file
        if(args.length < 2){
            System.out.println("Usage: KPT <plaintext_file> <cipher_filename>");
        }
        try{
            File pIn = new File(args[0]);
            Scanner pScan = new Scanner(pIn);
            plainBlock = pScan.nextLine();
            pScan.close();
    
            File cIn =  new File(args[1]);
            Scanner cScan = new Scanner(cIn);
            cipherBlock = cScan.nextLine();
            cScan.close();
        }catch(IOException e){
            System.err.println("Files could not be found or read.");
        }

        int pAsInt = Hex16.convert(plainBlock);
        int cAsInt = Hex16.convert(cipherBlock);
        System.out.println("p: " + pAsInt);
        System.out.println("c: " + cAsInt);
        //brute force to find key
        for(int i = 1; i <= noPossibleKeys; i++){
            int decrypt = Coder.decrypt(i,cAsInt);
            if(decrypt == pAsInt){
                System.out.println("The key is 0x" + Integer.toHexString(i) + "!");
                break;
            }
        }
    }   
}     
