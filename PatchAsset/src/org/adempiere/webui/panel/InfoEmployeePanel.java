/******************************************************************************
 * Product: Posterita Ajax UI 												  *
 * Copyright (C) 2007 Posterita Ltd.  All Rights Reserved.                    *
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
 * Posterita Ltd., 3, Draper Avenue, Quatre Bornes, Mauritius                 *
 * or via info@posterita.org or http://www.posterita.org/                     *
 *****************************************************************************/

package org.adempiere.webui.panel;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.webui.apps.AEnv;
import org.adempiere.webui.component.Combobox;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.ListItem;
import org.adempiere.webui.component.Listbox;
import org.adempiere.webui.component.ListboxFactory;
import org.adempiere.webui.component.Row;
import org.adempiere.webui.component.Rows;
import org.adempiere.webui.component.Tab;
import org.adempiere.webui.component.Tabbox;
import org.adempiere.webui.component.Tabpanel;
import org.adempiere.webui.component.Tabpanels;
import org.adempiere.webui.component.Tabs;
import org.adempiere.webui.component.Textbox;
import org.adempiere.webui.component.WListbox;
import org.adempiere.webui.editor.WTableDirEditor;
import org.adempiere.webui.event.WTableModelListener;
import org.compiere.apps.search.Info_Column;
import org.compiere.minigrid.ColumnInfo;
import org.compiere.minigrid.IDColumn;
import org.compiere.model.MColumn;
import org.compiere.model.MDMSDepartment;
import org.compiere.model.MEmpStatus;
import org.compiere.model.MLocation;
import org.compiere.model.MLookupFactory;
import org.compiere.model.MQuery;
import org.compiere.model.MWarehouse;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Msg;
import org.compiere.util.Trx;
import org.compiere.util.Util;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zkex.zul.Borderlayout;
import org.zkoss.zkex.zul.Center;
import org.zkoss.zkex.zul.North;

/**
*	Search Business Partner and return selection
*   Based on InfoBPartner written by Jorg Janke
* 	@author Sendy Yagambrum
* 
* 	Zk Port
* 	@author Elaine
* 	
 * @author Michael McKay, ADEMPIERE-72 VLookup and Info Window improvements
 * 	<li>https://adempiere.atlassian.net/browse/ADEMPIERE-72
* 	@version	InfoBPartner.java Adempiere Swing UI 3.7.1 
*/


public class InfoEmployeePanel extends InfoPanel implements EventListener, WTableModelListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5677624151607188344L;

	
	/**
	 *	Standard Constructor
	 *  @param record_id 	ID of current record, if known.  0 otherwise.
	 *  @param queryvalue   Query value Name or Value if contains only numbers
	 *  @param isSOTrx  if false, query vendors only
	 *  @param isSOMatch	Should the customer/vendor only checkbox be checked?
	 *  @param whereClause where clause
	 */
	@Deprecated
	public InfoEmployeePanel(int record_id, String value,int windowNo, 
				boolean isSOTrx, boolean isSOMatch, 
				boolean multipleSelection, 
				String whereClause)
	{
		this(windowNo, true, record_id, value, isSOTrx, isSOMatch, multipleSelection, 
				false, whereClause);

	}

	/**
	 *	Standard Constructor
	 *  @param record_id 	ID of current record, if known.  0 otherwise.
	 *  @param queryvalue   Query value Name or Value if contains only numbers
	 *  @param isSOTrx  if false, query vendors only
	 *  @param isSOMatch	Should the customer/vendor only checkbox be checked?
	 *  @param saveResults  True if results will be saved, false for info only
	 *  @param whereClause where clause
	 *  @param modal True if window is opened in modal mode.
	 */
	public InfoEmployeePanel(int windowNo, boolean modal, int record_id, String value, 
				boolean isSOTrx, boolean isSOMatch, boolean multipleSelection, 
				boolean saveResults, String whereClause)
	{

		super (windowNo, modal, "DM_DMSEmployee", "DM_DMSEmployee_ID", multipleSelection, saveResults, whereClause);
		log.info(value + ", WHERE=" + whereClause);
		setTitle(Msg.getMsg(Env.getCtx(), "InfoEmployee"));
//		m_isSOTrx = isSOTrx;
//		m_isSOMatch = isSOMatch;
		//
		StringBuffer where = new StringBuffer();
		where.append(" DM_DMSEmployee.IsActive='Y'");
		if (whereClause != null && whereClause.length() > 0)
			where.append(" AND ").append(whereClause);
		setWhereClause(where.toString());
		setTableLayout(s_Layout);
		setFromClause(s_From);
		setOrderClause(s_Order);
		//
		setShowTotals(true);
		//
        statInit();
		initInfo(record_id, value, false);
        //
        if(autoQuery() || record_id != 0 || (value != null && value.length() > 0 && value != "%"))
        {
        	prepareAndExecuteQuery();
        }
        //
        p_loadedOK = true;
	}

	/** SalesOrder Trx          */
