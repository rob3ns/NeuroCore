package Utils;

public class Panic {

	private static Log l = new Log("Panic");
	
	public static final int WRONG_OPCODE = 0;
	
	public static void ASSERT (boolean x, int code) {
		l.error("Assert code: " + code);
		if (x) { System.exit(1); }
	}
}
