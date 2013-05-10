package edu.buffalo.cse.cse486586.simpledht;




import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Formatter;


import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.os.Handler;
import android.telephony.TelephonyManager;
import android.util.Log;

public class SimpleDhtProvider extends ContentProvider {

	public static String base;
	public static String pre;
	public static String succ;
	public static String leastpre;
	public static String highsucc;
	public static String phone_compare;
	public static String input = "test0";
	public static int sequence_number = 0;
	public static int type_message=0;
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
	public static int portnum_hash_test1;
	public static int portnum_hash_test2;
	public static int portnum_hash_test3;
	public static int port_great;
	public static int port_less;
	public static int port_equal;
	public static String me;
	public static String myself;
	public static TelephonyManager teleman;
	public static TelephonyManager telem;
	public static String phone_another;
    public static String hash_value;
    public static String hash_copy;
    public static String msg;
    public static String manuplate="test";
    
    
    public static String phone;
    public static String hash5554 = "33d6357cfaaf0f72991b0ecd8c56da066613c089";
    public static String hash5556 = "208f7f72b198dadd244e61801abe1ec3a4857bc9";
    public static String hash5558 = "abf0fd8db03e5ecb199a9b82929e9db79b909643";
    public static String hash5560 = "c25ddd596aa7c81fa12378fa725f706d54325d12";
    public static String hash5562 = "";

    public static Socket socket1;
	public static Socket socket2;
	public static Socket socket3;
	public static Socket socket4;
	public static Socket socket5;
	public static Socket socket_hash_great;
	public static Socket socket_hash_great1;
	public static Socket socket_hash_less;
	public static Socket socket_hash_less1;
	public static Socket socket_hash_succ;
	public static Socket socket_hash_succ1;
	public static Socket socket_hash_succ2;
	public static Socket socket_hash_less2;
	public static Socket socket_hash_equal;
	public static ServerSocket servsock = null;
	public static Socket cliesock = null;
	public static String sequence="0";
	public static int seq;
	public static String teststr = "test";
	public ContentResolver cr;
	public static ContentValues values;
	public static int flag=0;
	public static int flag_thread = 0;
	public static int flag_prede = 0;
	public static int flag_succe = 0;
	public static String teststring = "test";
	public static int flag_query_thread = 0;
	public static int flag_query_succ = 0;
	public static int flag_wait = 0;
	public static String initiator = null;
	public static boolean flag_initiator = true;
	public	Cursor cursor = null;
	public Cursor cursor_serv = null;
	public static ArrayList <String> buffer_array = new ArrayList<String>();
	
	private static final String Authority = "edu.buffalo.cse.cse486_586.provider";
	public static final int TUTORIALS = 100;
	public static final int TUTORIALS_ID = 110;
	public static final String Excep = "wtf";
	private static final String TUTORIALS_BASE_PATH = "please";

	public static final Uri my_URI = Uri.parse("content://" + Authority + "/"+ TUTORIALS_BASE_PATH);
	
	public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE
	        + "/mt-tutorial";
	public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE
	        + "/mt-tutorial";
	
	public Mustafadatabase mdb;
	
