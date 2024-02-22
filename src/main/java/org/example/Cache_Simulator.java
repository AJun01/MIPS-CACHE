package org.example;

/*
 *This Java program is a simulation of a cache memory system,
 * designed to demonstrate how a cache interacts with main memory
 * during read and write operations.
 */
public class Cache_Simulator {
	

	static Cache cache = new Cache();
	
	static main_memory main_mem = new main_memory();

public static String read(int addressRaw) {
		 
	Address address = Cache.parseAddress(addressRaw);
		
	Block block = main_memory.getBlock(address.slot);
		
	String storedVal;
		
	String message;
		
	if (Cache.isCacheHit(address)) {
		
		storedVal = Integer.toHexString(block.data[address.offset]);
		
		message = "At that byte there is the value " + storedVal + " (Cache Hit)" + "\n";
	
	} else {
		
	if (Cache.isDirty(address)) { // write cache to memory before it is bumped out
		
		Cache.writeBack(address);
	
	}
		      
		block.loadMemory(address, main_mem.getRow(address.beginAddress));
		      
		storedVal = Integer.toHexString(main_mem.mainMemory[address.raw]);
		      
		message = "At that byte there is the value " + storedVal + " (Cache Miss)" + "\n";
		    
	}
		    
	if (!Cache.test) {
		      
		System.out.println(message);
		    
	}
		    
	return message;
		  
} 
		  
public static String write(int addressRaw, short data) {
			    
	Address address = Cache.parseAddress(addressRaw);
			    
	if (!Cache.test) {
			      
		System.out.println(data);
			   
	}
			    
	Block block = main_memory.getBlock(address.slot);
			    
	String message;
			    
	block.dirty = 1;
			   
	if (Cache.isCacheHit(address)) {
			    
		block.update(address.offset, data);
			     
		message = "Value " + data + " has been written to address " + Integer.toHexString(address.raw) + " (Cache Hit)" + "\n";
			    
	} else {
			     
		if (Cache.isDirty(address)) {
			    
			Cache.writeBack(address);
			     
		}
			     
		block.loadMemory(address, main_mem.getRow(address.beginAddress));
			     
		block.update(address.offset, data);
			    
		message = "Value " + data + " has been written to address " + Integer.toHexString(address.raw) + " (Cache Miss)" + "\n";
			   
	}
			   
	if (!Cache.test) {
			    
		System.out.println(message);
			   
	}
			   
	return message;
			 
}

			 
public static void displayCache() {
			  
	System.out.println("Slot Valid Tag     Data----------------------------------------------------------");
			   
	for (short i = 0; i < main_memory.blocks.length; i++) {
			   
		System.out.println(main_memory.blocks[i].toString());

			   
	}
	System.out.print("---------------------------------------------------------------------------------"+ "\n");

}

public static void main(String[] args) {
	
//	R
//	5
	read(0x5);
//	R
//	6
	read(0x6);
//	R
//	7
	read(0x7);
//	R
//	14c
	read(0x14c);
//	R
//	14d
	read(0x14d);
//	R
//	14e
	read(0x14e);
//	R
//	14f
	read(0x14f);
//	R
//	150
	read(0x150);
//	R
//	151
	read(0x151);
//	R
//	3A6
	read(0x3a6);
//	R
//	4C3
	read(0x4c3);
//	D 
	displayCache();
//	W 
//	14C 
//	99
	write(0x14C,(short) 99);
//	W
//	63B
//	7
	write(0x63B,(short) 7);
//	R
//	582
	read(582);
//	D Display the cache
	displayCache();
//	R
//	348
	read(348);
//	R
//	3F
	read(0x3f);
//	D Display the cache
	displayCache();
//	R
//	14b
	read(0x14b);
//	R
//	14c
	read(0x14c);
//	R
//	63F
	read(0x63f);
//	R
//	83
	read(0x83);
//	D Display the cache one last time, making sure that the correct blocks are there
	displayCache();
}
}