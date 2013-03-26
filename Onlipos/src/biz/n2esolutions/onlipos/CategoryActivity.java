package biz.n2esolutions.onlipos;

import java.util.ArrayList;  
import java.util.HashMap;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import biz.n2esolutions.onlipos.library.*; 
import android.os.Bundle; 
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.app.ListActivity;
import android.content.Intent;

public class CategoryActivity extends ListActivity {
	
	private String host = "http://" + OnliposActivity.IP +"/OnliposAPI/index/selectByCategory";
	public static String name;
	
	String tag = "type";
	String categoryName = "category";
	
	 @Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.categorymain);
	    
	    
		// Hashmap for ListView
	    ArrayList<HashMap<String, String>> itemList = new ArrayList<HashMap<String, String>>(); 
	    
	    ArrayList<NameValuePair> nameValuePairs  = new ArrayList<NameValuePair>();
	    nameValuePairs.add(new BasicNameValuePair(categoryName, tag));
	    
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
						String id = json_data.getString("it_id");
						String name = json_data.getString("type_name");
						String active = json_data.getString("active");
				
						
						// creating new HashMap
						HashMap<String, String> map = new HashMap<String, String>();
						
						// adding each child node to HashMap key => value
						map.put("it_id", id);
						map.put("type_name", name);
						map.put("active", active);
						
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
				R.layout.listbycategoryview,
				new String[] { "type_name", "it_id" }, new int[] {
						R.id.shortname });

		setListAdapter(adapter);
		
		// selecting single ListView item
		ListView lv = getListView();

		// Launching new screen on Selecting Single ListItem
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				// getting values from selected ListItem
				name = ((TextView) view.findViewById(R.id.shortname)).getText().toString();
				
				Intent i = new Intent("android.intent.action.LISTOFITEMACTIVITY");
				
				startActivity(i);

			}
		});
		    
	   
}
}
