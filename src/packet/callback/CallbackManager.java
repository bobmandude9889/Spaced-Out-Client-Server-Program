package packet.callback;

import java.util.HashMap;
import java.util.UUID;

import packet.Packet;

public class CallbackManager {

	public static HashMap<UUID, PacketCallback<?>> packets;
	
	public static void init() {
		packets = new HashMap<>();
	}
	
	public static void register(Packet packet, PacketCallback<?> callback) {
		packets.put(packet.id, callback);
	}
	
	public static boolean isActive() {
		return packets != null;
	}
	
	public static PacketCallback<?> getCallback(Packet packet) {
		if (packets == null)
			return null;
		
		if (packets.containsKey(packet.id))
			return packets.get(packet.id);
		return null;
	}
	
}
