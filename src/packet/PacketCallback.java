package packet;

public interface PacketCallback<T> {

	public void resultReceived(T result);
	
}
