package be.infogroep.droidtool;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
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

		String line = null;
		
		try {
			InputStream fin = ctx.openFileInput(FILENAME); 
			// if file the available for reading

			// prepare the file for reading
			InputStreamReader input = new InputStreamReader(fin);
			BufferedReader buffreader = new BufferedReader(input);

			

			// read every line of the file into the line-variable, on line at the time
			//while (( line = buffreader.readLine())) {
				// do something with the settings from the file
			//}
			
			line = buffreader.readLine();
			// close the file again
			fin.close();
		} catch (java.io.FileNotFoundException e) {
			// do something if the myfilename.txt does not exits
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return line;
	}
}