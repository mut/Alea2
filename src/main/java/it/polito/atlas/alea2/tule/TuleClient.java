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
	
	/**
	 * The default TULE hosting PC
	 */
	String host="localhost";
	
	/**
	 * The default port for TULE Italian analyzer 
	 */
	int port=2728;

	public TuleClient () {}

	public TuleClient (String host) {
		this.host=host;
	}	

	public TuleClient (String host, int port) {
		this.host=host;
		this.port=port;
	}	
	
	/**
	 * Try to connect to TULE
	 * @return true if connected
	 */
	public boolean connect() {
		try {
			tuleSocket = new Socket(host, port);
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
	
	/**
	 * Submit a text to the analyzer
	 * @param text The text to analyze
	 * @return the TULE output string of analyzed text
	 */
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
			// Read 1024 bytes at time from the socket
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
	
	/**
	 * TULE output text parser
	 * @param text The text to analyze TULE
	 * @return the list of Lemmas generated
	 */
	public List<Lemma> parseTuleText(String tuleString) {
		// The analyzed Lemmas
		List <Lemma> lemmas = new ArrayList<Lemma>();
		
		// The stack that contains the parent Lemmas
		Stack<Lemma> treeLemmas = new Stack<Lemma>();
		
		// The active Lemma
		Lemma activeLemma = null;
		
		// The active parent Lemma
		Lemma activeParent = null; 
		
		// The active level of parenthesis
		int level=0;

		// The parent level of parenthesis
		int parentLevel=0;

		// The active attribute name
		String attrName="";
		
		// Indicates if the parser is reading a attribute value
		boolean attr = false;
	
		// Check the parameter
		if (tuleString==null)
			return lemmas;

		// Eliminate superfluous chars and split the TULE output in sub-strings
		String[] elements=tuleString.split("[ \t\n\r\\#|]");

		// Iterate on sub-strings
		for (int i=0; i<elements.length; ++i) {
			// Get the sub-string
			String element=elements[i];

			// Check the sub-string
			if (element.compareTo("")==0)
				continue;
			
			// The read value sub-string (without parenthesis)
			String word="";

			// Check the working level and set the value of sub-string
			for (int j=0; j<element.length(); ++j) {
				char c = element.charAt(j);
				if (c == '(') {
					level++;
					continue;
				}
				if (c == ')') {
					level--;
					// Check if need to change the active parent
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
				word+=c;
			}

			// Test first if the word is a attribute value, then test the keywords
			if (attr==true) {
				activeLemma.setAttr(attrName, word);
				attr=false;
			} else if (word.compareTo("HEAD")==0) {
				// Found a new lemma
				activeLemma = new Lemma();
				lemmas.add(activeLemma);
			} else if (word.compareTo("DEPENDENTS")==0) {
				// Set the active lemma as parent of the next own lemmas
				activeLemma.setParent(activeParent);
				activeParent = treeLemmas.push(activeLemma);
				parentLevel = level;
			} else if (Lemma.isAttrValidName(word)) {
				// The read word is a valid attribute name,
				// set the parser in the reading value state
				attrName=word;
				attr=true;
			}
		}
		return lemmas;
	}
}