//	private boolean 		m_isSOTrx = false;
//	private boolean			m_isSOMatch = true;

//	private int m_AD_User_ID = -1;
    private int m_DM_EmployeeContact_ID = -1;

	/** From Clause             */
	private static String s_From = "DM_DMSEmployee";
	/** Order Clause             */
	private static String s_Order = "DM_DMSEmployee.EmployeeCode";

	/**  Array of Column Info    */
	private static Info_Column[] s_Layout = {
		new Info_Column(" ", "DM_DMSEmployee.DM_DMSEmployee_ID", IDColumn.class),
		new Info_Column(Msg.translate(Env.getCtx(), "EmpCode"), "DM_DMSEmployee.EmployeeCode", String.class),
		new Info_Column(Msg.translate(Env.getCtx(), "FName"), "DM_DMSEmployee.FName", String.class),
		new Info_Column(Msg.translate(Env.getCtx(), "MName"), "DM_DMSEmployee.MName", String.class),
		new Info_Column(Msg.translate(Env.getCtx(), "LName"), "DM_DMSEmployee.LName", String.class),
		new Info_Column(Msg.translate(Env.getCtx(), "StartDate"), "DM_DMSEmployee.StartDate", Date.class),
		new Info_Column(Msg.translate(Env.getCtx(), "Birthday"), "DM_DMSEmployee.Birthday", Date.class),
		new Info_Column(Msg.translate(Env.getCtx(), "EndDate"), "DM_DMSEmployee.EndDate", Date.class)
	};
	
	
	private int fieldID = 0; 
	private Label labelValue ;
	private Textbox fieldValue ;
	private Label labelName;
	private Textbox fieldName ;
	private Label labelStatus ;
	private WTableDirEditor fieldStatus_ID;
	private Label labelEMail ;
	private Textbox fieldEMail;
	private Label labelDepartment;
	private WTableDirEditor fieldDepartment_ID;
	private Label labelPhone;
	private Textbox fieldPhone;


	/**	Logger			*/
	protected CLogger log = CLogger.getCLogger(getClass());
	
	private Tabbox detailTabBox = new Tabbox();
	
	private WListbox addressTbl = ListboxFactory.newDataTable();
	private String m_sqlAddress;
	
	private int m_DM_DMSEmployee_ID = 0;
	private static int ADDRESS_INDEX;

	/**
	 *  Initialize the zk components.
	 */
	private void initComponents()
	{
		labelValue = new Label();
		labelValue.setValue(Util.cleanAmp(Msg.translate(Env.getCtx(), "EmpCode")));
		labelName = new Label();
		labelName.setValue(Util.cleanAmp(Msg.translate(Env.getCtx(), "FName")));
		labelStatus = new Label();
		labelStatus.setValue(Msg.translate(Env.getCtx(), "EmpStatus"));
		labelEMail = new Label();
		labelEMail.setValue(Msg.getMsg(Env.getCtx(), "EMail"));
		labelDepartment = new Label();
		labelDepartment.setValue(Msg.getMsg(Env.getCtx(), "Department"));
		labelPhone = new Label();
		labelPhone.setValue(Msg.translate(Env.getCtx(), "MPhone"));
		
		fieldID = 0; //Record_ID
		//
		fieldValue = new Textbox();
		fieldValue.setMaxlength(40);
		fieldValue.setAttribute("zk_component_ID", "Lookup_Criteria_fieldValue");
		fieldValue.addEventListener(Events.ON_CHANGE, this);
		//
		fieldName = new Textbox();
		fieldName.setMaxlength(40);
		fieldName.setAttribute("zk_component_ID", "Lookup_Criteria_fieldName");
		fieldName.addEventListener(Events.ON_CHANGE, this);
		//
		fieldEMail = new Textbox();
		fieldEMail.setMaxlength(40);
		fieldEMail.setAttribute("zk_component_ID", "Lookup_Criteria_fieldEMail");
		fieldEMail.addEventListener(Events.ON_CHANGE, this);
		//
		

		fieldDepartment_ID = new WTableDirEditor("DM_DMSDepartment_ID", false, false, true,
				MLookupFactory.get (Env.getCtx(), p_WindowNo, 0, 
						MColumn.getColumn_ID(MDMSDepartment.Table_Name, MDMSDepartment.COLUMNNAME_DM_DMSDepartment_ID),
				DisplayType.TableDir));
		fieldDepartment_ID.getComponent().addEventListener(Events.ON_CHANGE, this);
		fieldDepartment_ID.getComponent().setAttribute("zk_component_ID", "Lookup_Criteria_DM_DMSDepartment_ID");
		fieldDepartment_ID.getComponent().setAttribute("zk_component_prefix", "Lookup_");
		fieldDepartment_ID.getComponent().setAttribute("IsDynamic", "True");
		fieldDepartment_ID.getComponent().setAttribute("fieldName", "fieldDepartment_ID");
		
		//
		fieldPhone = new Textbox();
		fieldPhone.setMaxlength(40);
		fieldPhone.setAttribute("zk_component_ID", "Lookup_Criteria_fieldPhone");
		fieldPhone.addEventListener(Events.ON_CHANGE, this);
		
		fieldStatus_ID = new WTableDirEditor("DM_EmpStatus_ID", false, false, true,
				MLookupFactory.get (Env.getCtx(), p_WindowNo, 0, 
						MColumn.getColumn_ID(MEmpStatus.Table_Name, MEmpStatus.COLUMNNAME_DM_EmpStatus_ID),
				DisplayType.TableDir));
		fieldStatus_ID.getComponent().addEventListener(Events.ON_CHANGE, this);
		fieldStatus_ID.getComponent().setAttribute("zk_component_ID", "Lookup_Criteria_DM_EmpStatus_ID");
		fieldStatus_ID.getComponent().setAttribute("zk_component_prefix", "Lookup_");
		fieldStatus_ID.getComponent().setAttribute("IsDynamic", "True");
		fieldStatus_ID.getComponent().setAttribute("fieldName", "fieldStatus_ID");

		
	}
	
	private void statInit()
	{
		initComponents();
		
		fieldValue.setWidth("100%");
		fieldPhone.setWidth("100%");
		fieldName.setWidth("100%");
		fieldEMail.setWidth("100%");
		
		
		Rows rows = new Rows();
		
		Row row = new Row();
		rows.appendChild(row);
		row.appendChild(labelValue.rightAlign());
		row.appendChild(fieldValue);
		row.appendChild(labelStatus.rightAlign());
		row.appendChild(fieldStatus_ID.getComponent());
		row.appendChild(labelPhone.rightAlign());
		row.appendChild(fieldPhone);
		

		row = new Row();
		rows.appendChild(row);
		row.appendChild(labelName.rightAlign());
		row.appendChild(fieldName);
		row.appendChild(labelEMail.rightAlign());
		row.appendChild(fieldEMail);
		row.appendChild(labelDepartment.rightAlign());
		row.appendChild(fieldDepartment_ID.getComponent());
	
        
		statusBar.setEastVisibility(false);
		
		
        
        //  Location Tab
        ColumnInfo[] s_layoutAddress = new ColumnInfo[]{
        		new ColumnInfo(" ", "l.DM_EmployeeContact_ID", IDColumn.class),
        		new ColumnInfo(Msg.translate(Env.getCtx(), "Type"), "l.ContactType", String.class),
        		new ColumnInfo(Msg.translate(Env.getCtx(), "MobilePhone"), "l.MobilePhone", String.class),
        		new ColumnInfo(Msg.translate(Env.getCtx(), "EMail"), "l.EMail", String.class),
        		new ColumnInfo(Msg.translate(Env.getCtx(), "Department"), "dep.Name", String.class),        		
        		new ColumnInfo(Msg.translate(Env.getCtx(), "Address"), "a.Address1", String.class)};
        ADDRESS_INDEX = 5;
        /**	From Clause							*/
        String s_locationFrom = "DM_EmployeeContact l" 
        		+ " Inner JOIN DM_DMSEmployee emp ON (l.DM_DMSEmployee_ID=emp.DM_DMSEmployee_ID)"
        		+ " LEFT OUTER JOIN DM_DMSDepartment dep ON (emp.DM_DMSDepartment_ID=dep.DM_DMSDepartment_ID)"
    		+ " LEFT OUTER JOIN C_Location a ON (l.C_Location_ID=a.C_Location_ID)";
        /** Where Clause						*/
        String s_locationWhere = "l.DM_DMSEmployee_ID = ? and l.IsActive = 'Y'";
        m_sqlAddress = addressTbl.prepareTable(s_layoutAddress, s_locationFrom, s_locationWhere, false, "l");
        addressTbl.setMultiSelection(false);
        addressTbl.autoSize();
        addressTbl.getModel().addTableModelListener(this);
        addressTbl.setAttribute("zk_component_ID", "Lookup_Data_Address");
		//
        detailTabBox.setHeight("100%");
        Tabpanels tabPanels = new Tabpanels();
		detailTabBox.appendChild(tabPanels);
		Tabs tabs = new Tabs();
		detailTabBox.appendChild(tabs);

		

		Tab tab = new Tab(Msg.translate(Env.getCtx(), "Location"));
		tab.addEventListener(Events.ON_SELECT, this);
		tabs.appendChild(tab);
		Tabpanel desktopTabPanel = new Tabpanel();
		desktopTabPanel.setHeight("100%");
		desktopTabPanel.appendChild(addressTbl);
		tabPanels.appendChild(desktopTabPanel);
		
		tabs.setAttribute("zk_component_ID", "Subordinate_Tabs");
	
		Borderlayout southSP = new Borderlayout();
		Center center = new Center();
		North north = new North();
		center.appendChild(detailTabBox);
		southSP.appendChild(north);
		southSP.appendChild(center);
        
		p_centerSouth.setTitle(Msg.translate(Env.getCtx(), "ContactAndAddress"));
		p_centerSouth.setTooltiptext(Msg.translate(Env.getCtx(), "ContactAndAddress"));
		p_centerSouth.appendChild(southSP);
		p_criteriaGrid.appendChild(rows);
		super.setSizes();
		
	

		addressTbl.addActionListener(new EventListener() {
			public void onEvent(Event event) throws Exception {
				int leadRowKey = 0;

				if (addressTbl != null || addressTbl.getRowCount() > 0)
					leadRowKey = addressTbl.getLeadRowKey();
		    	
				if (m_DM_EmployeeContact_ID != leadRowKey)
		    	{
					m_DM_EmployeeContact_ID = leadRowKey;  //  From the main table
		    	}
			}
		});

	}	
	
	/**
	 *	Reset the Criteria Info - init with blank data
	 */		
	protected void initInfo()
	{
		initInfo(0,"", true);
	}

	/**
	 *	Dynamic Init
	 *  @param value value
	 *  @param whereClause where clause
	 */
		
	private void initInfo(int record_id, String value, boolean reset)
	{			
	    //
	    if (!(record_id == 0) && value != null && value.length() > 0)
		{
			log.severe("Received both a record_id and a value: " + record_id + " - " + value);
		}
		//  In case of reset, clear all parameters to ensure we are at a known starting point.
		if(reset)
		{
			clearParameters();
		}
		//
	    if (!(record_id == 0))  // A record is defined
	    {
	    	fieldID = record_id;
	    }
	    else
	    {
			if (value != null && value.length() > 0)
			{
				//	Put query string in Name if not fully numeric
	    		if (!value.matches(".*\\D+.*")) // If value has no non-digit characters, use the Value
	    			fieldValue.setText(value);
	    		else
	    			fieldName.setText(value);  // A few non-digit characters might be in the name. E.g. 451Group, 1st Choice, ...
			}
			else
			{
				//  Try to find the fieldID from the context
	        	String bp = Env.getContext(Env.getCtx(), p_WindowNo, "DM_DMSEmployee_ID");
				if (bp != null && bp.length() != 0)
				{
					fieldID = new Integer(bp).intValue();
				}
			}
	    }
	}	//	initInfo

	/**
	 * A record was selected - take action to sync subordinate tables if any
	 * @param key of the selected record
	 */
	protected void recordSelected(int key)
	{
		m_DM_DMSEmployee_ID = key;
		refresh();
		p_centerSouth.setOpen(true);
		return;
	}
	/**
	 * No record was selected - take action to sync subordinate tables if any
	 */
	protected void noRecordSelected()
	{
		//  Nothing was selected, or the query is empty
		//  - close the panel
		m_DM_DMSEmployee_ID = 0;
    	p_centerSouth.setOpen(false);
		return;
	}

    public void onEvent(Event e)
    {
    	// Handle panel specific actions and pass the event to the parent class

		if(!p_loadedOK)
			return;
				
		Component component = e.getTarget();
		
		if(component != null)
		{
			if (component instanceof Tab) // a tab in the subordinate panel is selected
			{
				refresh();
				return;
			}
		} 
		
		super.onEvent(e);

    }
	/**
	 * 	Refresh Query
	 */
	protected void refresh()
	{				
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int leadRowKey = 0;

		if (p_table != null || p_table.getRowCount() > 0)
			leadRowKey = p_table.getLeadRowKey();
    	
		if (m_DM_DMSEmployee_ID != leadRowKey)
    	{
			m_DM_DMSEmployee_ID = leadRowKey;  //  From the main table
    	}
		if (detailTabBox.getSelectedIndex() == 0)
		{
			log.finest(m_sqlAddress);
			try
			{
				pstmt = DB.prepareStatement(m_sqlAddress, null);
				pstmt.setInt(1, m_DM_DMSEmployee_ID);
				rs = pstmt.executeQuery();
				addressTbl.loadTable(rs);
				rs.close();
			}
			catch (Exception e)
			{
				log.log(Level.WARNING, m_sqlAddress, e);
			}
			finally
			{
				DB.close(rs, pstmt);
				rs = null; pstmt = null;
			}
	
			String trxName = Trx.createTrxName();
	        for (int row=0; row < addressTbl.getRowCount(); row++)
			{
				int loc_id = 0;
				Object loc_data = addressTbl.getValueAt(row, addressTbl.getKeyColumnIndex());
	            if (loc_data != null && loc_data instanceof IDColumn)
	            {
	            	IDColumn dataColumn = (IDColumn) loc_data;
	        		loc_id = dataColumn.getRecord_ID();
	            }
	
				MLocation loc = getLocation(Env.getCtx(), loc_id, trxName);
				addressTbl.setValueAt(loc.toString(), row, ADDRESS_INDEX);
			}
			Trx.get(trxName, false).close();
			addressTbl.autoSize();
		}
		else
		{
			log.finest(m_sqlAddress);
			try
			{
				pstmt = DB.prepareStatement(m_sqlAddress, null);
				pstmt.setInt(1, m_DM_DMSEmployee_ID);
				rs = pstmt.executeQuery();
				addressTbl.loadTable(rs);
				rs.close();
			}
			catch (Exception e)
			{
				log.log(Level.WARNING, m_sqlAddress, e);
			}
			finally
			{
				DB.close(rs, pstmt);
				rs = null; pstmt = null;
			}
	
			String trxName = Trx.createTrxName();
	        for (int row=0; row < addressTbl.getRowCount(); row++)
			{
				int loc_id = 0;
				Object loc_data = addressTbl.getValueAt(row, addressTbl.getKeyColumnIndex());
	            if (loc_data != null && loc_data instanceof IDColumn)
	            {
	            	IDColumn dataColumn = (IDColumn) loc_data;
	        		loc_id = dataColumn.getRecord_ID();
	            }
	
				MLocation loc = getLocation(Env.getCtx(), loc_id, trxName);
				addressTbl.setValueAt(loc.toString(), row, ADDRESS_INDEX);
			}
			Trx.get(trxName, false).close();
			addressTbl.autoSize();
		}
	}	//	refresh

	
 
	public static MLocation getLocation (Properties ctx, int DM_EmployeeContact_ID, String trxName)
	{
		if (DM_EmployeeContact_ID == 0)					//	load default
			return null;

		MLocation loc = null;
		String sql = "SELECT * FROM C_Location l "
			+ "WHERE C_Location_ID IN (SELECT C_Location_ID FROM DM_EmployeeContact WHERE DM_EmployeeContact_ID=?)";
		try
		{
			PreparedStatement pstmt = DB.prepareStatement(sql, trxName);
			pstmt.setInt(1, DM_EmployeeContact_ID);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
				loc = new MLocation (ctx, rs, trxName);
			rs.close();
			pstmt.close();
		}
		catch (SQLException e)
		{
			loc = null;
		}
		return loc;
	}	//	getBPLocation
	
	/**
	 *	Construct SQL Where Clause and define parameters.
	 *  (setParameters needs to set parameters)
	 *  Includes first AND
	 *  @return WHERE clause
	 */
	protected String getSQLWhere()
	{
		StringBuffer sql = new StringBuffer();
		//  => ID
		if(isResetRecordID())
			fieldID = 0;
		if(!(fieldID == 0)){
//			list.add("DM_DMSEmployee.DM_DMSEmployee_ID = ?");
		sql.append(" AND DM_DMSEmployee.DM_DMSEmployee_ID = ?");
		//	=> Emp Code
		}if (isValidSQLText(fieldValue)){
//			list.add ("UPPER(DM_DMSEmployee.EmployeeCode) LIKE ?");
		sql.append(" AND UPPER(DM_DMSEmployee.EmployeeCode) LIKE ?");
		//	=> Name
		}if (isValidSQLText(fieldName)){
//			list.add ("UPPER(DM_DMSEmployee.FName) LIKE ?");
		sql.append(" AND UPPER(DM_DMSEmployee.FName) LIKE ?");
		//	=> Status
		}if (fieldStatus_ID.getValue() != null){
//			list.add(" DM_DMSEmployee.DM_EmpStatus_ID=?");
			sql.append(" AND DM_DMSEmployee.DM_EmpStatus_ID=?");

		}if (isValidSQLText(fieldEMail)){
//			list.add ("DM_DMSEmployee.DM_DMSEmployee_ID IN (SELECT DM_DMSEmployee_ID from DM_EmployeeContact c WHERE UPPER(c.EMail) LIKE ?)");
		sql.append(" AND DM_DMSEmployee.DM_DMSEmployee_ID IN (SELECT DM_DMSEmployee_ID from DM_EmployeeContact c WHERE UPPER(c.EMail) LIKE ?)");
		//	=> Phone
		}if (isValidSQLText(fieldPhone)){
//			list.add ("DM_DMSEmployee.DM_DMSEmployee_ID IN (SELECT DM_DMSEmployee_ID from DM_EmployeeContact c WHERE UPPER(c.Phone) LIKE ?)");
		sql.append(" AND DM_DMSEmployee.DM_DMSEmployee_ID IN (SELECT DM_DMSEmployee_ID from DM_EmployeeContact c WHERE UPPER(c.MobilePhone) LIKE ?)");
		//	=> Postal
		}if (fieldDepartment_ID.getValue() != null){
//			list.add(" DM_DMSEmployee.DM_DMSDepartment_ID=?");
		sql.append(" AND DM_DMSEmployee.DM_DMSDepartment_ID=?");
		}

		return sql.toString();
	}	//	getSQLWhere

	/**
	 *  Set Parameters for Query.
	 *  (as defined in getSQLWhere)
	 *  @param pstmt pstmt
	 *  @param forCount for counting records
	 *  @throws SQLException
	 */
	protected void setParameters(PreparedStatement pstmt, boolean forCount) throws SQLException
	{
		
		int index = 1;
		//  => ID
		if (!(fieldID == 0))
		{
			pstmt.setInt(index++, fieldID);
			log.fine("Record ID: " + fieldID);
		}
		//	=> Value
		if (isValidSQLText(fieldValue))
		{
			pstmt.setString(index++, getSQLText(fieldValue));
			log.fine("Value: " + fieldValue.getText());
		}
		//	=> Name
		if (isValidSQLText(fieldName))
		{
			pstmt.setString(index++, getSQLText(fieldName));
			log.fine("Name: " + fieldName.getText());
		}
		//	=> Contact
		if (fieldStatus_ID.getValue() != null)
		{
			Integer bp = (Integer)fieldStatus_ID.getValue();
			pstmt.setInt(index++, bp.intValue());
			log.fine("EmpStatus=" + bp);
		}
		
		//	=> EMail
		if (isValidSQLText(fieldEMail))
		{
			pstmt.setString(index++, getSQLText(fieldEMail));
			log.fine("EMail: " + fieldEMail.getText());
		}
		//	=> Phone
		if (isValidSQLText(fieldPhone))
		{
			pstmt.setString(index++, getSQLText(fieldPhone));
			log.fine("Phone: " + fieldPhone.getText());
		}
		//	=> Postal
		if (fieldDepartment_ID.getValue() != null)
		{
			Integer bp = (Integer)fieldDepartment_ID.getValue();
			pstmt.setInt(index++, bp.intValue());
			log.fine("EmpDept=" + bp);
		}
		
	}   //  setParameters
    
    /*************************************************************************/

    /**
     *  Save Selection Details
     *  Get Location/Partner Info
     */
    public void saveSelectionDetail()
    {
        int row = p_table.getSelectedRow();
        if (row == -1)
            return;

        
        //
        if (m_DM_EmployeeContact_ID == -1)
        {
			int leadRowKey = -1;

			if (addressTbl != null || addressTbl.getRowCount() > 0)
				leadRowKey = addressTbl.getLeadRowKey();
	    	
			if (m_DM_EmployeeContact_ID != leadRowKey)
	    	{
				m_DM_EmployeeContact_ID = leadRowKey;  //  From the main table
	    	}
        }
        //  publish for Callout to read
        Env.setContext(Env.getCtx(), p_WindowNo, Env.TAB_INFO, "DM_DMSEmployee_ID", String.valueOf(m_DM_DMSEmployee_ID));
//        Env.setContext(Env.getCtx(), p_WindowNo, Env.TAB_INFO, "AD_User_ID", String.valueOf(m_AD_User_ID));
        Env.setContext(Env.getCtx(), p_WindowNo, Env.TAB_INFO, "DM_EmployeeContact_ID", String.valueOf(m_DM_EmployeeContact_ID));
       
    }   //  saveSelectionDetail
    
    // Elaine 2008/12/16
	/**************************************************************************
	 *	Show History
	 */
	protected void showHistory()
	{
		log.info("");
		Integer DM_DMSEmployee_ID = getSelectedRowKey();
		if (DM_DMSEmployee_ID == null)
			return;
		InvoiceHistory ih = new InvoiceHistory (this, DM_DMSEmployee_ID.intValue(), 
			0, 0, 0);
		ih.setVisible(true);
		ih = null;
	}	//	showHistory

	/**
	 *	Has History
	 *  @return true
	 */
	protected boolean hasHistory()
	{
		return true;
	}	//	hasHistory

	/**
	 *	Zoom
	 */
	public void zoom()
	{
		log.info( "InfoEmployee.zoom");
		Integer DM_DMSEmployee_ID = getSelectedRowKey();
		if (DM_DMSEmployee_ID == null)
			return;

		MQuery query = new MQuery("DM_DMSEmployee");
		query.addRestriction("DM_DMSEmployee_ID", MQuery.EQUAL, DM_DMSEmployee_ID);
		query.setRecordCount(1);
		int AD_WindowNo = getAD_Window_ID("DM_DMSEmployee", true);	//	SO
		AEnv.zoom (AD_WindowNo, query);
	}	//	zoom

	/**
	 *	Has Zoom
	 *  @return true
	 */
	protected boolean hasZoom()
	{
		return true;
	}	//	hasZoom

		
	/**
	 * Does the parameter panel have outstanding changes that have not been
	 * used in a query?
	 * @return true if there are outstanding changes.
	 */
	protected boolean hasOutstandingChanges()
	{
		//  All the tracked fields
		return(
			fieldValue.hasChanged()	||
			fieldName.hasChanged() ||
			fieldStatus_ID.hasChanged() ||
			fieldEMail.hasChanged() ||
			fieldPhone.hasChanged() ||
			fieldDepartment_ID.hasChanged()

			);
			
	}
	/**
	 * Record outstanding changes by copying the current
	 * value to the oldValue on all fields
	 */
	protected void setFieldOldValues()
	{
		fieldValue.set_oldValue();
		fieldName.set_oldValue();
		fieldStatus_ID.set_oldValue();
		fieldEMail.set_oldValue();
		fieldPhone.set_oldValue();
		fieldDepartment_ID.set_oldValue();

		return;
	}

    /**
	 *  Clear all fields and set default values in check boxes
	 */
	private void clearParameters()
	{
		//  Clear fields and set defaults
		fieldValue.setText("");
		fieldName.setText("");
		fieldStatus_ID.setValue(null);
		fieldEMail.setText("");
		fieldPhone.setText("");
		fieldDepartment_ID.setValue(null);
	}

}
