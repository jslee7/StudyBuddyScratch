package com.example.studybuddy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter 
{ 
    public static final String CARD_ID = "_id";
    public static final String FRONT_CARD = "fcard";
    public static final String BACK_CARD = "bcard";
    public static final String CARD_HINT = "cardhint";    
    private static final String TAG = "DBAdapter";
    
    private static final String DATABASE_NAME = "flashcard";
    private static final String DATABASE_TABLE = "subject";
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE =
        "create table subject (_id integer primary key autoincrement, "
        + "isbn text not null, title text not null, " 
        + "publisher text not null);";
        
    private final Context context; 
	//private DatabaseHelper DBHelper;
	private SQLiteDatabase db; 
	
	public DBAdapter(Context ctx){
		this.context = ctx;
	//	this.DBHelper = new DatabaseHelper(context);
	}
	
	/*private static class DatabaseHelper extends SQLiteOpenHelper{
		DatabaseHelper(Context context){
			super(context,DATABASE_NAME, null, DATABASE_VERSION);
		}
	}
	
	@Override
	public void onCreate(SQLiteDatabase db){
		db.execSQL(DATABASE_CREATE);
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion,int newVersion){
		Log.w(TAG, "Upgrading database from version " + oldVersion 
				+ " to " + newVersion);
		db.execSQL("DROP TABLE IF EXISTS subject");
		onCreate(db);
	
	}
	
	public DBAdapter open() throws SQLException{
		db = DBHelper.getWriteableDatabase();
		return this;
	}*/
	
	public void close(){
	//	DBHelper.close();
	}
	  //---insert a title into the database---
    public long insertCard(String fcard, String bcard, String hint) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(FRONT_CARD, fcard);
        initialValues.put(BACK_CARD, bcard);
        initialValues.put(CARD_HINT, hint);
        return db.insert(DATABASE_TABLE, null, initialValues);
    }

    //---deletes a particular title---
    public boolean deleteCard(long rowId){
        return db.delete(DATABASE_TABLE, CARD_ID + 
        		"=" + rowId, null) > 0;
    }

    //---retrieves all the titles---
    public Cursor getAllCards(){
        return db.query(DATABASE_TABLE, new String[] {
        		CARD_ID, 
        		FRONT_CARD,
        		BACK_CARD,
                CARD_HINT}, 
                null, 
                null, 
                null, 
                null, 
                null);
    }

    //---retrieves a particular title---
    public Cursor getCard(long rowId) throws SQLException{
        Cursor mCursor =
                db.query(true, DATABASE_TABLE, new String[] {
                		CARD_ID,
                		FRONT_CARD, 
                		BACK_CARD,
                		CARD_HINT
                		}, 
                		CARD_ID + "=" + rowId, 
                		null,
                		null, 
                		null, 
                		null, 
                		null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    //---updates a title---
    public boolean updateCard(long rowId, String fcard, 
    String subj, String bcard, String hint) 
    {
        ContentValues args = new ContentValues();
        args.put(FRONT_CARD, fcard);
        args.put(BACK_CARD, bcard);
		args.put(CARD_HINT, hint);
        return db.update(DATABASE_TABLE, args, 
                         CARD_ID + "=" + rowId, null) > 0;
    }
}
