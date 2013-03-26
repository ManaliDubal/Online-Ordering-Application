package biz.n2esolutions.onlipos;

import biz.n2esolutions.onlipos.library.OnliposManagerActivity;
import android.content.Intent; 
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class OrderMenuItemActivity extends OnliposManagerActivity {
	
	public Button orderbtn;
	public EditText quantity;
	public static String item_id;
	public static String shortname;
	public static String description;
	public static String unit_price;
	public TextView customer_name;
	public TextView customer_course;
	
	// JSON node keys
	private static final String TAG_ITEM = "id";
	private static final String TAG_NAME = "shortname";
	private static final String TAG_DESCRIPTION = "description";
	private static final String TAG_PRICE = "unit_price";
	
	//private static int reOrder = 0;
	 

	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_list_item);
        
        getOnlipostApplicationManager().reOrder++;
             
        
        TextView header_form = (TextView)findViewById(R.id.titleForm);
        TextView name_header = (TextView)findViewById(R.id.name_header);
        TextView name_form = (EditText)findViewById(R.id.cname);
        TextView course_header = (TextView)findViewById(R.id.course_header);
        TextView course_form = (EditText)findViewById(R.id.course);
        quantity = (EditText)findViewById(R.id.QTY);
        customer_name = (TextView)findViewById(R.id.cname);
        customer_course = (TextView)findViewById(R.id.course);
        
       if( getOnlipostApplicationManager().reOrder > 1) {
        	name_header.setVisibility(View.INVISIBLE);
        	header_form.setVisibility(View.INVISIBLE);
        	name_form.setVisibility(View.INVISIBLE);
        	course_header.setVisibility(View.INVISIBLE);
        	course_form.setVisibility(View.INVISIBLE);
        	
        } 
        orderbtn = (Button)findViewById(R.id.OrderBtn);
        
        // getting intent data
        Intent in = getIntent();
        
        // Get JSON values from previous intent
        item_id = in.getStringExtra(TAG_ITEM);
        shortname = in.getStringExtra(TAG_NAME);
        description = in.getStringExtra(TAG_DESCRIPTION);
        unit_price = in.getStringExtra(TAG_PRICE);
        
        // Displaying all values on the screen
        TextView lblName = (TextView) findViewById(R.id.shortname);
        TextView lblCost = (TextView) findViewById(R.id.description);
        TextView lblDesc = (TextView) findViewById(R.id.price);
        
        lblName.setText(shortname);
        lblCost.setText(description);
        lblDesc.setText(unit_price);         
        
		// click the Search Button
		orderbtn.setOnClickListener(new OnClickListener() {
	    	 @Override
			public void onClick(View v) {
	    		 
	    	String QTY = quantity.getText().toString();
	    	String name = customer_name.getText().toString();
	    	String course = customer_course.getText().toString();
    			     	  
		    getOnlipostApplicationManager().setOrder(item_id, shortname, name, course, QTY, unit_price);
	    	
			Intent i = new Intent("android.intent.action.CHECKOUTACTIVITY");
			startActivity(i);
	    	 }
	    	 
	     });
    }
}


