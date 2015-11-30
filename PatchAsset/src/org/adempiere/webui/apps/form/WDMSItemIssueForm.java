package org.adempiere.webui.apps.form;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;

import org.adempiere.webui.component.ListModelTable;
import org.adempiere.exceptions.AdempiereException;
import org.adempiere.webui.LayoutUtils;
import org.adempiere.webui.apps.AEnv;
import org.adempiere.webui.apps.ProcessModalDialog;
import org.adempiere.webui.component.Button;
import org.adempiere.webui.component.Checkbox;
import org.adempiere.webui.component.Grid;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.ListHeader;
import org.adempiere.webui.component.ListItem;
import org.adempiere.webui.component.Listbox;
import org.adempiere.webui.component.NumberBox;
import org.adempiere.webui.component.Row;
import org.adempiere.webui.component.Rows;
import org.adempiere.webui.component.Textbox;
import org.adempiere.webui.component.WListItemRenderer;
import org.adempiere.webui.component.WListbox;
import org.adempiere.webui.panel.ADForm;
import org.adempiere.webui.session.SessionManager;
import org.adempiere.webui.window.FDialog;
import org.compiere.model.GridField;
import org.compiere.model.Lookup;
import org.compiere.model.MDMSEmployee;
import org.compiere.model.MDocType;
import org.compiere.model.MInOut;
import org.compiere.model.MInOutLine;
import org.compiere.model.MInvoiceTax;
import org.compiere.model.MIssuedToEmployee;
import org.compiere.model.MLocator;
import org.compiere.model.MProcess;
import org.compiere.model.MStorageDetails;
import org.compiere.model.MSysConfig;
import org.compiere.model.MWarehouse;
import org.compiere.process.DocAction;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Msg;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zkex.zul.Borderlayout;
import org.zkoss.zkex.zul.Center;
import org.zkoss.zkex.zul.South;
import org.zkoss.zul.Div;

