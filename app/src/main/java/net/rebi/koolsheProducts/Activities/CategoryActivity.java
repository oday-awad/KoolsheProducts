package net.rebi.koolsheProducts.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import net.rebi.koolsheProducts.Calsses.Category;
import net.rebi.koolsheProducts.CustomAdapter;
import net.rebi.koolsheProducts.R;
import net.rebi.koolsheProducts.arraies;

import java.util.Objects;

public class CategoryActivity extends AppCompatActivity {

    EditText      editText;
    CustomAdapter customAdapter;
    Switch        switch1;
    Button        setting;
    Button        newCategory, newProduct;

    SharedPreferences sharedPreferences;
    String categoryTag;
    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_category );
        switch1 = findViewById ( R.id.switch1 );
        setting = findViewById ( R.id.setting );

        Intent inten       = getIntent ( );
         categoryTag = null;
        try {
            categoryTag =
                    Objects.requireNonNull ( inten.getExtras ( ) ).getString ( "categoryTag","categories" );
        }
        catch ( Exception e ) {
            categoryTag = "categories";
            e.printStackTrace ( );
        }
        sharedPreferences = getSharedPreferences ( "koolshe" , MODE_PRIVATE );


        sharedPreferences.edit ().putString ( "categoryTag" , categoryTag ).apply ();

        setTitle ( categoryTag );

        if ( ! sharedPreferences.getBoolean ( "isNamed" , false ) ) {
            Intent intent = new Intent ( this , BasicInfoActivity.class );
            startActivity ( intent );
        }
        else {
//            Toast.makeText ( this ,
//                             "Welcome Back " + sharedPreferences.getString ( "name" , "" ) ,
//                             Toast.LENGTH_SHORT ).show ( );
        }
        newCategory = findViewById ( R.id.newCategory );
        newProduct = findViewById ( R.id.newProduct );

        newCategory.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View view ) {
                showAlertDialogButtonClicked ( );
            }
        } );

        newProduct.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View view ) {
//                Intent intent = new Intent(getBaseContext () , AddActivity.class);
//                startActivity ( intent );
            }
        } );


        switch1.setOnCheckedChangeListener ( new CompoundButton.OnCheckedChangeListener ( ) {
            @Override
            public void onCheckedChanged ( CompoundButton buttonView , boolean isChecked ) {
                if ( isChecked ) {

                    findViewById ( R.id.scrollView ).setAlpha ( 0.5f );
                    arraies.IsDeleting = true;
                }
                else {
                    findViewById ( R.id.scrollView ).setAlpha ( 1 );
                    arraies.IsDeleting = false;
                }
            }
        } );


        setting.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {
                Intent intent = new Intent ( getBaseContext ( ) , MainActivity.class );
                startActivity ( intent );
            }
        } );


        arraies.loadCategories ( this ,categoryTag);

        RecyclerView               rv            = findViewById ( R.id.rv );
        RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager ( 2 , 1 );
        customAdapter = new CustomAdapter ( this , arraies.categories );
        rv.setLayoutManager ( layoutManager );
        rv.setAdapter ( customAdapter );

    }


    public void showAlertDialogButtonClicked ( ) {
        AlertDialog.Builder builder = new AlertDialog.Builder ( this );
        builder.setTitle ( getString ( R.string.EnterNewCategoryName ) );

        LinearLayout customLayout = new LinearLayout ( this );
        customLayout.setOrientation ( LinearLayout.VERTICAL );
        editText = new EditText ( this );
        editText.setHint ( getString ( R.string.AddAnewSection ) );
        customLayout.addView ( editText );

        builder.setView ( customLayout );
        builder.setPositiveButton ( getString ( R.string.add ) ,
                                    new DialogInterface.OnClickListener ( ) {
            @Override
            public void onClick ( DialogInterface dialog , int which ) {
                sendDialogDataToActivity ( editText.getText ( ).toString ( ).trim () );
            }
        } );
        AlertDialog dialog = builder.create ( );
        dialog.show ( );
    }


    private void sendDialogDataToActivity ( String data ) {
        if ( ! data.trim ( ).isEmpty ( ) ) {
            //todo
            arraies.categories.add ( new Category ( data , getTitle ( ).toString ( )+"-"+data ,
                                                    new String[] { "Name" , "Price" , "Brand" } ) );

            arraies.saveCategories ( this ,categoryTag);
            customAdapter.notifyDataSetChanged ( );
        }
    }

    @Override
    protected void onResume ( ) {
        sharedPreferences.edit ().putString ( "categoryTag" , getTitle ().toString () ).apply ();
        super.onResume ( );
    }

}
