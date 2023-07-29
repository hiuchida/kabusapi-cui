package com.github.hiuchida.kabusapi.cui;

import com.github.hiuchida.kabusapi.cui.tool.Apisoftlimit;
import com.github.hiuchida.kabusapi.cui.tool.SymbolnameFuture;
import com.github.hiuchida.kabusapi.cui.tool.SymbolnameOption;
import com.github.hiuchida.kabusapi.cui.tool.SymbolnameOption2;
import com.github.hiuchida.kabusapi.cui.tool.SymbolnameOptionMini;
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
			usageTool(SymbolnameFuture.USAGE);
			usageTool(SymbolnameOption.USAGE);
			usageTool(SymbolnameOption2.USAGE);
			usageTool(SymbolnameOptionMini.USAGE);
			return;
		}
		switch (args[0]) {
		case Token.TOOLNAME:
			new Token().execute(args);
			break;
		case Apisoftlimit.TOOLNAME:
			new Apisoftlimit().execute(args);
			break;
		case SymbolnameFuture.TOOLNAME:
			new SymbolnameFuture().execute(args);
			break;
		case SymbolnameOption.TOOLNAME:
			new SymbolnameOption().execute(args);
			break;
		case SymbolnameOption2.TOOLNAME:
			new SymbolnameOption2().execute(args);
			break;
		case SymbolnameOptionMini.TOOLNAME:
			new SymbolnameOptionMini().execute(args);
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
