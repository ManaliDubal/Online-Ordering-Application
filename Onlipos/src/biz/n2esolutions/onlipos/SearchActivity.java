package biz.n2esolutions.onlipos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;


public class SearchActivity extends Activity {
	
	public static EditText itemName;
	public static Button searchbtn;
	public static String item;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchview);
        
           
     itemName = (EditText)findViewById(R.id.searchBox); 
     searchbtn = (Button) findViewById(R.id.searchBtn);
    
     
     searchbtn.setOnClickListener(new OnClickListener() {
    	 @Override
		public void onClick(View v) {
    		 
    	item = itemName.getText().toString();
    	 
		Intent i = new Intent("android.intent.action.RETRIEVEDATA");
		startActivity(i);
    	 }
    	 
     });
          	 
    	 }

}
