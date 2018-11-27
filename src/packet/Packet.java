package packet;

import java.io.Serializable;
import java.net.Socket;
import java.util.UUID;

import packet.callback.CallbackManager;
import packet.callback.PacketCallback;

public abstract class Packet implements Serializable {
	private static final long serialVersionUID = 2297751571337367820L;
	
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
