package ascii.base64;

import java.io.File;
import java.io.IOException;

public class Test {

	/*
	 * Steps of encoding ASCII character to base character:
	 * 
	 * Step 1: Convert character to ASCII character.
	 * 
	 * Step 2: Covert ASCII to binary string.
	 * 
	 * Step 3:Group into 6 bits.
	 * 
	 * Step 4:Convert each group to a decimal number.
	 * 
	 * Step 5: Look up the number in a base64 index table.
	 */

	public static void main(String[] args) throws IOException {
		Base64TextEncoder encode = new Base64TextEncoder(Base64TextEncoder.getDefaultEncoder());
		Base64BinaryEncoder binary = new Base64BinaryEncoder(Base64BinaryEncoder.getDefaultEncoder());
		//binary.encode(new File("C:\\Users\\user\\Desktop\\Flash Memory\\test.jpg"));
		
		Base64TextDecoder decoder = new Base64TextDecoder(Base64TextDecoder.getDefaultDecoder());
		
		String en = encode.encode("Man is distinguished, not only by his reason, but by this singular passion from");
		print(String.format("%30s %n %s %n","Encoded Text",en));
		
		print(String.format("%30s %n %s", "Decoded Text",decoder.decode(en)));
		
	}

	static void print(String s) {
		System.out.println(s);
	}
}
