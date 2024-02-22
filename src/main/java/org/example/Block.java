package org.example;

import java.util.Arrays;

public class Block {
	
	short slot;
	  
	int dirty;
	  
	int valid;
	  
	int tag;
	  
	short[] data = new short[16];

	public Block(short slotNum) {
		  
		this.slot = slotNum;
	    
		Arrays.fill(this.data, (short) 0);
	    
	}

	public void loadMemory(Address address, short[] cacheData) {
		  
		this.tag = address.tag;
	    
		this.valid = 1;
	    
		for (short i = 0; i < cacheData.length; i++) {
	    	
			this.data[i] = cacheData[i];
	      }
		}

	public void update(int offset, short data) {
		  
		this.data[offset] = data;
	  }

	public String dataToString() {
		  
		String array = "";
	    
		for (short i = 0; i < this.data.length; i++) {
	    	
			if (Integer.toHexString(this.data[i]).length() != 2) {
	    	  
				array += " " + Integer.toHexString(this.data[i]);
	        
			} else {
	    	  
				array += Integer.toHexString(this.data[i]);
	        
			}
			if (i != this.data.length - 1) {
	    	  
				array += ", ";
				}
			}
		
		return array;
	  }

	public String toString() {
		  
		return Integer.toHexString(this.slot) + "    " + this.valid + "     " + this.tag + "       " + this.dataToString();
		}
}
