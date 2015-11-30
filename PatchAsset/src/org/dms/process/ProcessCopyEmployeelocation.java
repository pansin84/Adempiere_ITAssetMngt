package org.dms.process;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.compiere.model.MDMSDepartment;
import org.compiere.model.MDMSPosition;
import org.compiere.model.MEmployeeDivision;
import org.compiere.model.MEmployeeLocation;
import org.compiere.model.PO;
import org.compiere.process.SvrProcess;

public class ProcessCopyEmployeelocation extends SvrProcess {

	public ProcessCopyEmployeelocation() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void prepare() {
		// TODO Auto-generated method stub

	}

	@Override
	protected String doIt() throws Exception {
		try {
			 
			Class.forName("org.postgresql.Driver");
 
		} catch (ClassNotFoundException e) {
 
			System.out.println("Where is your PostgreSQL JDBC Driver? "
					+ "Include in your library path!");
			e.printStackTrace();
 
		}
 
		System.out.println("PostgreSQL JDBC Driver Registered!");
		Connection connection = null;
	        Statement st = null;
	        ResultSet rs = null;
		try {
			 
			connection = DriverManager.getConnection(
					"jdbc:postgresql://192.168.2.29:5432/alkemprdfinal", "alkemprdfinal",
					"alkemprdfinal");
			 st = connection.createStatement();
			 //
			 rs = st.executeQuery("SELECT j.name "
			 	
			 		+ " from NV_OrgLocationMaster j"
			 		+ " WHERE j.AD_Client_ID = 1000000");
			 
			 
			 while(rs.next()) {
				 int catID;
				 int cat[]=PO.getAllIDs("DM_EmployeeLocation", "Name='"+rs.getString("name").trim()+"' AND AD_Client_ID=1000000", get_TrxName());
				 if(cat.length>0){}else{
					 MEmployeeLocation dep = new MEmployeeLocation(getCtx(), 0, get_TrxName());
					 dep.setName(rs.getString("name").trim());
					 dep.setAD_Org_ID(0);
					 if(dep.save()){}
				 }
			 }
			 
		} catch (SQLException e) {
 
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
		}finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (connection != null) {
                	connection.close();
                	connection=null;
                }

            } catch (SQLException ex) {
               
            }
        }
 
		if (connection != null) {
			System.out.println("You made it, take control your database now!");
		} else {
			System.out.println("Connection Closed");
		}
		
		return null;
	}

}
