package echo;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.websocket.MessageInbound;
import org.apache.catalina.websocket.StreamInbound;
import org.apache.catalina.websocket.WebSocketServlet;
import org.apache.catalina.websocket.WsOutbound;

@WebServlet("/webtest")
public class WebTest extends WebSocketServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected StreamInbound createWebSocketInbound(String arg0,
			HttpServletRequest arg1) {

		return new TheWebSocket();
	}

	private class TheWebSocket extends MessageInbound {
		private WsOutbound outbound;

		@Override
		public void onOpen(WsOutbound outbound) {
			this.outbound = outbound;
			System.out.println("socket opened!");
		}

		@Override
		public void onTextMessage(CharBuffer buffer) throws IOException {
			try {
				outbound.writeTextMessage(CharBuffer.wrap("abc testing"
						.toCharArray()));
				System.out.println("Message sent from server.");
			} catch (IOException ioException) {
				System.out.println("error opening websocket");
			}

		}

		@Override
		protected void onBinaryMessage(ByteBuffer arg0) throws IOException {
			// TODO Auto-generated method stub

		}
	}

}
