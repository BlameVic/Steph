package eu.thog92.steph.irc;

import eu.thog92.steph.irc.IRCSteph;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws Exception{
        //String configFile = args[0];

        IRCSteph steph = new IRCSteph();
        Map<String,String> config = new HashMap<String, String>();
        config.put("hostname", "irc.esper.net");
        config.put("port", "6666");
        config.put("username", "StephanieDola");
        config.put("debug", "true");
        steph.setConfig(config);
        steph.connect();
    }
}
