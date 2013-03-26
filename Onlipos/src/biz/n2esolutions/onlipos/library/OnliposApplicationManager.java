package biz.n2esolutions.onlipos.library;

import android.app.Application;   
import java.util.ArrayList; 
import java.util.HashMap;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class OnliposApplicationManager extends Application {
	
	protected ArrayList<HashMap<String, String>> orderList = new ArrayList<HashMap<String, String>>();
	protected String item_id;
	protected String item_name;
	protected String order_id;
	protected String order_name;
	protected String order_course;
	protected String order_QTY;
	protected String item_price;
	protected String total_price;
	
	public int reOrder = 0;
	
	public void onCreate(){
		super.onCreate();
	}
	
	public void setOrder(String id, String itemName, String name, String course, String QTY, String price) {
		
		this.item_id = "'" + id + "'";
		this.item_name = "'" + itemName + "'";
		this.order_name = "'" + name + "'";
		this.order_course = "'" + course + "'";
		this.order_QTY = QTY;
		this.item_price = price;
	
		int qty = Integer.parseInt(this.order_QTY.trim());
		double it_price = Double.parseDouble(this.item_price.trim());
		
		double total = qty * it_price;
				
		String total_price = Double.toString(total);
		
		HashMap<String, String> map = new HashMap<String, String>();
		
		map.put("'item_id'", this.item_id);
		map.put("'item_name'", this.item_name);
		map.put("'order_name'", this.order_name);
		map.put("'order_course'", this.order_course);
		map.put("'order_QTY'", "'"+this.order_QTY +"'");
		map.put("'item_price'", "'"+this.item_price+"'");
		map.put("'item_total'", "'"+total_price+"'");
		
		orderList.add(map);
		
	}
	
	public ArrayList<HashMap<String, String>> getOrder() {
		return orderList;
	}
	
	public ArrayList<NameValuePair> getNameValuePair(String itemList) {
		
		ArrayList<NameValuePair> nameValuePairs  = new ArrayList<NameValuePair>();
		
		nameValuePairs.add(new BasicNameValuePair("items", itemList));
		
		return nameValuePairs;
	}
	
	public String setURL(String URL) {
		
		String host ="http://" + URL +"/OnliposAPI/index/insert";
		
		return host;
	}
	
	// empty the orderlist
	
	public void emptyList() {
		orderList.clear();
	}
	
	// reset the number of order
	public void resetReOrder() {
		this.reOrder = 0;
	}
}
