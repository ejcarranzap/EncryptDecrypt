package tools;

import java.io.IOException;
import java.net.URISyntaxException;

import net.sf.jasperreports.engine.JRException;

public class EncryptTool {
	public static String encriptar(String str) throws JRException, IOException, URISyntaxException{
		return AES256.encrypt(str);
	}
	
	public static String desencriptar(String str) throws JRException, IOException, URISyntaxException{
		return AES256.decrypt(str);
	}
}
