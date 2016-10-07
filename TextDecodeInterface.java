package ascii.base64;

public interface TextDecodeInterface {
	
	//Return base64 index of the character
	int toBase64Index(char c);
	
	//Convert the index to a binary string
	String toBinaryString(int d);
	
	//Remove any padding from the binary string
	void removePadding(StringBuilder s,int paddingCount);
	
	//Convert an 8 bit binary string to decimal
	int toDecimal(String binary);
	
	//Convert a decimal number to an ascii character
	char toAscii(int d);

}
