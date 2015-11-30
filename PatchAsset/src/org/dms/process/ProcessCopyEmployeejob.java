package org.dms.process;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.compiere.model.MDMSDepartment;
import org.compiere.model.MDMSPosition;
import org.compiere.model.PO;
import org.compiere.process.SvrProcess;

public class ProcessCopyEmployeejob extends SvrProcess {

	public ProcessCopyEmployeejob() {
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
			 rs = st.executeQuery("SELECT j.name as job,jc.name as jcat "
			 	
			 		+ "from C_Job j"
			 		+ " inner join C_JobCategory jc on (j.C_JobCategory_id=jc.C_JobCategory_ID)"
			 		+ " WHERE j.AD_Client_ID = 1000000");
			 
			 
			 while(rs.next()) {
				 
				 System.out.println(rs.getString("job")+"---"+rs.getString("jcat"));
				 int catID;
				 int cat[]=PO.getAllIDs("DM_DMSDepartment", "Name='"+rs.getString("jcat").trim()+"' AND AD_Client_ID=1000000", get_TrxName());
				 if(cat.length>0){
					 catID=cat[0];
					 int job[]=PO.getAllIDs("DM_DMSPosition", "Name='"+rs.getString("job").trim()+"' AND DM_DMSDepartment_ID="+catID+" AND AD_Client_ID=1000000", get_TrxName());
					 if(job.length<=0){
						 MDMSPosition pos = new MDMSPosition(getCtx(), 0, get_TrxName());
						 pos.setName(rs.getString("job").trim());
						 pos.setDM_DMSDepartment_ID(catID);
						 pos.setAD_Org_ID(0);
						 pos.save();
					 }
				 }else{
					 MDMSDepartment dep = new MDMSDepartment(getCtx(), 0, get_TrxName());
					 dep.setName(rs.getString("jcat").trim());
					 dep.setAD_Org_ID(0);
					 if(dep.save()){
						 int job[]=PO.getAllIDs("DM_DMSPosition", "Name='"+rs.getString("job").trim()+"' AND DM_DMSDepartment_ID="+dep.get_ID()+" AND AD_Client_ID=1000000", get_TrxName());
						 if(job.length<=0){
							 MDMSPosition pos = new MDMSPosition(getCtx(), 0, get_TrxName());
							 pos.setName(rs.getString("job").trim());
							 pos.setDM_DMSDepartment_ID(dep.get_ID());
							 pos.setAD_Org_ID(0);
							 pos.save();
						 }
					 }
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
