package biz.n2esolutions.onlipos;

import java.io.IOException;  
import java.io.InputStream;

import biz.n2esolutions.onlipos.library.IPsetting;
import biz.n2esolutions.onlipos.library.OnliposManagerActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button; 
import android.content.Intent;

public class SelectCategoryActivity extends OnliposManagerActivity {
	
	public Button categorybtn, searchbtn;
	public InputStream is;
	public static String response;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.categoryview);
		
		try {
			
			is = getAssets().open("setting.txt");
			
			// create object
			IPsetting get = new IPsetting();
			
			// get the IP in the file text
			response = get.getIP(is).toString();
			
			getOnlipostApplicationManager().setURL(response);
			
			categorybtn = (Button)findViewById(R.id.CategoryBtn);
			searchbtn = (Button)findViewById(R.id.SearchBtn);
			
			// click the List By Category Button
			categorybtn.setOnClickListener(new OnClickListener() {
		    	 @Override
				public void onClick(View v) {
		    		 
				Intent i = new Intent("android.intent.action.CATEGORYACTIVITY");
				startActivity(i);
		    	 }
		    	 
		     });
			
			// click the Search Button
			searchbtn.setOnClickListener(new OnClickListener() {
		    	 @Override
				public void onClick(View v) {
		    		 
				Intent i = new Intent("android.intent.action.SEARCHACTIVITY");
				startActivity(i);
		    	 }
		    	 
		     });
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
