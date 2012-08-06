package src;

import static org.junit.Assert.*;

import org.junit.Test;
import java.util.*;

public class TrayTest {

	@Test
	public void testEasyIsOK() {
		Tray testTray = new Tray(3,3);
		Block block1 = new Block(2,2);
		Block block2 = new Block(2,1);
		Block block3 = new Block(1,2);
		Block block4 = new Block(1,1);
		testTray.place(block1,0,0);
		testTray.place(block2,0,2);
		testTray.place(block3,2,0);
		testTray.place(block4,2,2);
		assertTrue(testTray.isOK());
	}
	
	@Test
	public void testIsAtGoal() {
		ArrayList<Block> goalBlocks = new ArrayList<Block>();
		Block goalBlock1 = new Block(1,1);
		goalBlock1.upLCrow = 2; goalBlock1.upLCcol = 2;
		Block goalBlock2 = new Block(2,2);
		goalBlock2.upLCrow = 0; goalBlock2.upLCcol = 0;
		goalBlocks.add(goalBlock1);
		goalBlocks.add(goalBlock2);
		ArrayList<Block> nobueno = new ArrayList<Block>();
		Block nobuenoBlock = new Block(2,2);
		nobuenoBlock.upLCrow = 0; nobuenoBlock.upLCcol = 1;
		nobueno.add(nobuenoBlock);
		Tray testTray = new Tray(3,3);
		Block block1 = new Block(2,2);
		Block block2 = new Block(2,1);
		Block block3 = new Block(1,2);
		Block block4 = new Block(1,1);
		testTray.place(block1,0,0);
		testTray.place(block2,0,2);
		testTray.place(block3,2,0);
		testTray.place(block4,2,2);
		assertTrue(testTray.isAtGoal(goalBlocks));
		assertFalse(testTray.isAtGoal(nobueno));
	}
	
	@Test
	public void testIsValidMove() {
		Tray testTray = new Tray(5,5);
		Block block1 = new Block(2,2);
		testTray.place(block1, 0, 0);
		assertTrue(testTray.isValidMove(block1, 0, 3));
	}

}
