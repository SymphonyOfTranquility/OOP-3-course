package ocean;

import org.junit.Before;
import org.junit.Test;

import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class WebSocketEndpointTest {

    Session session = mock(Session.class);
    RemoteEndpoint.Basic basic = mock(RemoteEndpoint.Basic.class);
    WebSocketEndpoint webSocketEndpoint = new WebSocketEndpoint();

    @Before
    public void before() throws IOException {
        when(session.getBasicRemote()).thenReturn(basic);
        doNothing().when(basic).sendText(anyString());
        webSocketEndpoint.onOpen(session);
    }

    @Test
    public void checkOnOpen() throws IOException {
        verify(session, times(1)).getBasicRemote();
        verify(basic, times(1)).sendText(anyString());
    }

    @Test
    public void checkOnMessageRefresh() throws IOException {
        webSocketEndpoint.handleTextMessage(session,"refresh");
        verify(session, times(2)).getBasicRemote();
        verify(basic, times(2)).sendText(anyString());
    }

    @Test
    public void checkOnGetVals() throws IOException {
        webSocketEndpoint.handleTextMessage(session,"get_vals 1 2 3");
        verify(session, times(2)).getBasicRemote();
        verify(basic, times(2)).sendText(anyString());
    }
}
