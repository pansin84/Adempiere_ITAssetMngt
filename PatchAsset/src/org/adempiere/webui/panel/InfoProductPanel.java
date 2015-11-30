/******************************************************************************
 * Product: Posterita Ajax UI                                                 *
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
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;

import org.adempiere.webui.apps.AEnv;
import org.adempiere.webui.component.Button;
import org.adempiere.webui.component.Checkbox;
import org.adempiere.webui.component.Combobox;
import org.adempiere.webui.component.ConfirmPanel;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.ListModelTable;
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
import org.adempiere.webui.editor.WPAttributeEditor;
import org.adempiere.webui.editor.WSearchEditor;
import org.adempiere.webui.editor.WTableDirEditor;
import org.adempiere.webui.event.ValueChangeListener;
import org.compiere.apps.search.Info_Column;
import org.compiere.minigrid.ColumnInfo;
import org.compiere.minigrid.IDColumn;
import org.compiere.model.MAttributeSet;
import org.compiere.model.MColumn;
import org.compiere.model.MLookupFactory;
import org.compiere.model.MPAttributeLookup;
import org.compiere.model.MPriceListVersion;
import org.compiere.model.MProduct;
import org.compiere.model.MProductCategory;
import org.compiere.model.MQuery;
import org.compiere.model.MWarehouse;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Util;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zkex.zul.Borderlayout;
import org.zkoss.zkex.zul.Center;
import org.zkoss.zkex.zul.North;

/**
 * Search Product and return selection
 * This class is based on org.compiere.apps.search.InfoPAttribute written by Jorg Janke
 * @author Elaine
 *
 * Zk Port
 * @author Elaine
 * @version	InfoPayment.java Adempiere Swing UI 3.4.1
 * 
 * @author Michael McKay, ADEMPIERE-72 VLookup and Info Window improvements
 * 	<li>https://adempiere.atlassian.net/browse/ADEMPIERE-72
 */
