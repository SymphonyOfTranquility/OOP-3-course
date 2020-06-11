import javax.websocket.Session;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Modifier;

public class CheckWorker{

    private Session session;
    private OceanBoard board, visionBoard;
    private Boolean asked;
    public static int WIDTH = 45, HEIGHT = 45, MOUSE_HEIGHT = 10, ZERO_HEIGHT = 8;

    CheckWorker(Session session) {
        this.session = session;
        board = new OceanBoard(WIDTH, HEIGHT, true, ZERO_HEIGHT);
        visionBoard = new OceanBoard(WIDTH, HEIGHT, false, ZERO_HEIGHT);
    }

    public void createVisionBoard(int mouseX, int mouseY, int radius) throws IOException {
        visionBoard = board.getVision(mouseX - 1, mouseY - 1, ZERO_HEIGHT, radius);
        //System.out.println("VISION BOARD UPDATE");
        //System.out.println(mouseX + " " + mouseY + " " + radius);
        //board.output();
        //visionBoard.output();
        session.getBasicRemote().sendText("board " + visionBoard.boardToString());
    }

    public Session getSession() { return session; }

    public void newRandomBoard() {
        board = new OceanBoard(WIDTH, HEIGHT, true, ZERO_HEIGHT);
    }

}
