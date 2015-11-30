/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 2008 Low Heng Sin. All Rights Reserved.                      *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 *****************************************************************************/
package org.adempiere.webui.window;

import java.util.Vector;

import org.adempiere.webui.component.Button;
import org.adempiere.webui.component.Checkbox;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.ListHead;
import org.adempiere.webui.component.ListHeader;
import org.adempiere.webui.component.Listbox;
import org.adempiere.webui.component.SimpleListModel;
import org.adempiere.webui.component.Tab;
import org.adempiere.webui.component.Tabbox;
import org.adempiere.webui.component.Tabpanel;
import org.adempiere.webui.component.Tabpanels;
import org.adempiere.webui.component.Tabs;
import org.adempiere.webui.component.ToolBarButton;
import org.adempiere.webui.component.Window;
import org.adempiere.webui.theme.ThemeManager;
import org.compiere.Adempiere;
import org.compiere.model.MUser;
import org.compiere.util.CLogErrorBuffer;
import org.compiere.util.CLogMgt;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.zkoss.util.media.AMedia;
import org.zkoss.zhtml.Pre;
import org.zkoss.zhtml.Text;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.SizeEvent;
import org.zkoss.zul.Div;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Image;
import org.zkoss.zul.Separator;
import org.zkoss.zul.Vbox;

/**
 *
 * @author Low Heng Sin
 *
 */
public class AboutWindow extends Window implements EventListener {

	/**
	 *
	 */
	private static final long serialVersionUID = -257313771447940626L;
	private Checkbox bErrorsOnly;
	private Listbox logTable;
	private Tabbox tabbox;
	private Tabpanels tabPanels;
	private Button btnDownload;
	private Button btnErrorEmail;
	private Tab tabLog;

	public AboutWindow() {
		super();
		init();
	}

	private void init() {
		this.setWidth("786px");
		this.setHeight("491px");
		this.setPosition("center");
		this.setTitle(ThemeManager.getBrowserTitle());
		this.setClosable(true);
		this.setSizable(true);

		this.addEventListener(Events.ON_SIZE, this);

		Vbox layout = new Vbox();
		layout.setWidth("100%");
		layout.setParent(this);

		tabbox = new Tabbox();
		tabbox.setParent(layout);
		tabbox.setWidth("775px");
		tabbox.setHeight("460px");
//		tabbox.setSclass("lite");
		Tabs tabs = new Tabs();
		tabs.setParent(tabbox);
		tabPanels = new Tabpanels();
		tabPanels.setParent(tabbox);
		tabPanels.setWidth("775px");

		//about
		Tab tab = new Tab();
		tab.setLabel(Msg.getMsg(Env.getCtx(), "About"));
		tab.setParent(tabs);
		Tabpanel tabPanel = createAbout();
		tabPanel.setParent(tabPanels);

		//Credit
		tab = new Tab();
		tab.setLabel(Msg.getMsg(Env.getCtx(), "Credits"));
		tab.setParent(tabs);
		tabPanel = createCredit();
		tabPanel.setParent(tabPanels);

		//Info
		tab = new Tab();
		tab.setLabel(Msg.getMsg(Env.getCtx(), "Vision & Valuses"));
		tab.setParent(tabs);
		tabPanel = createInfo();
		tabPanel.setParent(tabPanels);

		//Trace
		tab = new Tab();
		tab.setLabel("Errors");
		tabLog = tab;
		tab.setParent(tabs);
		tabPanel = createTrace();
		tabPanel.setParent(tabPanels);

		Hbox hbox = new Hbox();
		hbox.setParent(layout);
		hbox.setPack("end");
		hbox.setWidth("100%");
		Button btnOk = new Button();
		btnOk.setImage("/images/Ok24.png");
		btnOk.addEventListener(Events.ON_CLICK, this);
		btnOk.setParent(hbox);

		this.setBorder("normal");
	}

