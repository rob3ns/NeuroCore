package Opcodes;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Opcode {
	
	public static Map<Integer, String> opcString = new HashMap<Integer,String>() {{
		opcString.put(1, "+");
		opcString.put(2, "*");
		opcString.put(3, "~");
		opcString.put(4, "-");
	}};
	
	public enum Opc {
		INFO(1), IMG(2), SOUND(3), ERROR(4);
		
		private Integer i;
		
		private Opc(Integer i) {
			this.setInt(i);
		}
		
		public Integer getInt() {
			return i;
		}
		
		public void setInt(Integer i) {
			this.i = i;
		}
		
		public String toString() {
			String s = opcString.get(i);
			return s != null ? s : "";
		}
	}
	
	/**
	 * 
	 * @param s
	 * @return -1 if not found
	 */
	public static Integer stringToInt(String s) {
		if (s == null) { return -1; }
		
		for (Entry<Integer, String> val : opcString.entrySet()) {
			if (val.getValue().equals(s)) {
				return val.getKey();
			}
		}
		return -1;
	}
}
