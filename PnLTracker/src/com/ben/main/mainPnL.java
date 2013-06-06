package com.ben.main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONObject;

//test222


public class mainPnL {

		 ResultSet rs = null;
		 Connection con = null;
		 Statement st = null;
	   
	   
	    String url = "jdbc:mysql://localhost:3306/Stocks";
	    String user = "root";
	    String password = "root";
	public static void main(String[] args) throws SQLException  {
	

		mainPnL m = new mainPnL();
		try {
		//	m.Value_Line_json();
			
			m.start();
		} catch (SQLException e) {
			m.LogOutput(e.toString());
		}
		
		
	}
	
	
	public void Value_Line_json() throws SQLException
	{
		JSONObject obj=new JSONObject();
		  JSONObject obj_cols_1=new JSONObject();
		  JSONObject obj_cols_2=new JSONObject();
		  obj_cols_1.put("id","");
		  obj_cols_1.put("label","BAC");
		  obj_cols_1.put("type","string");
		  
		  obj_cols_2.put("id","");
		  obj_cols_2.put("label","$Value");
		  obj_cols_2.put("type","number");
		  
		  LinkedList l_cols = new LinkedList();
		  
		  l_cols.add(obj_cols_1);
		  l_cols.add(obj_cols_2);
		  obj.put("cols", l_cols);
		  try
		  {
			  
		rs = LoadData("Select date  from PNL where Ticker = 'BAC'");
		  }
		  catch (Exception e)
		  {
			  System.out.println(e.toString());
			  
			  
		  }
		String Ticker;
		String LastPx;
		String Qty;
		String AvgPx;
		Double Value;
		GoogleScrape gs = new GoogleScrape();
		List<String> Date_list = new ArrayList<String>();
		 LinkedList l_final = new LinkedList();
		 
		 
		while (rs.next())
		
		{
			//Ticker = (rs.getString(1));
			Date_list.add(rs.getString(1));
		}
		for (String name : Date_list)
		{	
		Ticker = ("BAC");
	//	LastPx = gs.getLast(Ticker);
  //		Qty = LoadData_str("Select Quantity from CurrentHoldings where Ticker = '"+Ticker+"'");
	//	AvgPx = LoadData_str("Select AvgPx from CurrentHoldings where Ticker = '"+Ticker+"'");
	//	Value = Double.valueOf(LastPx)*Double.valueOf(Qty);
				  
		  JSONObject obj2=new JSONObject();
		  JSONObject obj3=new JSONObject();
		  JSONObject obj4=new JSONObject();
		  JSONObject obj_col=new JSONObject();
		  
		  obj3.put("v", name);
		  obj3.put("f", null);
		  obj4.put("v",Ticker);
		  obj4.put("f", null);
		 
		  LinkedList l1 = new LinkedList();
		  LinkedHashMap m1 = new LinkedHashMap();
		  l1.add(obj3);
		  l1.add(obj4);
		  m1.put("c",l1);
		  
		  l_final.add(m1);
		}
		obj.put("rows",l_final);
		 System.out.println(obj);
	//	  return obj.toJSONString();
		
	}
	private void json() throws SQLException
	{
		JSONObject obj=new JSONObject();
		  JSONObject obj_cols_1=new JSONObject();
		  JSONObject obj_cols_2=new JSONObject();
		  obj_cols_1.put("id","");
		  obj_cols_1.put("label","Topping");
		  obj_cols_1.put("type","string");
		  
		  obj_cols_2.put("id","");
		  obj_cols_2.put("label","Slices");
		  obj_cols_2.put("type","number");
		  
		  LinkedList l_cols = new LinkedList();
		  
		  l_cols.add(obj_cols_1);
		  l_cols.add(obj_cols_2);
		  obj.put("cols", l_cols);
		rs = LoadData("Select * from CurrentHoldings");
		
		String Ticker;
		String LastPx;
		String Qty;
		String AvgPx;
		
		List<String> Tickers = new ArrayList<String>();
		 LinkedList l_final = new LinkedList();
		while (rs.next())
		{
			//Ticker = (rs.getString(1));
			Tickers.add(rs.getString(1));
		}
		for (String name : Tickers)
		{	
		Ticker = (name);
  		Qty = LoadData_str("Select Quantity from CurrentHoldings where Ticker = '"+Ticker+"'");
		AvgPx = LoadData_str("Select AvgPx from CurrentHoldings where Ticker = '"+Ticker+"'");
		
				  
		  JSONObject obj2=new JSONObject();
		  JSONObject obj3=new JSONObject();
		  JSONObject obj4=new JSONObject();
		  JSONObject obj_col=new JSONObject();
		  
		  

		  
		  obj3.put("v", Ticker);
		  obj3.put("f", null);
		  obj4.put("v",Qty);
		  obj4.put("f", null);
		 
		  LinkedList l1 = new LinkedList();
		  LinkedHashMap m1 = new LinkedHashMap();
		  l1.add(obj3);
		  l1.add(obj4);
		  m1.put("c",l1);
		  
		  l_final.add(m1);
		}
		obj.put("rows",l_final);
		  //obj_col.put("rows", m1);
//		  JSONObject obj6=new JSONObject();
//		  JSONObject obj5=new JSONObject();
//		  obj5.put("v", "pepper");
//		  obj5.put("f", null);
//		  obj6.put("v",6);
//		  obj6.put("f", null);
//		  LinkedList l2 = new LinkedList();
//		  LinkedHashMap m2 = new LinkedHashMap();
//		  l2.add(obj5);
//		  l2.add(obj6);
//		  m2.put("c",l2);
		  
		 
	//	  l_final.add(m1);
		//  l_final.add(m2);
		  
		  
		  
		  
		  
		  
		  
		  
		  
		  System.out.print(obj);
		  
		  
		  
	}
	
	
	
	
	private void start() throws SQLException
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
	//	System.out.println(dateFormat.format(date));
		
