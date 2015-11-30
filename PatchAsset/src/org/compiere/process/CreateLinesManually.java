package org.compiere.process;

import java.math.BigDecimal;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MAssetModelDetails;
import org.compiere.model.MInOut;
import org.compiere.model.MInOutLine;
import org.compiere.model.MLocator;
import org.compiere.model.MWarehouse;
import org.compiere.util.DB;
import org.compiere.util.Env;

public class CreateLinesManually extends SvrProcess {

	public CreateLinesManually() {
		// TODO Auto-generated constructor stub
	}
	int m_AssetManufacturer_ID;
	int m_M_Product_Category_ID;
	int m_DM_AssetModelDetails_ID;
	int m_M_InOut_ID;
	int m_RAM;
	int m_HDD;
	int m_GraphicsCard;
	int m_qty;
	String m_Processor;
	boolean m_cdrom;
	String m_srno;
	String m_OsInstalled;
	BigDecimal m_ScreenSize;
	@Override
	protected void prepare() {
		ProcessInfoParameter[] para = getParameter();
		for (int i = 0; i < para.length; i++)
		{
			String name = para[i].getParameterName();
			if (para[i].getParameter() == null)
				;
			else if (name.equals("AssetManufacturer_ID"))
				m_AssetManufacturer_ID = para[i].getParameterAsInt();
			else if (name.equals("M_Product_Category_ID"))
				m_M_Product_Category_ID = para[i].getParameterAsInt();
			else if (name.equals("DM_AssetModelDetails_ID"))
				m_DM_AssetModelDetails_ID = para[i].getParameterAsInt();
			else if (name.equals("RAM"))
				m_RAM = para[i].getParameterAsInt();
			else if (name.equals("HDD"))
				m_HDD = para[i].getParameterAsInt();
			else if (name.equals("GraphicsCard"))
				m_GraphicsCard = para[i].getParameterAsInt();
			else if (name.equals("Qty"))
				m_qty = para[i].getParameterAsInt();
			else if (name.equals("ScreenSize"))
				m_ScreenSize = para[i].getParameterAsBigDecimal();
			else if (name.equals("Processor"))
				m_Processor = para[i].getParameterAsString();
			else if (name.equals("IsCDROMAvailable"))
				m_cdrom = para[i].getParameterAsBoolean();
			else if (name.equals("DeviceSerialNo"))
				m_srno = para[i].getParameterAsString();
			else if (name.equals("OsInstalled"))
				m_OsInstalled = para[i].getParameterAsString();
			
			else
				log.log(Level.SEVERE, "Unknown Parameter: " + name);
		}
		m_M_InOut_ID = getRecord_ID();
	}

	@Override
	protected String doIt() throws Exception {
		MInOut out = new MInOut(getCtx(), m_M_InOut_ID, get_TrxName());
		MWarehouse  warehouse = new MWarehouse(getCtx(), out.getM_Warehouse_ID(), get_TrxName());
		MAssetModelDetails det = new MAssetModelDetails(getCtx(), m_DM_AssetModelDetails_ID, get_TrxName());
		MLocator loc = MLocator.getDefault(warehouse);
		if(m_srno!=null){
			String[] parts = m_srno.split(",");
			if(parts.length!=m_qty){
				throw new AdempiereException("Serialno != qty");
			}else{
				boolean exist=false;
				String sql="";
				String sn="";
				for(int i=0 ;i<parts.length;i++){
					sql="select m_inoutline_id from m_inoutline where AD_Client_ID="+Env.getAD_Client_ID(getCtx())+
							" AND DeviceSerialNo='"+parts[i]+"'";
					int lineid=DB.getSQLValue(get_TrxName(), sql);
					if(lineid>0){
						exist=true;
						sn=parts[i];
						break;
					}
				}
				if(exist){
					throw new AdempiereException("Duplicate Serial No Found : "+sn);
				}
				for(int i=0 ;i<m_qty;i++){
					MInOutLine line = new MInOutLine(getCtx(), 0, get_TrxName());
					line.setM_Product_Category_ID(m_M_Product_Category_ID);
					line.setAssetManufacturer_ID(m_AssetManufacturer_ID);
					line.setDM_AssetModelDetails_ID(m_DM_AssetModelDetails_ID);
					line.setM_InOut_ID(m_M_InOut_ID);
					line.setLine((i*10)+10);
					line.setQty(Env.ONE);
					line.setM_Product_ID(det.getM_Product_ID(), true);
					line.setM_Locator_ID(loc.get_ID());
					line.setDeviceSerialNo(parts[i]);
					line.setRAM(m_RAM);
					line.setScreenSize(m_ScreenSize);
					line.setIsLaptopDektop(true);
					line.setHDD(m_HDD);
					line.setGraphicsCard(m_GraphicsCard);
					line.setIsSerialNoReq(true);
					line.setIsCDROMAvailable(m_cdrom);
					line.setProcessor(m_Processor);
					line.setPurchaseType("N");
					if(m_OsInstalled!=null){
						line.setOsInstalled(m_OsInstalled);
					}
					line.save();
				}
			}
				
		}else{
			for(int i=0 ;i<m_qty;i++){
				MInOutLine line = new MInOutLine(getCtx(), 0, get_TrxName());
				line.setM_Product_Category_ID(m_M_Product_Category_ID);
				line.setAssetManufacturer_ID(m_AssetManufacturer_ID);
				line.setDM_AssetModelDetails_ID(m_DM_AssetModelDetails_ID);
				line.setM_InOut_ID(m_M_InOut_ID);
				line.setLine((i*10)+10);
				line.setQty(Env.ONE);
				line.setM_Product_ID(det.getM_Product_ID(), true);
				line.setM_Locator_ID(loc.get_ID());
				line.setRAM(m_RAM);
				line.setScreenSize(m_ScreenSize);
				line.setIsLaptopDektop(true);
				line.setHDD(m_HDD);
				line.setGraphicsCard(m_GraphicsCard);
				line.setIsSerialNoReq(true);
				line.setIsCDROMAvailable(m_cdrom);
				line.setProcessor(m_Processor);
				line.setPurchaseType("N");
				if(m_OsInstalled!=null){
					line.setOsInstalled(m_OsInstalled);
				}
				line.save();
			}
		}
		return null;
	}

}
