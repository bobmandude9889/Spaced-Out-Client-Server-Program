package packet;

import java.io.Serializable;
import java.net.Socket;

public interface Packet extends Serializable {

	public abstract void onReceive(Socket socket);
	
}
