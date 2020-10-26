package net.rebi.koolsheProducts.Calsses;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

public class Mydatabase extends SQLiteOpenHelper {

    public static final String DB_NAME    = "Categories_db";
    public static final int    DB_VERSION = 1;
    public static final String TABLE_NAME = "Categories";
    Context context;

    public Mydatabase ( Context context ) {
        super ( context , DB_NAME , null , DB_VERSION );
        this.context = context;
    }

    @Override
    public void onCreate ( SQLiteDatabase db ) {

        db.execSQL ( "CREATE TABLE " + TABLE_NAME + " ( name TEXT , parent TEXT ,sectionsNames "
                     + "TEXT ,nameParent TEXT )" );
    }

    @Override
    public void onUpgrade ( SQLiteDatabase db , int oldVersion , int newVersion ) {
        db.execSQL ( "DROP TABLE IF EXISTS " + TABLE_NAME );
        onCreate ( db );

    }

    public boolean insertCategory ( Category1 category1 ) {

        SQLiteDatabase db = getWritableDatabase ( );

        ContentValues values = new ContentValues ( );
        values.put ( "name" , category1.getName ( ) );
        values.put ( "parent" , category1.getParent ( ) );
        values.put ( "sectionsNames" , category1.getSectionsNames ( ) );
        values.put ( "nameParent" , category1.getName ( )+category1.getParent ( ) );
        long result = db.insert ( TABLE_NAME , null , values );

        return result != - 1;
    }

    public boolean updateCategory ( Category1 category1 ) {

        SQLiteDatabase db = getWritableDatabase ( );

        ContentValues values = new ContentValues ( );
        values.put ( "name" , category1.getName ( ) );
        values.put ( "parent" , category1.getParent ( ) );
        values.put ( "sectionsNames" , category1.getSectionsNames ( ) );

        String[] name = { category1.getName ( ) };

        long result = db.update ( TABLE_NAME , values , "name = ?" , name );

        return result > 0;
    }

    public boolean deleteCategory ( Category1 category1 ) {
        SQLiteDatabase db = getWritableDatabase ( );

        String[] nameParent = { category1.getName ( )+category1.getParent () };
        String[] parent = { category1.getName () };


        int result = db.delete ( TABLE_NAME , "nameParent = ?" , nameParent );

        db.delete ( TABLE_NAME , "parent = ?" , parent);
        return result > 0;
    }

    public void deleteCategory ( String name1 , String parent1 ) {
        Toast.makeText ( context, "name " + name1 , Toast.LENGTH_SHORT ).show ( );
        SQLiteDatabase db = getWritableDatabase ( );

        String[] x = { name1 };

        db.execSQL ( "DELETE  FROM  " + TABLE_NAME + " WHERE name = " + name1 + " AND parent = " + parent1 );
        //        int result = db.delete ( TABLE_NAME , "name = ?" , x );

        //        return result > 0;
    }

    public ArrayList < Category1 > getCategories ( String parent ) {
        ArrayList < Category1 > categories = new ArrayList <> ( );

        SQLiteDatabase db = getReadableDatabase ( );
        String[]       x  = { parent };


        Cursor cursor = db.rawQuery ( "SELECT * FROM " + TABLE_NAME + " WHERE parent = ?" , x );

        if ( cursor.moveToFirst ( ) ) {
            do {
                String cateName          = cursor.getString ( 0 );
                String cateParent        = cursor.getString ( 1 );
                String cateSectionsNames = cursor.getString ( 2 );
                categories.add ( new Category1 ( cateName , cateParent , cateSectionsNames ) );
            }
            while ( cursor.moveToNext ( ) );
            cursor.close ( );
        }


        return categories;
    }


    //    public ArrayList < Category1 > getCategoryByName ( String name ,String parent ) {
    //        ArrayList < Category1 > categories = new ArrayList <> ( );
    //
    //        SQLiteDatabase db = getReadableDatabase ( );
    //        String[]       x  = { parent };
    //
    //
    //        Cursor cursor = db.rawQuery ( "SELECT * FROM " + TABLE_NAME + " WHERE parent =?" ,
    //        x );
    //
    //        if ( cursor.moveToFirst ( ) ) {
    //            do {
    //                String cateName = cursor.getString ( 0 );
    //                String cateParent = cursor.getString ( 1 );
    //                String cateSectionsNames = cursor.getString ( 2 );
    //                categories.add ( new Category1 ( cateName , cateParent , cateSectionsNames
    //                ) );
    //            }
    //            while ( cursor.moveToNext ( ) );
    //            cursor.close ();
    //        }
    //
    //
    //        return categories;
    //    }

}
