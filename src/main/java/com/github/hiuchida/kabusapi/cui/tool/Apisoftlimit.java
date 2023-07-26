package com.github.hiuchida.kabusapi.cui.tool;

import com.github.hiuchida.kabusapi.client_ex.InfoApiEx;
import com.github.hiuchida.kabusapi.cui.MainConsoleUI;
import com.google.gson.Gson;

import io.swagger.client.ApiException;
import io.swagger.client.JSON;
import io.swagger.client.model.ApiSoftLimitResponse;

public class Apisoftlimit {

	public static final String TOOLNAME = "apisoftlimit";
	public static final String USAGE = TOOLNAME + " X-API-KEY [-json | -curl]";

	private InfoApiEx info = new InfoApiEx();

	public Apisoftlimit() {
	}

	public void execute(String[] args) {
		if (args.length < 2) {
			MainConsoleUI.usage(USAGE);
			return;
		}
		String X_API_KEY = args[1];
		boolean bJson = false;
		boolean bCurl = false;
		if (args.length > 2) {
			if ("-json".equals(args[2])) {
				bJson = true;
			} else if ("-curl".equals(args[2])) {
				bCurl = true;
			}
		}
		try {
			ApiSoftLimitResponse response = info.apisoftlimitGet(X_API_KEY);
			if (bJson) {
				JSON json = new JSON();
				Gson gson = json.getGson();
				System.out.println(gson.toJson(response));
			} else if (bCurl) {
				String curl = "curl -H \"X-API-KEY: " + X_API_KEY + "\" -H \"Accept: application/json\" \"http://localhost:18080/kabusapi/apisoftlimit\"";
				System.out.println(curl);
			} else {
				System.out.println(response);
			}
		} catch (ApiException e) {
			e.printStackTrace();
		}
	}

}
