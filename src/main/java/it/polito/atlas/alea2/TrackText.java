package it.polito.atlas.alea2;

import it.polito.atlas.alea2.tule.TuleClient;

public class TrackText extends Track {
	public TrackText (Annotation parent, String name)
	{
		super(parent, name);
		type = Track.Types.Text;
	}
	
	public void addLemmas(String text) {
		addLemmas(text, " ");
	}
	
	public void addLemmas(String text, String regex) {
		String [] words = text.split(regex);
		
		// Create a slice for each word (lemma)
		for (String str : words) {
			if (str.compareTo("")==0)
				continue;
			Slice s=new Slice(null, 0, 0);
			s.setInfo(str);
			s.addProperty(new Property(s, "Lemma", str));
			addSlice(s);
			s.setParent(this);
		}
		setModified();
	}

	public void addTuleLemmas(String text, TuleClient tule) {
		if (tule==null)
			return;
		addLemmas(tule.parseTuleText(text), "[ ()\t\n\r\\#]");
	}
}
