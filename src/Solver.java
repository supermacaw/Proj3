package src;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Iterator;

//check hashcodes later and equals methods

public class Solver {
	private ArrayList<Block> goalBlocks; // need to add some stuff for this in solver
	private HashSet<Tray> seen;
    private LinkedList<Tray> stack;

	public Solver () {
		goalBlocks = new ArrayList<Block>();
		seen = new HashSet<Tray>();
        stack = new LinkedList<Tray> (); //Make this sorted by cost.
	}

	private void solve() {
		int step=1;
		int collisions = 0;
		int hashCollisions = 0;
		HashSet<Integer> hashes = new HashSet<Integer>();
		
        while (!stack.isEmpty() && step < 300) {
            //System.out.println("last " + stack.getLast().cost);
            Tray myTray = stack.pop();
            //System.out.println(myTray.cost);
            step+=1;
	            System.out.println("hashset size: " + seen.size() + " collisions: " + hashCollisions + " " + step + " hash: " + myTray.hashCode() + " size: " + stack.size() + " numMoves : " + myTray.history.size());
	            System.out.println(myTray);
	            if(hashes.contains(new Integer(myTray.hashCode()))){
	            	hashCollisions+=1;
	            }
	            else{
	            	hashes.add(new Integer(myTray.hashCode()));
	            }
	            
            //System.out.println(stack.size());
            if(myTray.isAtGoal(goalBlocks)){
            	for(int i = 0; i < myTray.history.size(); i++){
            		System.out.println(i + " " + myTray.history.get(i));
            	}
            	System.exit(0);
            	return;
            }
            myTray.populateNextMoves(goalBlocks);
            seen.add(myTray);
            for (Block toMove: myTray.nextMoves){
            	decideMove(toMove, myTray);
                int rCoor = toMove.upLCrow;
                int cCoor = toMove.upLCcol;
                for (Integer i : toMove.directions) {
                	switch (i) {
                	case 0: 
                		//System.out.println("Down " + toMove);
	                	Tray one = new Tray(myTray);
	                    Block copy1 = one.config[rCoor][cCoor];
	                    one.move(copy1, rCoor + 1, cCoor);
	                    one.calculateCost(goalBlocks);
	                    if(!seen.contains(one)) {
	                        this.push(one);
	                        one.history.add(copy1.length + " " 
	                                        + copy1.width + " "
	                                        + copy1.upLCrow + " "
	                                        + copy1.upLCcol);
	                    }
	                    /*else {
	                    	System.out.println("Just jumped to a previous configuration or different moving option");
	                    	System.out.println("");
	                    }*/
	                    break;
                	case 1:
                		//System.out.println("Up " + toMove);
	                    Tray two = new Tray(myTray);
	                    Block copy2 = two.config[rCoor][cCoor];
	                    two.move(copy2, rCoor-1, cCoor);
	                    two.calculateCost(goalBlocks);
	                    if(!seen.contains(two)){	
	                        this.push(two);
	                        two.history.add(copy2.length + " "
	                                        + copy2.width + " " 
	                                        + copy2.upLCrow + " " 
	                                        + copy2.upLCcol);
	                    }
	                    /*else {
	                    	System.out.println("Just jumped to a previous configuration or different moving option");
	                    	System.out.println("");
	                    }*/
	                    break;
                	case 2:
                	  	//System.out.println("Right " + toMove);
	                    Tray three = new Tray(myTray);
	                    Block copy3 = three.config[rCoor][cCoor];
	                    three.move(copy3, rCoor, cCoor + 1);
	                    three.calculateCost(goalBlocks);
	                    if(!seen.contains(three)){	
	                        this.push(three);
	                        three.history.add(copy3.length + " " 
	                                          + copy3.width + " " 
	                                          + copy3.upLCrow + " " 
	                                          + copy3.upLCcol);
	                    }
	                    /*else {
	                    	System.out.println("");
	                    	System.out.println("Just jumped to a previous configuration or different moving option");
	                    }*/
	                    break;
                	case 3:
                		//System.out.println("Left " + toMove);
	                    Tray four = new Tray(myTray);
	                    Block copy4 = four.config[rCoor][cCoor];
	                    four.move(copy4, rCoor, cCoor - 1);
	                    four.calculateCost(goalBlocks);
	                    if(!seen.contains(four)){
	                        this.push(four);
	                        four.history.add(copy4.length + " " 
	                                        + copy4.width + " " 
	                                        + copy4.upLCrow + " " 
	                                        + copy4.upLCcol);
	                    }
	                    /*else {
	                    	System.out.println("Just jumped to a previous configuration or different moving option");
	                    	System.out.println("");
	                    }*/
	                    break;
                	}
                }
            	for (Integer i : toMove.directions) {
            		switch (i) {
            		case 0: 
            			//System.out.println("Down " + toMove);
            			Tray one = new Tray(myTray);
            			Block copy1 = one.config[rCoor][cCoor];
            			one.move(copy1, rCoor + 1, cCoor);
            			one.calculateCost(goalBlocks);
            			if(!seen.contains(one)) {
            				this.push(one);
            				seen.add(one);
            				one.history.add(copy1.length + " " 
            						+ copy1.width + " "
            						+ copy1.upLCrow + " "
            						+ copy1.upLCcol);
            			}
            			else {
            				//System.out.println("Just jumped to a previous configuration or different moving option");
            				//System.out.println("");
            			}
            			break;
            		case 1:
            			//System.out.println("Up " + toMove);
            			Tray two = new Tray(myTray);
            			Block copy2 = two.config[rCoor][cCoor];
            			two.move(copy2, rCoor-1, cCoor);
            			two.calculateCost(goalBlocks);
            			if(!seen.contains(two)){	
            				this.push(two);
            				seen.add(two);
            				two.history.add(copy2.length + " "
            						+ copy2.width + " " 
            						+ copy2.upLCrow + " " 
            						+ copy2.upLCcol);
            			}
            			else {
            				//System.out.println("Just jumped to a previous configuration or different moving option");
            				//System.out.println("");
            			}
            			break;
            		case 2:
            			//System.out.println("Right " + toMove);
            			Tray three = new Tray(myTray);
            			Block copy3 = three.config[rCoor][cCoor];
            			three.move(copy3, rCoor, cCoor + 1);
            			three.calculateCost(goalBlocks);
            			if(!seen.contains(three)){	
            				this.push(three);
            				seen.add(three);
            				three.history.add(copy3.length + " " 
            						+ copy3.width + " " 
            						+ copy3.upLCrow + " " 
            						+ copy3.upLCcol);
            			}
            			else {
            				//System.out.println("");
            				//System.out.println("Just jumped to a previous configuration or different moving option");
            			}
            			break;
            		case 3:
            			//System.out.println("Left " + toMove);
            			Tray four = new Tray(myTray);
            			Block copy4 = four.config[rCoor][cCoor];
            			four.move(copy4, rCoor, cCoor - 1);
            			four.calculateCost(goalBlocks);
            			if(!seen.contains(four)){
            				this.push(four);
            				seen.add(four);
            				four.history.add(copy4.length + " " 
            						+ copy4.width + " " 
            						+ copy4.upLCrow + " " 
            						+ copy4.upLCcol);
            			}
            			else {
            				//System.out.println("Just jumped to a previous configuration or different moving option");
            				//System.out.println("");
            			}
            			break;
            		}
            	}
            }
        }
        System.out.println("got here :(");
	}
	
