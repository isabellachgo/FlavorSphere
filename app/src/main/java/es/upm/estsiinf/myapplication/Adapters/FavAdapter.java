package es.upm.estsiinf.myapplication.Adapters;

import static androidx.core.content.ContextCompat.startActivity;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import java.util.ArrayList;

import es.upm.estsiinf.myapplication.ActivityRecetaFav;
import es.upm.estsiinf.myapplication.R;

public class FavAdapter extends BaseAdapter {

    public static final String EXTRA_FAV_RECETA = "MyApplication2.RECETA_FAV_SELECCIONADA";
    Activity ctx;
    ArrayList<String[]> listFav;

    public FavAdapter(Activity ctx, ArrayList<String[]> listFav){
        System.out.println("FAV ADAPTER");

        this.listFav=listFav;
        this.ctx=ctx;
    }

    @Override
    public int getCount() {
        return listFav.size();
    }

    @Override
    public Object getItem(int position) {
        return listFav.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        if(view == null){
            LayoutInflater layoutInflater = ctx.getLayoutInflater();
            view = layoutInflater.inflate(R.layout.fav_list_layout, null);
        }

        // Se muestra la informacion de la base de datos en el layout
        TextView title = ((TextView)view.findViewById(R.id.main_list_txt_title));
        title.setText((listFav.get(i))[0]);

        CardView listcardview= (CardView)view.findViewById(R.id.main_list_cardview);
        listcardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ir al activity para mostrar la informacion de esta receta
                Intent intent = new Intent(ctx, ActivityRecetaFav.class);
                intent.putExtra(EXTRA_FAV_RECETA, listFav.get(i));
                startActivity(ctx, intent, null);

            }
        });

        return view;
    }
}
