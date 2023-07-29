package com.github.hiuchida.kabusapi.cui.tool;

import com.github.hiuchida.kabusapi.client_ex.InfoApiEx;
import com.github.hiuchida.kabusapi.client_ex.util.GsonUtil;
import com.github.hiuchida.kabusapi.cui.MainConsoleUI;
import com.github.hiuchida.kabusapi.enums.symbolname.option.PutOrCallCode;
import com.google.gson.Gson;

import io.swagger.client.ApiException;
import io.swagger.client.model.SymbolNameSuccess;

public class SymbolnameOptionMini {

	public static final String TOOLNAME = "symbolname.option.mini";
	public static final String USAGE = TOOLNAME + " X-API-KEY derivMonth derivWeekly putOrCall strikePrice [-json | -curl]";

	private InfoApiEx info = new InfoApiEx();

	public SymbolnameOptionMini() {
	}

	public void execute(String[] args) {
		if (args.length < 6) {
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
		String derivWeeklyStr = args[3];
		int derivWeekly = 0;
		try {
			derivWeekly = Integer.parseInt(derivWeeklyStr);
		} catch (NumberFormatException e1) {
		}
		String putOrCallStr = args[4];
		PutOrCallCode putOrCall = PutOrCallCode.valueOfCode(putOrCallStr);
		String strikePriceStr = args[5];
		int strikePrice = 0;
		try {
			strikePrice = Integer.parseInt(strikePriceStr);
		} catch (NumberFormatException e1) {
		}
		boolean bJson = false;
		boolean bCurl = false;
		if (args.length > 6) {
			if ("-json".equals(args[6])) {
				bJson = true;
			} else if ("-curl".equals(args[6])) {
				bCurl = true;
			}
		}
		try {
			SymbolNameSuccess response = info.symbolnameOptionMiniGet(X_API_KEY, derivMonth, derivWeekly, putOrCall, strikePrice);
			if (bJson) {
				Gson gson = GsonUtil.getGson();
				System.out.println(gson.toJson(response));
			} else if (bCurl) {
				String curl = "curl -H \"X-API-KEY: " + X_API_KEY + "\" -H \"Accept: application/json\" \"http://localhost:18080/kabusapi/symbolname/option"
						+ "?" + "DerivMonth=" + derivMonth + "&" + "DerivMonth=" + derivMonth + "&" + "PutOrCall=" + putOrCall.toString() + "&" + "StrikePrice=" + strikePrice
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
