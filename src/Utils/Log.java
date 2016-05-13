package Utils;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author rob3ns
 */
public class Log {

	private String className;
	private List<String> logInfo;
	private int saveLevel;
	private int skippedLines;

	public Log (Class<?> c) {
		className = c.getSimpleName();
		resetData();
	}

	private void resetData() {
		logInfo = new ArrayList<String>();
		saveLevel = 3; //3:all, 2:-err, 1:-dbug, 0:-sc
		skippedLines = 0;
	}
	
	public void println(String msg) {
		System.out.println(msg);
		saveLogInfo(msg, 0);
	}
	
	public void print(String msg) {
		System.out.print(msg);
		saveLogInfo(msg, 0);
	}

	public String readStr(Scanner sc) {
		String msg = sc.nextLine();
		saveLogInfo(msg, 1);
		return msg;
	}
	public void debug(String msg) {
		String info = buildMsg(2, msg);

		System.out.println(info);
		saveLogInfo(info, 2);
	}

	public void error(String msg) {
		String info = buildMsg(3, msg);

		System.out.println(info);
		saveLogInfo(info, 3);
	}

	private String buildMsg(int mode, String msg) {
		String type = "";

		switch (mode) {
		case 1:
			break;
		case 2:
			type = "Debug";
			break;
		case 3:
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
		if (saveLevel >= mode) {
			if (mode == 1) {
				int pos = logInfo.size() - 1;
				String s = logInfo.get(pos);
				s += info;
				logInfo.set(pos, s);
			} else {
				logInfo.add(info);
			}
		} else {
			skippedLines++;
		}
	}

	public void close() {
		String skpLines = "Amount of lines avoided: " + skippedLines;
		saveLogInfo(skpLines, saveLevel);

		try {
			PrintWriter file = new PrintWriter("./log.txt");
			
			for (String str : Caster.safeIterable(logInfo)) {
				file.println(str);
			}
			
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
