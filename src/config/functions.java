package config;

import java.awt.Component;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class functions {
	public String GetNextID(String table){
		// Generates MAXIMUM number of 'idno' field of input table
		String idno = null;
		try{
			ResultSet rs1 = new RecordSet().Open("SELECT MAX(idno) FROM "+table);
			if(rs1.next())
				idno=String.valueOf(Integer.parseInt(rs1.getString(1))+1);
			else
				idno="100";
			
			rs1.close();
		}catch (Exception e){
			//TODO: code here
		}
		return idno;
	}
	
	public String GetNextID(String prefix,String table,String field,Integer start){
		Integer sudoIDNo = null;
		try{
			String mQry="SELECT MAX( CAST( RIGHT(" + field + ",LENGTH(" + field + ")-" + (prefix.length()) + ") AS SIGNED ) ) FROM " + table;
			//String mQry="SELECT MAX( CAST( SUBSTR(" + field + ",LENGTH('" + prefix + "')+1) AS INT ) ) FROM " + table;
			ResultSet rs1 = new RecordSet().Open(mQry);
			if(rs1.next()){
				if(rs1.getString(1)==null){
					sudoIDNo=start;
				}else{
					sudoIDNo=Integer.parseInt(rs1.getString(1))+1;
				}
			}else{
				sudoIDNo=start;
			}
			rs1.close();
		}catch (SQLException e){
			System.out.println("function.java>GetNextID - " + e);
		}
		return prefix + String.valueOf(sudoIDNo);
	}
	
	public String GetNextID(String prefix,String table,String field){
		return GetNextID(prefix,table,field,1000);
	}
	
	public void SelectAll(JTextField input){
		// Highlight all the text in a jTextField
		Integer l = input.getText().length();
		input.setSelectionStart(0);
		input.setSelectionEnd(l);
		input.requestFocusInWindow();
	}
	
	public boolean isNumeric(String input){
		// Returns whether a string is Numeric or not
	   try{
	      Double.parseDouble(input);
	      return true;
	   }
	   catch( Exception e){
	      return false;  
	   }
	}
	
	public void SelectTableRow(JTable selTbl,Integer selColumn,String selStr){
		String rowValue = "";
		selTbl.clearSelection();
		if (selStr.equals("")){
			scrollToCenter(selTbl,0,selColumn);
			return;
		}
		for(int i=0;i<selTbl.getRowCount();i++){
			rowValue = selTbl.getValueAt(i, selColumn).toString().toUpperCase();
			if(rowValue.substring(0,Math.min(selStr.length(), rowValue.length())).equals(selStr.toUpperCase())){
				selTbl.setRowSelectionInterval(i, i);
				scrollToCenter(selTbl,i,selColumn);
				break;
			}
		}
	}
	
	public static void scrollToCenter(JTable table, int rowIndex, int vColIndex) {
	    if (!(table.getParent() instanceof JViewport)) {
	      return;
	    }
	    JViewport viewport = (JViewport) table.getParent();
	    Rectangle rect = table.getCellRect(rowIndex, vColIndex, true);
	    Rectangle viewRect = viewport.getViewRect();
	    rect.setLocation(rect.x - viewRect.x, rect.y - viewRect.y);

	    int centerX = (viewRect.width - rect.width) / 2;
	    int centerY = (viewRect.height - rect.height) / 2;
	    if (rect.x < centerX) {
	      centerX = -centerX;
	    }
	    if (rect.y < centerY) {
	      centerY = -centerY;
	    }
	    rect.translate(centerX, centerY);
	    viewport.scrollRectToVisible(rect);
	  }
	
	public void ClearTable(JTable input){
	    while (input.getRowCount() > 0) {
	        ((DefaultTableModel) input.getModel()).removeRow(0);
	    }
	}
	public void numOnly(Object objSource){
		((Component) objSource).addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				String filterStr = "0123456789.";
				char c = (char)e.getKeyChar();
				if(filterStr.indexOf(c)<0){
					e.consume();
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {}
			
			@Override
			public void keyPressed(KeyEvent e) {}
		});
	}
	public boolean isLoadedFrame(JInternalFrame mFrame, JInternalFrame frame[]){
		for(int i = 0; i < frame.length; i++)
	        if(frame[i].getTitle().toString().equals(mFrame.getTitle().toString()))
	            return true;
	        return false;
	}
	
	public String cDate(Calendar vDate){
		java.text.SimpleDateFormat df1 = new java.text.SimpleDateFormat("dd-MM-yyyy");
		return df1.format(vDate.getTime());
	}
	
	public String cSqlDate(Calendar vDate){
		java.text.SimpleDateFormat df1 = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df1.format(vDate.getTime());
	}
	
	public String cSqlToDate(String vDate){
		String[] dt;
		dt=vDate.split("-");
		return dt[2]+"-"+dt[1]+"-"+dt[0];
	}
	
	public boolean isValidDate(String date,String frmt){	    
		try {
			new SimpleDateFormat(frmt).parse(date);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}
	
	public boolean isValidDate(String date){	    
		try {
			new SimpleDateFormat("dd-MM-yyyy").parse(date);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}
	
	public Calendar cDateCalender(String vDate){
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Calendar rCal = Calendar.getInstance();
		try {
			rCal.setTime(sdf.parse(vDate));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rCal;
	}
	public Calendar cDateCalender(String vDate,String dateFormat){
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		Calendar rCal = Calendar.getInstance();
		try {
			rCal.setTime(sdf.parse(vDate));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rCal;
	}
	public static String determineDateFormat(String dateString) {
		Map<String, String> DATE_FORMAT_REGEXPS = new HashMap<String, String>() {{
		    put("^\\d{8}$", "yyyyMMdd");
		    put("^\\d{1,2}-\\d{1,2}-\\d{4}$", "dd-MM-yyyy");
		    put("^\\d{4}-\\d{1,2}-\\d{1,2}$", "yyyy-MM-dd");
		    put("^\\d{1,2}/\\d{1,2}/\\d{4}$", "MM/dd/yyyy");
		    put("^\\d{4}/\\d{1,2}/\\d{1,2}$", "yyyy/MM/dd");
		    put("^\\d{1,2}\\s[a-z]{3}\\s\\d{4}$", "dd MMM yyyy");
		    put("^\\d{1,2}\\s[a-z]{4,}\\s\\d{4}$", "dd MMMM yyyy");
		    put("^\\d{12}$", "yyyyMMddHHmm");
		    put("^\\d{8}\\s\\d{4}$", "yyyyMMdd HHmm");
		    put("^\\d{1,2}-\\d{1,2}-\\d{4}\\s\\d{1,2}:\\d{2}$", "dd-MM-yyyy HH:mm");
		    put("^\\d{4}-\\d{1,2}-\\d{1,2}\\s\\d{1,2}:\\d{2}$", "yyyy-MM-dd HH:mm");
		    put("^\\d{1,2}/\\d{1,2}/\\d{4}\\s\\d{1,2}:\\d{2}$", "MM/dd/yyyy HH:mm");
		    put("^\\d{4}/\\d{1,2}/\\d{1,2}\\s\\d{1,2}:\\d{2}$", "yyyy/MM/dd HH:mm");
		    put("^\\d{1,2}\\s[a-z]{3}\\s\\d{4}\\s\\d{1,2}:\\d{2}$", "dd MMM yyyy HH:mm");
		    put("^\\d{1,2}\\s[a-z]{4,}\\s\\d{4}\\s\\d{1,2}:\\d{2}$", "dd MMMM yyyy HH:mm");
		    put("^\\d{14}$", "yyyyMMddHHmmss");
		    put("^\\d{8}\\s\\d{6}$", "yyyyMMdd HHmmss");
		    put("^\\d{1,2}-\\d{1,2}-\\d{4}\\s\\d{1,2}:\\d{2}:\\d{2}$", "dd-MM-yyyy HH:mm:ss");
		    put("^\\d{4}-\\d{1,2}-\\d{1,2}\\s\\d{1,2}:\\d{2}:\\d{2}$", "yyyy-MM-dd HH:mm:ss");
		    put("^\\d{1,2}/\\d{1,2}/\\d{4}\\s\\d{1,2}:\\d{2}:\\d{2}$", "MM/dd/yyyy HH:mm:ss");
		    put("^\\d{4}/\\d{1,2}/\\d{1,2}\\s\\d{1,2}:\\d{2}:\\d{2}$", "yyyy/MM/dd HH:mm:ss");
		    put("^\\d{1,2}\\s[a-z]{3}\\s\\d{4}\\s\\d{1,2}:\\d{2}:\\d{2}$", "dd MMM yyyy HH:mm:ss");
		    put("^\\d{1,2}\\s[a-z]{4,}\\s\\d{4}\\s\\d{1,2}:\\d{2}:\\d{2}$", "dd MMMM yyyy HH:mm:ss");
		}};
	    for (String regexp : DATE_FORMAT_REGEXPS.keySet()) {
	        if (dateString.toLowerCase().matches(regexp)) {
	            return DATE_FORMAT_REGEXPS.get(regexp);
	        }
	    }
	    return null; // Unknown format.
	}
}
