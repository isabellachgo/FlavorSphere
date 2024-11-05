package es.upm.estsiinf.myapplication.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import es.upm.estsiinf.myapplication.Models.InfoModels.ExtendedIngredient;
import es.upm.estsiinf.myapplication.Models.InfoModels.InfoResponse;
import es.upm.estsiinf.myapplication.R;

public class InfoAdapter extends BaseAdapter {
    InfoResponse response;
    Activity ctx;

    public InfoAdapter(Activity ctx, InfoResponse response){
        this.response = response;
        this.ctx=ctx;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        TextView title, minutos, nPlatos, likes, ingredientes, pasos;
        ImageView img;

        if(view == null){
            LayoutInflater layoutInflater = ctx.getLayoutInflater();
            view = layoutInflater.inflate(R.layout.activity_receta, null);
        }

        // Se muestra la informacion de la respuesta de la API en el layout
        title = ((TextView)view.findViewById(R.id.recfav_tx_title));
        title.setText(response.title);
        minutos = ((TextView)view.findViewById(R.id.recfav_tx_tiempo));
        minutos.setText(response.readyInMinutes + "min.");
        nPlatos = ((TextView)view.findViewById(R.id.recfav_tx_personas));
        nPlatos.setText(response.servings + "personas");
        likes = ((TextView)view.findViewById(R.id.recfav_tx_likes));
        likes.setText(response.aggregateLikes + "likes");

        pasos = ((TextView)view.findViewById(R.id.receta_tx_pasos));
        ArrayList<Object> inst = response.analyzedInstructions;
        for(int i=0; i<inst.size(); i++){ pasos.append((String)inst.get(i) + "\n"); }

        ingredientes = ((TextView)view.findViewById(R.id.recfav_tx_ingredientes));
        ArrayList<ExtendedIngredient> ingt = response.extendedIngredients;
        for(int i=0; i<ingt.size(); i++){ ingredientes.append(ingt.get(i).amount + " " + ingt.get(i).name + " : " + ingt.get(i).meta + "\n"); }

        img = (ImageView)view.findViewById(R.id.list_img);
        Picasso.get().load(response.image).into(img);

        return null;
    }
}