		String Ticker;
		String LastPx;
		String Qty;
		String AvgPx;
		Double Pct;
		
		Double delta;
		
		GoogleScrape gs = new GoogleScrape();
		
		rs = LoadData("Select distinct Ticker from currentholdings");
		List<String> Tickers = new ArrayList<String>();
		while (rs.next())
		{
			Tickers.add(rs.getString(1));
		}
		for (String name : Tickers)
		{
			try
			{
		Ticker = (name);
  		LastPx = gs.getLast(name);
		Qty = LoadData_str("Select Quantity from currentholdings where Ticker = '"+Ticker+"'");
		AvgPx = LoadData_str("Select AvgPx from currentholdings where Ticker = '"+Ticker+"'");
		
		delta = (Double.valueOf(LastPx)-Double.valueOf(AvgPx))* Double.valueOf(Qty);
		
		Pct = ((Double.valueOf(LastPx) / Double.valueOf(AvgPx))-1)*100;
				
		  DecimalFormat df2 = new DecimalFormat("###.##");
	       
		
		
	//	String query = "insert into PnL values ('"+Ticker+"','"+LastPx+"','"+delta+"','"+dateFormat.format(date)+"')";
		ExecuteQuery("insert into pnl values ('"+Ticker+"','"+LastPx+"','"+Double.valueOf(df2.format(delta))+"','"+dateFormat.format(date)+"','"+Double.valueOf(df2.format(Pct))+"')");
			
			Bloomberg_scrape BS = new Bloomberg_scrape();
			String FX = BS.getFX();
			
			ExecuteQuery("insert into FX_Rate values ('"+dateFormat.format(date)+"','"+FX+"')");
			
			
			
			}
			catch (Exception e)
			{
				LogOutput(e.toString());
				
				
			}
		
		}
		
		
	}
	public String LoadData_str(String Message) throws SQLException
	{
		LogOutput(Message);
		 PreparedStatement pst = null;
		
	
         pst = con.prepareStatement(Message);
         rs = pst.executeQuery();
         rs.next();
    
		return rs.getString(1);
	}
	public ResultSet LoadData(String Message) throws SQLException
	{
		LogOutput(Message);
		 PreparedStatement pst = null;
		
		 con = DriverManager.getConnection(url, user, password);
	//	 st = con.createStatement();
    //     rs = st.executeQuery("SELECT VERSION()");	
    //     rs.next();
//		 System.out.println(rs.getString(1));
         pst = con.prepareStatement(Message);
         rs = pst.executeQuery();
		
      //   while (rs.next()) {
      //       System.out.print(rs.getString(1));
      //       System.out.print(": ");
         //    System.out.println(rs.getString(2));
      //   }
         
		return rs;
	}
	
	public void LogOutput(String Message)
	{
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		DateFormat dateFormat_log = new SimpleDateFormat("yyyy.MM.dd");
		Date date = new Date();
		System.out.println(dateFormat.format(date)+" : "+Message);
 		//System.out.printf("%D %R : ",date + Message);
	
		try {
			 
		
 
			File file = new File("/home/pi/logs/"+dateFormat_log.format(date)+".PiPnL.log.txt");
			//File file = new File("c:\\"+dateFormat_log.format(date)+".PiFinance.log.txt");
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
 
			FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(dateFormat.format(date)+" : "+Message+"\n");
			bw.close();
 
		
 
		} catch (IOException e) {
			System.out.println(e.toString());
		}
		
		
	}
	public void ExecuteQuery(String Message) throws SQLException
	{
		LogOutput(Message);
		
		
		
		PreparedStatement pst = null;
		 pst = con.prepareStatement(Message);
            pst.executeUpdate();
		
    
	}	
}
