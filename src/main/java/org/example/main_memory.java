package org.example;

import java.util.Arrays;

public class main_memory {
	
	//short[]0，1，2，3，4，5，6，7，8，9，A，B, C, D, E, F
	
	short[] mainMemory = new short[2048];
	 
	static Block[] blocks = new Block[16];
	
	{
		for (short i = 0, j = 0; i < mainMemory.length; i++, j++) {
	    
			if (j == (0xFF + 1)) {
	    
				j = 0;
	     
			}
	     
			mainMemory[i] = j;
	   
		}
	}
	{
		for (short i = 0; i < blocks.length; i++) {
		 
			blocks[i] = new Block(i);
		}
	}
	
	public static Block getBlock(int slot) {
	  
		return blocks[slot];
	  
	}
	
	protected short[] getRow(int beginAddress) {
		 
		return Arrays.copyOfRange(mainMemory, beginAddress, beginAddress + 16);
		
	}
	
	
}