	private Tabpanel createTrace() {
		Tabpanel tabPanel = new Tabpanel();
		Vbox vbox = new Vbox();
		vbox.setParent(tabPanel);
		vbox.setWidth("100%");
		vbox.setHeight("100%");

		Hbox hbox = new Hbox();
		bErrorsOnly = new Checkbox();
		bErrorsOnly.setLabel(Msg.getMsg(Env.getCtx(), "ErrorsOnly"));
		//default only show error
		bErrorsOnly.setChecked(true);
		bErrorsOnly.addEventListener(Events.ON_CHECK, this);
		hbox.appendChild(bErrorsOnly);
		btnDownload = new Button(Msg.getMsg(Env.getCtx(), "SaveFile"));
		btnDownload.addEventListener(Events.ON_CLICK, this);
		hbox.appendChild(btnDownload);
		btnErrorEmail = new Button(Msg.getMsg(Env.getCtx(), "SendEMail"));
		btnErrorEmail.addEventListener(Events.ON_CLICK, this);
		hbox.appendChild(btnErrorEmail);
		vbox.appendChild(hbox);

		Vector<String> columnNames = CLogErrorBuffer.get(true).getColumnNames(Env.getCtx());

		logTable = new Listbox();
		ListHead listHead = new ListHead();
		listHead.setParent(logTable);
		listHead.setSizable(true);
		for (Object obj : columnNames) {
			ListHeader header = new ListHeader(obj.toString());
			header.setWidth("100px");
			listHead.appendChild(header);
		}

		vbox.appendChild(logTable);
		logTable.setWidth("480px");
		logTable.setHeight("310px");
		logTable.setVflex(false);

		updateLogTable();

		return tabPanel;
	}

	private void updateLogTable() {
		Vector<Vector<Object>> data = CLogErrorBuffer.get(true).getLogData(bErrorsOnly.isChecked());
		SimpleListModel model = new SimpleListModel(data);
		model.setMaxLength(new int[]{0, 0, 0, 200, 0, 200});
		logTable.setItemRenderer(model);
		logTable.setModel(model);
		if (bErrorsOnly.isSelected())
			tabLog.setLabel(Msg.getMsg(Env.getCtx(), "Errors") + " (" + data.size() + ")");
		else
			tabLog.setLabel(Msg.getMsg(Env.getCtx(), "TraceInfo") + " (" + data.size() + ")");
	}

	private Tabpanel createInfo() {
		Tabpanel tabPanel = new Tabpanel();
//		Div div = new Div();
//		div.setParent(tabPanel);
//		div.setHeight("100%");
//		div.setStyle("overflow: auto;");
//		Pre pre = new Pre();
//		pre.setParent(div);
		Vbox vbox = new Vbox();
		vbox.setWidth("100%");
		vbox.setHeight("100%");
		vbox.setAlign("center");
		vbox.setPack("center");
		vbox.setParent(tabPanel);

		Image image = new Image(ThemeManager.getSmallLogo());
		image.setParent(vbox);

		Text text = new Text(Adempiere.getSubtitle());
		text.setParent(vbox);
		
		Separator separator = new Separator();
		separator.setParent(vbox);
		
		text = new Text("------------------VISION--------------------");
		text.setParent(vbox);
		separator = new Separator();
		separator.setParent(vbox);
		StringBuffer sb = new StringBuffer();
		StringBuffer sb1 = new StringBuffer();
		
		sb.append("Alkem is working towards becoming one of the top 5 pharmaceutical companies in India by 2014. Alkem further wishes to achieve this milestone through organic growth as well as through its affiliations with international associations, partnerships and strategic alliances.").append("\n");
		sb.append("Alkem’s vision, as an organization, is to achieve value driven leadership through consistent hard work in developing enduring quality products, marked by exceptional service.").append("\n");
	
		sb1.append("The belief that quality healthcare services have no boundaries in a global world drives Alkem to deliver the best healthcare products in its markets. A ‘People-First Policy’ ensures that Alkem meets and surpasses the evolving expectations of its customers, at all times.").append("\n");
		sb1.append("Alkem is founded on its DNA of - Passion, Curiosity and Accountability.").append("\n");
		sb1.append("Passion: The enthusiasm to complete a task encourages and enables the team to contribute significantly.").append("\n");
		sb1.append("Curiosity: An inquiring mind, vital in the field of research, distinguishes the team, helping grow the healthcare product development to higher levels.").append("\n");
		sb1.append("Accountability: While Curiosity and Passion pushes the team into the unexplored healthcare space, Accountability anchors the team in end-to-end engagement, from inception to roll out.").append("\n");
		text = new Text(sb.toString());
		text.setParent(vbox);

		separator = new Separator();
		separator.setParent(vbox);
		text = new Text("------------------VALUES--------------------");
		text.setParent(vbox);
		separator = new Separator();
		separator.setParent(vbox);
		
		 text = new Text(sb1.toString());
			text.setParent(vbox);
		return tabPanel;
	}

