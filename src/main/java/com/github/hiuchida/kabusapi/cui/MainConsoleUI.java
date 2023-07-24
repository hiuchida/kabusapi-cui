package com.github.hiuchida.kabusapi.cui;

import com.github.hiuchida.kabusapi.cui.tool.Apisoftlimit;
import com.github.hiuchida.kabusapi.cui.tool.Token;

public class MainConsoleUI {

	public static void main(String[] args) {
		System.err.println("Hello MainConsoleUI    under construction");
		usageLF();
		if (args.length < 1) {
			usage("toolname params [options]");
			usageTool(Token.USAGE);
			usageLF();
			usageTool(Apisoftlimit.USAGE);
			return;
		}
		switch (args[0]) {
		case "token":
			new Token().execute(args);
			break;
		case "apisoftlimit":
			new Apisoftlimit().execute(args);
			break;
		default:
			System.err.println("Unknown toolname. " + args[0]);
			break;
		}
	}

	public static void usage(String msg) {
		System.err.println("usage: MainConsoleUI " + msg);
	}

	public static void usageTool(String msg) {
		System.err.println("  " + msg);
	}

	public static void usageLF() {
		System.err.println();
	}

}
