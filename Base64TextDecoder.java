package ascii.base64;

import java.util.ArrayList;

public class Base64TextDecoder {

	private TextDecodeInterface textDecoder;
	private int paddingCount;

	public Base64TextDecoder(TextDecodeInterface textDecoder) {
		this.textDecoder = textDecoder;
	}

	public String decode(String s) {
		paddingCount = 0;
		ArrayList<Integer> index = new ArrayList<>();

		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);

			if (c == '=') {
				paddingCount += 2;
				continue;
			}

			index.add(textDecoder.toBase64Index(c));
		}

		StringBuilder binary = new StringBuilder();

		for (int a : index) {
			binary.append(textDecoder.toBinaryString(a));
		}

		textDecoder.removePadding(binary, paddingCount);
		index.clear();

		while (binary.length() > 0) {
			index.add(textDecoder.toDecimal(binary.substring(0, 8)));
			binary.delete(0, 8);

		}

		for (int d : index) {
			binary.append(textDecoder.toAscii(d));
		}

		return binary.toString();
	}

	public static DefaultTextDecoder getDefaultDecoder() {
		return new DefaultTextDecoder();
	}

	private static class DefaultTextDecoder implements TextDecodeInterface {

		private IndexTable table = new IndexTable();

		@Override
		public int toBase64Index(char c) {
			return table.index(c);
		}

		@Override
		public String toBinaryString(int d) {
			char[] c = Integer.toBinaryString(d).toCharArray();
			int in = c.length - 1;
			StringBuilder binary = new StringBuilder();

			while (binary.length() < 6 && in > -1) {
				binary.append(c[in]);
				in--;
			}

			while (binary.length() < 6) {
				binary.append("0");
			}

			return binary.reverse().toString();

		}

		@Override
		public void removePadding(StringBuilder s, int paddingCount) {
			for (int a = paddingCount; a > 0; a--) {
				s.delete(s.length() - 1, s.length());
			}

		}

		@Override
		public int toDecimal(String c) {
			int dec = 0;
			char[] arr = c.toCharArray();

			for (int a = c.length() - 1, exp = 0; a >= 0; a--, exp++) {
				int b = Integer.valueOf(String.valueOf((arr[a])));
				if (b == 0)
					continue;

				dec += Math.pow(2, exp);

			}

			return dec;
		}

		@Override
		public char toAscii(int d) {
			return (char) d;
		}

	}

}
