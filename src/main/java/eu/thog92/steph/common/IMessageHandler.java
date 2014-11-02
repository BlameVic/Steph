package eu.thog92.steph.common;

import java.util.List;

public interface IMessageHandler {
    public List<IEventMatcher> getEventMatchers();

    public void processEvent(ChatEvent event);
}
