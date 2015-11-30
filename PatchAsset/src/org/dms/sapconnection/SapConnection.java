package org.dms.sapconnection;

import com.sap.mw.jco.JCO;

//import com.sap.mw.jco.JCO;

public class SapConnection
{ 
	public static JCO.Client mConnection = null;
//	public static JCo mConnection = null;

  public SapConnection()
  {
  }

  public Exception Connect(String client, String User, String Pw,
			String Langu, String Host, String System)

	{
		try {
			
			mConnection = JCO.createClient(client, // SAP client
					User, // User ID
					Pw, // Password
					Langu, // Language
					Host, // Host
					System); // System
			mConnection.connect();

			return null;
		} catch (Exception ex) {
			ex.printStackTrace();
			mConnection = null;
			return ex;
		}

	}


}