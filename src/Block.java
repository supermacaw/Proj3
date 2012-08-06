package src;

import java.util.LinkedList;

public class Block{
	int length;
	int width;
	int upLCrow;
	int upLCcol;
    LinkedList<Integer> directions;
    boolean priority;
	
    public Block (int length, int width) {
		if (length < 0 || width < 0) {
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
    
    public int hashCode() {
    	int dim = length + (width * width);
    	int coord = upLCrow + (upLCcol*upLCcol);
    	return dim ^ coord;
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
}
