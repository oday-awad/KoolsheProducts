package net.rebi.koolsheProducts;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import net.rebi.koolsheProducts.Activities.CategoryActivity;
import net.rebi.koolsheProducts.Activities.EditCategory;
import net.rebi.koolsheProducts.Calsses.Category;

import java.util.ArrayList;


public class CustomAdapter extends RecyclerView.Adapter < CustomAdapter.ViewHolder > {
    private static       Context                context;
    private              ArrayList < Category > mDataSet;

    SharedPreferences sharedPreferences;
    public CustomAdapter ( Context context , ArrayList < Category > dataSet ) {
        mDataSet = dataSet;
        CustomAdapter.context = context;
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

        viewHolder.category_name.setText ( mDataSet.get ( position ).getName ( ) );
    }

    @Override
    public int getItemCount ( ) {
        return mDataSet.size ( );
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
                            String categoryTag =
                                    arraies.categories.get ( getAdapterPosition ( ) ).getCategoryTag ( );
                            arraies.categories.remove ( getAdapterPosition ( ) );
                            arraies.saveCategories ( context , categoryTag );
                            notifyDataSetChanged ( );
                        }
                        catch ( Exception e ) {
                            Toast.makeText ( context , "Error" , Toast.LENGTH_SHORT ).show ( );
                        }
                    }
                    else {
                        Intent intent = new Intent ( context , CategoryActivity.class );

                        intent.putExtra ( "position" , getAdapterPosition ( ) );
                        //todo
                        intent.putExtra ( "categoryTag" ,
                                          mDataSet.get ( getAdapterPosition ( ) ).getCategoryTag ( ) );

                        context.startActivity ( intent );
                    }
                }
            } );

            v.setOnLongClickListener ( new View.OnLongClickListener ( ) {
                @Override
                public boolean onLongClick ( View v ) {
                    Intent intent = new Intent ( context , EditCategory.class );
                    intent.putExtra ( "position" , getAdapterPosition ( ) );
                    intent.putExtra ( "categoryTag" ,context.getSharedPreferences ( "koolshe" ,
                     Context.MODE_PRIVATE).getString ( "categoryTag" , "categories" ) );

                    context.startActivity ( intent );
                    return false;
                }
            } );
        }


    }


}