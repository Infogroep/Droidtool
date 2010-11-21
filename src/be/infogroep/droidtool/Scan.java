package be.infogroep.droidtool;

import java.util.List;
import java.util.Vector;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class Scan extends ListActivity {

	private Context c = this;
	private Activity a = this;
	private String token;
	private String debugger;
	private String server;
	private LayoutInflater mInflater;
	private Vector data; //used to add values to the listview
	private CustomAdapter adapter; //used to update the listview


	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {

		//standard initializing
		//mInflater is later used in CustonAdapter to reuse the view
		super.onCreate(savedInstanceState);
		setContentView(R.layout.scan);
		mInflater = (LayoutInflater) getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		
		
		//data is used to set the data in the listview
		data = new Vector();
		
		//get the extra value that was passed though the intent.
		String product = null;
		Bundle extras = getIntent().getExtras();
		if(extras !=null)
		{
			product = extras.getString("product");
			debugger = extras.getString("debugger");
			server = extras.getString("server");
			token = extras.getString("token");
		}

		//add a first row in the list containing the product
		RowData rd = new RowData("scanned", product);
		data.add(rd);
		
		//testcode
		//rd = new RowData("bla", "5449000027382");data.add(rd);rd = new RowData("bla", "5449000027382");data.add(rd);rd = new RowData("bla", "5449000027382");data.add(rd);
		

		//set the list it's content.
		adapter = new CustomAdapter(this, R.layout.custom_row,R.id.item, data);
		setListAdapter(adapter);        
		getListView().setTextFilterEnabled(true);
		
		//set the scan button's listener
		Button btn_scan = (Button) findViewById(R.id.btn_scan);
		btn_scan.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				IntentIntegrator.initiateScan(a);
			}

		}); 
		
		//set the scribble button's listener
		Button btn_scribble = (Button) findViewById(R.id.btn_scribble);
		btn_scribble.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				int amount = adapter.getCount();
				for (int i = 0; i < amount; i++) {
					RowData row = (RowData) adapter.getItem(0);
					String bar = row.mDescription;
					//Toast.makeText(c, bar, Toast.LENGTH_SHORT).show();
					scribble(bar);
					adapter.remove(adapter.getItem(0));
					adapter.notifyDataSetChanged();
				}
			}

		}); 
		
		//set the kaching button's listener
		Button btn_kaching = (Button) findViewById(R.id.btn_kaching);
		btn_kaching.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				int amount = adapter.getCount();
				for (int i = 0; i < amount; i++) {
					RowData row = (RowData) adapter.getItem(0);
					String bar = row.mDescription;
					//Toast.makeText(c, bar, Toast.LENGTH_SHORT).show();
					kaching(bar);
					adapter.remove(adapter.getItem(0));
					adapter.notifyDataSetChanged();
				}
			}

		});
	}

	
	//when an item is clicked
	public void onListItemClick(ListView parent, View v, final int position, long id) {
		final CustomAdapter adapter = (CustomAdapter) parent.getAdapter();
		RowData row = (RowData) adapter.getItem(position);  
		Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(row.mItem);
		builder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				adapter.remove(adapter.getItem(position));
				adapter.notifyDataSetChanged();
			}
		});
		
		builder.setMessage(row.mDescription + " -> " + position );
		builder.setPositiveButton("Cancel", null);
		builder.show();
	}
	
	//This part takes care of the actual scanning, adding the value to data and notifying the adapter
	protected void onActivityResult(int requestCode, int resultCode, Intent idata) {
		switch (requestCode) {
		case IntentIntegrator.REQUEST_CODE:
			if (resultCode != RESULT_CANCELED) {
				IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, idata);
				if (scanResult != null)
				{
					String upc = scanResult.getContents();
					RowData rd = new RowData("scanned", upc);
					data.add(rd);
					adapter.notifyDataSetChanged();
				}
			}
			break;
		}
	}
	
	protected void scribble(String barcode) {
		URLReader.postScribble(debugger, token, barcode, c);
	}
	
	protected void kaching(String barcode) {
		URLReader.postKaching(debugger, token, barcode, c);
	}
	
	protected String lookUp(String barcode) {
		try {
			return URLReader.postProductLookup(debugger, token, barcode, c);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "ERROR!!!!!";
		}
	}
	
	/**
	 * Data type used for custom adapter. Single item of the adapter.      
	 */
	private class RowData {
		protected String mItem;
		protected String mDescription;

		RowData(String item, String description){
			mItem = item;
			mDescription = description;      
		}

		@Override
		public String toString() {
			return mItem + " " +  mDescription;
		}
	}

	private class CustomAdapter extends ArrayAdapter {

		public CustomAdapter(Context context, int resource,
				int textViewResourceId, List objects) {
			super(context, resource, textViewResourceId, objects);

		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder = null;

			//widgets displayed by each item in your list
			TextView item = null;
			TextView description = null;

			//data from your adapter
			RowData rowData= (RowData) getItem(position);


			//we want to reuse already constructed row views...
			if(null == convertView){
				convertView = mInflater.inflate(R.layout.custom_row, null);
				holder = new ViewHolder(convertView);
				convertView.setTag(holder);
			}
			// 
			holder = (ViewHolder) convertView.getTag();
			item = holder.getItem();
			item.setText(rowData.mItem);

			description = holder.getDescription();  
			description.setText(rowData.mDescription);

			return convertView;
		}
	}

	/**
	 * Wrapper for row data.
	 *
	 */
	private class ViewHolder {     
		private View mRow;
		private TextView description = null;
		private TextView item = null;

		public ViewHolder(View row) {
			mRow = row;
		}

		public TextView getDescription() {
			if(null == description){
				description = (TextView) mRow.findViewById(R.id.description);
			}
			return description;
		}

		public TextView getItem() {
			if(null == item){
				item = (TextView) mRow.findViewById(R.id.item);
			}
			return item;
		}     
	}
}
