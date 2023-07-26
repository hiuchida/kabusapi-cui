package com.github.hiuchida.kabusapi.cui.tool;

import com.github.hiuchida.kabusapi.client_ex.InfoApiEx;
import com.github.hiuchida.kabusapi.cui.MainConsoleUI;
import com.github.hiuchida.kabusapi.enums.symbolname.future.FutureCode;
import com.google.gson.Gson;

import io.swagger.client.ApiException;
import io.swagger.client.JSON;
import io.swagger.client.model.SymbolNameSuccess;

public class SymbolnameFuture {

	public static final String TOOLNAME = "symbolname.future";
	public static final String USAGE = TOOLNAME + " X-API-KEY derivMonth futureCode [-json | -curl]";

	private InfoApiEx info = new InfoApiEx();

	public SymbolnameFuture() {
	}

	public void execute(String[] args) {
		if (args.length < 4) {
			MainConsoleUI.usage(USAGE);
			return;
		}
		String X_API_KEY = args[1];
		String derivMonthStr = args[2];
		int derivMonth = 0;
		try {
			derivMonth = Integer.parseInt(derivMonthStr);
		} catch (NumberFormatException e1) {
		}
		String futureCodeStr = args[3];
		FutureCode futureCode = FutureCode.valueOfCode(futureCodeStr);
		boolean bJson = false;
		boolean bCurl = false;
		if (args.length > 4) {
			if ("-json".equals(args[4])) {
				bJson = true;
			} else if ("-curl".equals(args[4])) {
				bCurl = true;
			}
		}
		try {
			SymbolNameSuccess response = info.symbolnameFutureGet(X_API_KEY, derivMonth, futureCode);
			if (bJson) {
				JSON json = new JSON();
				Gson gson = json.getGson();
				System.out.println(gson.toJson(response));
			} else if (bCurl) {
				String curl = "curl -H \"X-API-KEY: " + X_API_KEY + "\" -H \"Accept: application/json\" \"http://localhost:18080/kabusapi/symbolname/future"
						+ "?" + "FutureCode=" + futureCode.toString() + "&" + "DerivMonth=" + derivMonth
						+ "\"";
				System.out.println(curl);
			} else {
				System.out.println(response);
			}
		} catch (ApiException e) {
			e.printStackTrace();
		}
	}

}
