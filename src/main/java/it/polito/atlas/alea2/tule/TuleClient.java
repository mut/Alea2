package it.polito.atlas.alea2.tule;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class TuleClient {
	Socket tuleSocket=null;
	InputStream is=null;
	OutputStream os=null;
	int port=2728;
	public TuleClient () {

	}
	
	public boolean connect() {
		try {
			tuleSocket = new Socket("localhost", port);
			is=tuleSocket.getInputStream();
			os=tuleSocket.getOutputStream();
		} catch (UnknownHostException e) {
			e.printStackTrace();
			tuleSocket=null;
			return false;		
		} catch (IOException e) {
			e.printStackTrace();
			tuleSocket=null;
			return false;		
		}
		return true;
	}
	
	public String submitText() {
		return submitText("(1) Babu ha fame");
	}

	public String submitText(String text) {
		if (tuleSocket==null) {
			if (!connect())
				return null;
		}
		String output="";
		int bufLen=1024;
		byte[] buf=new byte[bufLen];
		int count=0;
		int len=0;
		try {
			os.write(("(1) " + text + "\n").getBytes());
			while ((len=is.read(buf, 0, bufLen))>0) {
				String tmp = new String (buf);
				output+=tmp.substring(0, len);
				count+=len;
			} 
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		if (count==0)
			return "Error in input String";
		return output;
	}
	
	public String parseTuleText(String text) {
		// TODO
		return submitText(text);
	}
}
