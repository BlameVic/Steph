package eu.thog92.steph.common;

import java.util.List;

public interface IMessageHandler {
    public List<String> getMatchers();

    public void processMessage(ChatEvent event);
}
