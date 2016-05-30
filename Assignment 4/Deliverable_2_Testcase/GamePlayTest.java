package concordia.soen6011.tictactoe;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by pc on 5/29/2016.
 */
public class GamePlayTest {

    @Test
    public void getWhichPlayerTest(){
        GamePlay gamePlay = new GamePlay("Test1", "Test2", "X", "O");
        assertEquals(gamePlay.getWhichPlayer(), 1);
    }

    @Test
    public void checkWinner(){
        try{
            GamePlay gamePlay = new GamePlay("Test1", "Test2", "X", "O");
            final String[][] grid = {{"X", "O", "O"},{"O", "X", "O"},{"O", "O", "X"}};
            gamePlay.setGrid(grid);
            assertTrue(gamePlay.checkWinner());
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }
}
