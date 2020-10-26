package net.rebi.koolsheProducts.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import net.rebi.koolsheProducts.R;
import net.rebi.koolsheProducts.arraies;

public class MainActivity extends AppCompatActivity {

    Button export, clear, dataSize, changeName, changeMarket, changeID;
    EditText editText;
    TextView textView;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );


        setTitle ( "الإعدادات" );
        export = findViewById ( R.id.export );
        clear = findViewById ( R.id.clear );
        dataSize = findViewById ( R.id.dataSize );
        changeName = findViewById ( R.id.changeName );
        changeMarket = findViewById ( R.id.changeMarket );
        textView = findViewById ( R.id.textView );
        changeID = findViewById ( R.id.changeID );


        export.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {
                AlertDialogExport ( );
            }
        } );


        clear.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {

                showAlertDialogClear ( );

            }
        } );

        dataSize.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {
                arraies.loadRealData ( getBaseContext ( ) );
                int count = 0;
                for ( int i = 0 ; i < arraies.sections.size ( ) ; i++ ) {
                    if ( arraies.sections.get ( i ).getSectionName ( ).equals ( "###" ) ) {
                        count++;
                    }
                }
                Toast.makeText ( MainActivity.this , "عدد المنتجات : " + count ,
                                 Toast.LENGTH_SHORT ).show ( );
            }
        } );

        changeName.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {
                getSharedPreferences ( "koolshe" , MODE_PRIVATE ).edit ( ).putBoolean ( "isNamed" , false ).apply ( );
                Intent intent = new Intent ( getBaseContext ( ) , BasicInfoActivity.class );
                startActivity ( intent );
                finish ( );
            }
        } );

        changeMarket.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {
                showAlertDialogButtonClicked ( );
            }
        } );

        changeID.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {
                showAlertDialogButtonClicked2 ( );
            }
        } );
    }

    //    public void writeCSV ( ) {
    //        String name = getSharedPreferences ( "koolshe" , MODE_PRIVATE ).getString ( "name"
    //        , "" );
    //
    //        int filesCount =
    //                getSharedPreferences ( "koolshe" , MODE_PRIVATE ).getInt ( "filesCount" , 0 );
    //        getSharedPreferences ( "koolshe" , MODE_PRIVATE ).edit ( ).putInt ( "filesCount" ,
    //                                                                            ++ filesCount )
    //                                                                            .apply ( );
    //
    //        String csv = (
    //                Environment.getExternalStorageDirectory ( ).getAbsolutePath ( ) + "/" +
    //                name + " "
    //                + "CsvFile " + filesCount + ".csv"
    //        ); // Here csv file name is MyCsvFile.csv
    //
    //        CSVWriter writer = null;
    //        try {
    //            writer = new CSVWriter ( new FileWriter ( csv ) );
    //
    //            arraies.loadRealData ( this );
    //            List < String[] > data = new ArrayList <> ( );
    //            for ( int i = 0 ; i < arraies.sections.size ( ) ; i++ ) {
    //                data.add ( new String[] { arraies.sections.get ( i ).getSectionName ( ) ,
    //                        arraies.sections.get ( i ).getSectionContent ( ) } );
    //            }
    //
    //            writer.writeAll ( data ); // data is adding to csv
    //
    //            writer.close ( );
    //            //            callRead();
    //            Toast.makeText ( this , "تم" , Toast.LENGTH_LONG ).show ( );
    //        }
    //        catch ( IOException e ) {
    //            Toast.makeText ( this , "حدث خطأ" , Toast.LENGTH_LONG ).show ( );
    //            e.printStackTrace ( );
    //        }
    //    }

    public void showAlertDialogClear ( ) {
        AlertDialog.Builder builder = new AlertDialog.Builder ( this );
        builder.setTitle ( "مسح البيانات" );
        builder.setMessage ( "هل أنت متأكد ؟" );
        builder.setPositiveButton ( "نعم" , new DialogInterface.OnClickListener ( ) {
            @Override
            public void onClick ( DialogInterface dialog , int which ) {

                arraies.sections.clear ( );
                arraies.saveRealData ( getBaseContext ( ) );
                Toast.makeText ( getBaseContext ( ) , "تم مسح البيانات" , Toast.LENGTH_LONG ).show ( );

            }
        } );
        AlertDialog dialog = builder.create ( );
        dialog.show ( );
    }

    public void AlertDialogExport ( ) {
        AlertDialog.Builder builder = new AlertDialog.Builder ( this );
        builder.setTitle ( "استخراج البيانات" );
        builder.setMessage ( "هل أنت متأكد ؟" );
        builder.setPositiveButton ( "نعم" , new DialogInterface.OnClickListener ( ) {
            @Override
            public void onClick ( DialogInterface dialog , int which ) {

                arraies.loadRealData ( getBaseContext ( ) );
                if ( arraies.sections.size ( ) == 0 ) {
                    Toast.makeText ( MainActivity.this , "ليس لديك بيانات" , Toast.LENGTH_SHORT ).show ( );
                }
                else {
                    //                    writeCSV ( );
                    StringBuilder data = new StringBuilder ( );
                    for ( int i = 0 ; i < arraies.sections.size ( ) ; i++ ) {
                        data.
                                append ( '"' ).
                                append ( arraies.sections.get ( i ).getSectionName ( ) ).
                                append ( '"' ).

                                append ( "," ).

                                append ( '"' ).
                                append ( arraies.sections.get ( i ).getSectionContent ( ) ).
                                append ( '"' ).

                                append ( "\n" );
                    }

                    textView.setText ( data );
                    System.err.println ( data );

                }

            }
        } );
        AlertDialog dialog = builder.create ( );
        dialog.show ( );
    }

    public void showAlertDialogButtonClicked ( ) {
        AlertDialog.Builder builder = new AlertDialog.Builder ( this );
        builder.setTitle ( "اسم المتجر" );

        builder.setMessage ( "أدخل اسم المتجر" );
        LinearLayout customLayout = new LinearLayout ( this );
        customLayout.setOrientation ( LinearLayout.VERTICAL );
        editText = new EditText ( this );
        //        editText.setHint ( "أدخل اسم المتجر" );
        customLayout.addView ( editText );
        editText.setText ( getSharedPreferences ( "koolshe" , MODE_PRIVATE ).getString ( "market" , "" ) );
        builder.setView ( customLayout );
        builder.setPositiveButton ( getString ( R.string.add ) ,
                                    new DialogInterface.OnClickListener ( ) {
            @Override
            public void onClick ( DialogInterface dialog , int which ) {

                getSharedPreferences ( "koolshe" , MODE_PRIVATE ).edit ( ).putString ( "market" ,
                                                                                       editText.getText ( ).toString ( ).trim ( ) ).apply ( );

            }
        } );
        AlertDialog dialog = builder.create ( );
        dialog.show ( );
    }


    public void showAlertDialogButtonClicked2 ( ) {
        AlertDialog.Builder builder = new AlertDialog.Builder ( this );
        builder.setTitle ( "ID" );

        builder.setMessage ( "أدخل الحرف المستخدم" );
        LinearLayout customLayout = new LinearLayout ( this );
        customLayout.setOrientation ( LinearLayout.VERTICAL );
        editText = new EditText ( this );
        customLayout.addView ( editText );
        editText.setText ( getSharedPreferences ( "koolshe" , MODE_PRIVATE ).getString ( "ID" ,
                                                                                         "" ) );
        builder.setView ( customLayout );
        builder.setPositiveButton ( getString ( R.string.add ) ,
                                    new DialogInterface.OnClickListener ( ) {
            @Override
            public void onClick ( DialogInterface dialog , int which ) {

                getSharedPreferences ( "koolshe" , MODE_PRIVATE ).edit ( ).putString ( "ID" ,
                                                                                       editText.getText ( ).toString ( ).trim ( ) ).apply ( );

            }
        } );
        AlertDialog dialog = builder.create ( );
        dialog.show ( );
    }


}
