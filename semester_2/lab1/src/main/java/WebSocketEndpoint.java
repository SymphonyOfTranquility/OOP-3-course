import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@ServerEndpoint("/socket")
public class WebSocketEndpoint {
    private static Set<CheckWorker> workers = Collections.synchronizedSet(new HashSet<>());

    @OnMessage
    public void handleTextMessage(Session session, String message) throws IOException {
        synchronized (workers) {
            String[] tokens = message.split(" ");
            if (tokens[0].equals("get_vals")) {
                int mouseX = Integer.parseInt(tokens[1]);
                int mouseY = Integer.parseInt(tokens[2]);
                int radius = Integer.parseInt(tokens[3]);
                //System.out.println("get_vals");
                for (CheckWorker worker : workers) {
                    if (worker.getSession().equals(session)) {
                        worker.createVisionBoard(mouseX, mouseY, radius);
                    }
                }
            }
            else if (tokens[0].equals("refresh")) {
                //System.out.println("refresh");
                for (CheckWorker worker : workers) {
                    if (worker.getSession().equals(session)) {
                        worker.newRandomBoard();
                        //System.out.println("done!");
                        session.getBasicRemote().sendText("refresh " + CheckWorker.WIDTH + " " + CheckWorker.HEIGHT + " " +
                                + CheckWorker.MOUSE_HEIGHT + " " + CheckWorker.ZERO_HEIGHT);
                    }
                }
            }
        }
    }

    @OnOpen
    public void onOpen(Session session) throws IOException {
        workers.add(new CheckWorker(session));
        session.getBasicRemote().sendText("init " + CheckWorker.WIDTH +
                                            " " + CheckWorker.HEIGHT +
                                            " " + CheckWorker.MOUSE_HEIGHT +
                                            " " + CheckWorker.ZERO_HEIGHT
        );
    }


}