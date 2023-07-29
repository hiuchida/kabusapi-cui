package com.github.hiuchida.kabusapi.cui.tool;

import java.io.IOException;
import java.io.PrintWriter;

import com.github.hiuchida.kabusapi.client_ex.AuthApiEx;
import com.github.hiuchida.kabusapi.client_ex.util.GsonUtil;
import com.github.hiuchida.kabusapi.cui.MainConsoleUI;
import com.google.gson.Gson;

import io.swagger.client.ApiException;
import io.swagger.client.model.RequestToken;
import io.swagger.client.model.TokenSuccess;

public class Token {

	public static final String TOOLNAME = "token";
	public static final String USAGE = TOOLNAME + " APIPassword [-token | -json | -curl]";

	private AuthApiEx auth = new AuthApiEx();

	public Token() {
	}

	public void execute(String[] args) {
		if (args.length < 2) {
			MainConsoleUI.usage(USAGE);
			return;
		}
		String apiPassword = args[1];
		boolean bToken = false;
		boolean bJson = false;
		boolean bCurl = false;
		if (args.length > 2) {
			if ("-token".equals(args[2])) {
				bToken = true;
			} else if ("-json".equals(args[2])) {
				bJson = true;
			} else if ("-curl".equals(args[2])) {
				bCurl = true;
			}
		}
		try {
			TokenSuccess response = auth.tokenPost(apiPassword);
			if (bToken) {
				System.out.println(response.getToken());
			} else if (bJson) {
				Gson gson = GsonUtil.getGson();
				System.out.println(gson.toJson(response));
			} else if (bCurl) {
				RequestToken req = new RequestToken();
				req.apIPassword(apiPassword);
				Gson gson = GsonUtil.getGson();
				String reqJson = gson.toJson(req);
				try (PrintWriter pw = new PrintWriter("RequestToken")) {
					pw.print(reqJson);
					System.out.println("write file: RequestToken");
				} catch (IOException e) {
					System.out.println(reqJson);
				}
				System.out.println();
				String curl = "curl -X POST -H \"Content-Type: application/json\" -H \"Accept: application/json\" \"http://localhost:18080/kabusapi/token\" -d @RequestToken";
				System.out.println(curl);
			} else {
				System.out.println(response);
			}
		} catch (ApiException e) {
			e.printStackTrace();
		}
	}

}
