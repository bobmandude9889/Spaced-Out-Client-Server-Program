package packet;

import java.util.HashMap;
import java.util.UUID;

public class CallbackManager {

	public static HashMap<UUID, PacketCallback<?>> packets;
	
	public static void init() {
		packets = new HashMap<>();
	}
	
	protected static void register(Packet packet, PacketCallback<?> callback) {
		packets.put(packet.id, callback);
	}
	
	public static PacketCallback<?> getCallback(Packet packet) {
		if (packets.containsKey(packet.id))
			return packets.get(packet.id);
		return null;
	}
	
}