public class InfoProductPanel extends InfoPanel implements EventListener, ValueChangeListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6804975825156657866L;
	private int fieldID = 0;
	private Label lblBlank = new Label();
	private Label lblValue = new Label();
	private Textbox fieldValue = new Textbox();
	private Label lblName = new Label();
	private Textbox fieldName = new Textbox();

	
	private Label lblWarehouse = new Label();
	private WTableDirEditor fWarehouse_ID = null;
	private Label lblVendor = new Label();
	private WSearchEditor fVendor_ID = WSearchEditor.createBPartner(0);
	// Elaine 2008/11/21
	private Label lblProductCategory = new Label();
	private WTableDirEditor fProductCategory_ID = null;
	//
	private Tabbox detailTabBox = new Tabbox();

	
	private WListbox warehouseTbl = ListboxFactory.newDataTable();
	private String m_sqlWarehouse;
	
    //Available to Promise Tab
    private Info_Column[]		m_layoutATP = null;
	private WListbox 			m_tableAtp = null;
	private ListModelTable 		m_modelAtp = null;	
	private int 				m_M_Product_ID = 0;
	private int					m_M_Warehouse_ID = 0;
	private int 				m_M_PriceList_ID = 0;
	int mWindowNo = 0;
    //

	

	/**  Array of Column Info    */
	private static Info_Column[] s_Layout = null;
	private static int INDEX_PATTRIBUTE = 0;


	/** ASI							*/
	private int			m_M_AttributeSetInstance_ID = -1;
	/** Locator						*/
	private int			m_M_Locator_ID = 0;

	protected int m_ATP_M_Warehouse_ID;

	/**
	 *	Standard Constructor
	 * 	@param WindowNo window no
	 * 	@param M_Warehouse_ID warehouse
	 * 	@param M_PriceList_ID price list
	 *  @param record_id The record ID to find
	 *  @param value Query Value or Name if enclosed in @
	 * 	@param multiSelection multiple selections
	 *  @param saveResults  True if results will be saved, false for info only
	 * 	@param whereClause where clause
	 */
	public InfoProductPanel(int windowNo,
		int M_Warehouse_ID, int M_PriceList_ID, int record_id, String value,
		boolean multipleSelection, String whereClause)
	{
		this(windowNo, true, M_Warehouse_ID, M_PriceList_ID, record_id, value, multipleSelection, true, whereClause);
	}

	/**
	 *	Standard Constructor
	 * 	@param WindowNo window no
	 * 	@param M_Warehouse_ID warehouse
	 * 	@param M_PriceList_ID price list
	 *  @param record_id The record ID to find
	 *  @param value Query Value or Name if enclosed in @
	 * 	@param multiSelection multiple selections
	 *  @param saveResults  True if results will be saved, false for info only
	 * 	@param whereClause where clause
	 *  @param modal True if the column has a lookup - open modal
	 */
	public InfoProductPanel(int windowNo, boolean modal,
		int M_Warehouse_ID, int M_PriceList_ID, int record_id, String value,
		boolean multipleSelection, boolean saveResults, String whereClause)
	{
		super (windowNo, modal, "p", "M_Product_ID",multipleSelection, saveResults, whereClause);
		log.info(value + ", Wh=" + M_Warehouse_ID + ", PL=" + M_PriceList_ID + ", WHERE=" + whereClause);
		setTitle(Util.cleanAmp(Msg.getMsg(Env.getCtx(), "InfoProduct")));
		m_M_Warehouse_ID = M_Warehouse_ID;
		m_M_PriceList_ID = M_PriceList_ID;
		//
		//	Modify where clause to fit with column info definitions
		StringBuffer where = new StringBuffer();
		where.append("p.IsActive='Y'");
		//  Modify Where Clause
		if (whereClause != null && whereClause.length() > 0)
			where.append(" AND ")   //  replace fully qualified name with alias
				.append(Util.replace(whereClause, "M_Product.", "p."));
		setWhereClause(where.toString());
		//
		statInit();
		initInfo (record_id, value, M_Warehouse_ID, M_PriceList_ID, false);

        if(autoQuery() || record_id != 0 || (value != null && value.length() > 0 && value != "%"))
        {
        	prepareAndExecuteQuery();
    	}
        
		p_loadedOK = true;
		
	}	//	InfoProductPanel

	/**
	 *	initialize fields
	 */
	private void initComponents()
	{
		
		lblBlank.setValue(" ");
		lblValue = new Label();
		lblValue.setValue(Util.cleanAmp(Msg.translate(Env.getCtx(), "Value")));
		lblName = new Label();
		lblName.setValue(Util.cleanAmp(Msg.translate(Env.getCtx(), "Name")));
		
		lblProductCategory = new Label();
		lblProductCategory.setValue(Msg.translate(Env.getCtx(), "M_Product_Category_ID"));
		
		lblWarehouse = new Label();
		lblWarehouse.setValue(Util.cleanAmp(Msg.getMsg(Env.getCtx(), "Warehouse")));
		lblVendor = new Label();
		lblVendor.setValue(Msg.translate(Env.getCtx(), "Vendor"));

		
		

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
		
		
		// Elaine 2008/11/21
		fProductCategory_ID = new WTableDirEditor("M_Product_Category_ID", false, false, true,
				MLookupFactory.get (Env.getCtx(), p_WindowNo, 0, 
						MColumn.getColumn_ID(MProductCategory.Table_Name, MProductCategory.COLUMNNAME_M_Product_Category_ID), DisplayType.TableDir));
		fProductCategory_ID.getComponent().addEventListener(Events.ON_CHANGE, this);
		fProductCategory_ID.getComponent().setAttribute("zk_component_ID", "Lookup_Criteria_M_ProductCategory_ID");
		fProductCategory_ID.getComponent().setAttribute("zk_component_prefix", "Lookup_");
		fProductCategory_ID.getComponent().setAttribute("IsDynamic", "True");
		fProductCategory_ID.getComponent().setAttribute("fieldName", "fProductCategory_ID");
		
		
		fWarehouse_ID = new WTableDirEditor("M_Warehouse_ID", false, false, true,
				MLookupFactory.get (Env.getCtx(), p_WindowNo, 0, 
						MColumn.getColumn_ID(MWarehouse.Table_Name, MWarehouse.COLUMNNAME_M_Warehouse_ID),
				DisplayType.TableDir));
		fWarehouse_ID.getComponent().addEventListener(Events.ON_CHANGE, this);
		fWarehouse_ID.getComponent().setAttribute("zk_component_ID", "Lookup_Criteria_M_Warehouse_ID");
		fWarehouse_ID.getComponent().setAttribute("zk_component_prefix", "Lookup_");
		fWarehouse_ID.getComponent().setAttribute("IsDynamic", "True");
		fWarehouse_ID.getComponent().setAttribute("fieldName", "fWarehouse_ID");

		
		fVendor_ID.getComponent().getTextbox().setMaxlength(30);
		fVendor_ID.setIsSOTrx(true, false); // Override the isSOTrx context, Vendors only
		fVendor_ID.addValueChangeListener(this);
		fVendor_ID.getComponent().setAttribute("zk_component_ID", "Lookup_Criteria_C_BPartner_ID");
		fVendor_ID.getComponent().setAttribute("zk_component_prefix", "Lookup_");
		fVendor_ID.getComponent().setAttribute("IsDynamic", "False");
		fVendor_ID.getComponent().setAttribute("fieldName", "fVendor_ID");
		fVendor_ID.getComponent().setWidth("200px");
		
		

        initAtpTab();
        
	}	//	initComponents

	private void statInit()
	{
		//  Fill the grid, setup the center data table & add the tabs
		initComponents();
		
		Rows rows = new Rows();

		Row row = new Row();
		rows.appendChild(row);
		row.setSpans("1, 1, 1, 1, 1, 1");
		row.appendChild(lblValue.rightAlign());
		row.appendChild(fieldValue);
		row.appendChild(lblWarehouse.rightAlign());
		row.appendChild(fWarehouse_ID.getComponent());
		row.appendChild(lblBlank.rightAlign());
		row.appendChild(lblVendor.rightAlign());
		row.appendChild(fVendor_ID.getComponent());

		row = new Row();
		rows.appendChild(row);
		row.setSpans("1, 1, 1, 1, 1, 1");
		row.appendChild(lblName.rightAlign());
		row.appendChild(fieldName);
		row.appendChild(lblProductCategory.rightAlign());
		row.appendChild(fProductCategory_ID.getComponent());

		
		//
        ColumnInfo[] s_layoutWarehouse = new ColumnInfo[]{
        		new ColumnInfo(" ", "M_Warehouse_ID", IDColumn.class),
        		new ColumnInfo(Msg.translate(Env.getCtx(), "Warehouse"), "WarehouseName", String.class),
        		new ColumnInfo(Msg.translate(Env.getCtx(), "QtyAvailable"), "sum(QtyAvailable)", Double.class, true, true, null),
        		new ColumnInfo(Msg.translate(Env.getCtx(), "QtyOnHand"), "sum(QtyOnHand)", Double.class),
           		new ColumnInfo(Msg.translate(Env.getCtx(), "QtyReserved"), "sum(QtyReserved)", Double.class),
           		new ColumnInfo(Msg.translate(Env.getCtx(), "QtyOrdered"), "sum(QtyOrdered)", Double.class)};
//        		new ColumnInfo(Msg.translate(Env.getCtx(), "DocumentNote"), "DocumentNote", String.class)};
        /**	From Clause							*/
        String s_sqlFrom = " M_PRODUCT_STOCK_V ";
        /** Where Clause						*/
        String s_sqlWhere = "(QtyOnHand <> 0 OR QtyAvailable <> 0 OR QtyReserved <> 0 OR QtyOrdered <> 0) AND M_Product_ID = ?";
//      String s_sqlWhere = "M_Product_ID = ?";
        m_sqlWarehouse = warehouseTbl.prepareTable(s_layoutWarehouse, s_sqlFrom, s_sqlWhere, false, "M_PRODUCT_STOCK_V");
		m_sqlWarehouse += " Group By M_Warehouse_ID, Warehousename ";
		m_sqlWarehouse += " Order By sum(QtyOnHand) DESC, Warehousename ";		
		warehouseTbl.setMultiSelection(false);
        warehouseTbl.autoSize();
        warehouseTbl.setShowTotals(true);
        //warehouseTbl.getModel().addTableModelListener(this);
        warehouseTbl.setAttribute("zk_component_ID", "Lookup_Data_Warehouse");
		
        detailTabBox.setHeight("100%");
        Tabpanels tabPanels = new Tabpanels();
		detailTabBox.appendChild(tabPanels);
		Tabs tabs = new Tabs();
		detailTabBox.appendChild(tabs);

		Tab tab = new Tab(Util.cleanAmp(Msg.translate(Env.getCtx(), "Warehouse")));
		tab.addEventListener(Events.ON_SELECT, this);
		tabs.appendChild(tab);
		Tabpanel desktopTabPanel = new Tabpanel();
		desktopTabPanel.setHeight("100%");
		desktopTabPanel.appendChild(warehouseTbl);
		tabPanels.appendChild(desktopTabPanel);

		

		tabs.setAttribute("zk_component_ID", "Subordinate_Tabs");

		//  Add the tabs to the center south layout
		Borderlayout tabLayout = new Borderlayout();
		//  
		North north = new North();
		tabLayout.appendChild(north);
		//
		Center center = new Center();
		tabLayout.appendChild(center);
		center.appendChild(detailTabBox);

		//  Set main panel elements.  The other elements are handled by the info.java class
		p_criteriaGrid.appendChild(rows);
		p_centerSouth.appendChild(tabLayout);
		p_centerSouth.setTitle(Msg.translate(Env.getCtx(), "WarehouseStock"));
		p_centerSouth.setTooltiptext(Msg.translate(Env.getCtx(), "WarehouseStock"));
		super.setSizes();

		warehouseTbl.addActionListener(new EventListener() {
			public void onEvent(Event event) throws Exception {
				if (warehouseTbl.getRowCount() > 0)
				{
					int selectedRow = warehouseTbl.getSelectedRow();
					if (selectedRow<0) selectedRow = 0;

					Object wh_data = warehouseTbl.getValueAt(selectedRow, warehouseTbl.getKeyColumnIndex());
		            
					if (wh_data != null && wh_data instanceof IDColumn)
		            {
		            	IDColumn dataColumn = (IDColumn) wh_data;
		            	m_ATP_M_Warehouse_ID = dataColumn.getRecord_ID();
		            }
					else
					{
						m_ATP_M_Warehouse_ID = m_M_Warehouse_ID;
					}
				} 
				else
				{
					m_ATP_M_Warehouse_ID = m_M_Warehouse_ID;
				}
			}
		});

	}

	/**
	 * 	Refresh Query
	 */
	protected void refresh()
	{
		//  Invoke later to not delay events.
		//SwingUtilities.invokeLater(new Runnable(){public void run()
		//{
	    	
	
	    	String sql;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String eol = System.getProperty("line.separator"); 

			Boolean queryWarehouse = false;
			int leadRowKey = 0;
			
			if (p_table != null || p_table.getRowCount() > 0)
				leadRowKey = p_table.getLeadRowKey();
	    	
			if (m_M_Product_ID != leadRowKey)
	    	{
	    		m_M_Product_ID = leadRowKey;  //  From the main table
	    		queryWarehouse = true;  //  The product has changed, change the warehouse table
	    	}

			if(m_M_Product_ID <= 0) 
			{
				p_centerLayout.getSouth().setOpen(false);
				return;
			}
			else
			{
				p_centerLayout.getSouth().setOpen(true);
				if (queryWarehouse) // Product has changed and is valid
				{
					//  Find the ASI used by the product on the lead row
					MProduct mp = MProduct.get(Env.getCtx(), m_M_Product_ID);
					m_M_AttributeSetInstance_ID = mp.getM_AttributeSetInstance_ID();				
				}
			}
			
			if (detailTabBox.getSelectedIndex() == 0)
			{
				if (queryWarehouse)
				{
		    		//  Warehouse tab
					sql = m_sqlWarehouse;
			
					log.finest(sql);
					try
					{
						pstmt = DB.prepareStatement(sql, null);
						pstmt.setInt(1, m_M_Product_ID);
						rs = pstmt.executeQuery();
						warehouseTbl.loadTable(rs);
						rs.close();
					}
					catch (Exception e)
					{
						log.log(Level.WARNING, sql, e);
					}
					finally
					{
						DB.close(rs, pstmt);
						rs = null; pstmt = null;
					}
				}

				if (warehouseTbl.getRowCount() > 0)
				{
					int selectedRow = warehouseTbl.getSelectedRow();
					if (selectedRow<0)
					{
						warehouseTbl.setSelectedIndex(0);
						selectedRow = 0;
					}

					Object wh_data = warehouseTbl.getValueAt(selectedRow, warehouseTbl.getKeyColumnIndex());
		            
					if (wh_data != null && wh_data instanceof IDColumn)
		            {
		            	IDColumn dataColumn = (IDColumn) wh_data;
		            	m_ATP_M_Warehouse_ID = dataColumn.getRecord_ID();
		            }
					else
					{
						m_ATP_M_Warehouse_ID = m_M_Warehouse_ID;
					}
				} 
				else
				{
					m_ATP_M_Warehouse_ID = m_M_Warehouse_ID;
				}

			}
			
	    	

		//}});
	}	//	refresh
	
	
	/**
	 * Generic init call used by inherited class
	 */
	protected void initInfo ()
	{
		initInfo(0,"",m_M_Warehouse_ID, m_M_PriceList_ID, true);
	}

	/**
	 *	Dynamic Init
	 *
	 * @param record_id   M_Product_ID if known, otherwise, 0
	 * @param value value
	 * @param M_Warehouse_ID warehouse
	 * @param M_PriceList_ID price list
	 */
	private void initInfo (int record_id, String value, int M_Warehouse_ID, int M_PriceList_ID, boolean reset)
	{
		if (!(record_id == 0) && value != null && value.length() > 0)
		{
			log.severe("Received both a record_id and a value: " + record_id + " - " + value);
		}

		//  In case of reset, clear all parameters to ensure we are at a known starting point.
		if(reset)
		{
			clearParameters();
			p_resetColumns = true;
		}
		//  Set values
        if (!(record_id == 0))  // A record is defined
        {
        	fieldID = record_id;
        	fWarehouse_ID.setValue(new Integer(M_Warehouse_ID).intValue());
        } 
        else
        {
        	fieldID = 0;
        	
        	String id;
			if (value != null && value.length() > 0) //  The VLookup failed to find uniqueness across the direct access SQL fields
			{
				//  Match the query performed by the VLookup.  See getDirectAccessSQL().
				if (value.startsWith("@") && value.endsWith("@"))
				{
					fieldName.setText(value.substring(1,value.length()-1));
				}
				else
				{
					fieldValue.setText(value);
					fieldName.setText(value);
				}
				//
				fWarehouse_ID.setValue(0);
	        	
			}
			else
			{
				//  No field or value - the general case
				//  Try to find other criteria in the context
				//  M_Product_ID - only if visible
				id = Env.getContext(Env.getCtx(), p_WindowNo, p_TabNo, "M_Product_ID", true);
				if (id != null && id.length() != 0 && (new Integer(id).intValue() > 0))
				{
					fieldID = new Integer(id).intValue();
				}
				
				

				//  M_Warehouse_ID - general context
				if(M_Warehouse_ID == 0)
				{
					id = Env.getContext(Env.getCtx(), "#M_Warehouse_ID");
					if (id != null && id.length() != 0 && (new Integer(id).intValue() > 0))
					{
						fWarehouse_ID.setValue(new Integer(id).intValue());
					}
					else 
					{
						id = Env.getContext(Env.getCtx(), p_WindowNo, "M_Warehouse_ID");
						if (id != null && id.length() != 0 && (new Integer(id).intValue() > 0))
						{
							fWarehouse_ID.setValue(new Integer(id).intValue());
						}
					}
				}
				else
				{
		        	fWarehouse_ID.setValue(new Integer(M_Warehouse_ID).intValue());
				}
				
				id = Env.getContext(Env.getCtx(), p_WindowNo, p_TabNo, "C_BPartner_ID", false);
				boolean isSOTrx = "Y".equals(Env.getContext(Env.getCtx(), p_WindowNo, p_TabNo, "IsSOTrx", false));
				if (id != null && id.length() != 0 && (new Integer(id).intValue() > 0) && !isSOTrx)
				{
					fVendor_ID.setValue(new Integer(id).intValue());
				}			
			}
		}
	}	//	initInfo

	

	/**************************************************************************
	 *	Construct SQL From Clause
	 *  @return SQL From clause
	 */
	protected String getFromClause()
	{
		/** SQL From				*/
		String s_productFrom = "M_Product p";		
		return s_productFrom;
	}
	
	/**************************************************************************
	 *	Construct SQL Where Clause and define parameters
	 *  (setParameters needs to set parameters)
	 *  Includes first AND
	 *  @return SQL WHERE clause
	 */
	public String getSQLWhere()
	{
		ArrayList<String> list = new ArrayList<String>();
		
		//  => ID
		if(isResetRecordID())
			fieldID = 0;
		if(!(fieldID == 0))
		{
			list.add("p.M_Product_ID = ?");
		}
		
		//  Warehouse - if defined, don't include summary products
		if (fWarehouse_ID.getValue() != null && ((Integer) fWarehouse_ID.getValue()).intValue() != 0)
			list.add("p.IsSummary='N'");

		
			list.add("p.isStocked = 'Y'");
		
		
		
		//  Optional Product Category
		if (fProductCategory_ID.getValue() != null) {
			list.add("(p.M_Product_Category_ID=? OR p.M_Product_Category_ID IN "
			+ 		"(SELECT PPC.M_Product_Category_ID FROM M_Product_Category ppc WHERE "
			+		" ppc.M_Product_Category_Parent_ID = ?))");
		}
		
		

		//  => Value
		if(isValidSQLText(fieldValue))
			list.add("UPPER(p.Value) LIKE ?");

		//  => Name
		if(isValidSQLText(fieldName))
			list.add("UPPER(p.Name) LIKE ?");

		
		//	=> Vendor
		if (fVendor_ID.getValue() != null)
			list.add("ppo.C_BPartner_ID=?");
		
		StringBuffer sql = new StringBuffer();
		int size = list.size();
		//	Just one
		if (size == 1)
			sql.append(" AND ").append(list.get(0));
		
		
		return sql.toString();
	}	//	getSQLWhere

	/**
	 *  Set Parameters for Query
	 *  (as defined in getSQLWhere)	 *
	 * @param pstmt pstmt
	 *  @param forCount for counting records
	 * @throws SQLException
	 */
	protected void setParameters(PreparedStatement pstmt, boolean forCount) throws SQLException
	{
		int index = 1;

		//  => Warehouse
		Integer id;
		if (fWarehouse_ID.getValue() != null)
			id = ((Integer) fWarehouse_ID.getValue());
		else
			id = 0;
		if (!forCount)	//	parameters in select
		{
			for (int i = 0; i < p_layout.length; i++)
			{
				if (p_layout[i].getColSQL().indexOf('?') != -1)
					pstmt.setInt(index++, id.intValue());
			}
		}
		log.fine("M_Warehouse_ID=" + id + " (" + (index-1) + "*)");

		//  => ID
		if(!(fieldID == 0))
		{
			pstmt.setInt(index++, fieldID);
			log.fine("Record ID: " + fieldID);
		}

		
		
		//  => Product Category
		if (fProductCategory_ID.getValue() != null) {
			id = ((Integer) fProductCategory_ID.getValue());
			pstmt.setInt(index++, id.intValue());
			pstmt.setInt(index++, id.intValue());  //  Done twice - see getWhere()
			log.fine("M_Product_Category_ID=" + id);
		}
		
		
		//  => Value
		if (isValidSQLText(fieldValue))
			pstmt.setString(index++, getSQLText(fieldValue));
		//  => Name
		if (isValidSQLText(fieldName))
			pstmt.setString(index++, getSQLText(fieldName));
		
		//  => Vendor
		if (fVendor_ID.getValue() != null)
		{
			id = (Integer)fVendor_ID.getValue();
			pstmt.setInt(index++, id.intValue());
			log.fine("fVendor_ID=" + id);
		}

	}   //  setParameters

	/**
	 *	Show History
	 */
	protected void showHistory()
	{
		log.info("");
		Integer M_Product_ID = getSelectedRowKey();
		if (M_Product_ID == null)
			return;
		int M_Warehouse_ID = 0;
		if (fWarehouse_ID.getValue() != null)
			M_Warehouse_ID = (Integer)fWarehouse_ID.getValue();
		int M_AttributeSetInstance_ID = m_M_AttributeSetInstance_ID;
		if (m_M_AttributeSetInstance_ID < -1)	//	not selected
			M_AttributeSetInstance_ID = 0;
		//
		InvoiceHistory ih = new InvoiceHistory (this, 0,
			M_Product_ID.intValue(), M_Warehouse_ID, M_AttributeSetInstance_ID);
		ih.setVisible(true);
		ih = null;
	}	//	showHistory

	/**
	 *	Has History
	 *
	 * @return true (has history)
	 */
	protected boolean hasHistory()
	{
		return true;
	}	//	hasHistory

	// Elaine 2008/12/16
	/**
	 *	Zoom
	 */
	public void zoom()
	{
		log.info("");
		Integer M_Product_ID = getSelectedRowKey();
		if (M_Product_ID == null)
			return;

		MQuery query = new MQuery("M_Product");
		query.addRestriction("M_Product_ID", MQuery.EQUAL, M_Product_ID);
		query.setRecordCount(1);
		int AD_WindowNo = getAD_Window_ID("M_Product", true);	//	SO
		AEnv.zoom (AD_WindowNo, query);
	}	//	zoom
	//

	/**
	 *	Has Zoom
	 *  @return (has zoom)
	 */
	protected boolean hasZoom()
	{
		return true;
	}	//	hasZoom

	/**
	 *	Save Selection Settings for PriceList
	 */
	protected void saveSelectionDetail()
	{
		//  publish for Callout to read
		Integer ID = getSelectedRowKey();
		Env.setContext(Env.getCtx(), p_WindowNo, Env.TAB_INFO, "M_Product_ID", ID == null ? "0" : ID.toString());
		
		if (fWarehouse_ID.getValue() != null)
        {
			String pickWH = ((Integer)fWarehouse_ID.getValue()).toString();
            Env.setContext(Env.getCtx(), p_WindowNo, Env.TAB_INFO, "M_Warehouse_ID",pickWH);
        }
		
	}	//	saveSelectionDetail
	
	/**
	 *  Get Table Layout
	 *
	 * @return array of Column_Info
	 */
	protected Info_Column[] getTableLayout()
	{

		ArrayList<Info_Column> list = new ArrayList<Info_Column>();
		list.add(new Info_Column(" ", "p.M_Product_ID", IDColumn.class, false));
		list.add(new Info_Column(Msg.translate(Env.getCtx(), "Discontinued").substring(0, 1), "p.Discontinued", Boolean.class));
		//if (!isValidVObject(fProductCategory_ID) || (isValidVObject(fProductCategory_ID) && !checkAND.isSelected()))
		//{
			list.add(new Info_Column(Msg.translate(Env.getCtx(), "M_Product_Category_ID"), "pc.Name", String.class));
		//}
		list.add(new Info_Column(Msg.translate(Env.getCtx(), "Value"), "p.Value", String.class));
		list.add(new Info_Column(Msg.translate(Env.getCtx(), "Name"), "p.Name", String.class));
		
		if (isValidVObject(fWarehouse_ID))
		{
			list.add(new Info_Column(Msg.translate(Env.getCtx(), "IsStocked"), "p.isStocked", Boolean.class));
			list.add(new Info_Column(Msg.translate(Env.getCtx(), "QtyAvailable"), "case when p.IsBOM='N' and (p.ProductType!='I' OR p.IsStocked='N') then to_number(get_Sysconfig('QTY_TO_SHOW_FOR_SERVICES', '99999', p.ad_client_id, 0), '99999999999') else bomQtyAvailable(p.M_Product_ID,?,0) end AS QtyAvailable", Double.class, true, true, null));
			list.add(new Info_Column(Msg.translate(Env.getCtx(), "QtyOnHand"), "case when p.IsBOM='N' and (p.ProductType!='I' OR p.IsStocked='N') then to_number(get_Sysconfig('QTY_TO_SHOW_FOR_SERVICES', '99999', p.ad_client_id, 0), '99999999999') else bomQtyOnHand(p.M_Product_ID,?,0) end AS QtyOnHand", Double.class));
			list.add(new Info_Column(Msg.translate(Env.getCtx(), "QtyReserved"), "bomQtyReserved(p.M_Product_ID,?,0) AS QtyReserved", Double.class));
			list.add(new Info_Column(Msg.translate(Env.getCtx(), "QtyOrdered"), "bomQtyOrdered(p.M_Product_ID,?,0) AS QtyOrdered", Double.class));
			if (isUnconfirmed())
			{
				list.add(new Info_Column(Msg.translate(Env.getCtx(), "QtyUnconfirmed"), "(SELECT SUM(c.TargetQty) FROM M_InOutLineConfirm c INNER JOIN M_InOutLine il ON (c.M_InOutLine_ID=il.M_InOutLine_ID) INNER JOIN M_InOut i ON (il.M_InOut_ID=i.M_InOut_ID) WHERE c.Processed='N' AND i.M_Warehouse_ID=? AND il.M_Product_ID=p.M_Product_ID) AS QtyUnconfirmed", Double.class));
				list.add(new Info_Column(Msg.translate(Env.getCtx(), "QtyUnconfirmedMove"), "(SELECT SUM(c.TargetQty) FROM M_MovementLineConfirm c INNER JOIN M_MovementLine ml ON (c.M_MovementLine_ID=ml.M_MovementLine_ID) INNER JOIN M_Locator l ON (ml.M_LocatorTo_ID=l.M_Locator_ID) WHERE c.Processed='N' AND l.M_Warehouse_ID=? AND ml.M_Product_ID=p.M_Product_ID) AS QtyUnconfirmedMove", Double.class));
			}
		}
		list.add(new Info_Column(Msg.translate(Env.getCtx(), "Vendor"), "bp.Name", String.class));
		list.add(new Info_Column(Msg.translate(Env.getCtx(), "IsInstanceAttribute"), "pa.IsInstanceAttribute", Boolean.class));
		//
		s_Layout = new Info_Column[list.size()];
		list.toArray(s_Layout);
		//
		INDEX_PATTRIBUTE = s_Layout.length - 1;	//	last item
		//
		return s_Layout;
	}   //  getTableLayout
	/**
	 *  Get Order Clause
	 *
	 * @return orderClause  "
	 */
	protected String getOrderClause()
	{
		String orderClause = "";
		if (!isValidVObject(fProductCategory_ID))
		{
			orderClause += ", pc.Name";
		}
		
		orderClause += ", Value";
		
		if (isValidVObject(fWarehouse_ID))
		{
			orderClause += ", QtyAvailable DESC";
		}
		
		if (orderClause.startsWith(", "))
			orderClause = orderClause.substring(2);
		
		return orderClause;
	}
	/**
	 * 	System has Unforfirmed records
	 *	@return true if unconfirmed
	 */
	private boolean isUnconfirmed()
	{
		int no = DB.getSQLValue(null,
			"SELECT COUNT(*) FROM M_InOutLineConfirm WHERE AD_Client_ID=?",
			Env.getAD_Client_ID(Env.getCtx()));
		if (no > 0)
			return true;
		no = DB.getSQLValue(null,
			"SELECT COUNT(*) FROM M_MovementLineConfirm WHERE AD_Client_ID=?",
			Env.getAD_Client_ID(Env.getCtx()));
		return no > 0;
	}	//	isUnconfirmed

	
    public void onEvent(Event e)
    {
    	// Handle specific actions if possible or pass the event to the parent class

		if(!p_loadedOK)
			return;

		Component component = e.getTarget();
		
		if(component != null)
		{
			//  Handle product info specific fields here
			if (component.equals(confirmPanel.getButton(ConfirmPanel.A_PATTRIBUTE)))
			{
				//  Find the ASI used by the product on the lead row
				MProduct mp = MProduct.get(Env.getCtx(), m_M_Product_ID);
				//  Set title and parameters for the PattributeInstance window
				String title = "";
				int wh_id = 0;
				if (isValidVObject(fWarehouse_ID))
				{
					title = mp.getName() + " - " + fWarehouse_ID.getDisplay();
					wh_id = ((Integer) (fWarehouse_ID.getValue())).intValue();
				}
				//  Get the business partner from the context - it may be different than the Vendor
				int bp_id = 0;
				String s_bp_id = Env.getContext(Env.getCtx(), p_WindowNo, p_TabNo, "C_BPartner_ID", false);
				if (s_bp_id != null && s_bp_id.length() != 0 && (new Integer(s_bp_id).intValue() > 0))
					bp_id = new Integer(s_bp_id).intValue();
				//  Display the window
				InfoPAttributeInstancePanel pai = new InfoPAttributeInstancePanel (this, title, 
						wh_id, 0, p_table.getLeadRowKey(), bp_id);
				
				if (!pai.wasCancelled())
				{
					//  Get the results and update the fASI criteria field
					m_M_AttributeSetInstance_ID = pai.getM_AttributeSetInstance_ID();
					m_M_Locator_ID = pai.getM_Locator_ID();
					
				}
				
				//  Saving here is confusing with multi-selection.  The Product Attribute button shouldn't be enabled
				//  if multiple records are selected.  Also, don't close the info window if the
				//  pai window was cancelled or nothing was selected.  Assume the user was just
				//  looking around.
				if (p_saveResults && m_M_AttributeSetInstance_ID != -1 && !pai.wasCancelled())  //  If the results are saved, we can save now - an ASI is product specific
				{
					dispose(p_saveResults);
					return;
				}
				return;
			}		
			else if (component instanceof Combobox)
			{
				if(e.getName().equals("onChange"))
					{
						//  perform field-specific changes
						if (component.equals(fWarehouse_ID.getComponent()))
						{
							
						}
					}
				}
			else if (component instanceof Checkbox)
			{
				Checkbox cb = (Checkbox) component;
				//  ShowDetail check box
				if (cb.getName() != null && cb.getName().equals("ShowDetail"))
				{
					// Refresh only the ATP tab 
					refreshAtpTab();
					return;
				}
			}
			else if (component instanceof Tab) // a tab in the ATP panel is selected
			{

				refresh();				
				return;
			}
		} 
		//
		super.onEvent(e);
    }

	
    // Elaine 2008/11/26
	/**
	 *	Query ATP
	 */
	private void initAtpTab ()
	{
	
		//  Table
		m_tableAtp = ListboxFactory.newDataTable();
		m_tableAtp.setMultiSelection(false);
		//m_tableAtp.setRowSelectionAllowed(true);
		//m_tableAtp.addMouseListener(this);
		//m_tableAtp.setShowTotals(false);
		
		//	Header
		ArrayList<Info_Column> list = new ArrayList<Info_Column>();
		list.add(new Info_Column(" ", "M_Product_ID", IDColumn.class));
		list.add(new Info_Column(Msg.translate(Env.getCtx(), "M_Warehouse_ID"), "Warehouse", String.class));
		list.add(new Info_Column(Msg.translate(Env.getCtx(), "M_Locator_ID"), "Locator", String.class));
		list.add(new Info_Column(Msg.getMsg(Env.getCtx(), "Date", true), "Date", Timestamp.class));
		list.add(new Info_Column(Msg.translate(Env.getCtx(), "QtyAvailable"), "QtyAvailable", Double.class, true, true, null));
		list.add(new Info_Column(Msg.translate(Env.getCtx(), "QtyOnHand"), "QtyOnHand", Double.class));
		list.add(new Info_Column(Msg.getMsg(Env.getCtx(), "ExpectedChange", true), "DeltaQty", Double.class));
		list.add(new Info_Column(Msg.translate(Env.getCtx(), "C_BPartner_ID"), "BP_Name", String.class));
		list.add(new Info_Column(Msg.translate(Env.getCtx(), "QtyOrdered"), "QtyOrdered", Double.class));
		list.add(new Info_Column(Msg.translate(Env.getCtx(), "QtyReserved"), "QtyReserved", Double.class));
		list.add(new Info_Column(Msg.translate(Env.getCtx(), "M_AttributeSetInstance_ID"), "PASI", String.class));
		list.add(new Info_Column(Msg.translate(Env.getCtx(), "DocumentNo"), "DocumentNo", String.class));

		m_layoutATP = new Info_Column[list.size()];
		list.toArray(m_layoutATP);

	}	//	initAtpTab	
	
	/**
	 *	Refresh ATP
	 */
	private void refreshAtpTab ()
	{

		
		
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();

		int M_Warehouse_ID = 0;

		// If no warehouse row is selected in the warehouse tab, use the first warehouse
		// row to prevent array index out of bounds. BF 3051361
		if (m_ATP_M_Warehouse_ID > 0)
		{
        		M_Warehouse_ID = m_ATP_M_Warehouse_ID;
		} 
		else
			M_Warehouse_ID = m_M_Warehouse_ID;

		if (M_Warehouse_ID == 0)
		{
			// Do nothing and pass blank data to the table
		}
		else  // Update the table
		{
			//	Create the SELECT ..UNION. clause
			//  This is done in-line rather than using prepareTable() so we can add a running sum to the data.
			String sql;
				sql = "(SELECT s.M_Product_ID, w.Name as warehouse, l.value as locator, 0 as ID, null as Date,"
					+ " sum(s.QtyOnHand) as AvailQty, null as DeltaQty, sum(s.QtyOrdered) as QtyOrdered, sum(s.QtyReserved) as QtyReserved,"
					+ " null as sumPASI," // " s.PASI," 
					+ " 0 as ASI,"
					+ " null as BP_Name, null as DocumentNo, 10 as SeqNo";
			
			sql += " FROM (SELECT M_Product_ID, M_Locator_ID, QtyOnHand, QtyReserved, QtyOrdered,"
				+ 		 " COALESCE(productAttribute(M_AttributeSetInstance_ID)::varchar, '') as PASI,"
				+		 " COALESCE(M_AttributeSetInstance_ID,0) as M_AttributeSetInstance_ID FROM M_Storage) s "
				+ " INNER JOIN M_Locator l ON (s.M_Locator_ID=l.M_Locator_ID)"
				+ " INNER JOIN M_Warehouse w ON (l.M_Warehouse_ID=w.M_Warehouse_ID)"
				+ " AND s.M_Product_ID=" + m_M_Product_ID;
			if (M_Warehouse_ID != 0)
				sql += " AND l.M_Warehouse_ID=" + M_Warehouse_ID;
			
				sql += " GROUP BY s.M_Product_ID, w.Name, l.value, s.M_Locator_ID, sumPASI, ASI, BP_Name, DocumentNo, SeqNo ";
			

			
			sql += " UNION ALL ";
	
			//	Orders
			sql += "SELECT ol.M_Product_ID, w.Name as warehouse, null as locator, ol.M_AttributeSetInstance_ID as ID, o.DatePromised as date,"
				+ " null as AvailQty,"
				+ " CASE WHEN dt.DocBaseType = 'POO' THEN ol.QtyOrdered ELSE -ol.QtyReserved END as DeltaQty,"
				+ " CASE WHEN dt.DocBaseType = 'POO' THEN ol.QtyOrdered ELSE null END as QtyOrdered,"
				+ " CASE WHEN dt.DocBaseType = 'POO' THEN 0 ELSE 0 END as QtyReserved,"
				+ " productAttribute(ol.M_AttributeSetInstance_ID) as sumPASI," 
				+ " ol.M_AttributeSetInstance_ID as ASI,"
				+ " bp.Name as BP_Name, dt.PrintName || ' ' || o.DocumentNo As DocumentNo, 20 as SeqNo "
				+ "FROM C_Order o"
				+ " INNER JOIN C_OrderLine ol ON (o.C_Order_ID=ol.C_Order_ID)"
				+ " INNER JOIN C_DocType dt ON (o.C_DocType_ID=dt.C_DocType_ID)"
				+ " INNER JOIN M_Warehouse w ON (ol.M_Warehouse_ID=w.M_Warehouse_ID)"
				+ " INNER JOIN C_BPartner bp  ON (o.C_BPartner_ID=bp.C_BPartner_ID) "
				+ "WHERE ol.QtyReserved<>0 AND o.DocStatus in ('IP','CO')"
				+ " AND ol.M_Product_ID=" + m_M_Product_ID;
			if (M_Warehouse_ID != 0)
				sql += " AND w.M_Warehouse_ID=" + M_Warehouse_ID;
			//if (m_M_AttributeSetInstance_ID > 0)
			//	sql += " AND ol.M_AttributeSetInstance_ID=?";
			//sql += " ORDER BY M_Product_ID, SeqNo, ID, date, locator";

			sql += " UNION ALL ";
			
			//	Distribution Orders out bound
			sql += "SELECT ol.M_Product_ID, wf.Name as warehouse, lf.value as locator, ol.M_AttributeSetInstance_ID as ID, ol.DatePromised as date,"
				+ " null as AvailQty,"
				+ " -ol.QtyOrdered+ol.QtyInTransit+ol.QtyDelivered as DeltaQty,"
				+ " null as QtyOrdered,"
				+ " null  as QtyReserved,"
				+ " productAttribute(ol.M_AttributeSetInstance_ID) as sumPASI," 
				+ " ol.M_AttributeSetInstance_ID as ASI,"
				+ " bp.Name as BP_Name, dt.PrintName || ' ' || o.DocumentNo As DocumentNo, 20 as SeqNo "
				+ "FROM DD_Order o"
				+ " INNER JOIN DD_OrderLine ol ON (o.DD_Order_ID=ol.DD_Order_ID)"
				+ " INNER JOIN C_DocType dt ON (o.C_DocType_ID=dt.C_DocType_ID)"
				+ " INNER JOIN M_Locator l ON (l.M_Locator_ID = ol.M_LocatorTo_ID)"
				+ " INNER JOIN M_Locator lf on (lf.M_Locator_ID = ol.M_Locator_ID)"
				+ " INNER JOIN M_Warehouse w ON (l.M_Warehouse_ID=w.M_Warehouse_ID)"
				+ " INNER JOIN M_Warehouse wf ON (lf.M_Warehouse_ID=wf.M_Warehouse_ID)"
				+ " INNER JOIN C_BPartner bp  ON (o.C_BPartner_ID = bp.C_BPartner_ID) "
				+ "WHERE ol.QtyReserved<>0 AND o.DocStatus in ('IP','CO') AND o.IsDelivered = 'N'"
				+ " AND ol.M_Product_ID=" + m_M_Product_ID;
			if (M_Warehouse_ID != 0)
				sql += " AND wf.M_Warehouse_ID=" + M_Warehouse_ID;
			//if (m_M_AttributeSetInstance_ID > 0)
			//	sql += " AND ol.M_AttributeSetInstance_ID=?";

			sql += " UNION ALL ";
			
			//	Distribution Orders in bound
			sql += "SELECT ol.M_Product_ID, w.Name as warehouse, l.value as locator, ol.M_AttributeSetInstance_ID as ID, ol.DatePromised as date,"
				+ " null as AvailQty,"
				+ " ol.QtyOrdered-ol.QtyDelivered as DeltaQty,"
				+ " null as QtyOrdered,"
				+ " null  as QtyReserved,"
				+ " productAttribute(ol.M_AttributeSetInstance_ID) as sumPASI," 
				+ " ol.M_AttributeSetInstance_ID as ASI,"
				+ " bp.Name as BP_Name, dt.PrintName || ' ' || o.DocumentNo As DocumentNo, 20 as SeqNo "
				+ "FROM DD_Order o"
				+ " INNER JOIN DD_OrderLine ol ON (o.DD_Order_ID=ol.DD_Order_ID)"
				+ " INNER JOIN C_DocType dt ON (o.C_DocType_ID=dt.C_DocType_ID)"
				+ " INNER JOIN M_Locator l ON (l.M_Locator_ID = ol.M_LocatorTo_ID)"
				+ " INNER JOIN M_Locator lf on (lf.M_Locator_ID = ol.M_Locator_ID)"
				+ " INNER JOIN M_Warehouse w ON (l.M_Warehouse_ID=w.M_Warehouse_ID)"
				+ " INNER JOIN M_Warehouse wf ON (lf.M_Warehouse_ID=wf.M_Warehouse_ID)"
				+ " INNER JOIN C_BPartner bp  ON (wf.AD_Org_ID=bp.AD_OrgBP_ID) "
				+ "WHERE ol.QtyOrdered - ol.Qtydelivered > 0 AND o.DocStatus in ('IP','CO') AND o.IsDelivered='N'" 
				+ " AND ol.M_Product_ID=" + m_M_Product_ID;
			if (M_Warehouse_ID != 0)
				sql += " AND w.M_Warehouse_ID=" + M_Warehouse_ID;
			//if (m_M_AttributeSetInstance_ID > 0)
			//	sql += " AND ol.M_AttributeSetInstance_ID=?";
			sql += " ORDER BY M_Product_ID, SeqNo, ID, date, locator)";

			double qty = 0;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try
			{
				int index = 1;
				pstmt = DB.prepareStatement(sql, null);
				rs = pstmt.executeQuery();
				while (rs.next())
				{
					//  The order of data matches the layout, not the query
					//  M_Product_ID, warehouse, locator, ID, Date, AvailQty, DelataQty, (1..7) 
					//  QtyOrdered, QtyReserved, (8..9)
					//  PASI, ASI, (10..11)
					//  BP_Name, DocumentNo,  SeqNo (12..14)
					IDColumn mpid = new IDColumn(rs.getInt(1));
					Vector<Object> line = new Vector<Object>(9);
					line.add(mpid);							//  M_Product_ID
					line.add(rs.getString(2));						//  warehouse
					line.add(rs.getString(3));      				//  Locator
					line.add(rs.getTimestamp(5));					//  Date
					double deltaQty = rs.getDouble(7);
					qty += +rs.getDouble(6) + deltaQty;
					line.add(new Double(qty) - rs.getDouble(9));  					//  Qty Available (running sum)
					line.add(new Double(rs.getDouble(6)));			//  Qty on hand (this line)
					line.add(new Double(rs.getDouble(7)));			//  Delta Qty
					line.add(rs.getString(12));						//  BPartner
					line.add(new Double(rs.getDouble(8)));  		//  QtyOrdered
					line.add(new Double(rs.getDouble(9)));  		//  QtyReserved
					line.add(rs.getString(10));						//  ASI
					line.add(rs.getString(13));						//  DocumentNo
					data.add(line);
				}
			}
			catch (SQLException e)
			{
				log.log(Level.SEVERE, sql, e);
			}
			finally {
				DB.close(rs, pstmt);
				rs = null; pstmt = null;
			}
		}
		
		//  Update the table
		
		//	Header
		for (int i = 0; i < m_layoutATP.length; i++)
		{
			m_tableAtp.addColumn(m_layoutATP[i].getColHeader());
		}

		m_modelAtp = new ListModelTable(data);

		//  Avoid an exception
		//SwingUtilities.invokeLater(new Runnable(){public void run(){
			m_tableAtp.setModel(m_modelAtp);
			//  set editors (two steps)
			for (int i = 0; i < m_layoutATP.length; i++)
			{
				m_tableAtp.setColumnClass(i, m_layoutATP[i].getColClass(), m_layoutATP[i].isReadOnly(), m_layoutATP[i].getColHeader());
				if (m_layoutATP[i].isColorColumn())
				{
					m_tableAtp.setColorColumn(i);  // QtyAvailable.
				}
			}
			m_tableAtp.autoSize();
			m_tableAtp.repaint();
		//}});


	}	//	refreshAtpTab
	// Elaine 2008/11/21
    public int getM_Product_Category_ID()
    {
		int M_Product_Category_ID = 0;

		if (fProductCategory_ID.getValue()!=null)
			M_Product_Category_ID = (Integer) fProductCategory_ID.getValue();

		return M_Product_Category_ID;
	}
  
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
			fWarehouse_ID.hasChanged() ||
			fVendor_ID.hasChanged() ||
			fProductCategory_ID.hasChanged()
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
		fWarehouse_ID.set_oldValue();
		fVendor_ID.set_oldValue();
		fProductCategory_ID.set_oldValue();
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
    	fWarehouse_ID.setValue(null);
    	fProductCategory_ID.setValue(null);
    	fVendor_ID.setValue(null);
	}

	/**
	 * A record was selected - take action to sync subordinate tables if any
	 */
	protected void recordSelected(int key)
	{
		//  Found and selected the same record or selected the first record
    	if (m_M_Product_ID != key)
    	{
    		refresh();
    	}
    	p_centerSouth.setOpen(p_table.getSelectedCount()>0);
		return;
	}
	/**
	 * No record was selected - take action to sync subordinate tables if any
	 */
	protected void noRecordSelected()
	{
		//  Nothing was selected, or the query is empty
		//  - close the panel
		m_M_Product_ID = 0;
		p_centerLayout.getSouth().setOpen(false);
		return;
	}

}	//	InfoProduct
