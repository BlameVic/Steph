package eu.thog92.steph.common;

import java.util.List;

/**
 * Created by rx14 on 29/10/14.
 */
public interface IMessageHandler {
    public List<String> getMatchers();

    public void processMessage(ChatEvent event);
}
