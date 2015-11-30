package org.asset.callout;

import java.util.Properties;

import org.compiere.model.CalloutEngine;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MProductCategory;

public class CalloutAssetModelDetails extends CalloutEngine {

	public CalloutAssetModelDetails() {
	}
	public String assetType (Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue)
	{
		Integer ProductCatID = (Integer)value;
		if (ProductCatID == null || ProductCatID <= 0)
			return "";
		if (isCalloutActive())
			return "";
		
		MProductCategory cat = new MProductCategory(ctx, ProductCatID, null);
		if(cat.isPrinter()){
			mTab.setValue("IsPrinter",true);
			mTab.setValue("IsLaptopDektop",false);
			mTab.setValue("IsNetComp",false);
			mTab.setValue("IsAccessories",false);
			mTab.setValue("IsPrinterAccessory",false);
			mTab.setValue("IsInternet",false);
		}
		if(cat.isLaptopDektop()){
			mTab.setValue("IsPrinter",false);
			mTab.setValue("IsLaptopDektop",true);
			mTab.setValue("IsNetComp",false);
			mTab.setValue("IsAccessories",false);
			mTab.setValue("IsPrinterAccessory",false);
			mTab.setValue("IsInternet",false);
		}
		if(cat.isNetComp()){
			mTab.setValue("IsPrinter",false);
			mTab.setValue("IsLaptopDektop",false);
			mTab.setValue("IsNetComp",true);
			mTab.setValue("IsAccessories",false);
			mTab.setValue("IsPrinterAccessory",false);
			mTab.setValue("IsInternet",false);
		}
		if(cat.isLDAccessory()){
			mTab.setValue("IsPrinter",false);
			mTab.setValue("IsLaptopDektop",false);
			mTab.setValue("IsNetComp",false);
			mTab.setValue("IsAccessories",true);
			mTab.setValue("IsPrinterAccessory",false);
			mTab.setValue("IsInternet",false);
		}
		if(cat.isPrinterAccessory() ){
			mTab.setValue("IsPrinter",false);
			mTab.setValue("IsLaptopDektop",false);
			mTab.setValue("IsNetComp",false);
			mTab.setValue("IsAccessories",false);
			mTab.setValue("IsPrinterAccessory",true);
			mTab.setValue("IsInternet",false);
		}
		if(cat.isInternet() ){
			mTab.setValue("IsPrinter",false);
			mTab.setValue("IsLaptopDektop",false);
			mTab.setValue("IsNetComp",false);
			mTab.setValue("IsAccessories",false);
			mTab.setValue("IsPrinterAccessory",false);
			mTab.setValue("IsInternet",true);
		}
		
		
		return "";
	}
	public String assetSelection(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue)
	{
		if (isCalloutActive() || value == null)
			return "";
		boolean IsSelected=false;
		if(value instanceof Boolean){
			IsSelected=(Boolean) value;
		}else{
			if(value instanceof String){
				if(value.equals("Y")){
					IsSelected=true;
				}else{
					IsSelected=false;
				}
			}
		}
		if(IsSelected){
			mTab.setValue(mField.getColumnName(), true);
			if(mField.getColumnName().equals("IsPrinter")){
				mTab.setValue("IsLaptopDektop",false);
				mTab.setValue("IsNetComp",false);
				mTab.setValue("IsLDAccessory",false);
				mTab.setValue("IsPrinterAccessory",false);
				mTab.setValue("IsInternet",false);
			}
			if(mField.getColumnName().equals("IsLaptopDektop")){
				mTab.setValue("IsPrinter",false);
				mTab.setValue("IsNetComp",false);
				mTab.setValue("IsLDAccessory",false);
				mTab.setValue("IsPrinterAccessory",false);
				mTab.setValue("IsInternet",false);
			}
			if(mField.getColumnName().equals("IsNetComp")){
				mTab.setValue("IsLaptopDektop",false);
				mTab.setValue("IsPrinter",false);
				mTab.setValue("IsLDAccessory",false);
				mTab.setValue("IsPrinterAccessory",false);
				mTab.setValue("IsInternet",false);
			}
			if(mField.getColumnName().equals("IsLDAccessory")){
				mTab.setValue("IsLaptopDektop",false);
				mTab.setValue("IsNetComp",false);
				mTab.setValue("IsPrinter",false);
				mTab.setValue("IsPrinterAccessory",false);
				mTab.setValue("IsInternet",false);
			}
			if(mField.getColumnName().equals("IsPrinterAccessory")){
				mTab.setValue("IsLaptopDektop",false);
				mTab.setValue("IsNetComp",false);
				mTab.setValue("IsLDAccessory",false);
				mTab.setValue("IsPrinter",false);
				mTab.setValue("IsInternet",false);
			}
			if(mField.getColumnName().equals("IsInternet")){
				mTab.setValue("IsLaptopDektop",false);
				mTab.setValue("IsNetComp",false);
				mTab.setValue("IsLDAccessory",false);
				mTab.setValue("IsPrinter",false);
				mTab.setValue("IsPrinterAccessory",false);
			}
		}
		return "";
	}
}