	private void decideMove(Block movingBlock, Tray t) { //Takes in the Block and mutates the ArrayList direction in order of priority. 
    	if (movingBlock.isPriority()) { //a special decision is only made for blocks with priority: potential goal blocks
    		int goalRow = movingBlock.upLCrow; //this will only be updated if a decision for which particular
    		int goalCol = movingBlock.upLCcol; //direction to move in can be come up with
    		int row = movingBlock.upLCrow;
    		int col = movingBlock.upLCcol;
    		for (int m = 0; m < this.goalBlocks.size(); m++) {
    			Block toCheck = this.goalBlocks.get(m);
            	if (movingBlock.length == toCheck.length && movingBlock.width == toCheck.width) { //find which goal block the moving block corresponds to
            		if (t.config[toCheck.upLCrow][toCheck.upLCcol] != null) { //see if end goal coordinate of particular goal block is already filled
            			if (!t.config[toCheck.upLCrow][toCheck.upLCcol].equals(toCheck)) { //if it is filled, make sure it is not filled by a potential goal block already
            				//System.out.println("Saw that " + toCheck.upLCrow + " " + toCheck.upLCcol + " was not null and was not desirably filled");
            				goalRow = toCheck.upLCrow;
            				goalCol = toCheck.upLCcol;
            				break;
            			}
            		} else {
            			//System.out.println("Saw that " + toCheck.upLCrow + " " + toCheck.upLCcol + " was null");
            			goalRow = toCheck.upLCrow; //if end goal coordinate of this particular goal block is not already filled
            			goalCol = toCheck.upLCcol; //moving block will try to move towards that position
            			break;
            		}
    			}
           	 } //next the solver will decide which particular direction is the best for the potential block to move in, if any
    		if (row - goalRow > 0 && movingBlock.directions.contains(1)) { 
    			movingBlock.directions.remove(new Integer(1));
    			movingBlock.directions.addFirst(new Integer(1));
    		}
    		if (row - goalRow < 0 && movingBlock.directions.contains(0)) {
    			movingBlock.directions.remove(new Integer(0));
    			movingBlock.directions.addFirst(new Integer(0));
    		}
    		if (col - goalCol > 0 && movingBlock.directions.contains(3)) {
    			movingBlock.directions.remove(new Integer(3));
    			movingBlock.directions.addFirst(new Integer(3));
    		}
    		if (movingBlock.upLCcol - goalCol < 0 && movingBlock.directions.contains(2)) {
    			movingBlock.directions.remove(new Integer(2));
    			movingBlock.directions.addFirst(new Integer(2));
    		}
    	}
    }

