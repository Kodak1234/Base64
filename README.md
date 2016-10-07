<h1> Base 64 </p>
<h4> Encoding a text </h4>
<p> Class for encoding a text</p>
<p>Intantiate it passing in a custom TextEncodeInterface or use the default encoder by call Base64TextEncoder.getDefaultEncoder()</p>

<strong>

Base64TextEncoder encode = new Base64TextEncoder(Base64TextEncoder.getDefaultEncoder());

String en = encode.encode("Man is distinguished, not only by his reason, but by this singular passion from");

</strong>


<h4> Decoding a text </h4>
<p> Class for decoding a text</p>
<p>Intantiate it passing in a custom TextDecodeInterface or use the default decoder by call Base64TextDecoder.getDefaultdecoder()</p>

<strong>
Base64TextDecoder decoder = new Base64TextDecoder(Base64TextDecoder.getDefaultDecoder());

String d = decoder.decode(en)
</strong>

<h4> Encoding a binary data </h4>

<p> Class for encoding binary data. Passing a custom BinaryEncodeInterface or call Base64BinaryEncoder.getDefaultEncoder() to use the default encoder.</p>

<strong>
Base64BinaryEncoder binaryEncoder = new Base64BinaryEncoder(Base64BinaryEncoder.getDefaultEncoder());

binaryEncoder.encode(file);
</strong>





	
