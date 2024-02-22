package org.example;

public class Address {
	
	  public int raw;
	  
	  public int offset;
	  
	  public int beginAddress;
	  
	  public int tag;
	  
	  public int slot;

	  public Address(int address) {
		  
	    this.raw = address;
	    
	    this.offset = address & 0xF;
	    
	    this.beginAddress = address & 0xFFF0;
	    
	    this.tag = address >>> 8;
	    
	    this.slot = (address & 0xF0) >>> 4;
	    
	  }
}
