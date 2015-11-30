package org.compiere.model;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

import org.adempiere.model.MProductGroup;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Util;

public class MAssetModelDetails extends X_DM_AssetModelDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MAssetModelDetails(Properties ctx, int DM_AssetModelDetails_ID, String trxName) {
		super(ctx, DM_AssetModelDetails_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MAssetModelDetails(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected boolean beforeSave (boolean newRecord)
	{
		
		int cnt = DB.getSQLValue(get_TrxName(),
				"SELECT COUNT(*) FROM DM_AssetModelDetails  WHERE M_Product_Category_ID=? AND DM_AssetModelNo_ID=? AND Lower(ModelNo)=?"
				+ " AND ISActive='Y' AND AD_Client_ID=? AND DM_AssetModelDetails_ID!=? "
				+ " ",
				getM_Product_Category_ID(),getDM_AssetModelNo_ID(), getModelNo().toLowerCase(),getAD_Client_ID(), get_ID());
		if (cnt > 0) {
			Object[] args = new Object[] {"Item Type : "+getM_Product_Category().getName(),"Model No ", getModelNo()};
			log.saveError("SaveError", Msg.getMsg(getCtx(), "SAVE_ERROR_NOT_UNIQUE_MSG",args) +" - "+ "Model No"+"("+getModelNo()+")");
			return false;
		}
		setModelNo(getModelNo().toUpperCase().trim());
		if(!newRecord){
			if(is_ValueChanged("ModelNo")){
				if(getM_Product()!=null){
					MProduct product=new MProduct(getCtx(), getM_Product_ID(), get_TrxName());
					product.setName(getModelNo());
					product.save();
				}
			}
		}
		setAssetManufacturer_ID(getDM_AssetModelNo().getAssetManufacturer_ID());
		setM_Product_Category_ID(getDM_AssetModelNo().getM_Product_Category_ID());
		
		return true;
	}
	protected boolean afterSave (boolean newRecord, boolean success)
	{
		if (!success)
			return success;
		String WhereClause="lower(Name)='"+getModelNo().toLowerCase()+"' "
				+ "  AND M_Product_Category_ID="+getM_Product_Category_ID()+"  AND AD_Client_ID="+Env.getAD_Client_ID(getCtx());
		int[] productID =  MProduct.getAllIDs(MProduct.Table_Name, WhereClause, get_TrxName());
		
		int[] taxcatID=MTaxCategory.getAllIDs(MTaxCategory.Table_Name, "IsDefault='Y'", get_TrxName());
		MProduct product=null;
		if(productID.length>0){
			product=new MProduct(getCtx(), productID[0], get_TrxName());
		}else{
			product=new MProduct(getCtx(), 0, get_TrxName());
			product.setName(getModelNo());
			product.setM_Product_Category_ID(getM_Product_Category_ID());
			product.setProductType("I");
			product.setC_UOM_ID(getC_UOM_ID());
			product.setIsPurchased(true);
			product.setIsSold(false);
			product.setIsSelfService(true);
			product.setIsStocked(true);
			product.setC_TaxCategory_ID(taxcatID[0]);
			if(getproductgroup()>0){
				product.setM_Product_Group_ID(getproductgroup());
			}
			if(product.save()){
				DB.executeUpdate("Update DM_AssetModelDetails set M_Product_ID="+product.get_ID()+""
						+ " Where DM_AssetModelDetails_ID="+get_ID()+""
								+ " AND AD_Client_ID="+Env.getAD_Client_ID(getCtx()), get_TrxName());
				MProductPO[] po = MProductPO.getOfProduct(getCtx(), product.get_ID(), get_TrxName());
				if(po.length<=0){
					MProductPO vendor=new MProductPO(getCtx(), 0, get_TrxName());
					vendor.setC_BPartner_ID(getAssetManufacturer_ID());
					vendor.setVendorProductNo(product.getValue());
					vendor.setIsCurrentVendor(true);
					vendor.setM_Product_ID(product.get_ID());
					vendor.save();
				}
			}
		}
		return success;
	}
	
	protected boolean beforeDelete ()
	{
		
		for (MAssetDetails line : getlinescad()) {
			line.deleteEx(true);
		}
		for (MSupportedPrinterModel line : getlinespr()) {
			line.deleteEx(true);
		}
		return true;
	}
	protected boolean afterDelete (boolean success)
	{
		if(getM_Product()!=null){
			MProduct product=new MProduct(getCtx(), getM_Product_ID(), get_TrxName());
			product.deleteEx(true);
		}
		return success;
	}	//	afterDelete
	
	public int getproductgroup(){
		int progroup=0;
		String name="";
		if(isLaptopDektop())
			name="Laptop/Desktop/AIO";
		else if(isPrinter())
			name="Printer";
		else if(isInternet())
			name= "Mobile/Internet Solution";
		else if(isNetComp())
			name="Network Components";
		else
			name="Accessories";
		
		String WhereClause="lower(Name)='"+name.toLowerCase()+"'  AND AD_Client_ID="+Env.getAD_Client_ID(getCtx());
		int[] proGrpID =  MProductGroup.getAllIDs(MProductGroup.Table_Name, WhereClause, get_TrxName());
		if(proGrpID.length>0){
			progroup=proGrpID[0];
		}else{
			MProductGroup progrp=new MProductGroup(getCtx(), 0, get_TrxName());
			progrp.setName(name);
			progrp.setIsDefault(false);
			if(progrp.save()){
				progroup=progrp.get_ID();
			}
		}
		return progroup;
	}
	private MAssetDetails[] m_cad=null;
	private MSupportedPrinterModel[] m_pri=null;
	public MAssetDetails[] getlinescad (String whereClause, String orderClause)
	{
		//red1 - using new Query class from Teo / Victor's MDDOrder.java implementation
		StringBuffer whereClauseFinal = new StringBuffer(MAssetDetails.COLUMNNAME_DM_AssetModelDetails_ID+"=? ");
		if (!Util.isEmpty(whereClause, true))
			whereClauseFinal.append(whereClause);
		if (orderClause.length() == 0)
			orderClause = MAssetDetails.COLUMNNAME_DM_AssetDetails_ID;
		//
		List<MAssetDetails> list = new Query(getCtx(), MAssetDetails.Table_Name, whereClauseFinal.toString(), get_TrxName())
										.setParameters(get_ID())
										.setOrderBy(orderClause)
										.list();

		return list.toArray(new MAssetDetails[list.size()]);		
	}	//	getLines

	/**
	 * 	Get Lines of Order
	 * 	@param requery requery
	 * 	@param orderBy optional order by column
	 * 	@return lines
	 */
	public MAssetDetails[] getlinescad (boolean requery, String orderBy)
	{
		if (m_cad != null && !requery) {
			set_TrxName(m_cad, get_TrxName());
			return m_cad;
		}
		//
		String orderClause = "";
		if (orderBy != null && orderBy.length() > 0)
			orderClause += orderBy;
		else
			orderClause += "DM_AssetDetails_ID";
		m_cad = getlinescad(null, orderClause);
		return m_cad;
	}	//	getLines

	/**
	 * 	Get Lines of Order.
	 * 	(used by web store)
	 * 	@return lines
	 */
	public MAssetDetails[] getlinescad()
	{
		return getlinescad(false, null);
	}	//	getLines

	public MSupportedPrinterModel[] getlinespr (String whereClause, String orderClause)
	{
		//red1 - using new Query class from Teo / Victor's MDDOrder.java implementation
		StringBuffer whereClauseFinal = new StringBuffer(MSupportedPrinterModel.COLUMNNAME_DM_AssetModelDetails_ID+"=? ");
		if (!Util.isEmpty(whereClause, true))
			whereClauseFinal.append(whereClause);
		if (orderClause.length() == 0)
			orderClause = MSupportedPrinterModel.COLUMNNAME_DM_SupportedPrinterModel_ID;
		//
		List<MSupportedPrinterModel> list = new Query(getCtx(), MSupportedPrinterModel.Table_Name, whereClauseFinal.toString(), get_TrxName())
										.setParameters(get_ID())
										.setOrderBy(orderClause)
										.list();

		return list.toArray(new MSupportedPrinterModel[list.size()]);		
	}	//	getLines

	/**
	 * 	Get Lines of Order
	 * 	@param requery requery
	 * 	@param orderBy optional order by column
	 * 	@return lines
	 */
	public MSupportedPrinterModel[] getlinespr (boolean requery, String orderBy)
	{
		if (m_pri != null && !requery) {
			set_TrxName(m_pri, get_TrxName());
			return m_pri;
		}
		//
		String orderClause = "";
		if (orderBy != null && orderBy.length() > 0)
			orderClause += orderBy;
		else
			orderClause += "DM_SupportedPrinterModel_ID";
		m_pri = getlinespr(null, orderClause);
		return m_pri;
	}	//	getLines

	/**
	 * 	Get Lines of Order.
	 * 	(used by web store)
	 * 	@return lines
	 */
	public MSupportedPrinterModel[] getlinespr()
	{
		return getlinespr(false, null);
	}	//	getLines

}
