/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package org.compiere.process;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;

import org.compiere.model.MAssetModelDetails;
import org.compiere.model.MAssetModelNo;
import org.compiere.model.MBPGroup;
import org.compiere.model.MBPartner;
import org.compiere.model.MBPartnerLocation;
import org.compiere.model.MDocType;
import org.compiere.model.MInOut;
import org.compiere.model.MInOutLine;
import org.compiere.model.MLocation;
import org.compiere.model.MLocator;
import org.compiere.model.MProductCategory;
import org.compiere.model.MWarehouse;
import org.compiere.model.X_I_InOut;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 *	Import Invoice from I_InOut
 *
 * 	@author 	Jorg Janke
 *  @author		victor.perez@e-evolution.com, www.e-evolution.com
 *  <li> https://adempiere.atlassian.net/browse/ADEMPIERE-84
 * 	@version 	$Id: ImportInvoice.java,v 1.1 2007/09/05 09:27:31 cruiz Exp $
 */
public class ImportInOut extends SvrProcess
{
	/**	Client to be imported to		*/
	private int				m_AD_Client_ID = 0;
	/**	Organization to be imported to		*/
	private int				m_AD_Org_ID = 0;
	/**	Warehouse to be imported to		*/
	private int				m_M_Warehouse_ID = 0;
	/**	Delete old Imported				*/
	private boolean			m_deleteOldImported = false;
	/**	Document Action					*/
	private int			m_C_DocType_ID =0;
	private boolean isuniquesrno=false;
	private boolean isinitialImport=false;

	/** Effective						*/
	private Timestamp		m_DateValue = null;

	/**
	 *  Prepare - e.g., get Parameters.
	 */
	protected void prepare()
	{
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (name.equals("AD_Client_ID"))
				m_AD_Client_ID = ((BigDecimal)para[i].getParameter()).intValue();
			else if (name.equals("AD_Org_ID"))
				m_AD_Org_ID = ((BigDecimal)para[i].getParameter()).intValue();
			else if (name.equals("M_Warehouse_ID"))
				m_M_Warehouse_ID = ((BigDecimal)para[i].getParameter()).intValue();
			else if (name.equals("DeleteOldImported"))
				m_deleteOldImported = "Y".equals(para[i].getParameter());
			else if (name.equals("C_DocType_ID"))
				m_C_DocType_ID =  ((BigDecimal)para[i].getParameter()).intValue();
			else if (name.equals("IsUniqueSrNo"))
				isuniquesrno = (para[i].getParameterAsBoolean());
			else if (name.equals("IsInitialImport"))
				isinitialImport = (para[i].getParameterAsBoolean());
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
		if (m_DateValue == null)
			m_DateValue = new Timestamp (System.currentTimeMillis());
	}	//	prepare