	@Override
	public boolean onCreate() {
		
		try
		{
			// TODO Auto-generated method stub
			
		Log.d("content provider","on create of content provider on create");	
		
		mdb = new Mustafadatabase(getContext());
		SQLiteDatabase sqlcr = mdb.getWritableDatabase();
		mdb.onCreate(sqlcr);
		base = "5554";
		pre = "5554";
		succ = "5554";
		leastpre = "5554";
		highsucc = "5554";
		}
		catch(Exception er){
			er.printStackTrace();
		}
	
		Thread serverthread = new Thread(new Serverthr(),"server thread");
		serverthread.start();
		
		return true;
	}

	
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		/*SQLiteDatabase 	sqlDB = mdb.getWritableDatabase();
		int counter = 0;
		switch(sURIMatcher.match(uri)){
		case TUTORIALS:
			counter = sqlDB.delete(Mustafadatabase.TABLE_MESSENGER,selection, selectionArgs);
			break;
			default:
				
				throw new IllegalArgumentException("bhenchod URI"+uri);
		}
		getContext().getContentResolver().notifyChange(uri, null);
	*/	
		throw new UnsupportedOperationException();
	
	}

	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues initialvalues) {
		
		// TODO Auto-generated method stub
	
		
		Log.d(CONTENT_TYPE, "kahan pahunche");
		
		if(initialvalues!=null)
		{
			values = new ContentValues(initialvalues);
		}
		else
		{
			values = new ContentValues();
		}
		
		SQLiteDatabase sqld = mdb.getWritableDatabase();
		Log.d(CONTENT_TYPE, "before	 inserting");
		//long rowID = sqld.insert(Mustafadatabase.TABLE_MESSENGER, "", values);
		Log.d("sequence received","number is "+values.getAsString(Mustafadatabase.provider_key));
		Log.d("value received ","value received is "+values.getAsString(Mustafadatabase.provider_value));
		try 
		{
			
			if(genHash(values.getAsString(Mustafadatabase.provider_key)).compareTo(genHash(SimpleDhtMainActivity.phonenumber))<0)
			{
				if(genHash(values.getAsString(Mustafadatabase.provider_key)).compareTo(genHash(pre)) > 0 || SimpleDhtMainActivity.phonenumber.equals(leastpre) )
				{
					Log.d("values","the sequence number is"+values.getAsString(Mustafadatabase.provider_key));
					Log.d("provider","inserting the data");
			
sqld.delete(Mustafadatabase.TABLE_MESSENGER, Mustafadatabase.provider_key +"="+values.getAsString(Mustafadatabase.provider_key),null);
					long row = sqld.insert(Mustafadatabase.TABLE_MESSENGER,"", values);
			         //ContentResolver cr = getContext().getContentResolver();
			         Log.d("insertion","inserting the row"+row);
			
				}
				
				else
				{
				flag_thread=1;
				flag_prede = 1;
				}
			}
			
		
			if(genHash(values.getAsString(Mustafadatabase.provider_key)).compareTo(genHash(SimpleDhtMainActivity.phonenumber))>0)
			{
				if(SimpleDhtMainActivity.phonenumber.equals(leastpre) && genHash(values.getAsString(Mustafadatabase.provider_key)).compareTo(genHash(pre))>0)
				{
					 Log.d("greater","sequ "+values.getAsString(Mustafadatabase.provider_key));
					 Log.d("provider","inserting the data");
					
					 long bow = sqld.insert(Mustafadatabase.TABLE_MESSENGER,"", values);
			         //ContentResolver cr = getContext().getContentResolver();
			         Log.d("insert","inserting the data"+values.getAsString(Mustafadatabase.provider_key));
			         Log.d("insertion","inserting the row"+bow);
			
				}
				else
				{
						//send successor
					flag_thread = 1;
					flag_succe = 1;
				}
				
			}
			if(flag_thread==1)
			{
				Log.d("else","thread has started");
				Thread insertion = new Thread(new ChordDHT(values),"insertion thread");
				insertion.start();
			}
			
			} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			
			}
				
		/*if(rowID>0)
		{
			Uri inserturi = ContentUris.withAppendedId(uri, rowID);
			getContext().getContentResolver().notifyChange(inserturi, null);
			Log.d(CONTENT_TYPE, "after inserting");
			Log.d("message inserted is ", values.toString());
			return inserturi;
		}*/
		return null;
	}

	
	@Override
	public Cursor query(Uri uri, String[] projection, String selection,	String[] selectionArgs, String sortOrder) {
		// TODO Auto-generated method stub
		if(selection==null)
		{
			SQLiteQueryBuilder querybuilder = new SQLiteQueryBuilder();
			querybuilder.setTables(Mustafadatabase.TABLE_MESSENGER);
		
			SQLiteDatabase sqd = mdb.getReadableDatabase();
			
			cursor = querybuilder.query(sqd, projection, selection, selectionArgs, null, null, null);
			cursor.setNotificationUri(getContext().getContentResolver(), uri);
			
		}
		else
		{
			Log.d("query","selection sequence :" +selection);
			Log.d("i","am : "+SimpleDhtMainActivity.phonenumber);
			
			try {
				
				if(genHash(selection).compareTo(genHash(SimpleDhtMainActivity.phonenumber))<0)
				{
					if(genHash(selection).compareTo(genHash(pre)) > 0 || SimpleDhtMainActivity.phonenumber.equals(leastpre) )
					{
						Log.d("query","the query is found here inside : "+SimpleDhtMainActivity.phonenumber);
			
						SQLiteQueryBuilder querybuild = new SQLiteQueryBuilder();
						querybuild.setTables(Mustafadatabase.TABLE_MESSENGER);
						SQLiteDatabase sqlqd = mdb.getReadableDatabase();
		
						Log.d("query","query coming"+ Mustafadatabase.provider_key + "=" +selection);
						
						
						cursor = querybuild.query(sqlqd, null, Mustafadatabase.provider_key + "=" +selection, null, null, null, null);			
						cursor.setNotificationUri(getContext().getContentResolver(), uri);
						//return cursor;
				
					}
					else
					{
						flag_query_thread=1;
					}
				}
				
				else if(genHash(selection).compareTo(genHash(SimpleDhtMainActivity.phonenumber))>0)
				{
					if(SimpleDhtMainActivity.phonenumber.equals(leastpre) && genHash(selection).compareTo(genHash(pre))>0)
					{	
						Log.d("query","the query is found here inside : "+SimpleDhtMainActivity.phonenumber);
						
						SQLiteQueryBuilder querybuild = new SQLiteQueryBuilder();
						querybuild.setTables(Mustafadatabase.TABLE_MESSENGER);
					
						SQLiteDatabase sqlqd = mdb.getReadableDatabase();
						
						Log.d("query","query coming:"+Mustafadatabase.provider_key+"="+selection);
									
						cursor = querybuild.query(sqlqd, null, Mustafadatabase.provider_key + "=" +selection, null, null, null, null);
						cursor.setNotificationUri(getContext().getContentResolver(), uri);
						//return cursor;
						
					}
					else
					{
						flag_query_thread=1;
					}
				}	
				
				if(flag_query_thread==1 && sortOrder.equals("init"))
				{
					Log.d("flag","flag query thread");
				
					initiator = SimpleDhtMainActivity.phonenumber;
					
					Log.d("initiator","initiator"+initiator);
					Thread query = new Thread(new queryDHT(selection),"insertion thread");
					query.start();
					
					flag_initiator = true;
					
					while(flag_initiator)
					{
						Thread.sleep(25);
					}
					
					//return cursor; // only trying here
				
				}
				else if(flag_query_thread==1)
				{
					Log.d("flag","flag query thread");
					Thread query = new Thread(new queryDHT(selection),"insertion thread");
					query.start();
				}
				
				
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			
		}
		return cursor;		
		}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		/*SQLiteDatabase sqlup = mdb.getWritableDatabase();
		int count;
		switch(sURIMatcher.match(uri)){
		case TUTORIALS:
			count = sqlup.update(Mustafadatabase.TABLE_MESSENGER, values, selection, selectionArgs);
			break;
			default:
				Log.d(selection,"Exception thrown bhenchod");
				throw new IllegalArgumentException("laude lag gaye bhenchod"+uri);
		}
		getContext().getContentResolver().notifyChange(uri, null);
		return count;
	*/
		throw new UnsupportedOperationException();
	}

	private static final UriMatcher sURIMatcher = new UriMatcher(
	        UriMatcher.NO_MATCH);
	static {
	    sURIMatcher.addURI(Authority, TUTORIALS_BASE_PATH, TUTORIALS);
	    sURIMatcher.addURI(Authority, TUTORIALS_BASE_PATH + "/#", TUTORIALS_ID);
	}
	
	
	
	
	
	class Serverthr implements Runnable
	{
		static final int server = 10000;	
		public void run()
		{
			Log.d("values:::::::___________________", values + " ");
			// TODO Auto-generated method stub
			try {
					servsock = new ServerSocket(server);
					synchronized(servsock)
					{
						Log.d("server listening ", "waiting");
						servsock.wait(1000*10);
					while(true)
					{			
						Log.d("client","before creating socket");
		
						cliesock = servsock.accept();
					
						if(cliesock!=null)
						{
							Log.d("accepted", "server accepted");
							Log.d("message","i reached here" + cliesock);

							InputStream is = cliesock.getInputStream();
							ObjectInputStream ois = new ObjectInputStream(is);
							Message tom = (Message)ois.readObject();
							
							Log.d("Server","the type of message received is"+tom.type);
					
							type_message=tom.type;
							sequence_number = tom.value;
							
							teleman = (TelephonyManager) getContext().getSystemService(Context.TELEPHONY_SERVICE);
							me = teleman.getLine1Number().substring(teleman.getLine1Number().length() - 4);
							
							Log.d("telephone","i am " +me);
							
						while(tom.type==10)
						{		
								
						msg = tom.data;
						phone = tom.phone;
						
						Log.d("messsage", "the phone number received is"+tom.phone);
						Log.d("join","join request received");
						
	/*-------------------------------------------------------------------------------------------------------------------------------------------------------*/
			
						/*the part where the computed hash is less than 5554 initially*/
						
						if(generateHash(me).compareTo(generateHash(tom.phone)) < 0)
							{
								Log.d("hash","hash of "+me);
								Log.d("hash","is less than"+tom.phone);
								//handle.post(myHandler);
								Log.d("inside","inside the hash");
								
								/*successor is greater than the new */
								
								if(generateHash(succ).compareTo(generateHash(tom.phone)) > 0)
								{
								type_message = 2;
								
								if(generateHash(tom.phone).compareTo(generateHash(leastpre)) < 0)
								{
									leastpre = tom.phone;
									Log.d("leastpre","least predecessor is :"+leastpre);
								}
								else if(generateHash(tom.phone).compareTo(generateHash(highsucc)) > 0)
								{
									highsucc = tom.phone;
									Log.d("successor","highest successor is "+highsucc);
								}
									
								Log.d("hash","hash of "+succ);
								Log.d("hash","is greater than"+tom.phone);
								
								portnum_hash_great= Integer.parseInt(tom.phone)*2;
								socket_hash_great = new Socket("10.0.2.2",portnum_hash_great);
								
								Log.d("portnumber","the portnum is" +portnum_hash_great);
								
								OutputStream os = socket_hash_great.getOutputStream();
								
								ObjectOutputStream oos = new ObjectOutputStream(os);
								
								Message to1 = new Message(0,null,tom.phone,type_message,me,succ,leastpre,highsucc);
								oos.writeObject(to1);
								//after sending the first successor message
							
								//after sending the second successor message
								Log.d("successor","the successor value is :"+succ);
								
								portnum_hash_great1 = Integer.parseInt(succ)*2;
								
								Log.d("portnumber","the portnum is" +portnum_hash_great1);
								
								socket_hash_great1 = new Socket("10.0.2.2",portnum_hash_great1);
								
								OutputStream os1 = socket_hash_great1.getOutputStream();
								ObjectOutputStream oos1 = new ObjectOutputStream(os1);
								Message to2 = new Message(0,null,tom.phone,type_message,tom.phone,"none",leastpre,highsucc);
								oos1.writeObject(to2);
								
								succ = tom.phone;
								
								Log.d("success","new successor"+tom.phone);
								
								
								//updating the new handler with new stuff
								
								}
								
								/*successor is lesser than the new */
								
								else if(generateHash(succ).compareTo(generateHash(tom.phone)) < 0)
								{
									Log.d("hash","successor less than the new");
									
									if(succ.equals(leastpre))
									{
										/*successor is equal to the least predecessor*/ 
										Log.d("success","successor is equal");
										Log.d("hash","hash of "+succ);
								
										if(generateHash(tom.phone).compareTo(generateHash(leastpre)) < 0)
										{
											leastpre = tom.phone;
											Log.d("leastpre","least predecessor is :"+leastpre);
										}
										else if(generateHash(tom.phone).compareTo(generateHash(highsucc)) > 0)
										{
											highsucc = tom.phone;
											Log.d("successor","highest successor is "+highsucc);
										}
									
										Log.d("hash","hash of "+tom.phone);
										//handle.post(myHandler);
										type_message = 2;
										
										portnum_hash_less= Integer.parseInt(tom.phone)*2;	
										socket_hash_less = new Socket("10.0.2.2",portnum_hash_less);
										
										Log.d("portnumber","the portnum is" + portnum_hash_less);
										
										OutputStream os3 = socket_hash_less.getOutputStream();
										ObjectOutputStream oos3 = new ObjectOutputStream(os3);
										Message to3 = new Message(0,null,tom.phone,type_message,me,leastpre,leastpre,highsucc);
										
										oos3.writeObject(to3);//sending the first successor message
										Log.d("successor","the successor value is :"+succ);
										
										portnum_hash_less1 = Integer.parseInt(leastpre)*2;
										Log.d("predecessor","the predecessor value is :"+leastpre);
										
										socket_hash_less1 = new Socket("10.0.2.2",portnum_hash_less1);
										
										OutputStream os4 = socket_hash_less1.getOutputStream();
										ObjectOutputStream oos4 = new ObjectOutputStream(os4);
										Message to4 = new Message(0,null,tom.phone,type_message,tom.phone,"none",leastpre,highsucc);
										oos4.writeObject(to4);
										
										succ = tom.phone;
										
										Log.d("success","new successor"+tom.phone);
									}
									
									else
									{
										/*the part where the successor is not equal to the least predecessor*/
									
										if(generateHash(tom.phone).compareTo(generateHash(leastpre)) < 0)
										{
											leastpre = tom.phone;
											Log.d("leastpre","least predecessor is :"+leastpre);
										}
										else if(generateHash(tom.phone).compareTo(generateHash(highsucc)) > 0)
										{
											highsucc = tom.phone;
											Log.d("successor","highest successor is "+highsucc);
										}
										
										Log.d("successor","successor not same as least pre");
										Log.d("successor","the succecessor value is :"+succ);
										//sending the initial message									
										type_message = 10;
										
										portnum_hash_less2 = Integer.parseInt(succ)*2;
										socket_hash_less2 = new Socket("10.0.2.2",portnum_hash_less2);

										Log.d("portnumber is","the portnumber is :"+portnum_hash_less2);
										
										OutputStream os5 = socket_hash_less2.getOutputStream();
										ObjectOutputStream oos5 = new ObjectOutputStream(os5);
										Message to5 = new Message(0,null,tom.phone,type_message,"none","none",leastpre,highsucc);
										oos5.writeObject(to5);
										
									}	
								}
							}
							
	/*-------------------------------------------------------------------------------------------------------------------------------------------------------*/						
		
							/*the part where the hash value is greater than 5554 initially*/
							
							else if(generateHash(me).compareTo(generateHash(tom.phone)) > 0)
							{
								//the part where the predecessor will come
								Log.d("inside","predecessor part");
								Log.d("hash","hash of "+me);
								Log.d("hash","is less than"+tom.phone);
								
								if(generateHash(pre).compareTo(generateHash(tom.phone)) < 0)
							{	
									/*it's predecessor is less than the requestor*/			
									type_message = 2;
									
									if(generateHash(tom.phone).compareTo(generateHash(leastpre)) < 0)
									{
										leastpre = tom.phone;
										Log.d("leastpre","least predecessor is :"+leastpre);
									}
									
									else if(generateHash(tom.phone).compareTo(generateHash(highsucc)) > 0)
									{
										highsucc = tom.phone;
										Log.d("successor","highest successor is "+highsucc);
									}
										
								Log.d("hash","hash of "+pre);
								Log.d("hash","is greater than"+tom.phone);
								
								portnum_hash_great= Integer.parseInt(tom.phone)*2;
								socket_hash_great = new Socket("10.0.2.2",portnum_hash_great);
								
								Log.d("portnumber","the portnum is" +portnum_hash_great);
								
								OutputStream os6 = socket_hash_great.getOutputStream();
								
								ObjectOutputStream oos6 = new ObjectOutputStream(os6);
								Message to6 = new Message(0,null,tom.phone,type_message,pre,me,leastpre,highsucc);
								oos6.writeObject(to6);
								//after sending the first successor message
							
								//after sending the second successor message
								Log.d("predecessor","the predecessor value is :"+pre);
								
								portnum_hash_great1 = Integer.parseInt(pre)*2;
								
								Log.d("portnumber","the portnum is" +portnum_hash_great1);
								
								socket_hash_great1 = new Socket("10.0.2.2",portnum_hash_great1);
								
								OutputStream os7 = socket_hash_great1.getOutputStream();
								ObjectOutputStream oos7 = new ObjectOutputStream(os7);
								Message to7 = new Message(0,null,tom.phone,type_message,"none",tom.phone,leastpre,highsucc);
								oos7.writeObject(to7);
								
								pre = tom.phone;
								
								Log.d("predess","new successor"+tom.phone);
								
								
								//updating the new handler with new stuff
								
							}
								
								/*successor is lesser than the new */
								
								else if(generateHash(pre).compareTo(generateHash(tom.phone)) > 0)
							{
									Log.d("hash","predecessor less than the new");
									if(pre.equals(highsucc))
									{
										/*highest successor is equal to the least predecessor*/ 
										Log.d("predess","predecessor is equal"+pre);
										Log.d("hash","predecessor is equal to"+highsucc);
									
										if(generateHash(tom.phone).compareTo(generateHash(leastpre)) < 0)
										{
											leastpre = tom.phone;
											Log.d("leastpre","least predecessor is :"+leastpre);
										}
										else if(generateHash(tom.phone).compareTo(generateHash(highsucc)) > 0)
										{
											highsucc = tom.phone;
											Log.d("successor","highest successor is "+highsucc);
										}
									
										type_message = 2;
										
										portnum_hash_less= Integer.parseInt(tom.phone)*2;	
										socket_hash_less = new Socket("10.0.2.2",portnum_hash_less);
										
										Log.d("portnumber","the portnum is" + portnum_hash_less);
										
										OutputStream os8 = socket_hash_less.getOutputStream();
										ObjectOutputStream oos8 = new ObjectOutputStream(os8);
										Message to8 = new Message(0,null,tom.phone,type_message,highsucc,me,leastpre,highsucc);
										oos8.writeObject(to8);//sending the first successor message
										
										Log.d("highest succ","the highest successor value is :"+highsucc);
										
										portnum_hash_less1 = Integer.parseInt(highsucc)*2;									
										socket_hash_less1 = new Socket("10.0.2.2",portnum_hash_less1);
										
										OutputStream os9 = socket_hash_less1.getOutputStream();
										ObjectOutputStream oos9 = new ObjectOutputStream(os9);
										Message to9 = new Message(0,null,tom.phone,type_message,"none",tom.phone,leastpre,highsucc);
										oos9.writeObject(to9);
										
										pre = tom.phone;
										
										Log.d("success","new successor"+tom.phone);
										
									
									}
									
									else if(!pre.equals(highsucc))
									{
										/*the part where the successor is not equal to the least predecessor*/
										Log.d("predecessor","predecessor not same as highest successor"+highsucc);
										Log.d("predecessor","the predecessor value is :"+pre);

										//sending the initial message									
										
										if(generateHash(tom.phone).compareTo(generateHash(leastpre)) < 0)
										{
											leastpre = tom.phone;
											Log.d("leastpre","least predecessor is :"+leastpre);
										}
										else if(generateHash(tom.phone).compareTo(generateHash(highsucc)) > 0)
										{
											highsucc = tom.phone;
											Log.d("successor","highest successor is "+highsucc);
										}
										type_message = 10;
										
										portnum_hash_less2 = Integer.parseInt(pre)*2;
										socket_hash_less2 = new Socket("10.0.2.2",portnum_hash_less2);

										Log.d("portnumber is","the portnumber is :"+portnum_hash_less2);
										
										OutputStream os0 = socket_hash_less2.getOutputStream();
										ObjectOutputStream oos0 = new ObjectOutputStream(os0);
										Message to0 = new Message(0,null,tom.phone,type_message,tom.phone,tom.phone,leastpre,highsucc);
										oos0.writeObject(to0);
										
									}	
								}
							}
							else if(generateHash(me).compareTo(generateHash(tom.phone))==0)
							{
								//do nothing
								//if they are equal
								Log.d("hash","is equal to"+tom.phone);
								//handle.post(myHandler);	
							}
							
						break;
						}						
						
						while(tom.type==2)
						{
						
							Log.d("type","the message type is"+tom.type);	
							
							if(tom.predec.equalsIgnoreCase("none"))
							{
								Log.d("predec","none received");		
							}	
							else
							{
								pre = tom.predec;	
								Log.d("type","the predecessor is"+tom.predec);
							}	
						
							if(tom.success.equalsIgnoreCase("none"))
							{
								Log.d("predec","none received");
							}	
							else
							{	
								succ = tom.success;	
								Log.d("type","the sucessor is"+tom.success);	
							}
							
							leastpre=tom.leastp;
							highsucc = tom.highs;
							Log.d("leastpre","least pre is: "+leastpre);
							Log.d("highsucc","highest succ is :" +highsucc);	
							//handle.post(myHandler);	
							Log.d("update","updating the pre and succ values");		
							break;
						}
						
						while(tom.type==5)
						{
							
							Log.d("message","message type 5 is detected");
							Log.d("sequence","sequence number received is"+tom.data);
							
							if(generateHash(tom.data).compareTo(generateHash(me))<0 )
							{
							    if(generateHash(tom.data).compareTo(generateHash(pre))>0 || me.equals(leastpre))
							    {
							    	Log.d("hash","comparing data");
									//inserting into the content provider of the function 
									Log.d("server","inserting the data in the server thread");
									Log.d("sequence","predecessor same"+pre);
									Log.d("inserting","inserting the data"+tom.data.toString());
									String local_insert = tom.data.toString();
									SQLiteDatabase sqld = mdb.getWritableDatabase();
									assert(tom.data!=null);
									
									values = new ContentValues();
									Log.d("BEFORE ERROR", values + tom.data + teststr);
									teststring = teststring + tom.data.toString();
									Log.d("string","string to be inserted"+teststring); 
									values.put(Mustafadatabase.provider_key, tom.data.toString());
									 values.put(Mustafadatabase.provider_value, teststring);
						        
									 Log.d("server thread","inserting the data"+tom.data);
											
									  long wow = sqld.insert(Mustafadatabase.TABLE_MESSENGER,"", values);
									  teststring = "test";
						             Log.d("server thread","inserting the data"+wow);
							    	}
								else
								{
									Log.d("reached","reached the else part of the server function");
										Log.d("send","predecessor send ");
										//sending it to the predecessor part comes here			
										try 
										{
							     			type_message=5;
							     			
											portnum_hash_less = Integer.parseInt(pre)*2;
											socket_hash_great1 = new Socket("10.0.2.2",portnum_hash_less);
											Log.d("send","sending to the successor"+leastpre);
											
											OutputStream os0 = socket_hash_great1.getOutputStream();
											ObjectOutputStream oos0 = new ObjectOutputStream(os0);
											Message to0 = new Message(0,tom.data,SimpleDhtMainActivity.phonenumber,type_message,null,null,null,null);
											oos0.writeObject(to0);
											
											Log.d("check","going to the successor for checking");
											
										} catch (Exception e) 
										{
											e.printStackTrace();
										} 

									}
							}
							
							if(generateHash(tom.data).compareTo(generateHash(me))>0)
							{
								if(me.equals(leastpre) && generateHash(tom.data).compareTo(generateHash(pre))>0)
								{
									//inserting into the content provider of the function 
									Log.d("equal","either equal to least pre or greater than high succ");
									
									Log.d("sequence","predecessor same"+pre);
									SQLiteDatabase sqld = mdb.getWritableDatabase();
									values = new ContentValues();
									teststring = teststring + tom.data.toString();
									Log.d("string","string to be inserted"+teststring);
									 values.put(Mustafadatabase.provider_key, tom.data.toString());
									 values.put(Mustafadatabase.provider_value, teststring);
						   											
						             flag=1;

						             long dow = sqld.insert(Mustafadatabase.TABLE_MESSENGER,"", values);                
						             Log.d("servd","inse data"+tom.data);
						             Log.d("data","insert"+dow);
						             teststring = "test";
								}
								else
								{
								Log.d("server","hsh greater than me");
								Log.d("server","hshould be send to the succ");
								//sending it to the predecessor part comes here			
								try 
								{
					     			type_message=5;

					     			portnum_hash_test3 = Integer.parseInt(succ)*2;
									socket_hash_succ2 = new Socket("10.0.2.2",portnum_hash_test3);
									
									Log.d("server","the successor is "+succ);
									Log.d("sequence","sequence value is:"+tom.data);
									
									OutputStream os0 = socket_hash_succ2.getOutputStream();
									ObjectOutputStream oos0 = new ObjectOutputStream(os0);
									Message to0 = new Message(0,tom.data,tom.phone,type_message,null,null,null,null);
									oos0.writeObject(to0);
									Log.d("check","going to the successor for checking");
									
								  } catch (Exception e) 
								  {
									e.printStackTrace();
								  } 
								}
								
							}
							
							break;
						}
						while(tom.type==7)
						{
							Log.d("query","selection sequence :" +tom.data);
							Log.d("i","am : "+me);
							try 
							{
										initiator = tom.phone;
										cursor = query(SimpleDhtProvider.my_URI,null,tom.data,null,"type");
										//String curs = cursor_serv.getString(1);
										//String curs_value = cursor_serv.getString(2);
							        	
										if(cursor!=null)
										{
											//sending the type 9 message here
											Log.d("what","am i returning");
										//	Log.d("cursor","key :"+curs);
										//	Log.d("cursor","value : "+curs_value);
											type_message=9;
											portnum_hash_test3 = Integer.parseInt(tom.phone)*2;
											socket_hash_equal = new Socket("10.0.2.2",portnum_hash_test3);
								
											
											OutputStream os0 = socket_hash_equal.getOutputStream();
											ObjectOutputStream oos0 = new ObjectOutputStream(os0);
											Log.d("erached ","before last initiation message");
											manuplate =  manuplate + tom.data;
											Log.d("test","string" + manuplate);
											Message to0 = new Message(0,tom.data,tom.phone,type_message,manuplate,null,null,null);
											oos0.writeObject(to0);
											
											Log.d("send","sending to the predecess");
											manuplate="test";											
										}
							}catch(Exception e)
							{
								e.printStackTrace();
							}
						break;
						}
								
								while(tom.type==9)
								{
								Log.d("message","type " +tom.type);
								String[] menus = new String[] { Mustafadatabase.provider_key, Mustafadatabase.provider_value };
								MatrixCursor matrix = new MatrixCursor(menus);
								Log.d("message","row : "+Mustafadatabase.provider_key);
								//matrix.newRow().add(tom.data).add(tom.phone);
								matrix.addRow(new String[] {tom.data,tom.predec});
								//matrix.addRow(new Object[]{tom.phone});
								
								Log.d("tom","row is :"+tom.data);
								Log.d("tom","column is :"+tom.predec);
//								Log.d("cursor","row is :"+matrix.getString(0));
//								Log.d("cursor","column is :"+matrix.getString(1));
								
								cursor = matrix;
								flag_initiator = false;			
								Log.d("changing","the flag value");
								//flag_initiator = true;
								break;											
								}
					}
				}
			}
		}catch(Exception er){
				er.printStackTrace();
			}
			
		}

		 String generateHash(String input3) throws NoSuchAlgorithmException 
			{
			MessageDigest sha1 = MessageDigest.getInstance("SHA-1");
			byte[] sha1Hash = sha1.digest(input3.getBytes());
			Formatter formatter = new Formatter();		
			for (byte b : sha1Hash) 
			    {
				formatter.format("%02x", b);
				}
				hash_copy = formatter.toString();
				return formatter.toString();
			}

		
}

	class ChordDHT implements Runnable
	{
			ContentValues values;	
		public ChordDHT(ContentValues cv)
		{
			this.values = cv;
		}
		
		public void run() {
			// TODO Auto-generated method stub
			
			try 
			{
				Log.d("tele","reached the teleman funtion "+ SimpleDhtMainActivity.phonenumber);
				
					if(flag_prede==1)
					{								
								Log.d("flag"," predecessor flag ");
								//sending it to the predecessor part comes here			
								try 
								{
					     			type_message=5;
					     			
									portnum_hash_test2 = Integer.parseInt(pre)*2;
									socket_hash_great1 = new Socket("10.0.2.2",portnum_hash_test2);
									Log.d("send","send to pre" + pre);
									
									OutputStream os0 = socket_hash_great1.getOutputStream();
									ObjectOutputStream oos0 = new ObjectOutputStream(os0);
									Message to0 = new Message(0,values.getAsString(Mustafadatabase.provider_key),SimpleDhtMainActivity.phonenumber,type_message,null,null,null,null);
									oos0.writeObject(to0);
									
									Log.d("send","sending to the predecess");
									flag_prede=0;
								} catch (Exception e) 
								{
									e.printStackTrace();
								} 

							}
					
					else if(flag_succe==1)
							{
								
							Log.d("flag","successor flag ");
							//sending it to the predecessor part comes here			
							try 
							{
				     			type_message=5;
				     			
								portnum_hash_less2 = Integer.parseInt(succ)*2;
								socket_hash_succ = new Socket("10.0.2.2",portnum_hash_less2);
								Log.d("send","sending to the successor"+succ);
								OutputStream os0 = socket_hash_succ.getOutputStream();
								ObjectOutputStream oos0 = new ObjectOutputStream(os0);
								Message to0 = new Message(0,values.getAsString(Mustafadatabase.provider_key),SimpleDhtMainActivity.phonenumber,type_message,null,null,null,null);
								oos0.writeObject(to0);
								Log.d("check","going to the successor for checking");
								flag_succe=0;
							} catch (Exception e) 
							{
								e.printStackTrace();
							} 

							}
					//seq++;
					//sequence = Integer.toString(seq);
					//sequence = ""+seq;
					Log.d("value", "sequencer is "+values.getAsString(Mustafadatabase.provider_key));
	}catch(Exception e)
	{
		e.printStackTrace();
	}
		}
		String genHash(String input2) throws NoSuchAlgorithmException 
			{
			MessageDigest sha1 = MessageDigest.getInstance("SHA-1");
			byte[] sha1Hash = sha1.digest(input2.getBytes());
			Formatter formatter = new Formatter();		
			for (byte b : sha1Hash) 
			    {
				formatter.format("%02x", b);
				}
				//hash_value = formatter.toString();
				return formatter.toString();
			}
		
	}
	
	
	class queryDHT implements Runnable
	{
		String selection;
		public queryDHT(String select)
		{
			this.selection=select;
		}
		
		public void run() 
		{
			Log.d("sequence","number received is : "+ selection);
			// TODO Auto-generated method stub
			Log.d("query","inside query thread");
			Log.d("query","i am"+SimpleDhtMainActivity.phonenumber);
			try 
			{
				Log.d("initial","the initiator is "+initiator);
				
				type_message=7;
     			
				portnum_hash_less2 = Integer.parseInt(succ)*2;
				socket_hash_succ = new Socket("10.0.2.2",portnum_hash_less2);
		
				Log.d("send","sending to the successor"+succ);
				
				OutputStream os0 = socket_hash_succ.getOutputStream();
				ObjectOutputStream oos0 = new ObjectOutputStream(os0);
				
				Message to0 = new Message(0,selection,initiator,type_message,null,null,null,null);
				oos0.writeObject(to0);
				
				Log.d("check","going to the successor for checking");
				flag_query_thread=0;
			} catch (Exception e) 
			{
				e.printStackTrace();
			} 
			
			
			
		}
		
		
	}
	
	
	
	/*public ChordDHT getChord(ContentResolver cr)
	{
		
		return new ChordDHT(cr);
	}*/

	String genHash(String input2) throws NoSuchAlgorithmException 
	{
	MessageDigest sha1 = MessageDigest.getInstance("SHA-1");
	byte[] sha1Hash = sha1.digest(input2.getBytes());
	Formatter formatter = new Formatter();		
	for (byte b : sha1Hash) 
	    {
		formatter.format("%02x", b);
		}
		//hash_value = formatter.toString();
		return formatter.toString();
	}

}