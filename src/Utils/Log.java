package Utils;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * @author rob3ns
 */
public class Log {

	private String className;
	private String logInfo;
	private int saveLevel;
	private int skippedLines;

	public Log (Class<?> c) {
		className = c.getSimpleName();
		logInfo = "";
		saveLevel = 0;
		skippedLines = 0;
	}

	public void print(String msg) {
		System.out.println(msg);
		saveLogInfo(msg, 0);
	}

	public void debug(String msg) {
		String info = buildMsg(1, msg);

		System.out.println(info);
		saveLogInfo(info, 1);
	}

	public void error(String msg) {
		String info = buildMsg(2, msg);

		System.out.println(info);
		saveLogInfo(info, 2);
	}

	private String buildMsg(int mode, String msg) {
		String type = "";

		switch (mode) {
		case 1:
			type = "Debug";
			break;
		case 2:
			type = "Error";
			break;
		default:
			type = "Unk";
			break;
		}
		type += "(";

		String output = type + this.className + "): " + msg;
		return output;
	}

	private void saveLogInfo(String info, int mode) {
		if (saveLevel <= mode) {
			logInfo += info + "/n";
		} else {
			skippedLines++;
		}
	}

	private void resetData() {
		logInfo = "";
		saveLevel = 0;
		skippedLines = 0;
	}

	public void close() {
		String skpLines = "Amount of avoided lines" + skippedLines;
		saveLogInfo(skpLines, saveLevel);

		try {
			PrintWriter file = new PrintWriter("./log.txt");
			file.println(logInfo);
			file.close();
		} catch (FileNotFoundException e) {
			System.out.println("Log file not found. Class: " + className);
			e.printStackTrace();
		}

		resetData();
	}

	public int getSaveLevel() {
		return saveLevel;
	}

	public void setSaveLevel(int saveLevel) {
		this.saveLevel = saveLevel;
	}
}
