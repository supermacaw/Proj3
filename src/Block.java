package src;

import java.util.LinkedList;
import java.util.ArrayList;

class Block{
	int length;
	int width;
	int upLCrow;
	int upLCcol;
    LinkedList<Integer> directions;
    boolean priority;
	
    public Block (int length, int width) {
		if (length <= 0 || width <= 0) {
			throw new IllegalArgumentException("Length and width must be greater than 0");
		}
		this.length = length;
        this.width = width;
        this.priority = false;
        directions = new LinkedList<Integer> ();
    }
    
    public Block(Block otherBlock){
    	this.length = otherBlock.length;
    	this.width = otherBlock.width;
    	this.upLCrow = otherBlock.upLCrow;
    	this.upLCcol = otherBlock.upLCcol;
        this.priority = otherBlock.isPriority();
    	this.directions = new LinkedList<Integer> ();
    }

    public void setPriority() {
        priority = true;
    }
    
    public String hashHelper(){
    	String row = (new Integer(upLCrow)).toString();
    	String col = (new Integer(upLCcol)).toString();
    	String sLength = (new Integer(length)).toString();
    	String sWidth = (new Integer(width)).toString();
    	return "r " + row + " c " +col + " l " + sLength + " w " + sWidth;
    }
    
    public int hashCode() {
    	String row = (new Integer(upLCrow)).toString();
    	String col = (new Integer(upLCcol)).toString();
    	String sLength = (new Integer(length)).toString();
    	String sWidth = (new Integer(width)).toString();
    	String blockString = ("row " + row + " col " +col + " length " + sLength + " width " + sWidth);
    	return blockString.hashCode();
    }
      
    public boolean equals(Object other){
    	Block otherB = null;
    	try{
    		otherB = (Block) other;
    	}catch (Exception e){
    		return false;
    	}
    	return (length == otherB.length 
    			&& width == otherB.width
    			&& upLCrow == otherB.upLCrow
    			&& upLCcol == otherB.upLCcol);
    }

    public boolean isPriority() {
        return priority;
    }

    
    public String toString(){
    	String toRtn = "Block l: " + length 
    			+ " w: " + width 
    			+ " coords: " + upLCrow + ", " + upLCcol + " | ";
    	for (Integer i : directions) {
    		toRtn += i;
    	}
    	return toRtn;
    }
    
    public boolean isOK() { 
    	return this.upLCrow >= 0 && this.upLCcol >= 0 && this.length > 0 && this.width > 0;
    }

    public static void main(String[] args) {
    	/*Block test = new Block(1,1);
    	test.upLCrow = 0;
    	test.upLCcol = 1;
    	Block test2 = new Block(1,1);
    	test2.upLCrow = 1;
    	test2.upLCcol = 0;
    	System.out.println(test.hashCode() == test2.hashCode());
    	System.out.println(test.equals(test2));*/

    }
}
