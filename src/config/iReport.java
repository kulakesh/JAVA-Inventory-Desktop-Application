package config;


import java.io.File;
import java.util.*;

import javax.swing.JOptionPane;

import net.sf.jasperreports.view.*;
import net.sf.jasperreports.engine.*;

public class iReport{
	JasperPrint JPrint;
	public String iPath="";
	public String iParam="";
	public void setPath(String iPth){
		iPath=iPth;
	}
	public void setParam(String iPrm){
		iParam+=(iParam.equals("") ? iPrm : "#" +iPrm);
	}
	public void ShowReport(){
		if(iPath.equals("")){
			System.err.println("Report Path is Empty");
		}
		try {
			HashMap hm = new HashMap();
			String[] nParam,sParam;
			nParam=iParam.split("#");
			if(!iParam.equals("")){
				for(int i=0;i<nParam.length;i++){
					sParam=nParam[i].split("=");
					hm.put(sParam[0],sParam[1]);
				}
			}
			JPrint=JasperFillManager.fillReport(config.Global.AppPath + File.separator + "rpt"+File.separator+iPath,hm,config.con.cnn);
			JasperViewer.viewReport(JPrint,false);
		} catch (Exception e) {
			System.err.println("Error in ShowReport: "+e.getMessage());
		}
	}
}