	public void addToGoalBlocks(Block blockToAdd, int row, int col){
		blockToAdd.upLCrow = row;
		blockToAdd.upLCcol = col;
		this.goalBlocks.add(blockToAdd);
	}
	
    /* This method maintains the order of the Trays */
	public void push (Tray t) {
        int index = 0;
        //Iterator itr = stack.descendingIterator();
        for (Tray comp : stack) {
        //	Tray comp = (Tray) itr.next();
            if (t.compareTo(comp) == 1) {
                index += 1;
            } else if (t.compareTo(comp) <= 0) {
                stack.add(index, t);
                return;
            }
        }
        stack.add(t);
	}

	public static void main(String[] args) {
		Tray t = null;
		Solver s = new Solver();
		check(args);
		FileItr configRdr = new FileItr(args[args.length - 2]);
		FileItr goalRdr = new FileItr(args[args.length - 1]);
        if (configRdr.hasNext()) {
            int[] param = parseInt(configRdr.next().split(" "));
            t = new Tray(param[0], param[1]);
        }
		while (goalRdr.hasNext()) {
			int[] param = parseInt(goalRdr.next().split(" "));
			s.addToGoalBlocks(new Block(param[0], param[1]), param[2], param[3]);
		}
		while (configRdr.hasNext()) {
			int[] param = parseInt(configRdr.next().split(" "));
            Block newBlock = new Block(param[0], param[1]);
            for (Block gBlock : s.goalBlocks) {
            	if (newBlock.length == gBlock.length
            		&& newBlock.width == gBlock.width) {
                	newBlock.setPriority();
            	}
            }
            try{//just to make sure that random ass trays don't mess it up, maybe modify this
                t.place(newBlock, param[2], param[3]);
            }catch(IllegalArgumentException e){
                System.out.println("Bad tray or bad block (or other error)");
            }
		}
		t.populateNextMoves(s.goalBlocks);
		s.push(t);
		s.solve();
	}

	private static void check(String[] args) {
		if (args.length < 2) {
			throw new IllegalArgumentException("Not enough arguments");
		}
		for (int i = 0 ; i < args.length - 2 ; i ++) {
			if (!args[i].startsWith("-")) {
				throw new IllegalArgumentException("Option in wrong format");
			}
		}
	}

	private static int[] parseInt(String[] args) {
		int[] toRtn = new int[args.length];
		for (int i = 0; i < args.length ; i++) {
			toRtn[i] = Integer.parseInt(args[i]);
		}
		return toRtn;
	}
	
	/*	private int getDir(Block blockToCheck, int row, int col, Tray myTray){
	if(!myTray.inBounds(row, col)){
		throw new IllegalArgumentException();
	}
	else if(blockToCheck.upLCrow < row){
		return 0;
	}else if(blockToCheck.upLCrow > row){
		return 1;
	}else if(blockToCheck.upLCcol < col){
		return 2;
	}else if(blockToCheck.upLCcol > col){
		return 3;
	}else if(blockToCheck.upLCrow == row && blockToCheck.upLCcol == col){
		return -1;
	}else{
		throw new IllegalArgumentException();
	}
}

public boolean getDirWorks(){
	Tray myTray = new Tray(5,5);
	Block myBlock = new Block(2,2);
	myTray.place(myBlock, 2, 2);
	if(getDir(myBlock, 0, 0, myTray)!=1){
		return false;
	}if(getDir(myBlock, 3, 0, myTray)!=0){
		return false;
	}if(getDir(myBlock, 2, 0, myTray)!=3){
		return false;
	}if(getDir(myBlock, 2, 4, myTray)!=2){
		return false;
	}if(getDir(myBlock, 2, 2, myTray)!=(-1)){
		return false;
	}try{
		getDir(myBlock, 19, 19, myTray);
		return false;
	}catch(Exception e){
	}
	return true;
}*/
}
