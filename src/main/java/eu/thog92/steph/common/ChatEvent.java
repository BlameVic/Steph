package eu.thog92.steph.common;

public class ChatEvent {
    ISteph receiver;
    String message;
    String channel;
    String sender;

    public ChatEvent(ISteph receiver, String message, String channel, String sender) {
        this.receiver = receiver;
        this.message = message;
        this.channel = channel;
        this.sender = sender;
    }

    public void sendMessage(String message, String channel) {
        receiver.sendMessage(message, channel);
    }

    public void sendMessage(String message) {
        sendMessage(message, this.channel);
    }

    public String getSender() {
        return sender;
    }

    public ISteph getReceiver() {
        return receiver;
    }

    public String getMessage() {
        return message;
    }

    public String getChannel() {
        return channel;
    }
}
