package ocean;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.doubleThat;
import static org.mockito.Mockito.when;

public class OceanBoardTest {

    final double eps = 0.00000001;
    int side, mouseHeight;
    OceanBoard randomBoard, visionBoard;
    @Before
    public void before() {
        side = 3;
        mouseHeight = 10;
        randomBoard = new OceanBoard(side,true,mouseHeight);
        visionBoard = new OceanBoard(side, false, mouseHeight);
    }

    @Test
    public void checkCreateRandomBoard() {

        boolean flag = false;
        for (int i = 0;i < side && !flag; ++i)
            for (int j = 0;j < side && !flag; ++j)
                if (randomBoard.getAt(i, j) != randomBoard.getAt(0, 0))
                    flag = true;
        assertTrue(flag);
    }

    @Test
    public void checkCreateVisionBoard() {
        boolean flag = true;
        for (int i = 0;i < side && flag; ++i)
            for (int j = 0;j < side && flag; ++j)
                if (Math.abs(visionBoard.getAt(i, j) - mouseHeight) > eps)
                    flag = false;
        assertTrue(flag);
    }

    @Test
    public void checkSetAtGetAt() {
        double val = 100;
        randomBoard.setAt(1, 1, val);
        assertEquals(randomBoard.getAt(1, 1), val, eps);
    }

    @Test
    public void checkBoardToString() {
        String toCheck = "";
        for (int i = 0; i < side;++i) {
            for (int j = 0;j < side; ++j) {
                toCheck += randomBoard.getAt(i, j) + " ";
            }
        }
        assertEquals(toCheck, randomBoard.boardToString());
    }

    @Test
    public void checkGetVision() {
        int val = 1;
        OceanBoard newVision = randomBoard.getVision(val, val, val, val);
        assertNotNull(newVision);
    }
}