	private Tabpanel createCredit() {
		Tabpanel tabPanel = new Tabpanel();
		Vbox vbox = new Vbox();
		vbox.setParent(tabPanel);
		vbox.setWidth("100%");
		Hbox hbox = new Hbox();
		hbox.setParent(vbox);
		ToolBarButton link = new ToolBarButton();
		link.setImage("images/abtalkem.png");
		link.setParent(hbox);
		link.setHref("http://www.alkemlabs.com");
		link.setTarget("_blank");
		Label label= new Label("© 2013-2015 Alkem Document Management Software");
		label.setParent(hbox);

		Separator separator = new Separator();
		separator.setParent(vbox);

		Div div = new Div();
		div.setParent(vbox);
		div.setWidth("100%");
		Label caption = new Label("Sponsors");
		caption.setStyle("font-weight: bold;");
		div.appendChild(caption);
		separator = new Separator();
		separator.setBar(true);
		separator.setParent(div);
		Vbox content = new Vbox();
		content.setWidth("100%");
		content.setParent(div);
		link = new ToolBarButton();
		link = new ToolBarButton();

		link.setLabel("Alkem Document Management Software");
		link.setHref("http://www.alkemlabs.com/");
		link.setTarget("_blank");
		link.setParent(content);


		link.setLabel("NovelERP Solutions Pvt. Ltd.");
		link.setHref("http://www.novelerp.com/");
		link.setTarget("_blank");
		link.setParent(content);

		
		separator = new Separator();
		separator.setParent(vbox);

		div = new Div();
		div.setParent(vbox);
		div.setWidth("100%");
		caption = new Label("Contributors");
		caption.setStyle("font-weight: bold;");
		div.appendChild(caption);
		separator = new Separator();
		separator.setBar(true);
		separator.setParent(div);
		content = new Vbox();
		content.setWidth("100%");
		content.setParent(div);

		link = new ToolBarButton();
		link.setLabel("Pankaj Singh");
		link.setHref("http://www.novelerp.com/");
		link.setTarget("_blank");
		link.setParent(content);

		link = new ToolBarButton();
		link.setLabel("Vijay Yelwatker");
		link.setHref("http://www.alkemlabs.com/");
		link.setTarget("_blank");
		link.setParent(content);


		link = new ToolBarButton();
		link.setLabel("Suraj Tripathi");
		link.setHref("http://www.alkemlabs.com/");
		link.setTarget("_blank");
		link.setParent(content);


		link = new ToolBarButton();
		link.setLabel("Rupesh Joshi");
		link.setHref("http://www.novelerp.com/");
		link.setTarget("_blank");
		link.setParent(content);

	

		return tabPanel;
	}

