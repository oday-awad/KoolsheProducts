package net.rebi.koolsheProducts.Activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import net.rebi.koolsheProducts.Calsses.Category1;
import net.rebi.koolsheProducts.Calsses.Mydatabase;
import net.rebi.koolsheProducts.R;
import net.rebi.koolsheProducts.arraies;

import java.util.ArrayList;
import java.util.Objects;

public class CategoryActivity extends AppCompatActivity {

    EditText      editText;
    CustomAdapter customAdapter;
    Switch        switch1;
    Button        setting;
    Button        newCategory, newProduct;

    SharedPreferences       sharedPreferences;
    String                  parent;
    Mydatabase              mydatabase;
    RecyclerView            rv;
    ArrayList < Category1 > categories = new ArrayList <> ( );

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_category );
        sharedPreferences = getSharedPreferences ( "koolshe" , MODE_PRIVATE );

        mydatabase = new Mydatabase ( this );

        switch1 = findViewById ( R.id.switch1 );
        setting = findViewById ( R.id.setting );
        newCategory = findViewById ( R.id.newCategory );
        newProduct = findViewById ( R.id.newProduct );
        rv = findViewById ( R.id.rv );

        final Intent inten = getIntent ( );
        parent = "mainCategories";
        try {
            parent =
                    Objects.requireNonNull ( inten.getExtras ( ) ).getString ( "parent" ,
                                                                               "mainCategories" );
        }
        catch ( Exception e ) {
            parent = "mainCategories";
            e.printStackTrace ( );
        }


        setTitle ( parent );


        //todo
        categories = mydatabase.getCategories ( parent );

        RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager ( 2 , 1 );
        //        customAdapter = new CustomAdapter ( this , categories );
        rv.setLayoutManager ( layoutManager );
        //        rv.setAdapter ( customAdapter );


        if ( ! sharedPreferences.getBoolean ( "isNamed" , false ) ) {
            Intent intent = new Intent ( this , BasicInfoActivity.class );
            startActivity ( intent );
        }

        if ( parent.equals ( "mainCategories" ) ) {
//            newProduct.setEnabled ( false );
        }
        newCategory.setVisibility ( View.GONE );
        newCategory.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View view ) {
                showAlertDialogNewCategory ( );
            }
        } );
        newProduct.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View view ) {
                Intent intent = new Intent ( getBaseContext ( ) , AddActivity.class );
                intent.putExtra ( "parent" , parent );
                startActivity ( intent );
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

    }


    @Override
    protected void onResume ( ) {
        categories = mydatabase.getCategories ( parent );
        customAdapter = new CustomAdapter ( this , categories , mydatabase );
        rv.setAdapter ( customAdapter );
        customAdapter.notifyDataSetChanged ( );
        super.onResume ( );
    }

    public void showAlertDialogNewCategory ( ) {
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
                String name = editText.getText ( ).toString ( ).trim ( );
                if ( ! name.isEmpty ( ) ) {

                    Toast.makeText ( CategoryActivity.this , "parent = " + parent ,
                                     Toast.LENGTH_SHORT ).show ( );
                    boolean x =
                            mydatabase.insertCategory ( new Category1 ( name , parent , "Name-Price-Brand" ) );

                    String toastMSG = x ? "تم إضافة فئة جديدة":"لم يتم إضافة فئة";

                        Toast.makeText ( CategoryActivity.this , toastMSG ,
                                         Toast.LENGTH_SHORT ).show ( );

                    categories = mydatabase.getCategories ( parent );

                    customAdapter = new CustomAdapter ( getBaseContext ( ) , categories , mydatabase );
                    rv.setAdapter ( customAdapter );

                }
            }
        } );
        AlertDialog dialog = builder.create ( );
        dialog.show ( );
    }


    class CustomAdapter extends RecyclerView.Adapter < CustomAdapter.ViewHolder > {
        Mydatabase mydatabase;
        private Context                 context;
        private ArrayList < Category1 > categories;

        CustomAdapter (
                Context context , ArrayList < Category1 > categories , Mydatabase mydatabase
        ) {
            this.categories = categories;
            this.context = context;
            this.mydatabase = mydatabase;
        }


        @NonNull
        @Override
        public ViewHolder onCreateViewHolder ( @NonNull ViewGroup viewGroup , int viewType ) {

            View v =
                    LayoutInflater.from ( viewGroup.getContext ( ) ).inflate ( R.layout.category_item , viewGroup , false );

            return new ViewHolder ( v );
        }

        @Override
        public void onBindViewHolder ( @NonNull ViewHolder viewHolder , final int position ) {

            viewHolder.category_name.setText ( categories.get ( position ).getName ( ) );
        }

        @Override
        public int getItemCount ( ) {
            return categories.size ( );
        }


        class ViewHolder extends RecyclerView.ViewHolder {

            TextView category_name;

            ViewHolder ( View v ) {
                super ( v );

                category_name = v.findViewById ( R.id.category_name );
                v.setOnClickListener ( new View.OnClickListener ( ) {
                    @Override
                    public void onClick ( View v ) {

                        if ( arraies.IsDeleting ) {
                            try {

                                mydatabase.deleteCategory ( categories.get ( getAdapterPosition ( ) ) );
                                categories = mydatabase.getCategories ( parent );

                                notifyDataSetChanged ( );
                            }
                            catch ( Exception e ) {
                                Toast.makeText ( context , "Error 4" , Toast.LENGTH_SHORT ).show ( );
                            }
                        }
                        else {

                            Toast.makeText ( context ,
                                             categories.get ( getAdapterPosition ( ) ).getParent ( ) , Toast.LENGTH_SHORT ).show ( );

                            Intent intent = new Intent ( context , CategoryActivity.class );

                            intent.putExtra ( "position" , getAdapterPosition ( ) );
                            //todo
                            intent.putExtra ( "parent" ,
                                              categories.get ( getAdapterPosition ( ) ).getName ( ) );
                            context.startActivity ( intent );
                        }
                    }
                } );

                v.setOnLongClickListener ( new View.OnLongClickListener ( ) {
                    @Override
                    public boolean onLongClick ( View v ) {
//                        Intent intent = new Intent ( context , EditCategory.class );
//                        intent.putExtra ( "position" , getAdapterPosition ( ) );
//                        intent.putExtra ( "categoryTag" , sharedPreferences.getString (
//                                "categoryTag" , "mainCategories" ) );
//
//                        context.startActivity ( intent );
                        return false;
                    }
                } );
            }


        }


    }

}


