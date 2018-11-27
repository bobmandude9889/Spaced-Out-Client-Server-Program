package packet;

import java.io.Serializable;
import java.net.Socket;
import java.util.UUID;

public abstract class Packet implements Serializable {

	public UUID id;

	public abstract void onReceive(Socket socket);
	
	protected Packet() {
		this.id = UUID.randomUUID();
	}
	
	protected Packet(PacketCallback<?> callback) {
		this();
		CallbackManager.register(this, callback);
	}
	
	public Object getResult() {
		return null;
	}
	
}
