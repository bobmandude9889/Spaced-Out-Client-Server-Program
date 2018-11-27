package packet;

import java.net.Socket;

public class TestPacket extends Packet {

	String var1;
	
	public TestPacket(String var1) {
		this.var1 = var1;
	}
	
	@Override
	public void onReceive(Socket socket) {
		System.out.println("Test packet from " + socket.getInetAddress().getHostAddress());
	}

}
