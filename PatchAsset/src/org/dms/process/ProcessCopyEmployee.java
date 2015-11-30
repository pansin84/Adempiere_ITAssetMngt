package org.dms.process;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import org.compiere.model.MDMSDepartment;
import org.compiere.model.MDMSEmployee;
import org.compiere.model.MDMSPosition;
import org.compiere.model.MEmployeeContact;
import org.compiere.model.MEmployeeDivision;
import org.compiere.model.MEmployeeLocation;
import org.compiere.model.MEmployeeLocationType;
import org.compiere.model.PO;
import org.compiere.process.SvrProcess;

public class ProcessCopyEmployee extends SvrProcess {

	public ProcessCopyEmployee() {
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
			 String sql="select emp.firstname,emp.lastname,"+
						" emp.email,emp.phone,emp.joindate,emp.empcode,emp.gender,emp.meritalstatus,emp.eegroup,"+
						" otype.name as otype,oloc.name as oloc,jcat.name as jcat,job.name as job, dev.name as dev"+
						" from nv_employee emp"+
						" left outer join nv_orglocationtype otype on (emp.nv_orglocationtype_id=otype.nv_orglocationtype_id)"+
						" left outer join nv_orglocationmaster oloc on(emp.nv_orglocationmaster_id=oloc.nv_orglocationmaster_id)"+
						" left outer join c_jobcategory jcat on (emp.c_jobcategory_id=jcat.c_jobcategory_id)"+
						" left outer join c_job job on(emp.c_job_id=job.c_job_id)"+
						" left outer join nv_employeedivison dev on (emp.nv_employeedivison_id = dev.nv_employeedivison_id)"
			 			+ " WHERE emp.AD_Client_ID = 1000000";
			 rs = st.executeQuery(sql);
			 String firstname="",middlename="",lastname="";
			 while(rs.next()) {
				 	String name=rs.getString("firstname");
				 	if(rs.getString("lastname")!=null){
				 		String lname=rs.getString("lastname");
				 		
				 		if(lname.equals(",") || lname.equals(".")){
				 			name=name.trim();
							name=name.replace("  ", " ");
							String[] parts=name.split(" ");
							if(parts.length==2){
//								System.out.println(parts[0]+"-"+parts[1]);
								firstname=parts[0];
								lastname=parts[1];
							}
							if(parts.length==1){
//								System.out.println(parts[0]+"-"+parts[1]);
								firstname=parts[0];
								lastname="-";
							}else{
								String[] parts1=name.split(" ");
								if(parts1.length==1){
									firstname=parts1[0];
									lastname="-";
								}
								if(parts1.length==2){
									firstname=parts1[0];
									lastname=parts1[1];
								}
								if(parts1.length==3){
									firstname=parts1[0];
									middlename=parts[1];
									lastname=parts1[2];
								}else{
									firstname=rs.getString("firstname");
									lastname="-";
								}
								
							}
				 		}else{
							lname=lname.trim();
							lname=lname.replace("  ", " ");
							String[] parts=lname.split(" ");
							if(parts.length==2){
								middlename=parts[0];
								lastname=parts[1];
							}else{
								lastname=rs.getString("lastname");
							}
							firstname=rs.getString("firstname");
				 		}
				 	}else{
				 		name=name.trim();
						name=name.replace("  ", " ");
						String[] parts=name.split(" ");
						if(parts.length==2){
//							System.out.println(parts[0]+"-"+parts[1]);
							firstname=parts[0];
							lastname=parts[1];
						}
						if(parts.length==1){
//							System.out.println(parts[0]+"-"+parts[1]);
							firstname=parts[0];
							lastname="-";
						}else{
							String[] parts1=name.split(" ");
							if(parts1.length==1){
								firstname=parts1[0];
								lastname="-";
							}
							if(parts1.length==2){
								firstname=parts1[0];
								lastname=parts1[1];
							}
							if(parts1.length==3){
								firstname=parts1[0];
								middlename=parts[1];
								lastname=parts1[2];
							}else{
								firstname=rs.getString("firstname");
								lastname="-";
							}
						}
//				 		name=name.trim();
//						name=name.replace("  ", " ");
//						String[] parts=name.split(" ");
//						if(parts.length==2){
////							System.out.println(parts[0]+"-"+parts[1]);
//							firstname=parts[0];
//							lastname=parts[1];
//						}else{
//							String[] parts1=name.split(".");
//							if(parts1.length==2){
////								System.out.println(parts1[0]+"-"+parts1[1]);
//								firstname=parts1[0];
//								lastname=parts1[1];
//							}else{
////								System.out.println(rs.getString("firstname"));
//								firstname=rs.getString("firstname");
//							}
//							
//						}
				 	}
					
					String email = rs.getString("email");
					String phone = rs.getString("phone");
					Timestamp joindate = rs.getTimestamp("joindate");
					String empcode = rs.getString("empcode");
					String gender = rs.getString("gender");
					String mstatus = rs.getString("meritalstatus");
					String eegroup = rs.getString("eegroup");
					
					String otype = rs.getString("otype");
					String oloc = rs.getString("oloc");
					String jcat = rs.getString("jcat");
					String job = rs.getString("job");
					String dev = rs.getString("dev");
					
					MDMSEmployee emp = new MDMSEmployee(getCtx(), 0, get_TrxName());
					emp.setFName(firstname);
					emp.setMName(middlename);
					emp.setLName(lastname);
					if(email!=null)
						emp.setEMailUser(email.trim());
					if(empcode!=null)
						emp.setEmployeeCode(empcode.trim());
					emp.setDM_EmpStatus_ID(1000001);
					emp.setNationalCode("IN");
					emp.setNationality_ID(208);
					emp.setHR_Race_ID(1000002);
					if(gender!=null)
						emp.setGender(gender.trim().equals("FE")? "Female" : "Male");
					if(joindate!=null)
						emp.setStartDate(joindate);
					if(phone!=null)
						emp.setMobilePhone(phone.trim());
					
					if(eegroup!=null){
						emp.setDM_DMSEmployeeType_ID(eegroup.equals("RE") ? 1000002 : 1000003);
					}else{
						emp.setDM_DMSEmployeeType_ID(1000002);
					}
						
					if(otype !=null)
						emp.setDM_EmployeeLocationType_ID(getEmployeeLocationType(otype));
					if(oloc !=null)
						emp.setDM_EmployeeLocation_ID(getEmployeeLocation(oloc));
					if(jcat != null)
						emp.setDM_DMSDepartment_ID(getDMSDepartment(jcat));
					if(dev !=null)
						emp.setDM_EmployeeDivision_ID(getEmployeeDivision(dev));
					///
					if(job!=null){
						if(jcat !=null)
							emp.setDM_DMSPosition_ID(getDMSPosition(jcat,job));
					}
					if(emp.save()){
						System.out.println(emp.getFName()+" Saved");
						MEmployeeContact cont = new MEmployeeContact(getCtx(), 0, get_TrxName());
						cont.setContactType(MEmployeeContact.CONTACTTYPE_Primary);
						cont.setDM_DMSEmployee_ID(emp.get_ID());
						if(emp.getEMailUser()!=null)
						cont.setEMail(emp.getEMailUser());
						if(emp.getMobilePhone()!=null)
							cont.setMobilePhone(emp.getMobilePhone());
						cont.setC_Location_ID(1000098);
						cont.save();
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
                }

            } catch (SQLException ex) {
               
            }
        }
 
