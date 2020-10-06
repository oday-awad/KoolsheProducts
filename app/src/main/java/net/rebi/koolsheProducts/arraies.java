package net.rebi.koolsheProducts;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import net.rebi.koolsheProducts.Calsses.Category;
import net.rebi.koolsheProducts.Calsses.Section;

import java.lang.reflect.Type;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class arraies {

    public static ArrayList < Category > categories = new ArrayList <> ( );

    public static ArrayList < Section > sections = new ArrayList <> ( );

    public static boolean IsDeleting;




    public static void saveCategories ( Context context ,String categoryTag) {
        SharedPreferences        sharedPreferences =
                context.getSharedPreferences ( "koolshe" , MODE_PRIVATE );
        SharedPreferences.Editor editor            = sharedPreferences.edit ( );
        Gson                     gson              = new Gson ( );
        String                   json              = gson.toJson ( arraies.categories );
        editor.putString ( categoryTag , json );
        editor.apply ( );
    }

    public static void loadCategories ( Context context ,String categoryTag) {
        SharedPreferences sharedPreferences = context.getSharedPreferences ( "koolshe" , MODE_PRIVATE );
        Gson              gson              = new Gson ( );
        String            json              = sharedPreferences.getString ( categoryTag , null );
        Type type = new TypeToken < ArrayList < Category > > ( ) { }.getType ( );
        arraies.categories = gson.fromJson ( json , type );
        if ( arraies.categories == null ) {
            arraies.categories = new ArrayList <> ( );
        }
    }

    public static void saveRealData ( Context context ) {
        SharedPreferences sharedPreferences = context.getSharedPreferences ( "koolshe" , MODE_PRIVATE );
        SharedPreferences.Editor editor = sharedPreferences.edit ( );
        Gson                     gson   = new Gson ( );
        String                   json   = gson.toJson ( arraies.sections );
        editor.putString ( "realData" , json );
        editor.apply ( );
    }


    public static void loadRealData ( Context context ) {
        SharedPreferences sharedPreferences = context.getSharedPreferences ( "koolshe" , MODE_PRIVATE );
        Gson              gson              = new Gson ( );
        String            json              = sharedPreferences.getString ( "realData" , null );
        Type              type              =
                new TypeToken < ArrayList < Section > > ( ) { }.getType ( );
        arraies.sections = gson.fromJson ( json , type );
        if ( arraies.sections == null ) {
            arraies.sections = new ArrayList <> ( );
        }
    }


}
