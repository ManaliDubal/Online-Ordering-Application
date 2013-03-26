package biz.n2esolutions.onlipos;

import java.util.ArrayList;  
import java.util.HashMap; 
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle; 
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class PrintReceiptActivity extends ListActivity {
	   TextView txt;
	   public double grandTotal = 0.00;
	   public TextView cname;
	   public TextView oNumber;
	   
	   @Override
	   public void onCreate(Bundle savedInstanceState) {
	       super.onCreate(savedInstanceState);
	       setContentView(R.layout.receiptmain);
	       
			// Hashmap for ListView
	    // Hashmap for ListView
		    ArrayList<HashMap<String, String>> itemList = new ArrayList<HashMap<String, String>>(); 
		    
	        // getting intent data
	        Intent in = getIntent();
	        
	        // Get JSON values from previous intent
	        String orderList = in.getStringExtra("order_receipt");
	        String orderNumber = in.getStringExtra("orderNumber");
	        String customerName = in.getStringExtra("customerName");
	        
	        cname = (TextView)findViewById(R.id.customerName);
	        oNumber = (TextView)findViewById(R.id.orderNumber);
	        
	        cname.setText(customerName);
	        oNumber.setText(orderNumber);
	        
		    //parse json data
			try{
			        JSONArray jArray = new JSONArray(orderList);
			        for(int i=0;i<jArray.length();i++){
			                JSONObject json_data = jArray.getJSONObject(i);
			                
							// Storing each json item in variable
							String name = json_data.getString("item_name");
							String qty = json_data.getString("order_QTY");
							String price = json_data.getString("item_price");
							Double dTotal = json_data.getDouble("item_total");												
							
							grandTotal = dTotal + grandTotal;
							
							String total = Double.toString(dTotal);
							
							
							// creating new HashMap
							HashMap<String, String> map = new HashMap<String, String>();
							
							// adding each child node to HashMap key => value
							map.put("name", name);
							map.put("qty", qty);
							map.put("price", price);
							map.put("total", total);
							
							// adding HashList to ArrayList
							itemList.add(map);
			        }
			}catch(JSONException e){
			        Log.e("log_tag", "Error parsing data "+e.toString());
			    }
			
			/**
			 * Updating parsed JSON data into ListView
			 * */
			ListAdapter adapter = new SimpleAdapter(this, itemList,
					R.layout.printreceiptview,
					new String[] { "name", "qty", "price", "total" }, new int[] {
					R.id.name, R.id.qty, R.id.price, R.id.total });

			setListAdapter(adapter);
			
			TextView price = (TextView) findViewById(R.id.totalprice);
			Button homeBtn = (Button)findViewById(R.id.homeBtn);
			String strGrandTotal = Double.toString(grandTotal);
			
			price.setText(strGrandTotal);
			// click the home Button
			homeBtn.setOnClickListener(new OnClickListener() {
		    	 @Override
				public void onClick(View v) {
		    		 
		    	Intent i = new Intent("android.intent.action.SELECTCATEGORYACTIVITY");
				startActivity(i);
		    	 }
		    	 
		     });

	   }
}
