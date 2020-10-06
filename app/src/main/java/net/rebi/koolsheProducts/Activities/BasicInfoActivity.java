package net.rebi.koolsheProducts.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import net.rebi.koolsheProducts.Calsses.Category;
import net.rebi.koolsheProducts.R;

public class BasicInfoActivity extends AppCompatActivity {

    SharedPreferences        sharedPreferences;
    SharedPreferences.Editor editor;

    EditText name;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        sharedPreferences = getSharedPreferences ( "koolshe" , MODE_PRIVATE );
//        if ( sharedPreferences.getBoolean ( "isNamed" , false ) ) {
//            Intent intent = new Intent ( this , CategoryActivity.class );
//            startActivity ( intent );
//            finish ( );
//        }
        setContentView ( R.layout.activity_basic_info );

        name = findViewById ( R.id.name );
    }

    public void main ( View view ) {
        if ( name.getText ( ).toString ( ).trim ( ).isEmpty ( ) ) {
            Toast.makeText ( this , getString ( R.string.EnterYourName ) , Toast.LENGTH_SHORT ).show ( );
        }
        else {

            String                   nameSTR = name.getText ( ).toString ( );
            SharedPreferences.Editor editor  = sharedPreferences.edit ( );
            editor.putString ( "name" , nameSTR );
            editor.putBoolean ( "isNamed" , true );
            editor.apply ( );

//
//            Intent intent = new Intent ( this , CategoryActivity.class );
//            startActivity ( intent );
            finish ( );
        }
    }


    @Override
    public void onBackPressed ( ) {

    }
}
