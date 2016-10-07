package ascii.base64;

public interface TextEncodeInterface {
	
	//Convert the string to ASCII array characters
	int[] convertToAscii(String s);
	
	//Convert each character to a binary string
	String toBinaryString(int c);
	
	//Group the binary strings into 6 bits and add any required padding
	String[] group(String c);
	
	//Convert each grouped 6 bits to a decimal number
	int toDecimal(String c);
	
	//Return the base64 string using the indexes to look it up
	char toBase64(int index);
	
	void hasPadding(StringBuilder s);
}
