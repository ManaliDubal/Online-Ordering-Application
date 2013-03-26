package biz.n2esolutions.onlipos;


import java.io.InputStream;
import java.util.ArrayList;
import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import biz.n2esolutions.onlipos.library.JSONParser;
import biz.n2esolutions.onlipos.library.OnliposManagerActivity;
import android.content.Intent;
import android.os.Bundle; 
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class CheckoutActivity extends OnliposManagerActivity {
	
	public Button orderbtn, checkoutbtn;
	protected String IP = OnliposActivity.IP;
	public String orderNumber;
	public String customerName;
	public InputStream is;
	public static String response;
	public String receipt;
	
	 @Override
	 public void onCreate(Bundle savedInstanceState) {
	     super.onCreate(savedInstanceState);
	     setContentView(R.layout.checkoutview);
	     
	     this.orderbtn = (Button)findViewById(R.id.orderbtn);
	     this.checkoutbtn = (Button)findViewById(R.id.checkoutbtn);
			
			// click the List By Category Button
	     this.orderbtn.setOnClickListener(new OnClickListener() {
		    	 @Override
				public void onClick(View v) {
		    		 
				Intent i = new Intent("android.intent.action.SELECTCATEGORYACTIVITY");
				startActivity(i);
		    	 }
		    	 
		     });
			
			// click the Search Button
			this.checkoutbtn.setOnClickListener(new OnClickListener() {
		    	 @Override
				public void onClick(View v) {
		    		 
		    		String itemList = getOnlipostApplicationManager().getOrder().toString();
		    		 
		    		ArrayList<NameValuePair> nameValuePairs = getOnlipostApplicationManager().getNameValuePair(itemList);
	    			// Creating JSON Parser instance
	    			JSONParser jParser = new JSONParser();
	    				
		    			// get the IP in the file text
		    			response = OnliposActivity.IP;
		    			
		    			String host = getOnlipostApplicationManager().setURL(response);
		    			// getting JSON string from URL
		    			jParser.getJSONFromUrl(host, nameValuePairs);
		    			
		    		    //parse json data
		    			try{
		    			        JSONArray jArray = new JSONArray(JSONParser.result);
		    			        for(int i=0;i<jArray.length();i++){
		    			                JSONObject json_data = jArray.getJSONObject(i);
		    			                
		    							// Storing each json item in variable
		    							orderNumber = json_data.getString("order_number");
		    							customerName = json_data.getString("customer_name");

		    			        }
		    			}catch(JSONException e){
		    			        Log.e("log_tag", "Error parsing data "+e.toString());
		    			    }
			    		
		    			receipt = getOnlipostApplicationManager().getOrder().toString();
			  	    		
						Intent i = new Intent(CheckoutActivity.this, PrintReceiptActivity.class);
						i.putExtra("order_receipt", receipt);
						i.putExtra("orderNumber", orderNumber);
						i.putExtra("customerName", customerName);
						startActivity(i); 
						getOnlipostApplicationManager().emptyList();
						getOnlipostApplicationManager().resetReOrder();
	    			
		    	 }
		    	 
		     });

	 }
	 
}
