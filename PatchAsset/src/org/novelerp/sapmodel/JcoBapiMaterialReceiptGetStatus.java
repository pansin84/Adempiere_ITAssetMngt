package org.novelerp.sapmodel;

import com.sap.mw.jco.*; //The JCO
import java.util.*;
import java.text.*;

import org.adempiere.exceptions.AdempiereException;
import org.novelerp.sapconection.SapConnection;

public class JcoBapiMaterialReceiptGetStatus {
	private IRepository mRepository;
	private JCO.Function jcoFunction;

	// Return parameters
	private String oDocDate;
	private String oPurchNo;
	private String PoDate;
	private String CERNO;
	private String CERdate;
	private String SAPGRNDate;
	private String Vendorname;
	private String VendorCode;
	private String Grnby;
	private String Delchallan;
	private String challanDate;
	private String vendorloc;
	// private String oDlvBlock;
	private String oBapiReturn;
	private String oBapiReturnType;
	private String[][] itemData;

	public JcoBapiMaterialReceiptGetStatus() {
		sapConnection = new SapConnection();
	}

	public void GetStatus(String iSalesDocument)

	throws SalesDocumentEmptyException, RepositoryNotCreatedException,
			FunctionNotCreatedException, InvalidInputParameterException,
			ExecuteException, GetStatusException, BapiReturnException