public class WDMSItemIssueForm extends ADForm implements EventListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static CLogger log = CLogger.getCLogger(WDMSItemIssueForm.class);

	private Label lDMSemployee = new Label("DMS Employee");
	private Listbox fDMSemployee = new Listbox();
	private Label lDMScode = new Label("Employee Code");
	private Textbox fDMScode = new Textbox();
	private Label lEmpName = new Label("Employee Name");
	private Textbox fEmpName = new Textbox();
	
	private Label lapprovedBy = new Label("Approved By");
	private Listbox fapprovedBy = new Listbox();
	private Label lcheckedBy = new Label("Checked By");
	private Listbox fcheckedBy = new Listbox();
	private Label lissuedBy = new Label("Issued By");
	private Listbox fissuedBy = new Listbox();
	
	
	private Label lwarehouse = new Label("Warehouse/Org Location");
	private Listbox fwarehouse = new Listbox();
	private Label llocator = new Label("Locator / Storage");
	private Listbox flocator = new Listbox();
	
	
	private Label litemType = new Label("Item Type");
	private Listbox fitemType = new Listbox();
	private Label lManufacturer = new Label("Manufacturer");
	private Listbox fManufacturer = new Listbox();
	private Label lmodelNo = new Label("Model No");
	private Listbox fmodelNo = new Listbox();
	
	private Label lserialNo = new Label("Device Serial No");
	private Listbox fserialNo = new Listbox();
	private Label lquantity = new Label("Quantity");
	private NumberBox fquantity = new NumberBox(true);

	private Label lmateissuenote = new Label("Material Issue Note");
	private Textbox fmateissuenote= new Textbox();
	private Label lcarrytr = new Label("Carry Through");
	private Textbox fcarrytr = new Textbox();
	private Checkbox isReturn = new Checkbox();
	private Button btnIssue = new Button();
	private Button btnPrint = new Button();
	private Button btnReset = new Button();
	private Button btnZoom = new Button();
	private Button btnReturn = new Button();
	
	private ListModelTable model = null;
	private WListbox listbox = new WListbox();
	private MIssuedToEmployee[] 		m_activities = null;
	MIssuedToEmployee m_activity=null;
	public WDMSItemIssueForm() {
		super();
	}

	@Override
	protected void initForm() {
		btnIssue.setLabel("Issue To Employee");
		btnPrint.setLabel("Print Issue Letter");
		btnReset.setLabel("Reset Form");
		btnZoom.setLabel("ZOOM To Form");
		btnReturn.setLabel("Return From Employee");
		fDMSemployee.setMold("select");
		fapprovedBy.setMold("select");
		fcheckedBy.setMold("select");
		fissuedBy.setMold("select");
		fitemType.setMold("select");
		fwarehouse.setMold("select");
		flocator.setMold("select");
		fManufacturer.setMold("select");
		fmodelNo.setMold("select");
		fserialNo.setMold("select");
		isReturn.setLabel("Item Return");
		init();
		updatecheckedby();
		updateissuedby();
		updateapprovedby();
		updatewarehouse();
		updateLocator();
		updatedms();
		updateManufacturer();
		updateItemType();
		loadActivities();
	}

	private void init() {
		Grid grid = new Grid();
		grid.setWidth("100%");
		grid.setHeight("100%");
		grid.setStyle("margin:0; padding:0; position: absolute; align: center; valign: center;");
		grid.makeNoStrip();
		grid.setOddRowSclass("even");

		Rows rows = new Rows();
		grid.appendChild(rows);

		Row row = new Row();
		rows.appendChild(row);
		Div div = new Div();
		div.setAlign("center");
		div.appendChild(lDMSemployee);
		row.appendChild(div);
		row.appendChild(fDMSemployee);
		fDMSemployee.setId("fDMSemployee");
		fDMSemployee.setWidth("350px");
		fDMSemployee.setMaxlength(40);
		fDMSemployee.addEventListener(Events.ON_SELECT, this);

		rows.appendChild(row);
		row.setValign("top");
		div = new Div();
		div.setAlign("center");
		div.appendChild(lDMScode);
		row.appendChild(div);
		row.appendChild(fDMScode);
		fDMScode.setId("fDMScode");
		fDMScode.setWidth("350px");
		fDMScode.addEventListener(Events.ON_OK, this);
		
		row = new Row();
		rows.appendChild(row);
		div = new Div();
		div.setAlign("center");
		div.appendChild(lEmpName);
		row.appendChild(div);
		row.appendChild(fEmpName);
		fEmpName.setId("fEmpName");
		fEmpName.setWidth("350px");
		fEmpName.setReadonly(true);
		
		row = new Row();
		rows.appendChild(row);
		div = new Div();
		div.setAlign("center");
		div.appendChild(lwarehouse);
		row.appendChild(div);
		row.appendChild(fwarehouse);
		fwarehouse.setId("fwarehouse");
		fwarehouse.setWidth("350px");
		fwarehouse.setMaxlength(40);
		fwarehouse.addEventListener(Events.ON_SELECT, this);

		rows.appendChild(row);
		row.setValign("top");
		div = new Div();
		div.setAlign("center");
		div.appendChild(llocator);
		row.appendChild(div);
		row.appendChild(flocator);
		flocator.setId("flocator");
		flocator.setWidth("350px");
		flocator.addEventListener(Events.ON_SELECT, this);
		
		row = new Row();
		rows.appendChild(row);
		div = new Div();
		div.setAlign("center");
		div.appendChild(litemType);
		row.appendChild(div);
		row.appendChild(fitemType);
		fitemType.setId("fitemType");
		fitemType.setWidth("350px");
		fitemType.setMaxlength(40);
		fitemType.addEventListener(Events.ON_SELECT, this);

		rows.appendChild(row);
		row.setValign("top");
		div = new Div();
		div.setAlign("center");
		div.appendChild(lManufacturer);
		row.appendChild(div);
		row.appendChild(fManufacturer);
		fManufacturer.setId("fManufacturer");
		fManufacturer.setWidth("350px");
		fManufacturer.addEventListener(Events.ON_SELECT, this);
		
		row = new Row();
		rows.appendChild(row);
		div = new Div();
		div.setAlign("center");
		div.appendChild(lmodelNo);
		row.appendChild(div);
		row.appendChild(fmodelNo);
		fmodelNo.setId("fmodelNo");
		fmodelNo.setWidth("350px");
		fmodelNo.setMaxlength(40);
		fmodelNo.addEventListener(Events.ON_SELECT, this);
		
		rows.appendChild(row);
		div = new Div();
		div.setAlign("center");
		div.appendChild(lserialNo);
		row.appendChild(div);
		row.appendChild(fserialNo);
		fserialNo.setId("fserialNo");
		fserialNo.setWidth("350px");
		fserialNo.addEventListener(Events.ON_SELECT, this);
		
		row = new Row();
		rows.appendChild(row);
		div = new Div();
		div.setAlign("center");
		div.appendChild(lquantity);
		row.appendChild(div);
		row.appendChild(fquantity);
		fquantity.setId("fquantity");
		fquantity.setWidth("350px");
		fquantity.setValue(1);
		
		rows.appendChild(row);
		div = new Div();
		div.setAlign("center");
		div.appendChild(lissuedBy);
		row.appendChild(div);
		row.appendChild(fissuedBy);
		fissuedBy.setId("fissuedBy");
		fissuedBy.setWidth("350px");
		fissuedBy.setEnabled(false);
		
		row = new Row();
		rows.appendChild(row);
		div = new Div();
		div.setAlign("center");
		div.appendChild(lcheckedBy);
		row.appendChild(div);
		row.appendChild(fcheckedBy);
		fcheckedBy.setId("fcheckedBy");
		fcheckedBy.setWidth("350px");
		
		rows.appendChild(row);
		div = new Div();
		div.setAlign("center");
		div.appendChild(lapprovedBy);
		row.appendChild(div);
		row.appendChild(fapprovedBy);
		fapprovedBy.setId("fapprovedBy");
		fapprovedBy.setWidth("350px");
		
		row = new Row();
		rows.appendChild(row);
		div = new Div();
		div.setAlign("center");
		div.appendChild(lmateissuenote);
		row.appendChild(div);
		row.appendChild(fmateissuenote);
		fmateissuenote.setId("fmateissuenote");
		fmateissuenote.setMultiline(true);
		fmateissuenote.setWidth("350px");
		fmateissuenote.setHeight("40px");
		
		rows.appendChild(row);
		div = new Div();
		div.setAlign("center");
		div.appendChild(lcarrytr);
		row.appendChild(div);
		row.appendChild(fcarrytr);
		fcarrytr.setId("fcarrytr");
		fcarrytr.setWidth("350px");
		fcarrytr.setMultiline(true);
		fcarrytr.setHeight("40px");
		
		row = new Row();
		rows.appendChild(row);
		div = new Div();
		div.setAlign("right");
		row.appendChild(div);
		row.appendChild(btnIssue);
		btnIssue.setName("btnIssue");
		btnIssue.setImage("/images/BPartner24.png");
		btnIssue.setWidth("350px");
		btnIssue.addEventListener(Events.ON_CLICK, this);
		LayoutUtils.addSclass("action-button", btnIssue);
		
		rows.appendChild(row);
		div = new Div();
		div.setAlign("right");
		row.appendChild(div);
		row.appendChild(btnPrint);
		btnPrint.setName("btnPrint");
		btnPrint.setImage("/images/Print24.png");
		btnPrint.setWidth("350px");
		btnPrint.addEventListener(Events.ON_CLICK, this);
		LayoutUtils.addSclass("action-button", btnPrint);
		
		row = new Row();
		rows.appendChild(row);
		div = new Div();
		div.setAlign("right");
		row.appendChild(div);
		row.appendChild(btnReset);
		btnReset.setName("btnReset");
		btnReset.setImage("/images/Refresh24.png");
		btnReset.setWidth("350px");
		btnReset.addEventListener(Events.ON_CLICK, this);
		LayoutUtils.addSclass("action-button", btnReset);
		
		
		rows.appendChild(row);
		div = new Div();
		div.setAlign("right");
		row.appendChild(div);
		row.appendChild(btnZoom);
		btnZoom.setName("btnZoom");
		btnZoom.setImage("/images/Zoom24.png");
		btnZoom.setWidth("350px");
		btnZoom.addEventListener(Events.ON_CLICK, this);
		LayoutUtils.addSclass("action-button", btnZoom);
		
		row = new Row();
		rows.appendChild(row);
		div = new Div();
		div.setAlign("right");
		row.appendChild(div);
		row.appendChild(isReturn);
		isReturn.setName("isReturn");
		isReturn.setId("isReturn");
		isReturn.setChecked(false);
		isReturn.addEventListener(Events.ON_CHECK, this);
		LayoutUtils.addSclass("action-button", isReturn);
		
		rows.appendChild(row);
		div = new Div();
		div.setAlign("right");
		row.appendChild(div);
		row.appendChild(btnReturn);
		btnReturn.setName("btnReturn");
		btnReturn.setImage("/images/Ignore24.png");
		btnReturn.setWidth("350px");
		btnReturn.setEnabled(isReturn.isChecked());
		btnReturn.addEventListener(Events.ON_CLICK, this);
		LayoutUtils.addSclass("action-button", btnReturn);
		
		Borderlayout layout = new Borderlayout();
		layout.setWidth("100%");
		layout.setHeight("100%");
		layout.setStyle("background-color: transparent; position: absolute;");

		Center center = new Center();
		center.appendChild(grid);
		layout.appendChild(center);
		center.setStyle("background-color: transparent");
		center.setFlex(true);
		
		South south = new South();
		south.appendChild(listbox);
//		south.setSplittable(true);
		south.setFlex(true);
		south.setHeight("30%");
		layout.appendChild(south);
		south.setStyle("background-color: transparent");
		listbox.setId("activity");
		listbox.addEventListener(Events.ON_DOUBLE_CLICK, this);
		listbox.addEventListener(Events.ON_SELECT, this);
		
		this.appendChild(layout);
		this.setStyle("height: 100%; width: 100%; position: absolute;");
	}

	private void updatedms() {
		fDMSemployee.getItems().clear();

		KeyNamePair orgKNPairs[] = getdms();
		if (orgKNPairs != null && orgKNPairs.length > 0) {
			for (int i = 0; i < orgKNPairs.length; i++) {
				fDMSemployee.addItem(orgKNPairs[i]);
			}
		}
		if(fDMSemployee.getSelectedItem()!=null){
			ListItem employee = (ListItem) fDMSemployee.getSelectedItem();
			int employee_id = Integer.parseInt(employee.getValue().toString());
			MDMSEmployee mngt = new MDMSEmployee(Env.getCtx(), employee_id, null);
			
			StringBuffer sb = new StringBuffer();
			if(mngt.getFName() !=null){
				sb.append(mngt.getFName());
			}
			if(mngt.getMName() !=null){
				sb.append(" ").append(mngt.getMName());
			}
			if(mngt.getLName() !=null){
				sb.append(" ").append(mngt.getLName());
			}
			fEmpName.setValue(sb.toString());
		}
	}

	private KeyNamePair[] getdms() {
		// Using current locale for default values - teo_sarca [ 1691388 ]
		ArrayList<KeyNamePair> list = new ArrayList<KeyNamePair>();
		KeyNamePair[] retValue = null;
		// Currency
		
		String sql = "SELECT DM_DMSEmployee_ID, FName::text"+"||"+"' '"+"||"+"LName::text"+"||"+"'-'"+"||"+"EmployeeCode::text FROM DM_DMSEmployee Where AD_Client_ID="+Env.getAD_Client_ID(Env.getCtx())
				+" order by FName";
		Statement stmt = null;
		ResultSet rs = null;
		try
		{
			stmt = DB.createStatement();
			rs = stmt.executeQuery(sql);
			if (!rs.next())
			{
				rs.close();
				stmt.close();
				
				return null;
			}
			do
			{
				int DM_DocumentManagent_ID = rs.getInt(1);
				String EmployeeCode = rs.getString(2);
				KeyNamePair p = new KeyNamePair(DM_DocumentManagent_ID, EmployeeCode);
				list.add(p);
			}
			
			while (rs.next());

			rs.close();
			stmt.close();
			stmt = null;
			//
			retValue = new KeyNamePair[list.size()];
			list.toArray(retValue);
		}
		catch (SQLException e1)
		{
			log.log(Level.SEVERE, "VSetup.dynInit -currency", e1);
		}
		finally
		{
			DB.close(rs, stmt);
			rs = null; stmt = null;
		}

		return retValue;

	} // dynInit
	private void updatewarehouse() {
		fwarehouse.getItems().clear();

		KeyNamePair orgKNPairs[] = getwarehouse();
		if (orgKNPairs != null && orgKNPairs.length > 0) {
			for (int i = 0; i < orgKNPairs.length; i++) {
				fwarehouse.addItem(orgKNPairs[i]);

			}
		}
	}

	private KeyNamePair[] getwarehouse() {
		// Using current locale for default values - teo_sarca [ 1691388 ]
		ArrayList<KeyNamePair> list = new ArrayList<KeyNamePair>();
		KeyNamePair[] retValue = null;
		// Currency
		
		String sql = "SELECT M_Warehouse_ID, Name FROM M_Warehouse Where AD_Client_ID="+Env.getAD_Client_ID(Env.getCtx());
				if(!isReturn.isChecked())
					sql=sql.concat(" AND M_Warehouse_ID="+Env.getContextAsInt(Env.getCtx(), "#M_Warehouse_ID") +" order by Name");
		Statement stmt = null;
		ResultSet rs = null;
		try
		{
			stmt = DB.createStatement();
			rs = stmt.executeQuery(sql);
			if (!rs.next())
			{
				rs.close();
				stmt.close();
				
				return null;
			}
			do
			{
				int DM_DocumentManagent_ID = rs.getInt(1);
				String EmployeeCode = rs.getString(2);
				KeyNamePair p = new KeyNamePair(DM_DocumentManagent_ID, EmployeeCode);
				list.add(p);
			}
			
			while (rs.next());

			rs.close();
			stmt.close();
			stmt = null;
			//
			retValue = new KeyNamePair[list.size()];
			list.toArray(retValue);
		}
		catch (SQLException e1)
		{
			log.log(Level.SEVERE, "VSetup.dynInit -currency", e1);
		}
		finally
		{
			DB.close(rs, stmt);
			rs = null; stmt = null;
		}

		return retValue;

	} // dynInit
	private void updatecheckedby() {
		fcheckedBy.getItems().clear();

		KeyNamePair orgKNPairs[] = getuser();
		if (orgKNPairs != null && orgKNPairs.length > 0) {
			for (int i = 0; i < orgKNPairs.length; i++) {
				fcheckedBy.addItem(orgKNPairs[i]);

			}
		}
	}
	private void updateapprovedby() {
		fapprovedBy.getItems().clear();

		KeyNamePair orgKNPairs[] = getuser();
		if (orgKNPairs != null && orgKNPairs.length > 0) {
			for (int i = 0; i < orgKNPairs.length; i++) {
				fapprovedBy.addItem(orgKNPairs[i]);

			}
		}
	}
	private void updateissuedby() {
		fissuedBy.getItems().clear();

		KeyNamePair orgKNPairs[] = getuserissue();
		if (orgKNPairs != null && orgKNPairs.length > 0) {
			for (int i = 0; i < orgKNPairs.length; i++) {
				fissuedBy.addItem(orgKNPairs[i]);

			}
		}
	}

	private KeyNamePair[] getuserissue() {
		// Using current locale for default values - teo_sarca [ 1691388 ]
		ArrayList<KeyNamePair> list = new ArrayList<KeyNamePair>();
		KeyNamePair[] retValue = null;
		// Currency
		
		String sql = "SELECT AD_User_ID, Name FROM AD_User Where AD_Client_ID="+Env.getAD_Client_ID(Env.getCtx())
				+" AND AD_User_ID="+Env.getAD_User_ID(Env.getCtx())+" order by Name";
		Statement stmt = null;
		ResultSet rs = null;
		try
		{
			stmt = DB.createStatement();
			rs = stmt.executeQuery(sql);
			if (!rs.next())
			{
				rs.close();
				stmt.close();
				
				return null;
			}
			do
			{
				int DM_DocumentManagent_ID = rs.getInt(1);
				String EmployeeCode = rs.getString(2);
				KeyNamePair p = new KeyNamePair(DM_DocumentManagent_ID, EmployeeCode);
				list.add(p);
			}
			
			while (rs.next());

			rs.close();
			stmt.close();
			stmt = null;
			//
			retValue = new KeyNamePair[list.size()];
			list.toArray(retValue);
		}
		catch (SQLException e1)
		{
			log.log(Level.SEVERE, "VSetup.dynInit -currency", e1);
		}
		finally
		{
			DB.close(rs, stmt);
			rs = null; stmt = null;
		}

		return retValue;

	} // dynInit
	private KeyNamePair[] getuser() {
		// Using current locale for default values - teo_sarca [ 1691388 ]
		ArrayList<KeyNamePair> list = new ArrayList<KeyNamePair>();
		KeyNamePair[] retValue = null;
		// Currency
		
		String sql = "SELECT AD_User_ID, Name FROM AD_User Where AD_Client_ID="+Env.getAD_Client_ID(Env.getCtx())
				+" order by Name";
		Statement stmt = null;
		ResultSet rs = null;
		try
		{
			stmt = DB.createStatement();
			rs = stmt.executeQuery(sql);
			if (!rs.next())
			{
				rs.close();
				stmt.close();
				
				return null;
			}
			do
			{
				int DM_DocumentManagent_ID = rs.getInt(1);
				String EmployeeCode = rs.getString(2);
				KeyNamePair p = new KeyNamePair(DM_DocumentManagent_ID, EmployeeCode);
				list.add(p);
			}
			
			while (rs.next());

			rs.close();
			stmt.close();
			stmt = null;
			//
			retValue = new KeyNamePair[list.size()];
			list.toArray(retValue);
		}
		catch (SQLException e1)
		{
			log.log(Level.SEVERE, "VSetup.dynInit -currency", e1);
		}
		finally
		{
			DB.close(rs, stmt);
			rs = null; stmt = null;
		}

		return retValue;

	} // dynInit
	
	private void updateLocator() {
		flocator.getItems().clear();

		KeyNamePair orgKNPairs[] = getLocator();
		if (orgKNPairs != null && orgKNPairs.length > 0) {
			for (int i = 0; i < orgKNPairs.length; i++) {
				flocator.addItem(orgKNPairs[i]);

			}
		}
	}

	private KeyNamePair[] getLocator() {
		// Using current locale for default values - teo_sarca [ 1691388 ]
		ArrayList<KeyNamePair> list = new ArrayList<KeyNamePair>();
		KeyNamePair[] retValue = null;
		// Currency1
		String sql = "";
		if((ListItem) fwarehouse.getSelectedItem()!=null){
			ListItem warehouse = (ListItem) fwarehouse.getSelectedItem();
			int warehouseid= Integer.parseInt(warehouse.getValue().toString());
			
			sql = "SELECT M_Locator_ID, Value FROM M_Locator Where AD_Client_ID="+Env.getAD_Client_ID(Env.getCtx())
			+" AND M_Warehouse_ID="+warehouseid	
			+" order by Value";
		}else{
			sql = "SELECT M_Locator_ID, Value FROM M_Locator Where AD_Client_ID="+Env.getAD_Client_ID(Env.getCtx())
			+" AND M_Warehouse_ID="+Env.getContextAsInt(Env.getCtx(), "#M_Warehouse_ID")		
			+" order by Value";
		}
		
	
		Statement stmt = null;
		ResultSet rs = null;
		try
		{
			stmt = DB.createStatement();
			rs = stmt.executeQuery(sql);
			if (!rs.next())
			{
				rs.close();
				stmt.close();
				
				return null;
			}
			do
			{
				int DM_DocumentManagent_ID = rs.getInt(1);
				String EmployeeCode = rs.getString(2);
				KeyNamePair p = new KeyNamePair(DM_DocumentManagent_ID, EmployeeCode);
				list.add(p);
			}
			
			while (rs.next());

			rs.close();
			stmt.close();
			stmt = null;
			//
			retValue = new KeyNamePair[list.size()];
			list.toArray(retValue);
		}
		catch (SQLException e1)
		{
			log.log(Level.SEVERE, "VSetup.dynInit -currency", e1);
		}
		finally
		{
			DB.close(rs, stmt);
			rs = null; stmt = null;
		}

		return retValue;

	} // dynInit
	
	private void updateItemType() {
			fitemType.getItems().clear();
			
			KeyNamePair orgKNPairs[] = getItems();
	        if(orgKNPairs != null && orgKNPairs.length > 0)
	        {
	            for(int i = 0; i < orgKNPairs.length; i++)
	            {
	            	fitemType.addItem(orgKNPairs[i]);
	            }
	        }
	        updatemodelNo();
	}
 private KeyNamePair[] getItems() {
	 ArrayList<KeyNamePair> list = new ArrayList<KeyNamePair>();
		KeyNamePair[] retValue = null;
			String sql = "select M_Product_Category_ID,Name from M_Product_Category where IsActive='Y'"+
					" AND AD_Client_ID="+Env.getAD_Client_ID(Env.getCtx());
			
			
			Statement stmt = null;
			ResultSet rs = null;
			try
			{
				stmt = DB.createStatement();
				rs = stmt.executeQuery(sql);
				if (!rs.next())
				{
					rs.close();
					stmt.close();
					
					return null;
				}
				do
				{
					int ad_ref_list_id = rs.getInt(1);
					String name = rs.getString(2);
					KeyNamePair p = new KeyNamePair(ad_ref_list_id, name);
					list.add(p);
				}
				
				while (rs.next());

				rs.close();
				stmt.close();
				stmt = null;
				//
				retValue = new KeyNamePair[list.size()];
				list.toArray(retValue);
			}
			catch (SQLException e1)
			{
				log.log(Level.SEVERE, "VSetup.dynInit -currency", e1);
			}
			finally
			{
				DB.close(rs, stmt);
				rs = null; stmt = null;
			}
		
		
	return retValue;
}
 private void updateManufacturer() {
		fManufacturer.getItems().clear();
		
		KeyNamePair orgKNPairs[] = getManufacturer();
     if(orgKNPairs != null && orgKNPairs.length > 0)
     {
         for(int i = 0; i < orgKNPairs.length; i++)
         {
        	 fManufacturer.addItem(orgKNPairs[i]);
         }
     }
     updatemodelNo();
	}
