package org.compiere.process;

import java.math.BigDecimal;
import java.util.logging.Level;
import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MSAPConnection;
import org.compiere.model.Query;
import org.compiere.util.Env;
import org.novelerp.sapconection.SapConnection;
import org.novelerp.sapmodel.JcoBapiEmployeeGetStatus;
import com.sap.mw.jco.JCO;
import com.sun.enterprise.admin.server.core.jmx.IRepository;

public class GetEmployeeList extends SvrProcess {

	public static JCO.Client mConnection = null;
	private SapConnection sapConnection1;
	private IRepository mRepository;
	private JCO.Function jcoFunction;
	private JCO.Function jcoCommit;

	String result;
	int inoutID;
	StringBuffer stb = new StringBuffer();
	BigDecimal m_empcode = BigDecimal.ZERO;
	String m_empcodestr;

	// String code=null;

	protected void prepare() {

		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++) {
			String name = para[i].getParameterName();			

			if (name.equals("EmpCode")) {

				m_empcode = ((BigDecimal) para[i].getParameter());
			}

			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
		
	}

	public GetEmployeeList() {
		sapConnection1 = new SapConnection();
	}

	protected String doIt() throws Exception {
		System.out.println("Enrolled Empcode is: " + m_empcode);		
		JCO.Client client = null;

		try {
			
			// check Sap login comes from master
			
			MSAPConnection login = new Query(getCtx(), MSAPConnection.Table_Name,
					"IsDefault='Y' " + "And AD_Client_id="
							+ Env.getAD_Client_ID(getCtx()), get_TrxName())
					.setOnlyActiveRecords(true).first();
			// System.out.println("Logion----"+login.getUserName());

			if (login == null)

			{
				throw new AdempiereException(
						"Sap LogIn Not Configured, Please Verify ");

			}

			Exception sapException = null;

			sapConnection1 = new SapConnection();

			sapException = sapConnection1.Connect(login.getClientCode(),
					login.getLoginUserName(), login.getPassword(),
					login.getLanguageISO(), login.getHost(), login.getSystemCode()); // System

			if (sapException != null) {
				throw new AdempiereException("sapException");
			}

		} catch (Exception ex) {
			ex.printStackTrace();

		} finally {

			if (client != null)
				client.disconnect();
		}

		JcoBapiEmployeeGetStatus getemp = new JcoBapiEmployeeGetStatus();
		try {
			// For all employees
			if (m_empcode.compareTo(BigDecimal.ZERO) == 0) {
				m_empcodestr = "";
			}
			// for unique emp with para
			
			m_empcodestr = m_empcode.toString();		

			getemp.GetStatus(m_empcodestr, get_TrxName(), getCtx(),
					Env.getAD_Client_ID(getCtx()));

		} catch (Exception mException) {

			mException.printStackTrace();
		}

		if (getemp.getBapiReturn().length() > 2) {
			result = getemp.getBapiReturn();
			throw new AdempiereException(result);
		}

		else {
			if (m_empcodestr != "") {
				result = "EMP Code :-" + m_empcodestr + " Enrolled ";
			} else {
				result = "All EMP Enrolled ";
			}
		}

		return result;

	}

}
