//package net.rebi.koolsheProducts.Activities;
//
//import android.annotation.SuppressLint;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.graphics.Color;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.LinearLayout;
//import android.widget.ScrollView;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.google.gson.Gson;
//import com.google.gson.reflect.TypeToken;
//
//import net.rebi.koolsheProducts.Calsses.Category;
//import net.rebi.koolsheProducts.R;
//import net.rebi.koolsheProducts.arraies;
//
//import java.lang.reflect.Type;
//import java.util.ArrayList;
//import java.util.Objects;
//
//public class EditCategory extends AppCompatActivity {
//
//    Intent                 intent;
//    int                    count         = 0;
//    ArrayList < EditText > basicSections = new ArrayList <> ( );
//    LinearLayout           layout;
//
//    @Override
//    protected void onCreate ( Bundle savedInstanceState ) {
//        super.onCreate ( savedInstanceState );
//
//        //        setContentView ( R.layout.activity_edit_category );
//
//
//        intent = getIntent ( );
//
//        final int position = Objects.requireNonNull ( intent.getExtras ( ) ).getInt ( "position" );
//
//        String categoTag = intent.getExtras ( ).getString ( "parent" );
//
//        Toast.makeText ( this , categoTag, Toast.LENGTH_SHORT ).show ( );
//        arraies.loadCategories ( this , categoTag );
//        layout = new LinearLayout ( this );
//        layout.setOrientation ( LinearLayout.VERTICAL );
//
//        ScrollView scrollView = new ScrollView ( this );
//        scrollView.addView ( layout );
//
//
//        arraies.loadCategories ( this , categoTag );
////todo
////        final Category category = arraies.categories.get ( position );
//
//        System.err.println ( arraies.categories.get ( position ).getName ( ) );
//
//        assert basicSections != null;
//        //todo
////        for ( int i = 0 ; i < category.getSectionsNames ( ).length ; i++ ) {
////            layout.addView ( newSection ( count++ , category.getSectionsNames ( )[ i ] ) );
////        }
//        Button newSection = new Button ( this );
//
//        newSection.setText ( getString ( R.string.newSection ) );
//
//        newSection.setOnClickListener ( new View.OnClickListener ( ) {
//            @Override
//            public void onClick ( View v ) {
//                layout.addView ( newSection ( count++ , "" ) , count - 1 );
//            }
//        } );
//        layout.addView ( newSection );
//
//        Button save = new Button ( this );
//
//        save.setText ( getString ( R.string.save ) );
//
//        save.setOnClickListener ( new View.OnClickListener ( ) {
//            @Override
//            public void onClick ( View v ) {
//                //todo
////                String categoryName = category.getName ( );
////                String categoryTag  = category.getCategoryTag ( );
//
//                ArrayList < String > arrayList = new ArrayList <> ( );
//
//                for ( int i = 0 ; i < basicSections.size ( ) ; i++ ) {
//                    if ( ! basicSections.get ( i ).getText ( ).toString ( ).trim ( ).isEmpty ( ) ) {
//                        arrayList.add ( basicSections.get ( i ).getText ( ).toString ( ).trim ( ) );
//                    }
//                }
//
//                String[] sections = new String[ arrayList.size ( ) ];
//
//                for ( int i = 0 ; i < arrayList.size ( ) ; i++ ) {
//                    sections[ i ] = arrayList.get ( i );
//                }
//
//                //Delete the current data
//                arraies.categories.remove ( position );
//
//                //Add the new data
//                //todo
////                arraies.categories.add ( position , new Category ( categoryName , categoryTag ,
////                                                                   sections ) );
//
//                Toast.makeText ( EditCategory.this , getString ( R.string.save ) ,
//                                 Toast.LENGTH_SHORT ).show ( );
//                //todo
////                arraies.saveCategories ( getBaseContext () , categoryTag );
//                finish ( );
//            }
//        } );
//        layout.addView ( save );
//        setContentView ( scrollView );
//
//
//    }
//
//
//    @SuppressLint ( "SetTextI18n" )
//    private LinearLayout newSection ( int count , String basicSection ) {
//
//        LinearLayout section = new LinearLayout ( this );
//        section.setOrientation ( LinearLayout.VERTICAL );
//        section.setLayoutParams ( new LinearLayout.LayoutParams ( LinearLayout.LayoutParams.MATCH_PARENT , LinearLayout.LayoutParams.WRAP_CONTENT ) );
//        section.setPadding ( 20 , 20 , 20 , 20 );
//
//        if ( count % 2 == 0 ) {
//            section.setBackgroundColor ( Color.rgb ( 199 , 188 , 188 ) );
//        }
//        else {
//            section.setBackgroundColor ( Color.rgb ( 236 , 223 , 223 ) );
//        }
//
//
//        EditText sectionName = new EditText ( this );
//        sectionName.setLayoutParams ( new LinearLayout.LayoutParams ( LinearLayout.LayoutParams.MATCH_PARENT , LinearLayout.LayoutParams.MATCH_PARENT ) );
//        sectionName.setText ( basicSection );
//        section.addView ( sectionName );
//        //End Section Content
//
//
//        basicSections.add ( sectionName );
//
//        return section;
//    }
//
//
//
//
//
//}