private KeyNamePair[] getManufacturer() {
	ArrayList<KeyNamePair> list = new ArrayList<KeyNamePair>();
	KeyNamePair[] retValue = null;
		String sql = "select C_BPartner_ID,Name from C_BPartner where "+
				" IsManufacturer='Y' AND AD_Client_ID="+Env.getAD_Client_ID(Env.getCtx());
		
		
		Statement stmt = null;
		ResultSet rs = null;
		try
		{
			stmt = DB.createStatement();
			rs = stmt.executeQuery(sql);
			if (!rs.next())
			{
				rs.close();
				stmt.close();
				
				return null;
			}
			do
			{
				int ad_ref_list_id = rs.getInt(1);
				String name = rs.getString(2);
				KeyNamePair p = new KeyNamePair(ad_ref_list_id, name);
				list.add(p);
			}
			
			while (rs.next());

			rs.close();
			stmt.close();
			stmt = null;
			//
			retValue = new KeyNamePair[list.size()];
			list.toArray(retValue);
		}
		catch (SQLException e1)
		{
			log.log(Level.SEVERE, "VSetup.dynInit -currency", e1);
		}
		finally
		{
			DB.close(rs, stmt);
			rs = null; stmt = null;
		}
	
	
	return retValue;
}
private void updatemodelNo() {
	fmodelNo.getItems().clear();
	if(fitemType.getSelectedItem()!=null){
		if(fManufacturer.getSelectedItem()!=null){
			int itemid = Integer.parseInt(fitemType.getSelectedItem().getValue().toString());
			int mfid = Integer.parseInt(fManufacturer.getSelectedItem().getValue().toString());
			KeyNamePair orgKNPairs[] = getModelNo(itemid,mfid);
			 if(orgKNPairs != null && orgKNPairs.length > 0)
			 {
			     for(int i = 0; i < orgKNPairs.length; i++)
			     {
			    	 fmodelNo.addItem(orgKNPairs[i]);
			     }
			 }
		}
	}
	updateSerialNo();
	
}
private KeyNamePair[] getModelNo(int itemid,int manfid) {
ArrayList<KeyNamePair> list = new ArrayList<KeyNamePair>();
KeyNamePair[] retValue = null;
	String sql = "select M_Storage_ID,ModelNo from M_Storage where "+
			" QtyOnHand>0 AND AssetManufacturer_ID="+manfid+
			" AND M_Product_Category_ID="+itemid
			+ " AND AD_Org_ID="+Env.getAD_Org_ID(Env.getCtx())
			+ " AND AD_Client_ID="+Env.getAD_Client_ID(Env.getCtx());
	
	
	Statement stmt = null;
	ResultSet rs = null;
	try
	{
		stmt = DB.createStatement();
		rs = stmt.executeQuery(sql);
		if (!rs.next())
		{
			rs.close();
			stmt.close();
			
			return null;
		}
		do
		{
			int ad_ref_list_id = rs.getInt(1);
			String name = rs.getString(2);
			KeyNamePair p = new KeyNamePair(ad_ref_list_id, name);
			list.add(p);
		}
		
		while (rs.next());

		rs.close();
		stmt.close();
		stmt = null;
		//
		retValue = new KeyNamePair[list.size()];
		list.toArray(retValue);
	}
	catch (SQLException e1)
	{
		log.log(Level.SEVERE, "VSetup.dynInit -currency", e1);
	}
	finally
	{
		DB.close(rs, stmt);
		rs = null; stmt = null;
	}


	return retValue;
}
	