	{ // Date format used for date fields
		DateFormat dateFormatter;
		Locale dkLocale = new Locale("dk", "DK");
		dateFormatter = DateFormat
				.getDateInstance(DateFormat.DEFAULT, dkLocale);

		// Salesdocument number is empty
//		if (iSalesDocument.length() == 0)
//			throw new SalesDocumentEmptyException();

		// ---------------------------------------------------------
		// Create metadata with JCO Repository
		// ---------------------------------------------------------
		try {
			mRepository = new JCO.Repository("hFrank",
					SapConnection.mConnection);
		} catch (Exception mException) { // mException.printStackTrace();
			throw new RepositoryNotCreatedException();
		}

		// ---------------------------------------------------------
		// Get a function template for BAPI_SALESORDER_GETSTATUS
		// from the repository and create a function
		// ---------------------------------------------------------
		try { // Get a function template from the repository
		// IFunctionTemplate ftemplate =
		// mRepository.getFunctionTemplate("BAPI_SALESORDER_GETSTATUS");
			IFunctionTemplate ftemplate = mRepository
					.getFunctionTemplate("ZBAPI_GNR");

			// Create a function from the template
			jcoFunction = new JCO.Function(ftemplate);
			//System.out.println("ftemplat= " + ftemplate);
			if (jcoFunction == null)
				throw new FunctionNotCreatedException();
		} catch (Exception mException) { // mException.printStackTrace();
			throw new FunctionNotCreatedException();
		}

		// ---------------------------------------------------------
		// Set the SALESDOCUMENT import parameter
		// ---------------------------------------------------------
		try {
			JCO.Field SalesDocumentField = jcoFunction.getImportParameterList()
					.getField("GR_NO");
			// JCO.Field SalesDocumentField =
			// jcoFunction.getImportParameterList().getField("SALESDOCUMENT");
			SalesDocumentField.setValue(iSalesDocument);
		} catch (Exception mException) {
			throw new InvalidInputParameterException();
		}

		// ---------------------------------------------------------
		// Execute function
		// ---------------------------------------------------------

		try {
			SapConnection.mConnection.execute(jcoFunction);
		} catch (Exception mException) {
			// mException.printStackTrace();
			throw new ExecuteException();
			// throw new BapiReturnException();
		}

		// ---------------------------------------------------------
		// Get sales order status. Item info is saved in the
		// array itemData.
		// ---------------------------------------------------------

		try {
			JCO.Table jcoStatusInfo = jcoFunction.getTableParameterList()
					.getTable("IT_FINAL");

			// JCO.Table jcoStatusInfo =
			// jcoFunction.getTableParameterList().getTable("STATUSINFO");
			int NumRows = jcoStatusInfo.getNumRows();
			itemData = new String[NumRows][5];
			
			for (int i = 0; i < NumRows; i++) {
				jcoStatusInfo.setRow(i);

				// These fields are header fields and it is only necessary to
				// read info for the first item

				if (i == 0) {

					System.out.println("NumRows i=0-- "+NumRows);
					
					if (jcoStatusInfo.getField("BUDAT").getDate() != null) {
						oDocDate = dateFormatter.format(jcoStatusInfo.getField(
								"BUDAT").getDate());
//						System.out.println("BUDAT "
//								+ dateFormatter.format(jcoStatusInfo.getField(
//										"BUDAT").getDate()));
					}

					if (jcoStatusInfo.getField("EBELN").getValue().toString() != null) {
						oPurchNo = jcoStatusInfo.getField("EBELN").getValue()
								.toString();
//						System.out.println("oPurchNo "
//								+ jcoStatusInfo.getField("EBELN").getValue()
//										.toString());
					}

					if (jcoStatusInfo.getField("ERDAT").getDate() != null) {
						PoDate = dateFormatter.format(jcoStatusInfo.getField(
								"ERDAT").getDate());
//						System.out.println("POdate "
//								+ dateFormatter.format(jcoStatusInfo.getField(
//										"ERDAT").getDate()));
					}

					if (jcoStatusInfo.getField("CER").getValue().toString() != null) {
						CERNO = jcoStatusInfo.getField("CER").getValue()
								.toString();
//						System.out.println("CERNO "
//								+ jcoStatusInfo.getField("CER").getValue()
//										.toString());
					}

					if (jcoStatusInfo.getField("USNAM").getValue().toString() != null) {
						Grnby = jcoStatusInfo.getField("USNAM").getValue()
								.toString();
//						System.out.println("USNAM "
//								+ jcoStatusInfo.getField("USNAM").getValue()
//										.toString());
					}

					if (jcoStatusInfo.getField("CERDATE").getDate() != null) {
						CERdate = dateFormatter.format(jcoStatusInfo.getField(
								"CERDATE").getDate());
//						System.out.println("CERDATE "
//								+ dateFormatter.format(jcoStatusInfo.getField(
//										"CERDATE").getDate()));
					}

					if (jcoStatusInfo.getField("CPUDT").getDate() != null) {
						SAPGRNDate = dateFormatter.format(jcoStatusInfo
								.getField("CPUDT").getDate());
//						System.out.println(dateFormatter.format(jcoStatusInfo
//								.getField("CPUDT").getDate()));
					}

					if (jcoStatusInfo.getField("BLDAT1").getDate() != null) {
//						challanDate = jcoStatusInfo.getField("challanDate")
//								.getValue().toString();
//						System.out.println("challanDatechallanDate== "+challanDate);
						challanDate = dateFormatter.format(jcoStatusInfo
								.getField("BLDAT1").getDate());
//						System.out.println(dateFormatter.format(jcoStatusInfo
//								.getField("BLDAT").getDate()));
					}

					if (jcoStatusInfo.getField("S_NAME").getValue().toString() != null) {
						Vendorname = jcoStatusInfo.getField("S_NAME")
								.getValue().toString();
						System.out.println("Vendorname "
								+ jcoStatusInfo.getField("S_NAME").getValue()
										.toString());
					}

					if (jcoStatusInfo.getField("LIFNR").getValue().toString() != null) {
						VendorCode = jcoStatusInfo.getField("LIFNR").getValue()
								.toString();
						System.out.println("VendorCode "
								+ jcoStatusInfo.getField("LIFNR").getValue()
										.toString());
					}

					if (jcoStatusInfo.getField("ORT01").getValue().toString() != null) {
						vendorloc = jcoStatusInfo.getField("ORT01").getValue()
								.toString();
						System.out.println("ORT01 "
								+ jcoStatusInfo.getField("ORT01").getValue()
										.toString());
					}

					if (jcoStatusInfo.getField("XBLNR_MKPF").getValue()
							.toString() != null) {
						Delchallan = jcoStatusInfo.getField("XBLNR_MKPF")
								.getValue().toString();
						System.out.println("XBLNR_MKPF "
								+ jcoStatusInfo.getField("XBLNR_MKPF")
										.getValue().toString());
					}

					// oDlvBlock =
					// jcoStatusInfo.getField("DLV_BLOCK").getValue().toString();

				}
				System.out.println("NumRows-- "+NumRows);
				
				if (jcoStatusInfo.getField("MATNR").getValue().toString() != null)

				{
					itemData[i][0] = jcoStatusInfo.getField("MATNR").getValue()
							.toString();
					System.out.println("material code"
							+ jcoStatusInfo.getField("MATNR").getValue()
									.toString());
				}

				if (jcoStatusInfo.getField("MAKTX").getValue().toString() != null)

				{
					itemData[i][1] = jcoStatusInfo.getField("MAKTX").getValue()
							.toString();

					System.out.println("material name"
							+ jcoStatusInfo.getField("MAKTX").getValue()
									.toString());
				}

				if (jcoStatusInfo.getField("ATWTB").getValue().toString() != null) {
					itemData[i][2] = jcoStatusInfo.getField("ATWTB").getValue()
							.toString();

					System.out.println("ATWTB"
							+ jcoStatusInfo.getField("ATWTB").getValue()
									.toString());
				}

				if (jcoStatusInfo.getField("ATWRT").getValue().toString() != null) {
					itemData[i][3] = jcoStatusInfo.getField("ATWRT").getValue()
							.toString();

					System.out.println("ATWRT"
							+ jcoStatusInfo.getField("ATWRT").getValue()
									.toString());
				}

				if (jcoStatusInfo.getField("ERFMG").getValue().toString() != null)

				{

					itemData[i][4] = jcoStatusInfo.getField("ERFMG").getValue()
							.toString();
					System.out.println("quantity "
							+ jcoStatusInfo.getField("ERFMG").getValue()
									.toString());

				}
			}
		} catch (Exception mException) {
			
			mException.printStackTrace();

			throw new GetStatusException();

		}

		// ---------------------------------------------------------
		// Get BAPIRETURN
		// ---------------------------------------------------------

		try {

			JCO.Structure jcoBapiReturn = jcoFunction.getExportParameterList()
					.getStructure("RETURN");
			//System.out.println("///////////////");
			
			
			oBapiReturn = jcoBapiReturn.getField("TYPE").getValue() + " "
					+ jcoBapiReturn.getField("CODE").getValue() + " "
					+ jcoBapiReturn.getField("MESSAGE").getValue();

			oBapiReturnType = jcoBapiReturn.getField("TYPE").getValue()
					.toString();

//			if (oBapiReturn != null) {
//
//				//throw new AdempiereException(oBapiReturn);
//			}
		}

		catch (Exception mException) {

			throw new BapiReturnException();
		}

	}

