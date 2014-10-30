package eu.thog92.steph.vchat.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class InputStreamUtil {
	public static String getContent(String strURL) throws Exception {
		URL url = new URL(strURL);
		URLConnection connection = url.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(
				connection.getInputStream()));

		StringBuilder response = new StringBuilder();
		String inputLine;

		while ((inputLine = in.readLine()) != null)
			response.append(inputLine);

		in.close();

		return response.toString();
	}
}