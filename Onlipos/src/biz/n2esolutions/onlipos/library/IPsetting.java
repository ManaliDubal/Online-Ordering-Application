package biz.n2esolutions.onlipos.library;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;



public class IPsetting {
	
	public String result;
	public String addr;
	public String strContent;
	public FileInputStream fin = null;
	
	public String checking(InputStream is) {
			
		 try {

				
			 int size = is.available();
			 byte[] buffer = new byte[size];
			 is.read(buffer);
			 
		      if(size == 0) {
		        
		         result = "setIP";
		    	  
		      } else {
		    	  
		    	 result = "getIP";
		      }
		      is.close();
		      
		    } catch (Exception e) {
		      System.out.println(e);
		    }
		 
		return result;
		
	}
	
	public String getIP(InputStream is) {
		
		try {
			
			int size = is.available();
			byte[] buffer = new byte[size];
			is.read(buffer);
			is.close();
			result = new String(buffer);
			//long x = 1000;
			
			
			} catch (IOException e) {
					
				throw new RuntimeException(e);
			}
		
		return result;
	}
	
	public void setIP(InputStream is, String ip){

	
	}

}