	// *******************************************************************
	// Classes that returns status information
	// *******************************************************************

	public String getDocDate() {
		return oDocDate;
	}

	public String getoBapiReturnType() {
		return oBapiReturnType;
	}

	public String getPurchNo() {
		return oPurchNo;
	}

	public String getPoDate() {
		return PoDate;
	}

	public String getCERNO() {
		return CERNO;
	}

	public String getCERdate() {
		return CERdate;
	}

	public String getSAPGRNDate() {
		return SAPGRNDate;
	}

	public String getVendorname() {
		return Vendorname;
	}

	public String getVendorCode() {
		return VendorCode;
	}

	public String getvendorloc() {
		return vendorloc;
	}

	public String getGrnby() {
		return Grnby;
	}

	public String getDelchallan() {
		return Delchallan;
	}

	public String getchallanDate() {
		return challanDate;
	}

	// public String getDlvBlock()
	// { return oDlvBlock; }

	public String getBapiReturn() {
		return oBapiReturn;
	}

	public String[][] getItems() {
		return itemData;
	}

	public int getNumItems() {
		return itemData.length;

	}

	// *******************************************************************
	// Exception classes
	// *******************************************************************
	class SalesDocumentEmptyException extends Exception {
		public SalesDocumentEmptyException() {
			super("You must enter a sales document");
		}
	}

	class RepositoryNotCreatedException extends Exception {
		public RepositoryNotCreatedException() {
			super("Repository object could not be created");
		}
	}

	class FunctionNotCreatedException extends Exception {
		public FunctionNotCreatedException() {
			super("Function could not be created");
		}
	}

	class InvalidInputParameterException extends Exception {
		public InvalidInputParameterException() {
			super("Invalid parameter");
		}
	}

	class ExecuteException extends Exception {
		public ExecuteException() {
			super("Execution failed");
		}
	}

	class GetStatusException extends Exception {
		public GetStatusException() {
			super("Error when reading status information");
		}
	}

	class BapiReturnException extends Exception {

		public BapiReturnException() {
			super("Error when reading BAPI return information");
		}
	}

}
