package net.rebi.koolsheProducts.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import net.rebi.koolsheProducts.Calsses.Category;
import net.rebi.koolsheProducts.Calsses.Section;
import net.rebi.koolsheProducts.R;
import net.rebi.koolsheProducts.arraies;

import java.util.ArrayList;

public class AddActivity extends AppCompatActivity {

    Intent   intent;
    Category category;
    private int                    count                 = 0;
    private LinearLayout           layout;
    private ArrayList < EditText > sectionsNames         = new ArrayList <> ( );
    private ArrayList < TextView > sectionsNamesBasic    = new ArrayList <> ( );
    private ArrayList < EditText > sectionsContents      = new ArrayList <> ( );
    private ArrayList < EditText > sectionsContentsBasic = new ArrayList <> ( );
    private ArrayList < String >   basicSections         = new ArrayList <> ( );

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );


        intent = getIntent ( );
        //        final int position = Objects.requireNonNull ( intent.getExtras ( ) ).getInt (
        //        "position" );

        //        String parent = Objects.requireNonNull ( intent.getExtras ( ) ).getString (
        //        "parent" );

        //        category = arraies.aSASaSas.get ( position );

        //        arraies.loadCategories ( this  , categoryTag);
        //todo
        //        category = arraies.categories.get ( 0 );

        //        setTitle ( parent );

        ScrollView scrollView = new ScrollView ( this );

        layout = new LinearLayout ( this );
        layout.setOrientation ( LinearLayout.VERTICAL );
        scrollView.addView ( layout );


        String market =
                getSharedPreferences ( "koolshe" , MODE_PRIVATE ).getString ( "market" , "" );

        String ID =
                getSharedPreferences ( "koolshe" , MODE_PRIVATE ).getString ( "ID" , "" );


        layout.addView ( newSectionBasic ( count++ , "المتجر" , market , InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL  ) );
        layout.addView ( newSectionBasic ( count++ , "ID" , ID , InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL ) );
        layout.addView ( newSectionBasic ( count++ , "اسم المنتج" , "" ,
                                           InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL ) );
        layout.addView ( newSectionBasic ( count++ , "الفئة" , "" , InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL ) );
        layout.addView ( newSectionBasic ( count++ , "السعر" , "" ,
                                           InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL) );
        layout.addView ( newSectionBasic ( count++ , "الوزن" , "" ,
                                           InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL ) );
        layout.addView ( newSectionBasic ( count++ , "الوزن الكلي" , "" ,
                                           InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL ) );
        layout.addView ( newSectionBasic ( count++ , "الحجم" , "" ,
                                           InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL ) );
        layout.addView ( newSectionBasic ( count++ , "الشركة المصنعة" , "" ,
                                           InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL ) );
        layout.addView ( newSectionBasic ( count++ , "بلد الصنع" , "" ,
                                           InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL ) );
        layout.addView ( newSectionBasic ( count++ , "المحتويات" , "" ,
                                           InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL ) );

        //        for ( int i = 0 ; i < category.getSectionsNames ( ).length ; i++ ) {
        //            layout.addView ( newSectionBasic ( count++ , category.getSectionsNames ( )[
        //            i ] ,
        //                                               "" ) );
        //        }


        layout.addView ( actionButtons ( ) );


        setContentView ( scrollView );
    }

    @Override
    public void onBackPressed ( ) {
        showAlertDialogBack ( );
    }

    private LinearLayout newSection ( int count ) {

        LinearLayout section = new LinearLayout ( this );
        section.setOrientation ( LinearLayout.VERTICAL );
        section.setLayoutParams ( new LinearLayout.LayoutParams ( LinearLayout.LayoutParams.MATCH_PARENT , LinearLayout.LayoutParams.WRAP_CONTENT ) );
        section.setPadding ( 20 , 20 , 20 , 20 );

        if ( count % 2 == 0 ) {
            section.setBackgroundColor ( Color.rgb ( 199 , 188 , 188 ) );
        }
        else {
            section.setBackgroundColor ( Color.rgb ( 236 , 223 , 223 ) );
        }

        //Start Section Name
        TextView tv_1 = new TextView ( this );
        tv_1.setLayoutParams ( new LinearLayout.LayoutParams ( LinearLayout.LayoutParams.MATCH_PARENT , LinearLayout.LayoutParams.MATCH_PARENT ) );
        tv_1.setText ( getString ( R.string.sectionName ) );
        section.addView ( tv_1 );

        EditText sectionName = new EditText ( this );
        sectionName.setLayoutParams ( new LinearLayout.LayoutParams ( LinearLayout.LayoutParams.MATCH_PARENT , LinearLayout.LayoutParams.MATCH_PARENT ) );
        sectionName.setTextSize ( 24 );
        section.addView ( sectionName );
        //End Section Name

        section.addView ( newDivider ( ) );

        //Start Section Content
        TextView tv_2 = new TextView ( this );
        tv_2.setLayoutParams ( new LinearLayout.LayoutParams ( LinearLayout.LayoutParams.MATCH_PARENT , LinearLayout.LayoutParams.MATCH_PARENT ) );
        tv_2.setText ( getString ( R.string.sectionContent ) );
        section.addView ( tv_2 );

        EditText sectionContent = new EditText ( this );
        sectionContent.setLayoutParams ( new LinearLayout.LayoutParams ( LinearLayout.LayoutParams.MATCH_PARENT , LinearLayout.LayoutParams.MATCH_PARENT ) );
        section.addView ( sectionContent );
        //End Section Content


        sectionsNames.add ( sectionName );
        sectionsContents.add ( sectionContent );

        return section;
    }

    private LinearLayout newSectionBasic (
            int count , String SectionNameString , String SectionContentString , int inputeType
    ) {

        LinearLayout section = new LinearLayout ( this );
        section.setOrientation ( LinearLayout.VERTICAL );
        section.setLayoutParams ( new LinearLayout.LayoutParams ( LinearLayout.LayoutParams.MATCH_PARENT , LinearLayout.LayoutParams.WRAP_CONTENT ) );
        section.setPadding ( 20 , 20 , 20 , 20 );

        if ( count % 2 == 0 ) {
            section.setBackgroundColor ( Color.rgb ( 199 , 188 , 188 ) );
        }
        else {
            section.setBackgroundColor ( Color.rgb ( 236 , 223 , 223 ) );
        }
        //Start Section Name

        TextView sectionName = new TextView ( this );
        sectionName.setLayoutParams ( new LinearLayout.LayoutParams ( LinearLayout.LayoutParams.MATCH_PARENT , LinearLayout.LayoutParams.MATCH_PARENT ) );
        sectionName.setTextSize ( 24 );
        sectionName.setText ( SectionNameString );
        section.addView ( sectionName );
        //End Section Name

        section.addView ( newDivider ( ) );

        //Start Section Content

        EditText sectionContent = new EditText ( this );
        sectionContent.setLayoutParams ( new LinearLayout.LayoutParams ( LinearLayout.LayoutParams.MATCH_PARENT , LinearLayout.LayoutParams.MATCH_PARENT ) );
        sectionContent.setText ( SectionContentString );
        sectionContent.setInputType ( inputeType );
        section.addView ( sectionContent );
        //End Section Content


        sectionsNamesBasic.add ( sectionName );

        sectionsContentsBasic.add ( sectionContent );

        return section;
    }


    private LinearLayout actionButtons ( ) {
        LinearLayout section = new LinearLayout ( this );
        section.setOrientation ( LinearLayout.HORIZONTAL );
        section.setLayoutParams ( new LinearLayout.LayoutParams ( LinearLayout.LayoutParams.MATCH_PARENT , LinearLayout.LayoutParams.WRAP_CONTENT ) );

        Button btnNew = new Button ( this );
        btnNew.setLayoutParams ( new LinearLayout.LayoutParams ( LinearLayout.LayoutParams.WRAP_CONTENT , LinearLayout.LayoutParams.MATCH_PARENT ) );
        btnNew.setText ( getString ( R.string.newSection ) );
        btnNew.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {
                btnNew ( );
            }
        } );
        section.addView ( btnNew );

        Button btnSave = new Button ( this );
        btnSave.setLayoutParams ( new LinearLayout.LayoutParams ( LinearLayout.LayoutParams.WRAP_CONTENT , LinearLayout.LayoutParams.MATCH_PARENT ) );
        btnSave.setText ( getString ( R.string.save ) );
        section.addView ( btnSave );
        btnSave.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {
                showAlertDialog ( );
            }
        } );

        return section;
    }

    private View newDivider ( ) {
        View divider = new View ( this );
        divider.setLayoutParams ( new LinearLayout.LayoutParams ( LinearLayout.LayoutParams.WRAP_CONTENT , 20 ) );
        return divider;
    }

    private void btnSave ( ) {
        Toast.makeText ( this , getString ( R.string.save ) , Toast.LENGTH_SHORT ).show ( );

        arraies.loadRealData ( this );
        arraies.sections.add ( new Section ( "###" , "----------------------------------" ) );


        //Todo
        for ( int i = 0 ; i < sectionsNamesBasic.size ( ) ; i++ ) {
            if ( sectionsContentsBasic.get ( i ).getText ( ).toString ( ).trim ( ).isEmpty ( ) ) {
                continue;
            }
            arraies.sections.add ( new Section ( sectionsNamesBasic.get ( i ).getText ( ).toString ( ).trim ( ) , sectionsContentsBasic.get ( i ).getText ( ).toString ( ).trim ( ) ) );
        }

        for ( int i = 0 ; i < sectionsNames.size ( ) ; i++ ) {
            if ( sectionsNames.get ( i ).getText ( ).toString ( ).trim ( ).isEmpty ( ) || sectionsContents.get ( i ).getText ( ).toString ( ).trim ( ).isEmpty ( ) ) {
                continue;
            }
            arraies.sections.add ( new Section ( sectionsNames.get ( i ).getText ( ).toString ( ).trim ( ) , sectionsContents.get ( i ).getText ( ).toString ( ).trim ( ) ) );

        }


        arraies.saveRealData ( this );

        finish ( );
    }

    private void btnNew ( ) {
        layout.addView ( newSection ( count++ ) , count - 1 );
    }


    public void showAlertDialogBack ( ) {
        AlertDialog.Builder builder = new AlertDialog.Builder ( this );
        builder.setTitle ( "هل تريد الخروج ؟" );

        builder.setMessage ( "لن يتم حفظ البيانات التي قمت بإدخالها" );
        builder.setPositiveButton ( "خروج" , new DialogInterface.OnClickListener ( ) {
            @Override
            public void onClick ( DialogInterface dialog , int which ) {
                AddActivity.super.onBackPressed ( );
            }
        } );

        builder.setNegativeButton ( "بقاء" , new DialogInterface.OnClickListener ( ) {
            @Override
            public void onClick ( DialogInterface dialog , int which ) {

            }
        } );

        AlertDialog dialog = builder.create ( );
        dialog.show ( );
    }


    public void showAlertDialog ( ) {
        AlertDialog.Builder builder = new AlertDialog.Builder ( this );
        builder.setTitle ( "حفظ المنتج" );
        builder.setMessage ( "هل أنت متأكد ؟" );
        builder.setPositiveButton ( "نعم" , new DialogInterface.OnClickListener ( ) {
            @Override
            public void onClick ( DialogInterface dialog , int which ) {
                btnSave ( );
            }
        } );
        AlertDialog dialog = builder.create ( );
        dialog.show ( );
    }

}
