
package ascii.base64;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/*
 * Author: Ume Db
 * Date: October 6, 2016
 * 
 * This class uses an BinaryEncodeInterface to encode a text. The encoding is not kept in memory it is
 * written immediately to disk file because so files can be very large so this implementation prevents
 * memory overflow when encoding huge files.
 * The class has no encoding implementation it only calls the methods of the BinaryEncodeInterface
 * and the calls that implemented BinaryEncodeInterface will perform the necessary action at each method call
 * and returns the necessary data.
 */

public class Base64BinaryEncoder {
	
	private BinaryEncodeInterface binaryEncoder;

	public Base64BinaryEncoder(BinaryEncodeInterface binaryEncoder) {
		this.binaryEncoder = binaryEncoder;
	}
	
	/*
	 * This method calls the methods of BinaryEncode interface which does the actual encoding.
	 */
	public File encode(File f) throws IOException{
		//BinaryString file
		File fBinary = new File(f.getParent(),"binaryString_"+getName(f.getName())+".txt");
		
		//Encoded string file
		File fEncoded = new File(f.getParent(),"encoded_"+getName(f.getName())+".txt");
		
		FileInputStream inputStream = new FileInputStream(f);
		FileOutputStream outputStream = new FileOutputStream(fBinary);
		
		//Reads a byte from the supplied file, convert it to a binaryString and write it to a new file
		int read = 0;
		while((read = inputStream.read()) != -1){
			outputStream.write(binaryEncoder.toBinaryString(read).getBytes());
		}
		
		inputStream.close();
		outputStream.flush();
		
		//Adds any necessary padding to the binaryString file
		binaryEncoder.pad(outputStream);
		outputStream.close();
		
		inputStream = new FileInputStream(fBinary);
		outputStream = new FileOutputStream(fEncoded);
		read = 0;
		
		//Reads six characters from the binary string file and convert it to decimal
		//and gets the base64 character that corresponds to the number and write it to the
		//encoded file.
		StringBuilder builder = new StringBuilder();
		while((read = inputStream.read()) != -1){
			if(builder.length() == 6){
				outputStream.write(binaryEncoder.toBase64(binaryEncoder.toDecimal(builder.toString())));
				builder.delete(0,builder.length());
			}
			
			char c = (char)read;
			
			builder.append(String.valueOf(c));
		}
		
		//if any character is left in the stringBuilder write it to encoded file
		if(builder.length() == 6){
			outputStream.write(binaryEncoder.toBase64(binaryEncoder.toDecimal(builder.toString())));
			builder.delete(0,builder.length());
		}
		
		outputStream.flush();
		//If any padding was added, add in the = signs.
		binaryEncoder.hasPadding(outputStream);
		
		inputStream.close();
		outputStream.close();
		
		//return the encoded file.
		return fEncoded;
	}
	
	private String getName(String f){
		String[] s = f.split("\\.");
		if(s.length == 0){
			return f;
		}
		else{
			return s[0];
		}
	}
	
	public static DefaultBinaryEncoder getDefaultEncoder(){
		return new DefaultBinaryEncoder();
	}
	
	
	private static class DefaultBinaryEncoder implements BinaryEncodeInterface{
		//Number of padding added to the binary string file
		private int paddingCount = 0;
		//Holds the base64 characters
		private IndexTable table = new IndexTable();

		//Convert an integer to 8 bit binary
		@Override
		public String toBinaryString(int b) {
			String binary = Integer.toBinaryString(b);
			while(binary.length() < 8){
				binary = "0" + binary;
			}
			
			return binary;
		}

		//Adds any necessary padding to the binary string file
		@Override
		public void pad(FileOutputStream out) throws IOException {
			FileChannel channel = out.getChannel();
			long size = channel.size();
			long leftover = size % Long.valueOf(6);
			
			while(leftover > 0 && (paddingCount + leftover) < 6){
				paddingCount++;
				out.write('0');
				out.flush();
				
			}
			
		}
		
		//Convert a 6 bit binary string to an integer which corresponds to an index
		//in base64 index table
		@Override
		public int toDecimal(String c) {
			int dec = 0;
			char[] arr = c.toCharArray();
			
			for(int a = c.length()-1,exp = 0; a >= 0; a--,exp++){
				int b = Integer.valueOf(String.valueOf((arr[a])));
				if(b == 0)
					continue;
				
				dec += Math.pow(2,exp);
					
				
			}
			
			return dec;
		}

		//Return a bse64 character that corresponds to the passed index
		@Override
		public char toBase64(int index) {
			return table.get(index);
		}

		//Adds any = sign if there was a padding added in the binary string file
		@Override
		public void hasPadding(FileOutputStream output) throws IOException {
			for(int a = paddingCount /2; a > 0; a--){
				output.write('=');
			}
			output.flush();
			
			paddingCount = 0;
			
		}
		
	}

}
