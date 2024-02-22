package org.example;



public class Cache {
	
	 
	public static boolean test = false;

	static main_memory main_mem = new main_memory();
	 
	public static boolean isCacheHit(Address address) {
		  
		Block block = main_memory.getBlock(address.slot);
		    
		if (block.valid == 1 && block.tag == address.tag) {
		    
			return true;
		    
		}
		   
		return false;
		  
	}
		  
	public static boolean isDirty(Address address) {
		   
		Block block = main_memory.getBlock(address.slot);
		   
		if (block.dirty == 1) {
		    
			return true;
		   
		}
		    
		return false;
		 
	}
	
	public static void writeBack(Address address) {
		   
		Block block = main_memory.getBlock(address.slot);
		   
		Address writeBackAddress = new Address((block.tag << 8) + (block.slot << 4));
		   
		for (short i = 0; i < block.data.length; i++) {
		    
			if (main_mem.mainMemory[writeBackAddress.beginAddress + i] != block.data[i]) {
		     
				// System.out.println("\nUpdating address " + Integer.toHexString(address.beginAddress + i) + " new val: " + Integer.toHexString(block.data[i]) + ", old val: " + Integer.toHexString(mainMemory[address.beginAddress + i]));
		    
				main_mem.mainMemory[writeBackAddress.beginAddress + i] = block.data[i];
		     
			}
		    
		}
		  
	}	  
		  
	public static Address parseAddress(int rawAddress) {
			 
		Address address = new Address(rawAddress);
			  
		if (!test) {
			 
			System.out.println(Integer.toHexString(address.raw));
			 
		}
			 
		// System.out.println("address: " + Integer.toHexString(address.raw) + " offset: " + Integer.toHexString(address.offset) + " begin address: " + Integer.toHexString(address.beginAddress) + " tag: " + Integer.toHexString(address.tag) + " slotnum: " + Integer.toHexString(address.slot));
			 
		return address;
			 
	}
	 
}

