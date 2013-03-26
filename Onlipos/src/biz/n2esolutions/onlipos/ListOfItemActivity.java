package biz.n2esolutions.onlipos;

import biz.n2esolutions.onlipos.library.* ;    

import java.util.ArrayList;
import java.util.HashMap;
import biz.n2esolutions.onlipos.CategoryActivity;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;


public class ListOfItemActivity extends ListActivity  {
	
	
	private String host = "http://" + OnliposActivity.IP +"/OnliposAPI/index/selectByCategory";
	
	CategoryActivity typename = new CategoryActivity();
	
	String typeName = CategoryActivity.name.toString(); 
	String categoryName = "category";
	
	public static String id;
	public String name;
	public String desc;
	public String price;
	
	 @Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.serpsmain);
	        
	    
		// Hashmap for ListView
	    ArrayList<HashMap<String, String>> itemList = new ArrayList<HashMap<String, String>>(); 
	    
	    ArrayList<NameValuePair> nameValuePairs  = new ArrayList<NameValuePair>();
	    nameValuePairs.add(new BasicNameValuePair(categoryName, typeName ));
	    
		// Creating JSON Parser instance
		JSONParser jParser = new JSONParser();

		// getting JSON string from URL
		jParser.getJSONFromUrl(host, nameValuePairs);

	    //parse json data
		try{
		        JSONArray jArray = new JSONArray(JSONParser.result);
		        for(int i=0;i<jArray.length();i++){
		                JSONObject json_data = jArray.getJSONObject(i);
		                
						// Storing each json item in variable
						id = json_data.getString("i_id");
						this.name = json_data.getString("shortname");
						this.desc = json_data.getString("description");
						this.price = json_data.getString("unit_price");
		                //Get an output to the screen
		                //returnString += "\n\t" + jArray.getJSONObject(i);
		                
		                //txt.setText(id + name + desc + price);
						
						// creating new HashMap
						HashMap<String, String> map = new HashMap<String, String>();
						
						// adding each child node to HashMap key => value
						map.put("i_id", id);
						map.put("shortname", this.name);
						map.put("description",this.desc );
						map.put("unit_price", this.price);
						
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
				R.layout.serps,
				new String[] { "i_id", "shortname", "description", "unit_price" }, new int[] {
						R.id.it_id, R.id.shortname, R.id.description, R.id.unit_price });

		setListAdapter(adapter);
		
		// selecting single ListView item
		ListView lv = getListView();

				// Launching new screen on Selecting Single ListItem
				lv.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						// getting values from selected ListItem
						String name = ((TextView) view.findViewById(R.id.shortname)).getText().toString();
						String desc = ((TextView) view.findViewById(R.id.description)).getText().toString();
						String price = ((TextView) view.findViewById(R.id.unit_price)).getText().toString();
						String it_item = ((TextView) view.findViewById(R.id.it_id)).getText().toString();
						// Starting new intent
						Intent in = new Intent(getApplicationContext(), OrderMenuItemActivity.class);
						in.putExtra("id", it_item);
						in.putExtra("shortname", name);
						in.putExtra("description", desc);
						in.putExtra("unit_price", price);
						startActivity(in);

					}
				});
		    
		} 
}