		if (connection != null) {
			System.out.println("You made it, take control your database now!");
		} else {
			System.out.println("Failed to make connection!");
		}
//		 List<MJobCategory> lines = new Query(getCtx(), MJobCategory.Table_Name, 
//				  "  AD_Client_ID="+Env.getAD_Client_ID(getCtx()), get_TrxName())
//		  			.setOnlyActiveRecords(true).list();
//		 MDMSDepartment dept=null;
//		 int i=1;
//		 for(MJobCategory cat : lines){
//			 dept= new  MDMSDepartment(getCtx(), 0, get_TrxName());
//			 dept.setName(cat.getName());
//			 if(i<10){
//				 dept.setValue("0".concat(String.valueOf(i)));
//			 }else
//			 dept.setValue(String.valueOf(i));
//			 if(dept.save())
//				 i++;
//		 }
		
		return null;
	}

	private int getEmployeeDivision(String dev) {
		 int catID = 0;
		 int cat[]=PO.getAllIDs("DM_EmployeeDivision", "Name='"+dev.trim()+"' AND AD_Client_ID=1000000", get_TrxName());
		 if(cat.length>0){
			 catID=cat[0];
		 }else{
			 MEmployeeDivision dep = new MEmployeeDivision(getCtx(), 0, get_TrxName());
			 dep.setName(dev.trim());
			 dep.setAD_Org_ID(0);
			 if(dep.save()){
				 catID=dep.get_ID();
			 }
		 }
		 return catID;
	}

	private int getDMSPosition(String jcat,String job) {
		 int jobID = 0;
		 int cat[]=PO.getAllIDs("DM_DMSDepartment", "Name='"+jcat.trim()+"' AND AD_Client_ID=1000000", get_TrxName());
		 if(cat.length>0){
			 int job1[]=PO.getAllIDs("DM_DMSPosition", "Name='"+job.trim()+"' AND DM_DMSDepartment_ID="+cat[0]+" AND AD_Client_ID=1000000", get_TrxName());
			 if(job1.length<=0){
				 MDMSPosition pos = new MDMSPosition(getCtx(), 0, get_TrxName());
				 pos.setName(job.trim());
				 pos.setAD_Org_ID(0);
				 pos.setDM_DMSDepartment_ID(cat[0]);
				 if(pos.save()){
					 jobID=pos.get_ID(); 
				 }
			 }else{
				 jobID=job1[0];
			 }
		 }
		return jobID;
	}

	private int getDMSDepartment(String jcat) {
		int catID = 0;
		 int cat[]=PO.getAllIDs("DM_DMSDepartment", "Name='"+jcat.trim()+"' AND AD_Client_ID=1000000", get_TrxName());
		 if(cat.length>0){
			 catID=cat[0];
		 }else{
			 MDMSDepartment dep = new MDMSDepartment(getCtx(), 0, get_TrxName());
			 dep.setName(jcat.trim());
			 dep.setAD_Org_ID(0);
			 if(dep.save()){
				 catID=dep.get_ID();
			 }
		 }
		return catID;
	}

	private int getEmployeeLocationType(String otype) {
		int catID = 0;
		 int cat[]=PO.getAllIDs("DM_EmployeeLocationType", "Name='"+otype.trim()+"' AND AD_Client_ID=1000000", get_TrxName());
		 if(cat.length>0){
			 catID=cat[0];
		 }else{
			 MEmployeeLocationType dep = new MEmployeeLocationType(getCtx(), 0, get_TrxName());
			 dep.setName(otype.trim());
			 dep.setAD_Org_ID(0);
			 if(dep.save()){
				 catID=dep.get_ID();
			 }
		 }
		return catID;
	}
	
	private int getEmployeeLocation(String loc) {
		int catID = 0;
		 int cat[]=PO.getAllIDs("DM_EmployeeLocation", "Name='"+loc.trim()+"' AND AD_Client_ID=1000000", get_TrxName());
		 if(cat.length>0){
			 catID=cat[0];
		 }else{
			 MEmployeeLocation dep = new MEmployeeLocation(getCtx(), 0, get_TrxName());
			 dep.setName(loc.trim());
			 dep.setAD_Org_ID(0);
			 if(dep.save()){
				 catID=dep.get_ID();
			 }
		 }
		return catID;
	}

}
