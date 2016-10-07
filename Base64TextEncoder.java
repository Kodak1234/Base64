package ascii.base64;

import java.util.ArrayList;

public class Base64TextEncoder {
	
	private TextEncodeInterface textEncoder;

	public Base64TextEncoder(TextEncodeInterface inter) {
		this.textEncoder = inter;
	}
	
	public String encode(String s){
		
		//Convert the string to character array
		int[] c = textEncoder.convertToAscii(s);
		
		//Covert each character to a binary string
		StringBuilder sc = new StringBuilder();
		for(int a: c){
			sc.append(textEncoder.toBinaryString(a));
		}
		
		//Group the binary string and return it as an array
		String[] sg = textEncoder.group(sc.toString());
		
		//Convert each binary string to an integer
		ArrayList<Integer> index = new ArrayList<>();
		for(String in: sg){
			index.add(textEncoder.toDecimal(in));
		}
		
		//Return the base64 index
		StringBuilder s64 = new StringBuilder();
		for(Object a: index.toArray()){
			s64.append(textEncoder.toBase64((int)a));
		}
		
		//Adds == if there is any padding
		textEncoder.hasPadding(s64);
		
		return s64.toString();
	}
	
	public static TextEncodeInterface getDefaultEncoder(){
		return new DefaultEncoder();
	}
	
	
	
	private static class DefaultEncoder implements TextEncodeInterface{
		
		private int paddingCount = 0;
		private IndexTable table = new IndexTable();

		@Override
		public int[] convertToAscii(String s) {
			int[] c = new int[s.length()];
			
			for(int a = 0; a < s.length(); a++){
				c[a] = (int)s.charAt(a);
			}
			
			return c;
		}

		@Override
		public String toBinaryString(int c) {
			String binary = Integer.toBinaryString(c);
			while(binary.length() < 8){
				binary = "0" + binary;
			}
			
			return binary;
		}

		@Override
		public String[] group(String c) {
			ArrayList<String> g = new ArrayList<>();
			
			int leftOver = c.length() % 6, bound = c.length()/6 ,lb = 0, hb= 6;
			//abcdef gh
			
			while(bound > 0){
				bound--;
				
				g.add(c.substring(lb,hb));
				
				lb += 6;
				hb += 6;
			}
			
			if(leftOver != 0){
				g.add(pad(c.substring(lb)));
			}
			
			return g.toArray(new String[g.size()]);
		}

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

		@Override
		public char toBase64(int index) {
			return table.get(index);
		}

		@Override
		public void hasPadding(StringBuilder s) {
			for(int a = paddingCount /2; a > 0; a--){
				s.append("=");
			}
			
			paddingCount = 0;
			
		}
		
		private String pad(String s){
			StringBuilder b = new StringBuilder(s);
			
			while(b.length() < 6){
				b.append("0");
				paddingCount++;
			}
			
			return b.toString();
		}
		
		
	}

}
