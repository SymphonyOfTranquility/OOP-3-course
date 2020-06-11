package lab1.ocean;

import javax.websocket.Session;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Modifier;

public class CheckWorker{

    private Session session;
    private OceanBoard board, visionBoard;
    private Boolean asked;
    public static int SIDE_LEN = 45, MOUSE_HEIGHT = 10, ZERO_HEIGHT = 8;

    CheckWorker(Session session) {
        this.session = session;
        board = new OceanBoard(SIDE_LEN, true, ZERO_HEIGHT);
        visionBoard = new OceanBoard(SIDE_LEN, false, ZERO_HEIGHT);
    }

    public void createVisionBoard(int mouseX, int mouseY, int radius) throws IOException {
        visionBoard = board.getVision(mouseX - 1, mouseY - 1, ZERO_HEIGHT, radius);
        session.getBasicRemote().sendText("board " + visionBoard.boardToString());
    }

    public Session getSession() { return session; }

    public void newRandomBoard() {
        board = new OceanBoard(SIDE_LEN, true, ZERO_HEIGHT);
    }

}
