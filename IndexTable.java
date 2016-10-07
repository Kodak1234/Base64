package ascii.base64;

import java.util.Enumeration;
import java.util.Hashtable;

/*
 * This class represent base64 index table
 */

class IndexTable {
	private String ascii = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"
			+"0123456789+/";
	
	private Hashtable<Integer,Character> table = new Hashtable<>();
	private Hashtable<Character,Integer> indexTable = new Hashtable<>();
	
	IndexTable() {
		generateTable();
	}
	
	private void generateTable(){
		for(int i = 0; i < ascii.length(); i++){
			table.put(i,ascii.charAt(i));
		}
		
		Enumeration<Integer> v = table.keys();
		for(char k: table.values()){
			indexTable.put(k,v.nextElement());
		}
	}
	
	protected char get(int index){
		return table.get(index);
	}
	
	protected int index(char c){
		return indexTable.get(c);
	}

}
