package es.upm.estsiinf.myapplication.Adapters;


import static androidx.core.content.ContextCompat.startActivity;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import com.squareup.picasso.Picasso;

import es.upm.estsiinf.myapplication.ActivityReceta;
import es.upm.estsiinf.myapplication.Models.SearchModels.Result;
import es.upm.estsiinf.myapplication.Models.SearchModels.SearchResponse;
import es.upm.estsiinf.myapplication.R;


public class SearchAdapter extends BaseAdapter {
    SearchResponse response;
    Activity ctx;
    public static final String EXTRA_ID_RECETA = "MyApplication2.ID_RECETA_SELECCIONADA";

    public SearchAdapter(Activity ctx, SearchResponse response) throws Exception {
        this.response = response;
        this.ctx=ctx;
    }

    @Override
    public int getCount() {
        return Math.min(response.totalResults, response.number);
    }

    @Override
    public Result getItem(int i) {
        return response.recetas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return response.recetas.get(i).getId();
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        TextView title;
        ImageView img;
        CardView listcardview;

        if(view == null){
            LayoutInflater layoutInflater = ctx.getLayoutInflater();
            view = layoutInflater.inflate(R.layout.main_list_layout, null);
        }

        // Se muestra la informacion de la respuesta de la API en el layout
        title = ((TextView)view.findViewById(R.id.main_list_txt_title));
        title.setText(response.recetas.get(i).getTitle());
        img = (ImageView)view.findViewById(R.id.list_img);
        Picasso.get().load(response.recetas.get(i).image).into(img);
        listcardview= (CardView)view.findViewById(R.id.main_list_cardview);
        listcardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ir al activity para mostrar la informacion de esta receta
                Intent intent = new Intent(ctx, ActivityReceta.class);
                intent.putExtra(EXTRA_ID_RECETA, (int)getItemId(i));
                startActivity(ctx, intent, null);

            }
        });

        return view;
    }

}
