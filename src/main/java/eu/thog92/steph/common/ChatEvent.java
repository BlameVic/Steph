package eu.thog92.steph.common;

public class ChatEvent {
	IMessageSender sender;
	String         message;
	
	public ChatEvent(IMessageSender sender, String message) {
		this.sender  = sender;
		this.message = message;
	}
}

