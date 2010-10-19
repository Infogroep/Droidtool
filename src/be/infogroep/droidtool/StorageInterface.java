package be.infogroep.droidtool;

import android.content.Context;
import android.content.SharedPreferences;

public class StorageInterface {

	static String FILENAME = "DroidtoolStorage";
	public static final String PREFS_NAME = "DroidToolPrefsFile";

	public static SharedPreferences prefsInitial;
	//use getApplicationContext() as context parm
	public static void Save(String key, String val, Context c) {
		SharedPreferences settings = c.getSharedPreferences(PREFS_NAME, 0);
		SharedPreferences.Editor editor = settings.edit();
		editor.putString(key, val);
		editor.commit();
	}
	
	public static String Get(String key, Context c) {

		SharedPreferences settings = c.getSharedPreferences(PREFS_NAME, 0);
		String check = settings.getString(key, "");
		return check;
	}
}