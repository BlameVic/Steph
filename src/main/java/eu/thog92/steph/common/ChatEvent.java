package eu.thog92.steph.common;

public class ChatEvent {
    ISteph sender;
    String message;
    String channel;

    public ChatEvent(ISteph sender, String message, String channel) {
        this.sender = sender;
        this.message = message;
        this.channel = channel;
    }

    public void sendMessage(String message, String channel) {
        sender.sendMessage(message, channel);
    }

    public void sendMessage(String message) {
        sendMessage(message, this.channel);
    }

    public ISteph getSender() {
        return sender;
    }

    public String getMessage() {
        return message;
    }

    public String getChannel() {
        return channel;
    }
}
