package org.compiere.process;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MBPartner;
import org.compiere.model.MBPartnerLocation;
import org.compiere.model.MInOut;
import org.compiere.model.MInOutLine;
import org.compiere.model.MItemIssue;
import org.compiere.model.MItemType;
import org.compiere.model.MLocation;
import org.compiere.model.MLocator;
import org.compiere.model.MProduct;
import org.compiere.model.MSAPConnection;
import org.compiere.model.MSapLogIn;
import org.compiere.model.MWarehouse;
import org.compiere.model.Query;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.novelerp.sapconection.SapConnection;
import org.novelerp.sapmodel.JcoBapiMaterialReceiptGetStatus;
import org.novelerp.sapmodel.JcoBapiSalesorderGetStatus;
import org.python.modules.newmodule;

import com.sap.mw.jco.JCO;
import com.sun.enterprise.admin.server.core.jmx.IRepository;

public class GetMaterialList extends SvrProcess {

	public static JCO.Client mConnection = null;
	private SapConnection sapConnection1;
	private IRepository mRepository;
	private JCO.Function jcoFunction;
	private JCO.Function jcoCommit;
	private String[][] itemData;
	Timestamp Date;
	String sb;
	int country;
	int city;
	int region;
	String result = "Succeccfully Done";
	int inoutID;
	// StringBuffer stb=new StringBuffer();
	// StringBuffer stb1=new StringBuffer();
	int itemid=0;
	int BPartnerid=0;
	int prdid=0;
	int uomid=0;
	//Added By Rupesh Joshi on 04th March 2015
	/*Start 192.168.2.164*/
	private int MInOut_ID;
	private int	NV_ItemType_ID;
	private String ModelNo="";
	private int Memory;
	private String Processor="";
	private int Manufactere_ID;
	private String Description="";
	/*End*/
	protected void prepare() {
		MInOut_ID = getRecord_ID();
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (name.equals("NV_ItemType_ID"))
				NV_ItemType_ID = ((BigDecimal)para[i].getParameter()).intValue();
			else if (name.equals("ModelNo"))
				ModelNo = ((String)para[i].getParameter());
			else if (name.equals("Memory"))
				Memory = ((BigDecimal)para[i].getParameter()).intValue();
			else if (name.equals("Processor"))
				Processor = ((String)para[i].getParameter());
			else if (name.equals("Manufactere_ID"))
				Manufactere_ID = ((BigDecimal)para[i].getParameter()).intValue();
			else if (name.equals("Description"))
				Description = ((String)para[i].getParameter());
			else
				log.log(Level.SEVERE, "prepare - Unknown Parameter: " + name);			
		}
		System.out.println("NV_ItemType_ID: " + NV_ItemType_ID);
		System.out.println("ModelNo: " + ModelNo);
		System.out.println("Memory: " + Memory);
		System.out.println("Processor: " + Processor);
		System.out.println("Manufactere_ID: " + Manufactere_ID);
		System.out.println("Description: " + Description);
	}

	public GetMaterialList() {
		sapConnection1 = new SapConnection();
	}

	protected String doIt() throws Exception {
		MInOut inout = new MInOut(getCtx(), getRecord_ID(), get_TrxName());

		JCO.Client client = null;

		try {

			// System.out.println("\n\nVersion of the JCO-library:\n" +
			// "---------------------------\n" + JCO.getMiddlewareVersion());
			
			// check Sap login comes from master
		
			MSAPConnection login = new Query(getCtx(), MSAPConnection.Table_Name,
					"IsDefault='Y' " + "And AD_Client_id="
							+ Env.getAD_Client_ID(getCtx()), get_TrxName())
					.setOnlyActiveRecords(true).first();
			if (login == null) {

				throw new AdempiereException("Login Details Required ");
			}
			Exception sapException = null;

			sapConnection1 = new SapConnection();

			try {

				mConnection = JCO
						.createClient(login.getClientCode(),
								login.getLoginUserName(), login.getPassword(),
								login.getLanguageISO(), login.getHost(),
								login.getSystemCode());
				mConnection.connect();
				// System.out.println("Connected");

			} catch (Exception ex) {
				mConnection = null;
				ex.printStackTrace();
				// return ex;
			}

			sapException = sapConnection1.Connect(login.getClientCode(),
					login.getLoginUserName(), login.getPassword(),
					login.getLanguageISO(), login.getHost(), login.getSystemCode()); // System

			if (sapException != null) {
				throw new AdempiereException("sapException");
			}

			// System.out.println("\n\nCongratulations! It worked.");
		} catch (Exception ex) {
			ex.printStackTrace();
			// System.out.println("Caught an exception: \n" + ex);
		} finally {
			// do not forget to close the client connection
			if (client != null)
				client.disconnect();
		}

		JcoBapiMaterialReceiptGetStatus getOrderStatus = new JcoBapiMaterialReceiptGetStatus();
		// try { // Find order

		//call bapi with required parameter and get the data object
		
		if (inout.getSAPGR_NO() != null && inout.getSAPGR_NO().length()>0) {
			getOrderStatus.GetStatus(inout.getSAPGR_NO());
		}

		else {
			throw new AdempiereException(
					"Please Enter SAP Material Document no. ");
		}

		String uom = "select C_UOM_ID From C_Uom where Name='Each' and Isactive='Y' and AD_Client_Id="+Env.getAD_Client_ID(getCtx());
		uomid = DB.getSQLValue(get_TrxName(), uom);
		// Get header info
		
		//check the data and create material and lines
		
		if (getOrderStatus.getDocDate() != null) {
			Date = getDatepara(getOrderStatus.getDocDate());
			inout.setMovementDate(Date);
			//System.out.println("Date== "+Date);
		}
		if (getOrderStatus.getDocDate() != null) {
			Date = getDatepara(getOrderStatus.getDocDate());
			//System.out.println("Date== "+Date);
			//inout.setDateAcct(Date);
		}
		if (getOrderStatus.getPurchNo() != null) {
			inout.setPOReference(getOrderStatus.getPurchNo());
		}
		if (getOrderStatus.getCERNO() != null) {
			inout.setCERNo(getOrderStatus.getCERNO());
		}

		if (getOrderStatus.getDelchallan() != null) {
			inout.setDeliveryChallanNum(getOrderStatus.getDelchallan());
		}

		if (getOrderStatus.getGrnby() != null) {
			inout.setGRNPreparedBy(getOrderStatus.getGrnby());
		}
		if (getOrderStatus.getPoDate() != null) {
			Date = getDatepara(getOrderStatus.getPoDate());
			inout.setPODate(Date);
		}

		if (getOrderStatus.getCERdate() != null) {
			Date = getDatepara(getOrderStatus.getCERdate());
			inout.setCERDate(Date);
		}

		if (getOrderStatus.getSAPGRNDate() != null) {
			Date = getDatepara(getOrderStatus.getSAPGRNDate());
			inout.setGRNPrepeareDateSap(Date);
		}

		if (getOrderStatus.getchallanDate() != null) {
			Date = getDatepara(getOrderStatus.getchallanDate());
			inout.setDeliveryChallanDate(Date);
		}

		if (getOrderStatus.getVendorname() != null
				&& getOrderStatus.getVendorCode() != null) {

			MBPartner bp = new Query(getCtx(), MBPartner.Table_Name, "value='"
					+ getOrderStatus.getVendorCode() + "' AND " + "Name='"
					+ getOrderStatus.getVendorname() + "' And isvendor='Y' and AD_Client_ID="+Env.getAD_Client_ID(getCtx()),
					get_TrxName())

			.setOnlyActiveRecords(true).first();

			if (bp == null)

			{
				MBPartner bpnew = new MBPartner(getCtx(), 0, get_TrxName());
				bpnew.setName(getOrderStatus.getVendorname());
				bpnew.setValue(getOrderStatus.getVendorCode());
				bpnew.setIsVendor(true);
				bpnew.save();

				//inout.setC_BPartner_ID(bpnew.getC_BPartner_ID());

				sb = " select C_location_ID from c_country c inner join c_region r"
						+ " on (c.c_country_id=r.c_country_id and c.name='India' and r.name='Maharashtra' )"
						+ " inner join C_city ci on (r.c_region_id=ci.c_region_id and ci.name='Mumbai')"
						+ " inner join C_location loc on(c.c_country_id=loc.c_country_id "
						+ " and r.c_region_id=loc.c_region_id and ci.c_city_id=loc.c_city_id and address1='Lower Parel')"
						+ "  where loc.ad_client_id="
						+ Env.getAD_Client_ID(getCtx())
						+ " and loc.isactive='Y'";
				int location = DB.getSQLValue(get_TrxName(), sb);
				
			//System.out.println("location----  "+location);
				if (location > 0) {

					MBPartnerLocation bplnew = new MBPartnerLocation(bpnew);
					if (getOrderStatus.getvendorloc() != null)

					{
						bplnew.setName(getOrderStatus.getvendorloc());
					}

					else

					{
						bplnew.setName("India");
					}
					bplnew.setC_BPartner_ID(bpnew.getC_BPartner_ID());
					bplnew.setC_Location_ID(location);
					bplnew.save();
				}

				else {

					sb = "select c.c_country_id as country ,r.c_region_id as region,ci.c_city_id as city from c_country c inner join c_region r"
							+ " on (c.c_country_id=r.c_country_id and c.name='India' and r.name='Maharashtra')"
							+ " inner join C_city ci on (r.c_region_id=ci.c_region_id and r.c_country_id=ci.c_country_id and ci.name='Mumbai')";
					
					System.out.println("sb----  "+sb);
					//
					PreparedStatement pstmt = null;
					ResultSet rs = null;
					try {
						
						pstmt = DB.prepareStatement(sb, null);
						rs = pstmt.executeQuery();
						while (rs.next())

						{
							MLocation loc = new MLocation(getCtx(), 0,
									get_TrxName());
							loc.setAddress1("Lower Parel");
							loc.setC_Country_ID(rs.getInt("country"));
							loc.setC_Region_ID(rs.getInt("region"));
							loc.setC_City_ID(rs.getInt(city));
							loc.save();

							MBPartnerLocation bplnew = new MBPartnerLocation(
									bpnew);

							if (getOrderStatus.getvendorloc() != null) {

								bplnew.setName(getOrderStatus.getvendorloc());
							} else {
								bplnew.setName("India");
							}
							bplnew.setC_BPartner_ID(bpnew.getC_BPartner_ID());
							bplnew.setC_Location_ID(loc.getC_Location_ID());
							bplnew.save();

						}

					} 
					
					catch (Exception e) {
						log.log(Level.SEVERE, sb, e);
					} finally {
						DB.close(rs, pstmt);
						rs = null;
						pstmt = null;
					}

				}
			} 
			
			else

			{

				MBPartnerLocation bpl = new Query(getCtx(),
						MBPartnerLocation.Table_Name, "C_BPartner_ID="
								+ bp.getC_BPartner_ID() + " and AD_Client_id="
								+ Env.getAD_Client_ID(getCtx())
								+ " and isactive='Y'", get_TrxName())
						.setOnlyActiveRecords(true).first();

				inout.setC_BPartner_ID(bp.getC_BPartner_ID());
				inout.setC_BPartner_Location_ID(bpl.getC_BPartner_Location_ID());
			}

			if (inout.save()) {
				inoutID = inout.getM_InOut_ID();
			} else {
				throw new AdempiereException("Inout Not Saved");
			}
		}
		//int numItems = getOrderStatus.getNumItems(); // Number of items
		String[][] itemArray = getOrderStatus.getItems();
		itemData = new String[5][50];
		itemData[0][0] = "";
		// System.out.println("itemData  "+itemArray[0][0]);
	
		String sql = "Delete From M_inoutline where M_inout_ID="
		+ inoutID + "And Ad_Client_ID="
		+ Env.getAD_Client_ID(getCtx());
       int no = DB.executeUpdate(sql, get_TrxName());
		
    
    for (int row = 0; row < itemArray.length; row++) {
			for (int col = 0; col < 5; col++)

			itemData[col][row] = itemArray[row][col];

			String mcode = itemData[0][row];
			String mname = itemData[1][row];
			System.out.println("mname from SAP: " + mname);
			String mfgname = itemData[2][row];
			System.out.println("mfgname from SAP: " + mfgname);
			String mfgcode = itemData[3][row];
			System.out.println("mfgcode from SAP: " + mfgcode);
			BigDecimal qty = new BigDecimal(itemData[4][row]);

			//System.out.println("qty2222==" + qty);
			System.out.println("qty out==" + qty);
			
			for (int qt = 0; qt < qty.intValue(); qt++)
			{
				//System.out.println("qty==" + qt);
				System.out.println("qty in==" + qty);


				MInOutLine mline = new MInOutLine(getCtx(), 0, get_TrxName());
				mline.setM_InOut_ID(inoutID);
				mline.setQty(BigDecimal.ONE);				

				// if (mname.length()>0)
				if (!mname.equalsIgnoreCase("")) 
				{									
					itemid = getmaterial(mname, mcode);
					System.out.println("itemid set From SAP: " + itemid);
					mline.setNV_ItemType_ID(itemid);
				}	

				 //if (mname.length()>0)
				if (!mname.equalsIgnoreCase("")) // mname != null |

				{
					prdid = getproductid(mname, mcode, itemid);
					System.out.println("prdid set From SAP : " + prdid);
					mline.setM_Product_ID(prdid);
				}

				MWarehouse ware = new MWarehouse(getCtx(),
						inout.getM_Warehouse_ID(), get_TrxName());

				MLocator loc = MLocator.getDefault(ware);
				mline.setM_Locator_ID(loc.getM_Locator_ID());

				if (mfgname.length()>0) // mfgname != null |
				{
					BPartnerid = getManfacture(mfgname, mfgcode);
					System.out.println("Manufactere_ID set From SAP: " + BPartnerid);
					mline.setManufactere_ID(BPartnerid);
				}

				//Added By Rupesh Joshi on 05th March 2015
				//Start				
				mline.setNV_ItemType_ID(NV_ItemType_ID);				
				String sqlProduct ="SELECT M_Product_ID FROM M_Product where isactive='Y' And NV_ItemType_ID=" + NV_ItemType_ID;
				int PrdID = DB.getSQLValue(get_TrxName(), sqlProduct);
				mline.setM_Product_ID(PrdID);
				mline.setModelNo(ModelNo);
				mline.setMemory(Memory);
				mline.setProcessor(Processor);				
				mline.setManufactere_ID(Manufactere_ID);	
				System.out.println("Manufactere_ID set From Process: " + Manufactere_ID);												
				mline.setDescription(Description);
				//End
	
				mline.saveEx();

			}

		}

		if (getOrderStatus.getBapiReturn() != null || getOrderStatus.getBapiReturn().length()>2 ) {
			result = getOrderStatus.getBapiReturn();
			
			//System.out.println("result------ "+result+" and length---- "+result.length());
			if(result.length()>2){
				inout.setSAPMaterialDoc(null);
			throw new AdempiereException(result);
			}
		}

		return result;

	}

	public Timestamp getDatepara(String date) {
		java.sql.Timestamp timestamp = null;
		String LARGE_TWITTER_DATE_FORMAT = "MMM dd, yyyy";

		try {

			java.util.Date dd = new SimpleDateFormat(LARGE_TWITTER_DATE_FORMAT,
					Locale.ENGLISH).parse(date);
			timestamp = new java.sql.Timestamp(dd.getTime());
			//System.out.println(timestamp);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return timestamp;
	}

	public int getmaterial(String mname, String mcode) {

		StringBuffer stb = new StringBuffer();

//		stb = stb.append("Select NV_ItemType_ID from NV_ItemType where ")
//				.append(" Name='").append(mname).append("'")
//				.append(" And IsActive='Y' And AD_Client_ID=")
//				.append(Env.getAD_Client_ID(getCtx())).append(" And value= '")
//				.append(mcode).append("'");

		stb = stb.append("Select NV_ItemType_ID from NV_ItemType where ")
				.append(" Name='").append(mname).append("'")
				.append(" And IsActive='Y' And AD_Client_ID=")
				.append(Env.getAD_Client_ID(getCtx()));
		System.out.println("stb: " + stb);
		
		 if (mcode.length()>0)
		
		 {
			 stb = stb.append(" And value='").append(mcode).append("'");
		 }

		itemid = DB.getSQLValue(get_TrxName(), stb.toString());

		if (itemid <= 0) {
			MItemType type = new MItemType(getCtx(), 0, null);
			type.setName(mname);
			type.setC_UOM_ID(uomid);
			if (mcode.length()>0)
				type.setValue(mcode);
			if (type.save()) {
				itemid = type.getNV_ItemType_ID();
			} else {
				throw new AdempiereException(" Item Type Not Saved ");
			}

		}
		return itemid;
	}

	public int getproductid(String mname, String mcode, int itemid) {

		StringBuffer stb2 = new StringBuffer();

//		stb2 = stb2.append("Select M_Product_ID from M_Product where ")
//				.append(" Name='").append(mname).append("'")
//				.append(" And IsActive='Y' And AD_Client_ID=")
//				.append(Env.getAD_Client_ID(getCtx())).append(" And value='")
//				.append(mcode).append("'").append(" and NV_ItemType_ID=")
//				.append(itemid);
//		
		stb2 = stb2.append("Select M_Product_ID from M_Product where ")
				.append(" Name='").append(mname).append("'")
				.append(" And IsActive='Y' And AD_Client_ID=")
				.append(Env.getAD_Client_ID(getCtx())).append(" and NV_ItemType_ID=")
				.append(itemid);
		
		//System.out.println("stb2----"+stb2);
		
		 if (mcode.length()>0)
		
		 {
		 stb2 = stb2.append(" And value='").append(mcode).append("'");
		 }
		 //System.out.println("stb2"+stb2.toString());

		prdid = DB.getSQLValue(get_TrxName(), stb2.toString());
		//System.out.println("prdiddb----"+prdid);
		if (prdid <= 0) {
			//System.out.println("prdid123----"+prdid);
			MProduct prd = new MProduct(getCtx(), 0, null);
			prd.setName(mname);
			prd.setC_UOM_ID(uomid);
			prd.setC_TaxCategory_ID(107);
			prd.setM_Product_Category_ID(105);
			prd.setProductType("I");
			prd.setNV_ItemType_ID(itemid);
			if (mcode.length()>0)
				prd.setValue(mcode);

			if (prd.save()) {
				prdid = prd.getM_Product_ID();
				//System.out.println("inside prdid----"+prdid);
			}

			else {

				throw new AdempiereException(" product Not Saved ");
			}
		}
		//System.out.println("finalprdid----"+prdid);
		return prdid;
	}

	public int getManfacture(String mfgname, String mfgcode) {

		StringBuffer stb1 = new StringBuffer();

		stb1 = stb1
				.append("Select C_BPartner_ID from C_BPartner where ")
				.append(" Name= '")
				.append(mfgname)
				.append("'")
				.append(" And IsManufacturer='Y' And isVendor='Y' And IsActive='Y' And AD_Client_ID=")
				.append(Env.getAD_Client_ID(getCtx()));//.append(" And value='")
				//.append(mfgcode).append("'");
		
		//System.out.println("mfgcode---  "+mfgcode.length());
		 if (mfgcode.length()>0)
		
		 {
		 stb1.append(" And value='").append(mfgcode).append("'");
		 }

		BPartnerid = DB.getSQLValue(get_TrxName(), stb1.toString());
//		System.out.println("stb1stb1stb1stb1stb1---  "+stb1);
//		System.out.println("BPartnerid---  "+BPartnerid);
		if (BPartnerid <= 0)

		{
			MBPartner bpMfg = new MBPartner(getCtx(), 0, get_TrxName());
			bpMfg.setName(mfgname);
			if (mfgcode.length()>0)
				bpMfg.setValue(mfgcode);
			bpMfg.setIsVendor(true);
			bpMfg.setIsManufacturer(true);

			if (bpMfg.save()) {
				BPartnerid = bpMfg.getC_BPartner_ID();
			}

		else 
		    {

				throw new AdempiereException(" Manufacturere Not Saved ");
			}
		}

		return BPartnerid;
	}

}
