<h1> Base 64 </p>
<h4> Encoding a text </h4>
<p> Class for encoding a text</p>
<p>Intantiate it passing in a custom TextEncodeInterface or use the default encoder by call Base64TextEncoder.getDefaultEncoder()</p>

Base64TextEncoder encode = new Base64TextEncoder(Base64TextEncoder.getDefaultEncoder());

String en = encode.encode("Man is distinguished, not only by his reason, but by this singular passion from");


<h4> Decoding a text </h4>
<p> Class for decoding a text</p>
<p>Intantiate it passing in a custom TextDecodeInterface or use the default decoder by call Base64TextDecoder.getDefaultdecoder()</p>
	
Base64TextDecoder decoder = new Base64TextDecoder(Base64TextDecoder.getDefaultDecoder());
		
String d = decoder.decode(en)


<h4> Encoding a binary data </h4>

<p> Class for encoding binary data. Passing a custom BinaryEncodeInterface or call Base64BinaryEncoder.getDefaultEncoder()<br> to use the default encoder.</p>

Base64BinaryEncoder binaryEncoder = new Base64BinaryEncoder(Base64BinaryEncoder.getDefaultEncoder());

<p>Call encode() and pass the file you wish two encode. The methods returns the encoded file</p>
binaryEncoder.encode(file);

<h4> Implementing custom TextEncodeInterface </h4>

<p> Implement this interface and do what each method says. Some method are called multiples times during the encoding process.</p>
<p> Don't call the methods directly</p>
<p>public interface TextEncodeInterface {
	
	//Convert the string s to ASCII array characters
	int[] convertToAscii(String s);
	
	//Convert integer c to a binary string
	String toBinaryString(int c);
	
	//Group the binary strings in String c into 6 bits and add any required padding
	String[] group(String c);
	
	//Convert 6 bits binary string to a decimal number
	int toDecimal(String c);
	
	//Return the base64 character that corresponds to the index
	char toBase64(int index);
	
	//If there was any padding, add = to the encoded string s.
	void hasPadding(StringBuilder s);
}</p>


<h4> Implementing custom TextEncodeInterface </h4>

<p> Implement this interface and do what each method says. Some method are called multiples times during the encoding process </p>
<p> Don't call the methods directly</p>
<p>public interface TextDecodeInterface {
	
	//Return base64 index of the character c
	int toBase64Index(char c);
	
	//Convert the base64 index d to a binary string
	String toBinaryString(int d);
	
	//Remove any padding from the binary string
	//Padding count is the number of padding the stringBuilder
	//Example paddingCount = 4 means there are 0000 padded to the string.
	void removePadding(StringBuilder s,int paddingCount);
	
	//Convert an 8 bit binary string to decimal
	int toDecimal(String binary);
	
	//Convert a decimal number to an ascii character
	char toAscii(int d);

}</p>


<h4> Implementing custom TextEncodeInterface </h4>

<p> Implement this interface and do what each method says. Some method are called multiples times during the encoding process </p>
<p> Don't call the methods directly</p>
<p>public interface BinaryEncodeInterface {
	//Convert an int to 8 bit binary string
	String toBinaryString(int b);
	
	//Add any necessary padding to the binary string file.
	void pad(FileOutputStream out) throws IOException ;
	
	//Convert 6 bit binary string b to a decimal number
	int toDecimal(String b);
	
	//Return a bse64 character with the given index
	char toBase64(int index);
	
	//Adds = sign to the encoded file if there was a padding 
	void hasPadding(FileOutputStream output)throws IOException;
}</p>




	
