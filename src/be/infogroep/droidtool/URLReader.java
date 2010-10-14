package be.infogroep.droidtool;
import java.net.*;
import java.io.*;

public class URLReader {
	public static String checkToken(String name, String token) throws Exception {
		String url = "http://infogroep.be:2007/validate";
		URL open;
		URLConnection openConnection;
		try {
			open = new URL(url);
			openConnection = open.openConnection();
			openConnection.setRequestProperty("Token", token);
			openConnection.setRequestProperty("Accept", "*, */*");
			openConnection.connect();
		}
		catch (IOException e) {
			//Call method; catch TokenExcpetion; prompt for username and password; generate new token.
			throw new TokenException("Token no longer valid");
			//Other exceptions are thrown through
		}
		return "Token Valid";
	}
	public static String getInterne(String name, String token) throws Exception {
		String url = "http://infogroep.be:2007/interne//"+name;
		URL igwe = new URL(url);
		URLConnection igweConnection = igwe.openConnection();
		igweConnection.setRequestProperty("Token", token);
		igweConnection.setRequestProperty("Accept", "*, */*");
		igweConnection.connect();
		BufferedReader in = new BufferedReader (
				new InputStreamReader(
						igweConnection.getInputStream()), 10);
		
		String inputLine="";
		String returnvalue = "Interne: ";
		
		while ((inputLine = in.readLine()) != null)
			returnvalue += inputLine;
		in.close();
		return returnvalue;
	}
	public static String getToken(String name, String password) throws Exception {
		String data;
		String output="";
		try {
		data = "--- \nusername: " + name + "\n" + "password: " + password;
		
		URL igwe = new URL("http://igwe.rave.org:2007/wannabe");
		URLConnection con = igwe.openConnection();
		con.setRequestProperty("Content-Type", "text/yaml");
		con.setRequestProperty("Accept", "*, */*");
		con.setDoOutput(true);
		OutputStreamWriter out = new OutputStreamWriter(con.getOutputStream());
		out.write(data);
		out.flush();
		BufferedReader in = new BufferedReader(
				new InputStreamReader(
						con.getInputStream()));
		String decode;
		while ((decode = in.readLine()) != null){
			output += decode;
		}
		out.close();
		in.close();
		} catch(Exception e){
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		//Stripping begin and end quotes
		return output.substring(1, output.length() -1);
	}
}