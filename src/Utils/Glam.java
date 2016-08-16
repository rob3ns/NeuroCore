package Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Glam<T> {

	private Map<String, ArrayList<T>> content;
	
	public Glam() {
		content = new HashMap<String, ArrayList<T>>();
	}

	public Map<String, ArrayList<T>> getContent() {
		return content;
	}
	
	public ArrayList<T> relationByWord(String word) {
		return content.get(word);
	}
}
