package biz.n2esolutions.onlipos.library;

import android.app.Activity;

public class OnliposManagerActivity extends Activity {
	
	public OnliposManagerActivity() {
		super();
	}
	
	protected OnliposApplicationManager getOnlipostApplicationManager() {
		return (OnliposApplicationManager) getApplication();
	}

}
