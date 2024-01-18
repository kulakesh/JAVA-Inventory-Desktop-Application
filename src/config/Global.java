package config;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;


public class Global {
	public static String CompanyName = "ELECTRO";
	public static String dbName = "cg_electro";
	public static String dbUser = "cg_electro";
	public static String dbPass = "SZQF86yXnrbvHMYf";
	public static String dbSocket = "8889";
	
//	publitic String dbPass = "bitbynext";
	
	public static String AppPath;
	public static String OS = System.getProperty("os.name");
	public static String DeviceName;
	public static Color textFocus = new Color(255,248,220);
	
	
	public static String getEnvironmentDetails() throws IOException{
		String path = Global.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		//String decodedPath = URLDecoder.decode(path, "UTF-8");
		AppPath = System.getProperty("user.dir");//new File(".").getCanonicalPath();//path.replaceFirst("/bin", "").replaceFirst("/INVERP.jar", "") ;// new java.io.File("").getAbsolutePath();//
		try{
		    InetAddress addr;
		    addr = InetAddress.getLocalHost();
		    DeviceName = addr.getHostName();
		}catch (UnknownHostException ex){
		    System.out.println("Hostname can not be resolved");
		}
		return AppPath;
	}
}
