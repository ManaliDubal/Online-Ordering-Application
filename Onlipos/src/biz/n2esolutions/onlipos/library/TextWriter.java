package biz.n2esolutions.onlipos.library;

import java.io.FileInputStream; 
import java.io.FileOutputStream;
import java.io.IOException;
import android.content.Context;
import android.util.Log;

public class TextWriter {
	
	private FileOutputStream fos;
	private FileInputStream fin;
	private StringBuffer mStrContent;
	private String mFILENAME;
	
	public TextWriter(){
		
		// initializes some member variables
		this.fos = null;
		this.fin = null;
		this.mStrContent = new StringBuffer("");
		this.mFILENAME = "ipsetting";
	}
	
	public void writeToFile(Context c, String str) {
		
		try {
			
			this.fos = c.openFileOutput(this.mFILENAME, Context.MODE_PRIVATE);
			this.fos.write(str.getBytes());
			
			this.fos.close();
			
		}catch(IOException err) {
			Log.e("TextWriter", err.toString());
		}
		
				
	}
	
	public String readFromFile(Context c) {
		
		try {
			this.fin = c.openFileInput(this.mFILENAME);
			int ch;
			
			//Log.e("Returned Value - openFileInput", ">>>" + this.fin.toString() + "<<<");
			
			while((ch = this.fin.read()) != -1) {
				this.mStrContent.append((char)ch);				
			}
			
			this.fin.close();
				
			Log.e("Settings File", this.mStrContent.toString());
			
		} catch (IOException err) {
			Log.e("TextWriter", err.toString());
		}
		return this.mStrContent.toString();
		
	}
	
}
