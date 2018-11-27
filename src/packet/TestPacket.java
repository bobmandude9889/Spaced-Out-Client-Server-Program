package packet;

import java.net.Socket;

public class TestPacket extends Packet {
	private static final long serialVersionUID = 684943179934632606L;
	
	String var1;
	
	public TestPacket(String var1) {
		this.var1 = var1;
	}
	
	@Override
	public void onReceive(Socket socket) {
		System.out.println("Test packet from " + socket.getInetAddress().getHostAddress());
	}

}
