package biz.n2esolutions.onlipos;

import java.io.BufferedWriter; 
import java.io.IOException;
import java.io.InputStream;
import biz.n2esolutions.onlipos.library.IPsetting;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;


public class SetIPActivity extends Activity {
	public EditText display;
	public Button btn;
	public static String IP;
	public BufferedWriter in = null; 
	public String line;
	public InputStream is;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setipview);
       
	   		 display=(EditText)findViewById(R.id.IP); 
		     btn= (Button) findViewById(R.id.set);
		    
		     
		            	
             	            
	        btn.setOnClickListener(new OnClickListener() {
				 @Override
				public void onClick(View v) {
				 
				IP = display.getText().toString();
				
				try {
					
					is = getAssets().open("setting.txt");
					
					IPsetting check = new IPsetting();
					check.setIP(is, IP);
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//Intent i = new Intent("android.intent.action.SELECTCATEGORYACTIVITY");
				//startActivity(i);
				 }
			 });
	 
    	 }
}
