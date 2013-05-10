package edu.buffalo.cse.cse486586.simpledht;








import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;


public class SimpleDhtMainActivity extends Activity {
    /** Called when the activity is first created. */
	   public static TextView statusmsg;
	   public static Button testcase1;
	   public static Button connect;
	   public static EditText editwindow;
	   public static Button join_case;
	
		public static String input = "test0";
		public static int sequence_number = 0;
		public static int type_message=0;
		public static String phonenumber = null;
		public static String phone_test = null;
		public static int portnum1 = 11108;
		public static int portnum2 = 11112;
		public static int portnum3 = 11116;
		public static int portnum4 = 11120;
		public static int portnum5 = 11124;
		public static int portnum_hash_great;
		public static int portnum_hash_less;
		public static int portnum_hash_equal;
		public static int portnum_hash_great1;
		public static int portnum_hash_less1;
		public static int portnum_hash_less2;
		public static int portnum_hash_equal1;
		public static int port_great;
		public static int port_less;
		public static int port_equal;
		public static String me;
		public static TelephonyManager teleman;
		public static TelephonyManager telem;
		public static String teststr = "test";
		public static String phone_another;
	    public static String hash_value;
	    public static String hash_copy;
	    public static String msg;
		public static String curs=null;
		public static String curs_val=null;

	    
	    public static String phone;
	    public static String hash5554 = "33d6357cfaaf0f72991b0ecd8c56da066613c089";
	    public static String hash5556 = "208f7f72b198dadd244e61801abe1ec3a4857bc9";
	    public static String hash5558 = "abf0fd8db03e5ecb199a9b82929e9db79b909643";
	    public static String hash5560 = "c25ddd596aa7c81fa12378fa725f706d54325d12";
	    public static String hash5562 = "";
	    public static String curs_dump=null;
	    public static String curs_val_dump=null;
	    public static Socket socket1;
		public static Socket socket2;
		public static Socket socket3;
		public static Socket socket4;
		public static Socket socket5;
		public static Socket socket_hash_great;
		public static Socket socket_hash_great1;
		public static Socket socket_hash_less;
		public static Socket socket_hash_less1;
		public static Socket socket_hash_less2;
		public static Socket socket_hash_equal;
	    public static ArrayList <String> buffer_array = new ArrayList<String>();
	    ContentResolver cr;
		public static String sequence = "0";
		public static int sequ;
		public Cursor cursor_dum;
	    Handler handle = new Handler();
	@Override   
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_dht_main);
   //Mustafaprovider.ChordDHT  nh = new ChordDHT().cons(cr);
    // ChordDHT cdt = new Mustafaprovider.ChordDHT().contentchord(cr);
        
        
        TextView tv = (TextView) findViewById(R.id.textView1);
        tv.setMovementMethod(new ScrollingMovementMethod());
        findViewById(R.id.button3).setOnClickListener(
                new OnTestClickListener(tv, getContentResolver()));
    

	
        Button connect1 = (Button) findViewById(R.id.button1);
    //    statusmsg = (TextView) findViewById(R.id.text1);
		//testcase1 = (Button) findViewById(R.id.test1);
		//editwindow = (EditText) findViewById(R.id.edit1);
		
	
		
		
		Thread join_thr = new Thread(new Join(),"join thread");
		join_thr.start();
		Log.d("join","join thread has started");
		
		testcase1.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				
				Thread thr = new Thread(new Insert(),"insert thread");
				thr.start();
				
			}
		});
		  connect.setOnClickListener(new View.OnClickListener() {
				
				public void onClick(View v) {
					// TODO Auto-generated method stub
				       Cursor cursor_dum = getContentResolver().query(SimpleDhtProvider.my_URI, null, null, null,"mustafa");
				           
				        if(cursor_dum!=null)
				        {     
				        	cursor_dum.moveToFirst();
				        	while(!cursor_dum.isLast())
				        	{
				        		cursor_dum.moveToNext();
				        		String str[] = cursor_dum.getColumnNames();
				        		Log.d("mess","message : "+str[0]);
				        		//curs = cursor_dum.getString(0);
				        		curs_val = cursor_dum.getString(1);
				        		Log.d("cursor","cursor message:"+curs);
				        		Log.d("cursor","cursor message :"+curs_val);
				        		handle.post(myHand);
				        	}
				        }
				}
			});
		}
	
		Runnable myHand = new Runnable() {
			public void run() 
		{
				// TODO Auto-generated method stub
			try{
				//statusmsg.append(sequence_number);
				statusmsg.append(curs);
				statusmsg.append(",");
				statusmsg.append(curs_val);
				statusmsg.append("\n");
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		};

		Runnable myHandler = new Runnable() {
			public void run() 
		{
				// TODO Auto-generated method stub
			try{
				//statusmsg.append(sequence_number);
				statusmsg.append(sequence);
				statusmsg.append(",");
				statusmsg.append(teststr);
				statusmsg.append("\n");
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		};
		
	class Join implements Runnable
	{

		public void run() {
			// TODO Auto-generated method stub
			Log.d("message","join request reached");
		try{
			
			socket1 = null;
			type_message=10;
		
			teleman = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
			Log.d("tele","reached the teleman funtion");
			phonenumber = teleman.getLine1Number().substring(teleman.getLine1Number().length() - 4);
			Log.d("tele","reached the teleman funtion "+ phonenumber);
			
			socket1 = new Socket("10.0.2.2",portnum1);
			
			OutputStream os = socket1.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(os);
			Message to1 = new Message(0,null,phonenumber,type_message,null,null,null,null);
			oos.writeObject(to1);
			
			Log.d("message","after sending the join request ");
			
		}catch(Exception ert)
		{
			ert.printStackTrace();
		}
		
		}

	}
	class Insert implements Runnable
	{
		public void run() {
			// TODO Auto-generated method stub
			int i;
			for(i=0;i<10;i++)
			{
				try {
					Thread.sleep(1200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				teststr = teststr+i;
				
				Log.d("test","string"+teststr);
				
				 ContentValues contentValues = new ContentValues();
				 contentValues.put(Mustafadatabase.provider_key, sequence);
	             contentValues.put(Mustafadatabase.provider_value, teststr);
	             sequ=Integer.parseInt(sequence);
	 			 Log.d("send","sequence"+sequence);
	             getContentResolver().insert(SimpleDhtProvider.my_URI, contentValues);
				 handle.post(myHandler);	             
	         	 sequ++;
				 sequence = Integer.toString(sequ);
				 teststr = "test";
				//sequence = ""+seq;

			}
			
			Log.d("thread","problem in thread ?");
		
			   try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		     //  Cursor cursor_test = getContentResolver().query(Provider.my_URI, null, null, null,"insque");
		    
			for(i=0;i<10;i++)
			{
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			String ins = null; 
			ins = ""+i;
			Log.d("sequence_____","is "+ins);
			Cursor cursor_test = getContentResolver().query(SimpleDhtProvider.my_URI, null, ins, null, "init");           
		    
			if(cursor_test!=null)
		        {     

		        	cursor_test.moveToFirst();
		        	while(!cursor_test.isLast())
		        	{
		        		cursor_test.moveToNext();
		        		String str[] = cursor_test.getColumnNames();
		        		curs = cursor_test.getString(1);
		        		curs_val = cursor_test.getString(0);
		        		//handle.post(myHand);
		        		Log.d("cursor","cursor message:"+curs);
		        		Log.d("cursor","cursor value:"+curs_val);
		        	}
	        		
		        }
			}
			}
		
		
		
		
		}
		


	}