	/**
	 *  Perform process.
	 *  @return clear Message
	 *  @throws Exception
	 */
	protected String doIt() throws java.lang.Exception
	{
		StringBuffer sql = null;
		int no = 0;
		String clientCheck = " AND AD_Client_ID=" + m_AD_Client_ID;

		//	****	Prepare	****

		//	Delete Old Imported
		if (m_deleteOldImported)
		{
			sql = new StringBuffer ("DELETE I_InOut "
				  + "WHERE I_IsImported='Y'").append (clientCheck);
			no = DB.executeUpdate(sql.toString(), get_TrxName());
			log.fine("Delete Old Impored =" + no);
			commitEx();
			return "Deleted";
		}

		//	Set Client, Org, IsActive, Created/Updated
		sql = new StringBuffer ("UPDATE I_InOut "
			  + "SET AD_Client_ID = COALESCE (AD_Client_ID,").append (m_AD_Client_ID).append ("),"
			  + " AD_Org_ID = COALESCE ("+m_AD_Org_ID+",").append ("AD_Org_ID").append ("),"
			  + " IsActive = COALESCE (IsActive, 'Y'),"
			  + " Created = COALESCE (Created, SysDate),"
			  + " CreatedBy = COALESCE (CreatedBy, 0),"
			  + " Updated = COALESCE (Updated, SysDate),"
			  + " UpdatedBy = COALESCE (UpdatedBy, 0),"
			  + " M_Warehouse_ID = COALESCE ("+m_M_Warehouse_ID+",").append ("M_Warehouse_ID").append ("),"
			  + " C_DocType_ID = COALESCE ("+m_C_DocType_ID+",").append ("C_DocType_ID").append ("),"
			
			  + " I_ErrorMsg = ' ',"
			  + " I_IsImported = 'N' "
			  + "WHERE I_IsImported<>'Y' OR I_IsImported IS NULL");
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		commitEx();
		System.out.println("**************************");
		log.info ("Reset=" + no);

		sql = new StringBuffer ("UPDATE I_InOut o "
			+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Invalid Org, '"
			+ "WHERE (AD_Org_ID IS NULL OR AD_Org_ID=0"
			+ " OR EXISTS (SELECT * FROM AD_Org oo WHERE o.AD_Org_ID=oo.AD_Org_ID AND (oo.IsSummary='Y' OR oo.IsActive='N')))"
			+ " AND I_IsImported<>'Y'").append (clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		if (no != 0)
			log.warning ("Invalid Org=" + no);
		//String docsql ="Select oo.C_DocType_ID from C_DocType oo where oo.DocBaseType='"+MDocType.DOCBASETYPE_MaterialReceipt+"' AND ooNAME='MM Receipt'";
		sql = new StringBuffer ("UPDATE I_InOut o "
				  + "SET C_DocType_ID=(Select oo.C_DocType_ID from C_DocType oo where oo.DocBaseType='"+MDocType.DOCBASETYPE_MaterialReceipt+"' AND oo.NAME='MM Receipt' AND o.AD_Client_ID=oo.AD_Client_ID) "
				  + "WHERE C_DocType_ID IS NULL"
				  + " AND I_IsImported<>'Y'").append (clientCheck);
			no = DB.executeUpdate(sql.toString(), get_TrxName());
			log.fine("Set BP from Value=" + no);
			
		sql = new StringBuffer ("UPDATE I_InOut "
			  + "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=No DocType, ' "
			  + "WHERE C_DocType_ID IS NULL"
			  + " AND I_IsImported<>'Y'").append (clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		if (no != 0)
			log.warning ("No DocType=" + no);

		sql = new StringBuffer ("UPDATE I_InOut "
				  + "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=No device Serial No, ' "
				  + "WHERE DeviceSerialNo IS NULL "
				  + " AND I_IsImported<>'Y'").append (clientCheck);
			no = DB.executeUpdate(sql.toString(), get_TrxName());
			if (no != 0)
				log.warning ("No device Serial No=" + no);
		sql = new StringBuffer ("UPDATE I_InOut "
				 + "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=No Model Number, ' "
				 + "WHERE ModelNo IS NULL "
				 + " AND I_IsImported<>'Y'").append (clientCheck);
			no = DB.executeUpdate(sql.toString(), get_TrxName());
			if (no != 0)
				log.warning ("No Model Number=" + no);
			
			sql = new StringBuffer ("UPDATE I_InOut "
					 + "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=No Vendor, ' "
					 + "WHERE BPartnerValue IS NULL "
					 + " AND I_IsImported<>'Y'").append (clientCheck);
				no = DB.executeUpdate(sql.toString(), get_TrxName());
				if (no != 0)
					log.warning ("No Vendor=" + no);
				
			if(isuniquesrno){
				sql = new StringBuffer ("UPDATE I_InOut o "
						+ "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=Serial No Already Exist, '"
						+ "WHERE (DeviceSerialNo IS NOT NULL "
						+ " AND EXISTS (SELECT * FROM M_InoutLine oo WHERE o.DeviceSerialNo=oo.DeviceSerialNo AND o.AD_Client_ID=oo.AD_Client_ID))"
						+ " AND I_IsImported<>'Y'").append (clientCheck);
				no = DB.executeUpdate(sql.toString(), get_TrxName());
				commitEx();
				if (no != 0)
					log.warning ("Serial No Already Exist=" + no);
			}	
			
			if(!isinitialImport){
				//	BP from Value
				sql = new StringBuffer ("UPDATE I_InOut o "
					  + "SET C_BPartner_ID=(SELECT MAX(C_BPartner_ID) FROM C_BPartner bp"
					  + " WHERE o.BPartnerValue=bp.Value AND o.AD_Client_ID=bp.AD_Client_ID) "
					  + "WHERE C_BPartner_ID IS NULL AND BPartnerValue IS NOT NULL"
					  + " AND I_IsImported<>'Y'").append (clientCheck);
				no = DB.executeUpdate(sql.toString(), get_TrxName());
				log.fine("Set BP from Value=" + no);
		
				
				//	Set Location from BPartner
				sql = new StringBuffer ("UPDATE I_InOut o "
					  + "SET C_BPartner_Location_ID=(SELECT MAX(C_BPartner_Location_ID) FROM C_BPartner_Location l"
					  + " WHERE l.C_BPartner_ID=o.C_BPartner_ID AND o.AD_Client_ID=l.AD_Client_ID"
					  + " AND ((l.IsBillTo='Y' AND o.IsSOTrx='Y') OR o.IsSOTrx='N')"
					  + ") "
					  + "WHERE C_BPartner_ID IS NOT NULL AND C_BPartner_Location_ID IS NULL"
					  + " AND I_IsImported<>'Y'").append (clientCheck);
				no = DB.executeUpdate(sql.toString(), get_TrxName());
				log.fine("Set BP Location from BP=" + no);
			}
		
		// Product category
//			sql = new StringBuffer ("UPDATE I_InOut o "
//				  + "SET M_Product_Category_ID=(SELECT MAX(M_Product_Category_ID) FROM M_Product_Category p"
//				  + " WHERE upper(trim(both ' ' from  o.ItemType))=p.Name AND o.AD_Client_ID=p.AD_Client_ID) "
//				  + "WHERE M_Product_Category_ID IS NULL AND ItemType IS NOT NULL"
//				  + " AND I_IsImported<>'Y'").append (clientCheck);
//			no = DB.executeUpdate(sql.toString(), get_TrxName());
//			log.fine("Set Product from Value=" + no);
			
			sql = new StringBuffer ("UPDATE I_InOut "
					 + "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=No Item Type, ' "
					 + "WHERE ItemType IS NULL "
					 + " AND I_IsImported<>'Y'").append (clientCheck);
				no = DB.executeUpdate(sql.toString(), get_TrxName());
				if (no != 0)
					log.warning ("No Item Type=" + no);
				
		// model no
//		sql = new StringBuffer ("UPDATE I_InOut o "
//					+ "SET DM_AssetModelDetails_ID=(SELECT MAX(DM_AssetModelDetails_ID) FROM DM_AssetModelDetails p"
//					+ " WHERE trim(both ' ' from  o.ModelNo)=p.Name AND o.AD_Client_ID=p.AD_Client_ID) "
//					+ "WHERE DM_AssetModelDetails_ID IS NULL AND ModelNo IS NOT NULL "
//					+ " AND Assetmanufacture_ID=(SELECT MAX(C_BPartner_ID) FROM C_BPartner bp"
//					+ " WHERE o.BPartnerValue=bp.Value AND o.AD_Client_ID=bp.AD_Client_ID)"
//					+" AND M_Product_Category_ID=(SELECT MAX(M_Product_Category_ID) FROM M_Product_Category p"
//				  + " WHERE o.ItemType=p.Name AND o.AD_Client_ID=p.AD_Client_ID)"
//				  + "WHERE DM_AssetModelDetails_ID IS NULL AND ModelNo IS NOT NULL"
//					+ " AND I_IsImported<>'Y'").append (clientCheck);
//				no = DB.executeUpdate(sql.toString(), get_TrxName());
//				log.fine("Set Product from Value=" + no);
		
		commitEx();
		
		//	-- New BPartner ---------------------------------------------------

		//	Go through Invoice Records w/o C_BPartner_ID
		sql = new StringBuffer ("SELECT * FROM I_InOut "
			  + "WHERE I_IsImported='N' ").append (clientCheck);
		try
		{
			PreparedStatement pstmt = DB.prepareStatement (sql.toString(), get_TrxName());
			ResultSet rs = pstmt.executeQuery ();
			MBPGroup bpgroup=null;
			
			String Where="Name='Vendor' AND Value='Vendor' AND AD_Client_ID="+Env.getAD_Client_ID(getCtx());
			int[] partnerGrpID =  MBPGroup.getAllIDs(MBPGroup.Table_Name, Where, get_TrxName());
			if(partnerGrpID.length<=0){
				bpgroup=new MBPGroup(getCtx(), 0, get_TrxName());
				bpgroup.setName("Vendor");
				bpgroup.setValue("Vendor");
				if(bpgroup.save()){
					commitEx();
				}
			}else{
				bpgroup=new MBPGroup(getCtx(), partnerGrpID[0], get_TrxName());
			}
			while (rs.next ())
			{
				X_I_InOut imp = new X_I_InOut (getCtx(), rs, get_TrxName());
				if(isinitialImport){
					imp.setC_BPartner_ID(1000011);
					imp.setC_BPartner_Location_ID(1000005);
					MBPartner bp = MBPartner.get (getCtx(), imp.getBPartnerValue().toUpperCase().trim());
					if (bp == null)
					{
						bp = new MBPartner (getCtx (), 0, get_TrxName());
						bp.setClientOrg (imp.getAD_Client_ID (), imp.getAD_Org_ID ());
						bp.setValue (imp.getBPartnerValue ().toUpperCase().trim());
						bp.setName (imp.getBPartnerValue ().toUpperCase().trim());
						bp.setIsVendor(true);
						bp.setIsManufacturer(true);
						bp.setC_BP_Group_ID(bpgroup.get_ID());
						bp.setIsSummary(false);
						bp.setSOCreditStatus("X");
						bp.setAD_Language(Env.getAD_Language(getCtx()));
						if (!bp.save ())
							continue;
						else{
							commitEx();
						}
					}
					imp.setAssetManufacturer_ID(bp.getC_BPartner_ID ());
					
					imp.save ();
				}else{
					
					MBPartner bp = MBPartner.get (getCtx(), imp.getBPartnerValue().toUpperCase().trim());
					if (bp == null)
					{
						bp = new MBPartner (getCtx (), -1, get_TrxName());
						bp.setClientOrg (imp.getAD_Client_ID (), imp.getAD_Org_ID ());
						bp.setValue (imp.getBPartnerValue ().toUpperCase().trim());
						bp.setName (imp.getBPartnerValue ().toUpperCase().trim());
						bp.setIsVendor(true);
						bp.setC_BP_Group_ID(bpgroup.get_ID());
						bp.setIsSummary(false);
						bp.setSOCreditStatus("X");
						bp.setAD_Language(Env.getAD_Language(getCtx()));
						if (!bp.save ())
							continue;
						else{
							commitEx();
						}
					}
					imp.setC_BPartner_ID (bp.getC_BPartner_ID ());
					imp.setAssetManufacturer_ID(bp.getC_BPartner_ID ());
					MBPartnerLocation bpl = null; 
					MBPartnerLocation[] bpls = bp.getLocations(true);
					
					 if(bpls.length<=0){
							MLocation location=null;
							String Wherecl="Address1='Mumbai'"
									+ " AND C_City_ID=1000000 "
									+ " AND C_Region_ID=1000000 "
									+ " AND C_Country_ID=208 "
									+ " AND AD_Client_ID="+Env.getAD_Client_ID(getCtx());
							
							int[] locationID =  MLocation.getAllIDs(MLocation.Table_Name, Where, get_TrxName());
							if(locationID.length<=0){
								location=new MLocation(getCtx(), 0, get_TrxName());
								location.setAddress1("Address1");;
								location.setC_City_ID(1000000);
								location.setC_Country_ID(208);
								location.setC_Region_ID(1000000);
								if(location.save()){
									commitEx();
								}
							}else{
								location=new MLocation(getCtx(), locationID[0], get_TrxName());
							}
							bpl=new MBPartnerLocation(getCtx(), 0, get_TrxName());
							bpl.setName(bp.getName()+"(Mumbai)");
							bpl.setC_Location_ID(location.get_ID());
							bpl.setIsShipTo(true);
							bpl.setIsBillTo(false);
							bpl.setC_BPartner_ID(bp.get_ID());
							if(bpl.save()){
								imp.setC_BPartner_Location_ID(bpl.get_ID());
								commitEx();
							}
					 }else{
						 imp.setC_BPartner_Location_ID(bpls[0].get_ID());
					 }
					 imp.save ();
					 commitEx();
				}
				
				String catClause="upper(Name)='"+imp.getItemType().toUpperCase().trim()
				+"' AND AD_Client_ID="+Env.getAD_Client_ID(getCtx());
				int[] prodID =  MProductCategory.getAllIDs(MProductCategory.Table_Name, catClause, get_TrxName());
				MProductCategory cat =null;
				if(prodID.length>0){
					 cat = new MProductCategory(getCtx(),prodID[0], get_TrxName());	
					 imp.setM_Product_Category_ID(cat.get_ID());
				}else{
					 cat = new MProductCategory(getCtx(), 0, get_TrxName());
					cat.setName(imp.getItemType().toUpperCase().trim());
					cat.setValue(imp.getItemType().toUpperCase().trim());
					//cat.setIsLaptopDektop(true);
					//cat.setIsLDAccessory(true);
					//cat.setIsPrinter(true);
					//cat.setIsNetComp(true);
					cat.setIsSerialNoReq(true);
					cat.setMMPolicy(MProductCategory.MMPOLICY_FiFo);
					if(cat.save()){
						imp.setM_Product_Category_ID(cat.get_ID());
					}
					imp.save ();
				}
				if(imp.getM_Warehouse_ID()>0){
					MWarehouse ware= new MWarehouse(getCtx(), imp.getM_Warehouse_ID(), get_TrxName());
					imp.setM_Locator_ID(MLocator.getDefault(ware).get_ID());
					imp.setAD_Org_ID(ware.getAD_Org_ID());
					imp.save();
					commitEx();
				}
				if(imp.getModelNo()!=null){
					String WhereClause="AssetManufacturer_ID="+imp.getAssetManufacturer_ID()
					+" AND M_Product_Category_ID="+cat.getM_Product_Category_ID()
					+" AND AD_Client_ID="+Env.getAD_Client_ID(getCtx());
					int[] modelID =  MAssetModelNo.getAllIDs(MAssetModelNo.Table_Name, WhereClause, get_TrxName());
					MAssetModelNo model=null;
					if(modelID.length>0){
						model= new MAssetModelNo(getCtx(), modelID[0], get_TrxName());
					}else{
						model = new MAssetModelNo(getCtx(), 0, get_TrxName());
						model.setM_Product_Category_ID(imp.getM_Product_Category_ID());
						model.setAssetManufacturer_ID(imp.getAssetManufacturer_ID());
						model.save();
						commitEx();
					}
					
					WhereClause="AssetManufacturer_ID="+imp.getAssetManufacturer_ID()
					+" AND M_Product_Category_ID="+imp.getM_Product_Category_ID()
					+" AND DM_AssetModelNo_ID="+model.get_ID()
					+" AND upper(ModelNo)='"+imp.getModelNo().toUpperCase().trim()+"'"
					+" AND AD_Client_ID="+Env.getAD_Client_ID(getCtx());
					MAssetModelDetails det =null;
					int[] modeldetID =  MAssetModelDetails.getAllIDs(MAssetModelDetails.Table_Name, WhereClause, get_TrxName());
					if(modeldetID.length>0){
						det = new MAssetModelDetails(getCtx(), modeldetID[0], get_TrxName());
						imp.setDM_AssetModelDetails_ID(modeldetID[0]);
						imp.setM_Product_ID(det.getM_Product_ID());
					}else{
						det = new MAssetModelDetails(getCtx(), 0, get_TrxName());
						det.setM_Product_Category_ID(imp.getM_Product_Category_ID());
						det.setAssetManufacturer_ID(imp.getAssetManufacturer_ID());
						det.setModelNo(imp.getModelNo().toUpperCase().trim());
						det.setDM_AssetModelNo_ID(model.get_ID());
						//det.setIsLaptopDektop(true);
						//det.setIsAccessories(true);
						//det.setIsNetComp(true);
						det.setIsPrinter(true);
						det.setHDD(imp.getHDD()>0? imp.getHDD() : 0);
						det.setRAM(imp.getRAM()>0? imp.getRAM() : 0);
						det.setGraphicsCard(imp.getGraphicsCard()>0? imp.getGraphicsCard() : 0);
						det.setIsCDROMAvailable(imp.isCDROMAvailable());
						det.setC_UOM_ID(100);
						if(imp.getProcessor()!=null){
							det.setProcessor(imp.getProcessor());
						}
						det.setScreenSize(imp.getScreenSize().compareTo(Env.ZERO)>0 ? imp.getScreenSize():Env.ZERO );	
						if(det.save()){
							imp.setDM_AssetModelDetails_ID(det.get_ID());
							commitEx();
						}
					}
					imp.setM_Product_ID(det.getM_Product_ID());
					 DB.executeUpdate("Update I_InOut set M_Product_ID="+det.getM_Product_ID()+""
								+ " Where I_InOut_ID="+imp.get_ID()+""
										+ " AND AD_Client_ID="+Env.getAD_Client_ID(getCtx()), get_TrxName());
				}
				 imp.save ();
				
			}
			rs.close ();
			pstmt.close ();
			//
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, "CreateBP", e);
		}
		sql = new StringBuffer ("UPDATE I_InOut "
			  + "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=No BPartner, ' "
			  + "WHERE C_BPartner_ID IS NULL"
			  + " AND I_IsImported<>'Y'").append (clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		if (no != 0)
			log.warning ("No BPartner=" + no);
		
		sql = new StringBuffer ("UPDATE I_InOut "
				  + "SET I_IsImported='E', I_ErrorMsg=I_ErrorMsg||'ERR=No NodelNo, ' "
				  + "WHERE DM_AssetModelDetails_ID IS NULL"
				  + " AND I_IsImported<>'Y'").append (clientCheck);
			no = DB.executeUpdate(sql.toString(), get_TrxName());
			if (no != 0)
				log.warning ("No NodelNo=" + no);
		
		commitEx();
		
		//	-- New Invoices -----------------------------------------------------

		int noInsert = 0;
		int noInsertLine = 0;

		//	Go through Invoice Records w/o
		sql = new StringBuffer ("SELECT * FROM I_InOut "
			  + "WHERE I_IsImported='N'").append (clientCheck)
			.append(" ORDER BY C_BPartner_ID, C_BPartner_Location_ID, I_InOut_ID");
		try
		{
			PreparedStatement pstmt = DB.prepareStatement (sql.toString(), get_TrxName());
			ResultSet rs = pstmt.executeQuery ();
//			//	Group Change
			int oldC_BPartner_ID = 0;
			int oldC_BPartner_Location_ID = 0;
			String oldDocumentNo = "";
//			//
			MInOut inout = null;
			int lineNo = 0;
			while (rs.next ())
			{
				X_I_InOut imp = new X_I_InOut (getCtx (), rs, null);
				String cmpDocumentNo = imp.getDocumentNo();
				if (cmpDocumentNo == null)
					cmpDocumentNo = "";
				//	New Invoice
				if (oldC_BPartner_ID != imp.getC_BPartner_ID() 
					|| oldC_BPartner_Location_ID != imp.getC_BPartner_Location_ID()
					|| !oldDocumentNo.equals(cmpDocumentNo)	)
				{
					if (inout != null)
					{
						inout.processIt(DocAction.ACTION_Complete);
						inout.saveEx();
					}
					//	Group Change
					oldC_BPartner_ID = imp.getC_BPartner_ID();
					oldC_BPartner_Location_ID = imp.getC_BPartner_Location_ID();
					oldDocumentNo = imp.getDocumentNo();
					if (oldDocumentNo == null)
						oldDocumentNo = "";
					//
					inout = new MInOut (getCtx(), 0, null);
					inout.setAD_Org_ID(imp.getAD_Org_ID());
					inout.setC_DocType_ID(imp.getC_DocType_ID());
					inout.setMovementType(MInOut.MOVEMENTTYPE_VendorReceipts);
					inout.setM_Warehouse_ID(imp.getM_Warehouse_ID());
					inout.setIsSOTrx(false);
					inout.setMovementDate(new Timestamp(System.currentTimeMillis()));
//					if (imp.getDocumentNo() != null)
//						inout.setDocumentNo(imp.getDocumentNo());
					//
					inout.setC_BPartner_ID(imp.getC_BPartner_ID());
					inout.setC_BPartner_Location_ID(imp.getC_BPartner_Location_ID());

					//
					if (imp.getDescription() != null)
						inout.setDescription(imp.getDescription());
					//
					
					//
					if (imp.getDateAcct() != null)
						inout.setDateAcct(imp.getDateAcct());
					else
						inout.setDateAcct(new Timestamp(System.currentTimeMillis()));
					
					//
					inout.saveEx();
					noInsert++;
					lineNo = 10;
				}
//				imp.setC_Invoice_ID (invoice.getC_Invoice_ID());
				//	New InvoiceLine
				MInOutLine line = new MInOutLine (inout);
				
				line.setLine(lineNo);
				lineNo += 10;
				if (imp.getDM_AssetModelDetails().getM_Product_ID() != 0)
					line.setM_Product_ID(imp.getDM_AssetModelDetails().getM_Product_ID(), true);
				// globalqss - import invoice with charges
				line.setAssetManufacturer_ID(imp.getAssetManufacturer_ID());
				line.setM_Product_Category_ID(imp.getM_Product_Category_ID());
				line.setDM_AssetModelDetails_ID(imp.getDM_AssetModelDetails_ID());
				line.setDeviceSerialNo(imp.getDeviceSerialNo().trim());
				line.setModelNo(imp.getDM_AssetModelDetails().getModelNo());
				line.setM_Locator_ID(imp.getM_Locator_ID());
				//line.setIsLaptopDektop(true);
				//line.setIsAccessories(true);
				//line.setIsNetComp(true);
				line.setIsPrinter(true);
				if(isuniquesrno)
					line.setIsSerialNoReq(true);
				line.setHDD(imp.getHDD()>0? imp.getHDD() : 0);
				line.setRAM(imp.getRAM()>0? imp.getRAM() : 0);
				line.setGraphicsCard(imp.getGraphicsCard()>0? imp.getGraphicsCard() : 0);
				line.setIsCDROMAvailable(imp.isCDROMAvailable());
				line.setC_UOM_ID(100);
				if(imp.getProcessor()!=null){
					line.setProcessor(imp.getProcessor());
				}
				if(imp.getMobilePhone()!=null){
					line.setMobilePhone(imp.getMobilePhone());
				}
				line.setPurchaseType("N");
				line.setScreenSize(imp.getScreenSize().compareTo(Env.ZERO)>0 ? imp.getScreenSize():Env.ZERO );	
				line.setOsInstalled(imp.getOsInstalled()!=null ? imp.getOsInstalled():null);
				line.setOsSerialNo(imp.getOsSerialNo()!=null ? imp.getOsSerialNo():null);
				line.setQty(Env.ONE);
				
				line.saveEx();
			
				imp.setI_IsImported(true);
				imp.setProcessed(true);
				//
				if (imp.save())
					noInsertLine++;
			}
//			
			rs.close();
			pstmt.close();
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, "CreateInvoice", e);
		}

		//	Set Error to indicator to not imported
		sql = new StringBuffer ("UPDATE I_InOut "
			+ "SET I_IsImported='N', Updated=SysDate "
			+ "WHERE I_IsImported<>'Y'").append(clientCheck);
		no = DB.executeUpdate(sql.toString(), get_TrxName());
		addLog (0, null, new BigDecimal (no), "@Errors@");
		//
		addLog (0, null, new BigDecimal (noInsert), "@C_Invoice_ID@: @Inserted@");
		addLog (0, null, new BigDecimal (noInsertLine), "@C_InvoiceLine_ID@: @Inserted@");
		return "";
	}	//	doIt

}	//	ImportInvoice