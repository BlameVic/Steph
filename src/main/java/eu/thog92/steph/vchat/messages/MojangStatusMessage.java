package eu.thog92.steph.vchat.messages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import vic.mod.chat.api.bot.IBotHandler;
import vic.mod.chat.api.bot.IChannelBase;
import vic.mod.chat.api.bot.IChatEntity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import eu.thog92.steph.vchat.utils.InputStreamUtil;

public class MojangStatusMessage extends AbstractMessageHandler {

	public MojangStatusMessage(IBotHandler botHandler) {
		super(botHandler);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<String> getMessageNames() {
		ArrayList<String> temp = new ArrayList<String>();
		Collections.addAll(temp, new String[] { "mostats" });
		Collections.addAll(temp, new String[] { "mojstats" });
		Collections.addAll(temp, new String[] { "stats" });
		Collections.addAll(temp, new String[] { "mojang status" });
		return temp;
	}

	@Override
	public void processMessage(String message, String match, IChatEntity sender,
			IChannelBase channel) {
		try {
			// irc.sendToChat(irc.getChannel(), "Mojang Server Status :");
			StringBuilder b = new StringBuilder();
			Gson gson = new GsonBuilder().create();
			String json = InputStreamUtil
					.getContent("http://status.mojang.com/check");
			List<Map<String, String>> servicesMap = gson.fromJson(json,
					List.class);

			String temp = " Auth ";
			String session = "";
			for (Map<String, String> server : servicesMap) {
				for (Entry<String, String> s : server.entrySet()) {
					switch (s.getValue()) {
					case "green":

						if (s.getKey().contains("auth.mojang.com")) {
							temp += "(§2Client §f/ ";
							System.out.println("HERE");
						}

						else if (s.getKey().contains("authserver.mojang.com"))
							temp += "§2Server§f) ";

						else if (s.getKey().contains("session.minecraft.net")) {
							session += "(§2Client §f/ ";
							System.out.println("HERE");
						}

						else if (s.getKey()
								.contains("sessionserver.mojang.com"))
							session += "§2Server§f) ";

						else
							b.append("§2");
						break;
					case "yellow":
						if (s.getKey().contains("auth.mojang.com"))
							temp += "(§6Client §f/ ";
						else if (s.getKey().contains("authserver.mojang.com"))
							temp += "§6Server§f) ";
						else if (s.getKey().contains("session.minecraft.net")) {
							session += "(§6Client §f/ ";
							System.out.println("HERE");
						}

						else if (s.getKey()
								.contains("sessionserver.mojang.com"))
							session += "§6Server§f) ";
						else
							b.append("§6");
						break;
					default:
						if (s.getKey().contains("auth.mojang.com"))
							temp += "(§4Client §f/ ";
						else if (s.getKey().contains("authserver.mojang.com"))
							temp += "§4Server§f) ";
						else if (s.getKey().contains("session.minecraft.net")) {
							session += "(§4Client §f/ ";
							System.out.println("HERE");
						}

						else if (s.getKey()
								.contains("sessionserver.mojang.com"))
							session += "§4Server§f) ";
						else
							b.append("§4");
						break;

					}
					switch (s.getKey()) {
					case "minecraft.net":
						b.append(" Website ");
						break;
					case "session.minecraft.net":
						break;
					case "account.mojang.com":
						b.append(" Account ");
						break;
					case "auth.mojang.com":
						break;
					case "skins.minecraft.net":
						b.append(" Skins ");
						break;
					case "authserver.mojang.com":
						b.append(temp);
						temp = " Session ";
						break;
					case "sessionserver.mojang.com":
						b.append(temp + session);
						break;
					case "api.mojang.com":
						b.append(" API ");
						break;
					case "textures.minecraft.net":
						b.append(" Textures");
						break;

					default:
						b.append("Unknown");
						break;
					}

					if (!s.getKey().equals("textures.minecraft.net")
							&& !s.getKey().equals("auth.mojang.com")
							&& !s.getKey().equals("session.minecraft.net"))
						b.append("§f|");

				}
			}
			botHandler.sendGlobalMessage(b.toString());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			botHandler
					.sendGlobalMessage("I can't communicate with the server, I assume that Mojang Service are down!");
		}
	}

}