	private Tabpanel createAbout() {
		Tabpanel tabPanel = new Tabpanel();

		Vbox vbox = new Vbox();
		vbox.setWidth("100%");
		vbox.setHeight("100%");
		vbox.setAlign("center");
		vbox.setPack("center");
		vbox.setParent(tabPanel);

		Image image = new Image(ThemeManager.getSmallLogo());
		image.setParent(vbox);

		Text text = new Text(Adempiere.getSubtitle());
		text.setParent(vbox);
		
		Separator separator = new Separator();
		separator.setParent(vbox);

		text = new Text(Adempiere.getVersion());
		text.setParent(vbox);

		separator = new Separator();
		separator.setParent(vbox);
		
//		ToolBarButton link = new ToolBarButton();
//		link.setLabel("Alkem Document Management");
//		link.setHref("http://alkemlabs.com/adempiere");
//		link.setTarget("_blank");
//		link.setParent(vbox);
		StringBuffer sb = new StringBuffer();
		sb.append("Established in 1973 and headquartered in Mumbai, India, Alkem began operations as a health care marketing company. Alkem has since grown to become India’s seventh largest pharmaceutical company with sales revenues exceeding USD 500 million with a CAGR of 16% over the last five years.").append("\n");
		sb.append("Alkem, one of the India’s leading generic and specialty pharmaceutical companies, is a significant player in the branded drugs and generic drugs space. With a wide ranging presence across acute and chronic therapeutic segments with substantial brand share in the Central Nervous System, Gastro-intestinal, Anti-diabetic, Anti-osteoporosis, Anti-malarial, Cardiovascular, Immunosuppressants, and Nutraceutical segments, Alkem leads the Indian market in the Anti-infective and Pain Management segments.").append("\n");
		sb.append("Alkem’s portfolio of 800 brands in India, with 14 of the brands featured among the top 300 brands in India, is reflective of its strong brand recognition and marketing expertise.").append("\n");
		sb.append("Alkem’s 7 world-class manufacturing facilities are compliant with the standards of global regulatory authorities including the US FDA, UK MHRA,  MCC.").append("\n");
		sb.append("Alkem, through its international operations team, markets branded and non-branded generic drugs in 48 countries globally.").append("\n");
		text = new Text(sb.toString());
		text.setParent(vbox);

		return tabPanel;
	}

	public void onEvent(Event event) throws Exception {
		if (event.getTarget() == bErrorsOnly) {
			this.updateLogTable();
		}
		else if (event.getTarget() == btnDownload)
			downloadLog();
		else if (event.getTarget() == btnErrorEmail)
			cmd_errorEMail();
		else if (event instanceof SizeEvent)
			doResize((SizeEvent)event);
		else if (Events.ON_CLICK.equals(event.getName()))
			this.detach();
	}

	private void doResize(SizeEvent event) {
		int width = Integer.parseInt(event.getWidth().substring(0, event.getWidth().length() - 2));
		int height = Integer.parseInt(event.getHeight().substring(0, event.getHeight().length() - 2));

		tabbox.setWidth((width - 20) + "px");
		tabbox.setHeight((height - 70) + "px");

		tabPanels.setWidth((width - 20) + "px");

		logTable.setHeight((height - 140) + "px");
		logTable.setWidth((width - 30) + "px");
	}

	private void downloadLog() {
		String log = CLogErrorBuffer.get(true).getErrorInfo(Env.getCtx(), bErrorsOnly.isChecked());
		AMedia media = new AMedia("trace.log", null, "text/plain", log.getBytes());
		Filedownload.save(media);
	}

	/**
	 * 	EMail Errors
	 */
	private void cmd_errorEMail()
	{
		new WEMailDialog(this,
			"EMail Trace",
			MUser.get(Env.getCtx()),
			"",			//	to
			"AlkemDMS Trace Info",
			CLogErrorBuffer.get(true).getErrorInfo(Env.getCtx(), bErrorsOnly.isSelected()),
			null);

	}	//	cmd_errorEMail
}
