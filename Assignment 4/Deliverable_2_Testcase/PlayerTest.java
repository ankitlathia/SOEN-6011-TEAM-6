package concordia.soen6011.tictactoe;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by pc on 5/29/2016.
 */
public class PlayerTest {



    @Test
    public void getIdTest(){
        Player player = new Player();
        player.setID(1);
        assertEquals(player.getID(), 1);
    }

    @Test
    public void getNameTest(){
        Player player = new Player();
        player.setName("Test");
        assertEquals(player.getName(), "Test");
    }

    @Test
    public void instantiationTest(){
        Player player = new Player();
        assertNotNull(player);
    }
}
