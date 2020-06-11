package ocean;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;
import java.io.IOException;

public class CheckWorkerTest {

	Session session = mock(Session.class);
	RemoteEndpoint.Basic basic = mock(RemoteEndpoint.Basic.class);
	CheckWorker worker = spy(new CheckWorker(session));
	@Test
	public void checkGetSession() {
		when(worker.getSession()).thenReturn(session);
		assertEquals(worker.getSession(), session);
	}

	@Test
	public void checkCreateVisionBoard() throws IOException {
		when(session.getBasicRemote()).thenReturn(basic);
		doNothing().when(basic).sendText(anyString());
		worker.createVisionBoard(1, 1, 1);
		verify(session).getBasicRemote();
		verify(basic).sendText(anyString());
	}
}