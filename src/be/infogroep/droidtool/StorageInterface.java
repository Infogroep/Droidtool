package be.infogroep.droidtool;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.content.Context;

public class StorageInterface {

	String FILENAME = "DroidtoolStorage";
	
	//use getApplicationContext() as context parm
	public void Save(String token, Context ctx) throws FileNotFoundException {
		String string = token;
		
		FileOutputStream fos = ctx.openFileOutput(FILENAME, Context.MODE_PRIVATE); //openFileOutput underlined red
        try {
          fos.write(string.getBytes());
          fos.close();
        } catch (IOException e) {
          //Log.e("Controller", e.getMessage() + e.getLocalizedMessage() + e.getCause());
        }

	}
	
	public String Get(Context ctx) throws FileNotFoundException {
				
		FileInputStream fin = ctx.openFileInput(FILENAME); //openFileOutput underlined red
		byte[] token = null;
        try {
          fin.read(token);
          fin.close();
        } catch (IOException e) {
          //Log.e("Controller", e.getMessage() + e.getLocalizedMessage() + e.getCause());
        }
		return token.toString();

	}
}