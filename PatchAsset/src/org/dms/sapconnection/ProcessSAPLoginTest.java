package org.dms.sapconnection;

import org.compiere.model.MSAPConnection;
import org.compiere.process.SvrProcess;

public class ProcessSAPLoginTest extends SvrProcess {
	private SapConnection sapConnection;
	public ProcessSAPLoginTest() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void prepare() {
		// TODO Auto-generated method stub

	}

	@Override
	protected String doIt() throws Exception {
		MSAPConnection conn = new MSAPConnection(getCtx(), getRecord_ID(), get_TrxName());
//		if(conn.getSAPDestination()== null){
//			return "SAP Destination Not Found";
//		}
		return sapLogon(conn.getClientCode(),
				conn.getLoginUserName(),
				conn.getPassword(),
				conn.getLanguageISO(),
				conn.getHost(),
				conn.getSystemCode(),conn.getSAPDestination()
       );
	}
	private String sapLogon(String client,
            String User,
            String Pw,
            String Langu,
            String Host,
            String System,String destination)
	{ 
		Exception sapException = null;
		sapConnection = new SapConnection();


		sapException = sapConnection.Connect(client,    //SAP client
                            User,      //User ID
                            Pw,        //Password
                            Langu,     //Language
                            Host,      //Host
                            System);   //System
		if ( sapException == null )
	    { 
			return "Logon ok";
	    }
	    else
	    { 
	    	return "Logon Fail";
	    }
	}
}
