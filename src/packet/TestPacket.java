package packet;

import java.net.Socket;

public class TestPacket implements Packet{

	@Override
	public void onReceive(Socket socket) {
		System.out.println("Test packet from " + socket.getInetAddress().getHostAddress());
	}

}
