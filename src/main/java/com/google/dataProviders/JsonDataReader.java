package com.google.dataProviders;
	import java.io.BufferedReader;
	import java.io.FileNotFoundException;
	import java.io.FileReader;
	import java.io.IOException;
	import com.google.gson.Gson;
import com.google.managers.FileReaderManager;
import com.google.testDataTypes.GmailInfo;
	
public class JsonDataReader {
	private final String gmailCredFilePath = FileReaderManager.getInstance().getConfigReader().getTestDataResourcePath() + "gmailCredDetails.json";
	private GmailInfo gmailInfoDetails;
	
	public JsonDataReader(){
		gmailInfoDetails = getFlightData();
	}
	
	private GmailInfo getFlightData() {
		Gson gson = new Gson();
		BufferedReader bufferReader = null;
		try {
			bufferReader = new BufferedReader(new FileReader(gmailCredFilePath));
			GmailInfo creds = gson.fromJson(bufferReader, GmailInfo.class);
			return creds;
		}catch(FileNotFoundException e) {
			throw new RuntimeException("Json file not found at path : " + gmailCredFilePath);
		}finally {
			try { if(bufferReader != null) bufferReader.close();}
			catch (IOException ignore) {}
		}
	}
	
	public final GmailInfo getUserByName(String userName){	
			if(gmailInfoDetails.userName.equalsIgnoreCase(userName)) return gmailInfoDetails;
				return null;
	}
		

}