private void updateSerialNo() {
	
	fserialNo.getItems().clear();
	if(fmodelNo.getSelectedItem()!=null){
		int modelid = Integer.parseInt(((ListItem)fmodelNo.getSelectedItem()).getValue().toString());
		KeyNamePair orgKNPairs[] = getSerialNo(modelid);
		 if(orgKNPairs != null && orgKNPairs.length > 0)
		 {
		     for(int i = 0; i < orgKNPairs.length; i++)
		     {
		    	 fserialNo.addItem(orgKNPairs[i]);
		     }
		 }
	}
	
}
private KeyNamePair[] getSerialNo(int modelid) {
ArrayList<KeyNamePair> list = new ArrayList<KeyNamePair>();
KeyNamePair[] retValue = null;
	String sql = "select DM_StorageDetails_ID,DeviceSerialNo from DM_StorageDetails where "+
			" QtyAvailable>0 "
			+" AND M_Storage_ID="+modelid
			+ " AND AD_Org_ID="+Env.getAD_Org_ID(Env.getCtx())
			+ " AND AD_Client_ID="+Env.getAD_Client_ID(Env.getCtx());
	
	Statement stmt = null;
	ResultSet rs = null;
	try
	{
		stmt = DB.createStatement();
		rs = stmt.executeQuery(sql);
		if (!rs.next())
		{
			rs.close();
			stmt.close();
			
			return null;
		}
		do
		{
			int ad_ref_list_id = rs.getInt(1);
			String name = rs.getString(2);
			KeyNamePair p = new KeyNamePair(ad_ref_list_id, name);
			list.add(p);
		}
		
		while (rs.next());

		rs.close();
		stmt.close();
		stmt = null;
		//
		retValue = new KeyNamePair[list.size()];
		list.toArray(retValue);
	}
	catch (SQLException e1)
	{
		log.log(Level.SEVERE, "VSetup.dynInit -currency", e1);
	}
	finally
	{
		DB.close(rs, stmt);
		rs = null; stmt = null;
	}


	return retValue;
}
private KeyNamePair empKNPairs[];
public void onEvent(Event event) throws Exception {
	
	if (Events.ON_SELECT.equals(event.getName())) {
		if (event.getTarget() instanceof Listbox) {
			Listbox ltb = (Listbox) event.getTarget();

			if ("fDMSemployee".equals(ltb.getId())){
				if(fDMSemployee.getSelectedItem()!=null){
					ListItem employee = (ListItem) fDMSemployee.getSelectedItem();
					int employee_id = Integer.parseInt(employee.getValue().toString());
					MDMSEmployee mngt = new MDMSEmployee(Env.getCtx(), employee_id, null);
					fDMScode.setValue(mngt.getEmployeeCode());
					StringBuffer sb = new StringBuffer();
					if(mngt.getFName() !=null){
						sb.append(mngt.getFName());
					}
					if(mngt.getMName() !=null){
						sb.append(" ").append(mngt.getMName());
					}
					if(mngt.getLName() !=null){
						sb.append(" ").append(mngt.getLName());
					}
					fEmpName.setValue(sb.toString());
					loadActivities();
				}
			}
			if ("fitemType".equals(ltb.getId())) {
				updatemodelNo();
			}
			else if ("fManufacturer".equals(ltb.getId())) {
				updatemodelNo();

			}else if ("fmodule".equals(ltb.getId())) {
				updateSerialNo();
			}else if ("fwarehouse".equals(ltb.getId())) {
				updateLocator();
			}else if("activity".equals(ltb.getId())){
				int m_index = listbox.getSelectedIndex();
				if (m_index >= 0){
					m_activity=display(m_index);
					
					if(m_activity !=null){
					}
				}
			}
			
						
		}
	}else if (Events.ON_CHECK.equals(event.getName())) {
		if(event.getTarget() instanceof Checkbox){
			Checkbox chk = (Checkbox)event.getTarget();
			if("isReturn".equals(chk.getName()) || "isReturn".equals(chk.getId())){
				btnReturn.setEnabled(isReturn.isChecked());
				updatewarehouse();
				updateLocator();
			}
		}
	}
	else if (Events.ON_CLICK.equals(event.getName())){
		if(event.getTarget() instanceof Button)
        {        	  
            Button btn = (Button)event.getTarget();
            
            if ("btnIssue".equals(btn.getName()))
            {
            	cmd_issue();
            }
            else if ("btnReset".equals(btn.getName()))
            {
            	cmd_reset();
            }
            else if ("btnPrint".equals(btn.getName()))
            {
            	cmd_print();
            }
            else if ("btnZoom".equals(btn.getName()))
            {
            	cmd_zoom();
            } 
            else if ("btnReturn".equals(btn.getName()))
            {
            	cmd_return();
            }
            
        }
	}
	else if(Events.ON_OK.equals(event.getName()))
    {
    	if (event.getTarget() instanceof Textbox) {
    		Textbox btn = (Textbox)event.getTarget();
    		if ("fDMScode".equals(btn.getId())){
    			String sql = "SELECT DM_DMSEmployee_ID FROM DM_DMSEmployee "
    					+ "Where EmployeeCode='"+fDMScode.getValue()+"' "
    					+ "AND AD_Client_ID="+Env.getAD_Client_ID(Env.getCtx());
    			int employee_ID= DB.getSQLValue(null, sql);
    			if(employee_ID>0){
    				MDMSEmployee mngt = new MDMSEmployee(Env.getCtx(), employee_ID, null);
        			empKNPairs = getdms();
					 if(empKNPairs != null && empKNPairs.length > 0)
					    {
					        for(int i = 0; i < empKNPairs.length; i++)
					        {
					        	if(empKNPairs[i].getKey()==mngt.get_ID()){
					        		fDMSemployee.setSelectedKeyNamePair(empKNPairs[i]);
					        		loadActivities();
					        		StringBuffer sb = new StringBuffer();
									if(mngt.getFName() !=null){
										sb.append(mngt.getFName());
									}
									if(mngt.getMName() !=null){
										sb.append(" ").append(mngt.getMName());
									}
									if(mngt.getLName() !=null){
										sb.append(" ").append(mngt.getLName());
									}
									fEmpName.setValue(sb.toString());
					        	}
					        }
					    }
    			}else{
    				fDMSemployee.setSelectedItem(null);
    				fEmpName.setValue(null);
    				FDialog.error(0, null, "","Invalid Employee Code");
    			}
    		}
    	}
    	
    }
	
}
private MIssuedToEmployee display(int selIndex)
{
	
	m_activity = null;

	if (m_activities.length > 0)
	{
		if (selIndex >= 0 && selIndex < m_activities.length)
			m_activity = m_activities[selIndex];
	}
	//	Nothing to show
	if (m_activity == null)
	{
	}
	return m_activity;
}

	private void cmd_zoom(){
		if(listbox.getSelectedItem()==null){
			FDialog.error(0, null, "","Please select atleast one row to Zoom");
		}else{
			AEnv.zoomItemIssue(MInOut.Table_ID, m_activity.getM_InOut_ID());
		}
	}
	private void cmd_return(){
		if(listbox.getSelectedItem()==null){
			FDialog.error(0, null, "","Please select atleast one row to return");
		}else{
			if((ListItem) fDMSemployee.getSelectedItem()==null){
				throw new AdempiereException("Please select Employee First");
			}
			if((ListItem) fwarehouse.getSelectedItem()==null){
				throw new AdempiereException("Please select Warehouse First");
			}
			if((ListItem) fwarehouse.getSelectedItem()!=null){
				ListItem warehouse = (ListItem) fwarehouse.getSelectedItem();
				if( Integer.parseInt(warehouse.getValue().toString())<=0){
					throw new AdempiereException("Please select Warehouse First");
				}
			}
			if(fquantity.getValue().intValue()<=0){
				throw new AdempiereException("Please select Quantity");
			}
			if(fquantity.getValue().intValue()>m_activity.getQtyIssued().intValue()){
				throw new AdempiereException("Quantity must be equal or less than issued quantity");
			}
			String sql ="Select C_DocType_ID from C_DocType where DocBaseType='"+MDocType.DOCBASETYPE_MaterialReceipt+"' AND NAME='MM Customer Return'";
			ListItem warehouse = (ListItem) fwarehouse.getSelectedItem();
			int warehouseid= Integer.parseInt(warehouse.getValue().toString());
			MWarehouse ware=new MWarehouse(Env.getCtx(), warehouseid, null);
			int docindex=listbox.getSelectedIndex();
			m_activity = display(docindex);
			MInOut inout = new MInOut(Env.getCtx(), 0, null);
			inout.setC_DocType_ID(DB.getSQLValue(null, sql));
			inout.setMovementDate(new Timestamp(System.currentTimeMillis()));
			inout.setDateAcct(new Timestamp(System.currentTimeMillis()));
			inout.setMovementType(MInOut.MOVEMENTTYPE_CustomerReturns);
			inout.setDM_DMSEmployee_ID(m_activity.getDM_DMSEmployee().getDM_DMSEmployee_ID());
			inout.setEmployeeCode( m_activity.getDM_DMSEmployee().getEmployeeCode());
			inout.setDM_EmployeeLocationType_ID(m_activity.getDM_DMSEmployee().getDM_EmployeeLocationType_ID());
			inout.setDM_EmployeeLocation_ID(m_activity.getDM_DMSEmployee().getDM_EmployeeLocation_ID());
			inout.setDM_DMSPosition_ID(m_activity.getDM_DMSEmployee().getDM_DMSPosition_ID());
			inout.setC_BPartner_ID(m_activity.getDM_DMSEmployee().getC_BPartner_ID());
			inout.setC_BPartner_Location_ID(m_activity.getDM_DMSEmployee().getC_BPartner_Location_ID());
			inout.setM_Warehouse_ID(warehouseid);
			inout.setAD_Org_ID(ware.getAD_Org_ID());
			inout.setDocStatus("DR");
			if((ListItem) fcheckedBy.getSelectedItem()!=null){
				int checkedby= Integer.parseInt(((ListItem) fcheckedBy.getSelectedItem()).getValue().toString());
				inout.setCheckedBy_ID(checkedby);
			}
			if((ListItem) fapprovedBy.getSelectedItem()!=null){
				int approvedby= Integer.parseInt(((ListItem) fapprovedBy.getSelectedItem()).getValue().toString());
				inout.setApprovedBy_ID(approvedby);
			}
			if(inout.save()){
				sql="Select DM_StorageDetails_ID from DM_StorageDetails"
						+ " Where DeviceSerialno='"+m_activity.getDeviceSerialNo()+"'"
								+ " AND DM_AssetModelDetails_ID="+m_activity.getDM_AssetModelDetails_ID()
								+ " AND M_Product_Category_ID="+m_activity.getM_Product_Category_ID()
								+ " AND AssetManufacturer_ID="+m_activity.getAssetManufacturer_ID()
								+ " AND AD_Client_ID="+Env.getAD_Client_ID(Env.getCtx());
				MStorageDetails det = new MStorageDetails(Env.getCtx(), DB.getSQLValue(null, sql), null);
				MInOutLine line = new MInOutLine(Env.getCtx(), 0, null);
				line.setM_Product_Category_ID(det.getM_Product_Category_ID());
				line.setAssetManufacturer_ID(det.getAssetManufacturer_ID());
				line.setDM_AssetModelDetails_ID(det.getDM_AssetModelDetails_ID());
				line.setM_InOut_ID(inout.get_ID());
				line.setDM_StorageDetails_ID(det.get_ID());
				line.setLine(10);
				line.setAD_Org_ID(inout.getAD_Org_ID());
				line.setQty(fquantity.getValue());
				line.setM_Product_ID(det.getM_Storage().getM_Product_ID(), true);
				line.setM_Locator_ID(MLocator.getDefault(ware).get_ID());
				line.setDeviceSerialNo(det.getDeviceSerialNo());
				if(det.isLaptopDektop()){
					line.setIsLaptopDektop(det.isLaptopDektop());
					line.setHDD(det.getHDD());
					line.setRAM(det.getRAM());
					line.setGraphicsCard(det.getGraphicsCard());
					line.setIsCDROMAvailable(det.isCDROMAvailable());
					line.setProcessor(det.getProcessor());
				}
				if(det.isPrinter()){
					line.setIsPrinter(true);
					line.setIsCanCopy(det.isCanCopy());
					line.setIsCanPrint(det.isCanPrint());
					line.setIsCanScan(det.isCanScan());
					line.setIsWifiSupport(det.isWifiSupport());
					line.setIsColorPrinter(det.isColorPrinter());
					line.setInputTrayCapacity(det.getInputTrayCapacity());
				}
				if(det.isPrinterAccessory()){
					line.setIsPrinterAccessory(true);
				}
				if(det.isLDAccessory()){
					line.setIsAccessories(true);
				}
				if(det.isNetComp()){
					line.setIsNetComp(true);
				}
				if(det.isInternet()){
					line.setIsInternet(true);
					if(det.getMobilePhone()!=null)
						line.setMobilePhone(det.getMobilePhone());
				}
				if(line.save()){
					inout.processIt(DocAction.ACTION_Complete);
					if(inout.save()){
						FDialog.info(0, null, "Item returned from "+m_activity.getDM_DMSEmployee().getFName()+" "+m_activity.getDM_DMSEmployee().getLName()+" Successfully");
						loadActivities();
						cmd_resetserial();
					}
				}
			}
		}
	}
	private void cmd_print(){
		if(listbox.getSelectedItem()==null){
			FDialog.error(0, null, "","Please select atleast one row to print");
		}else{
			int docindex=listbox.getSelectedIndex();
			m_activity = display(docindex);
			
			String sql="SELECT AD_Process_ID from AD_Tab tab" +
		    		   " inner join ad_table tabl "+
		    		   " on(tabl.ad_table_id=tab.ad_table_id and tabl.ad_window_id=tab.ad_window_id)"+
		       			" where tabl.ad_table_id="+MInOut.Table_ID;
		       int process_id=DB.getSQLValue(null, sql);
		       if(process_id>0){
		    	   MProcess process =MProcess.get(Env.getCtx(), process_id);
		    	   if(process.getJasperReport()!=null){
		    		   ProcessModalDialog dialog = new ProcessModalDialog(null,0,
		    	    		   process_id,MInOut.Table_ID, (Integer)  m_activity.getM_InOut_ID(), true);
		    			if (dialog.isValid()) {
		    				dialog.setPosition("center");
		    				try {
		    					dialog.setPage(SessionManager.getAppDesktop().getComponent().getPage());
		    					dialog.doModal();
		    				}
		    				catch (InterruptedException e) {
		    				}
		    			}
		    	   }else{
		    		   FDialog.error(0, "Jasper Report Not Found");
		    		   return;
		    	   }
		       }
		}
	}
	private void cmd_issue()
	{
		if((ListItem) fDMSemployee.getSelectedItem()==null){
			throw new AdempiereException("Please select Employee First");
		}
		if((ListItem) fwarehouse.getSelectedItem()==null){
			throw new AdempiereException("Please select Warehouse First");
		}
		if((ListItem) fwarehouse.getSelectedItem()!=null){
			ListItem warehouse = (ListItem) fwarehouse.getSelectedItem();
			if( Integer.parseInt(warehouse.getValue().toString())<=0){
				throw new AdempiereException("Please select Warehouse First");
			}
		}
		ListItem warehouse = (ListItem) fwarehouse.getSelectedItem();
		int warehouseid= Integer.parseInt(warehouse.getValue().toString());
		MDMSEmployee emp =new MDMSEmployee(Env.getCtx(), Integer.parseInt(((ListItem) fDMSemployee.getSelectedItem()).getValue().toString()), null);
		String sql ="Select C_DocType_ID from C_DocType where DocBaseType='"+MDocType.DOCBASETYPE_MaterialDelivery+"' AND NAME='MM Shipment'";
		
		
		MInOut inout = new MInOut(Env.getCtx(), 0, null);
		inout.setC_DocType_ID(DB.getSQLValue(null, sql));
		inout.setMovementDate(new Timestamp(System.currentTimeMillis()));
		inout.setDateAcct(new Timestamp(System.currentTimeMillis()));
		inout.setMovementType(MInOut.MOVEMENTTYPE_CustomerShipment);
		inout.setDM_DMSEmployee_ID(emp.get_ID());
		inout.setEmployeeCode( emp.getEmployeeCode());
		inout.setDM_EmployeeLocationType_ID(emp.getDM_EmployeeLocationType_ID());
		inout.setDM_EmployeeLocation_ID(emp.getDM_EmployeeLocation_ID());
		inout.setDM_DMSPosition_ID(emp.getDM_DMSPosition_ID());
		inout.setC_BPartner_ID(emp.getC_BPartner_ID());
		inout.setC_BPartner_Location_ID(emp.getC_BPartner_Location_ID());
		inout.setM_Warehouse_ID(warehouseid);
		inout.setAD_Org_ID(Env.getAD_Org_ID(Env.getCtx()));
		inout.setDocStatus("DR");
		if(fcarrytr.getValue()!=null){
			inout.setCarryThrough(fcarrytr.getValue());
		}
		if(fmateissuenote.getValue()!=null){
			inout.setDescription(fmateissuenote.getValue());
		}
		if((ListItem) fcheckedBy.getSelectedItem()!=null){
			int checkedby= Integer.parseInt(((ListItem) fcheckedBy.getSelectedItem()).getValue().toString());
			inout.setCheckedBy_ID(checkedby);
		}
		if((ListItem) fapprovedBy.getSelectedItem()!=null){
			int approvedby= Integer.parseInt(((ListItem) fapprovedBy.getSelectedItem()).getValue().toString());
			inout.setApprovedBy_ID(approvedby);
		}
		if(inout.save()){
			ListItem serialNo = (ListItem) fserialNo.getSelectedItem();
			int serialID= Integer.parseInt(serialNo.getValue().toString());
			MStorageDetails det = new MStorageDetails(Env.getCtx(), serialID, null);
			MInOutLine line = new MInOutLine(Env.getCtx(), 0, null);
			line.setM_Product_Category_ID(det.getM_Product_Category_ID());
			line.setAssetManufacturer_ID(det.getAssetManufacturer_ID());
			line.setDM_AssetModelDetails_ID(det.getDM_AssetModelDetails_ID());
			line.setM_InOut_ID(inout.get_ID());
			line.setDM_StorageDetails_ID(det.get_ID());
			line.setLine(10);
			line.setQty(fquantity.getValue());
			line.setM_Product_ID(det.getM_Storage().getM_Product_ID(), true);
			line.setM_Locator_ID(det.getM_Locator_ID());
			line.setDeviceSerialNo(det.getDeviceSerialNo());
			if(line.save()){
				inout.processIt(DocAction.ACTION_Complete);
				if(inout.save()){
					FDialog.info(0, null, "Item Issue to "+emp.getFName()+" "+emp.getLName()+" Successfully");
					loadActivities();
					cmd_resetserial();
				}
			}
		}
	
	}
	private void cmd_reset()
	{
		fapprovedBy.setValue(null);
		fcarrytr.setValue(null);
		fcheckedBy.setValue(null);
		fDMScode.setValue(null);
		fDMSemployee.setValue(null);
		fEmpName.setValue(null);
		fitemType.setValue(null);
		fManufacturer.setValue(null);
		fmateissuenote.setValue(null);
		fmodelNo.setValue(null);
		fquantity.setValue(Env.ZERO);
		fserialNo.setValue(null);
		
	}
	private void cmd_resetserial()
	{
		fapprovedBy.setValue(null);
		fcarrytr.setValue(null);
		fcheckedBy.setValue(null);
		fitemType.setValue(null);
		fManufacturer.setValue(null);
		fmateissuenote.setValue(null);
		fmodelNo.setValue(null);
		fquantity.setValue(Env.ZERO);
		fserialNo.setValue(null);
		
	}
	public int loadActivities()
	{
		listbox.clear();
		ListItem employee = (ListItem) fDMSemployee.getSelectedItem();
		int employee_id = Integer.parseInt(employee.getValue().toString());
		long start = System.currentTimeMillis();

		int MAX_ACTIVITIES_IN_LIST = MSysConfig.getIntValue("MAX_ACTIVITIES_IN_LIST", 200, Env.getAD_Client_ID(Env.getCtx()));

		model = new ListModelTable();

		ArrayList<MIssuedToEmployee> list = new ArrayList<MIssuedToEmployee>();
		
		
		String sql = "select det.DM_IssuedToEmployee_ID"+
					 " from DM_IssuedToEmployee det "+
					 " where det.DM_DMSEmployee_ID="+employee_id+
					 " AND det.AD_Client_ID="+Env.getAD_Client_ID(Env.getCtx())+" "+
					 " Order By det.DeviceSerialNo,det.DM_IssuedToEmployee_ID";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement (sql, null);				
			rs = pstmt.executeQuery ();
			while (rs.next ())
			{
				MIssuedToEmployee activity = new MIssuedToEmployee(Env.getCtx(), rs.getInt(1), null);
				list.add (activity);
				List<Object> rowData = new ArrayList<Object>();
				rowData.add(String.valueOf(rs.getRow()));
				rowData.add(String.valueOf(activity.getM_Product_Category().getName()));
				rowData.add(String.valueOf(activity.getAssetManufacturer().getName()));				
				rowData.add(String.valueOf(activity.getM_Product().getName()));
				rowData.add(String.valueOf(activity.getDeviceSerialNo()));
				rowData.add(String.valueOf(activity.getQtyIssued()));
				
				model.add(rowData);
				
				if (list.size() > MAX_ACTIVITIES_IN_LIST && MAX_ACTIVITIES_IN_LIST > 0)
				{
					log.warning("More then 200 Activities - ignored");
					break;
				}
			}
		}
		catch (Exception e)
		{
			log.log(Level.SEVERE, sql, e);
		}
		finally
		{
			DB.close(rs, pstmt);
			rs = null; pstmt = null;
		}
		m_activities = new MIssuedToEmployee[list.size ()];
		list.toArray (m_activities);
		//
		log.fine("#" + m_activities.length
			+ "(" + (System.currentTimeMillis()-start) + "ms)");
		String[] columns = new String[]{
				Msg.translate(Env.getCtx(), "SN"),
				Msg.translate(Env.getCtx(), "Item Type"),
				Msg.translate(Env.getCtx(), "Manufacturer"),
				Msg.translate(Env.getCtx(), "Model No"),
				Msg.translate(Env.getCtx(), "Serial No"),
				Msg.translate(Env.getCtx(), "Quantity")
				};

		WListItemRenderer renderer = new WListItemRenderer(Arrays.asList(columns));
		ListHeader header = new ListHeader();
		header.setWidth("30px");
		renderer.setListHeader(0, header);
		renderer.addTableValueChangeListener(listbox);
		model.setNoColumns(columns.length);
		listbox.setModel(model);
//		listbox.setCheckmark(true);
//		listbox.setMultiple(true);
		listbox.setItemRenderer(renderer);
		listbox.repaint();
		listbox.setId("activity");
		listbox.setFixedLayout(true);
//		listbox.setMultiSelection(true);
		listbox.setColumnReadOnly(5, false);
		
		if(m_activities!=null)
			return m_activities.length;
		else
			return 0;
}	//	loadActivities
}
