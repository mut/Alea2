package it.polito.atlas.alea2.tule;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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
	
	public List<Lemma> parseTuleText(String text) {
		List <Lemma> lemmas = new ArrayList<Lemma>();
		Stack<Lemma> treeLemmas = new Stack<Lemma>();
		
		Lemma activeLemma = null;
		Lemma activeParent = null; 
		String tuleString=submitText(text);
		if (tuleString==null)
			return lemmas;
		String[] elements=tuleString.split("[ \t\n\r\\#|]");

		int level=0;
		int parentLevel=0;
		String attrName="";
		boolean attr = false;
	
		// iteration for each substring
		for (int i=0; i<elements.length; ++i) {
			String element=elements[i];
			if (element.compareTo("")==0)
				continue;
			String value="";

			// parse of sub-string
			for (int j=0; j<element.length(); ++j) {
				char c = element.charAt(j);
				if (c == '(') {
					level++;
					continue;
				}
				if (c == ')') {
					level--;
					if (level<parentLevel-1) {
						if (treeLemmas.isEmpty())
							continue;
						treeLemmas.pop();
						parentLevel -= 3;
						if (treeLemmas.isEmpty()) {
							activeParent=null;
						} else {
							activeParent = treeLemmas.lastElement();
						}
					}
					continue;
				}
				value+=c;
			}

			// test first if is a value, then test the keywords
			if (attr==true) {
				activeLemma.setAttr(attrName, value);
				attr=false;
			} else if (value.compareTo("HEAD")==0) {
				activeLemma = new Lemma();
				lemmas.add(activeLemma);
			} else if (value.compareTo("DEPENDENTS")==0) {
				activeLemma.setParent(activeParent);
				activeParent = treeLemmas.push(activeLemma);
				parentLevel = level;
			} else if (Lemma.isAttrValidName(value)) {
				attrName=value;
				attr=true;
			}
		}
		return lemmas;
	}
}
