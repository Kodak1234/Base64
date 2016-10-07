package ascii.base64;

import java.io.FileOutputStream;
import java.io.IOException;

public interface BinaryEncodeInterface {
	//Convert an int to 8 bit binary string
	String toBinaryString(int b);
	
	//Add any necessary padding to the binary string file
	void pad(FileOutputStream out) throws IOException ;
	
	//Convert 6 bit binary string to a decimal number
	int toDecimal(String b);
	
	//Return a bse64 character with the given index
	char toBase64(int index);
	
	//Adds = sign to the encoded file if there was a padding 
	void hasPadding(FileOutputStream output)throws IOException;
}
