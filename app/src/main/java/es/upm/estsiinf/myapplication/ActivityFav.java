package es.upm.estsiinf.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

import es.upm.estsiinf.myapplication.Adapters.FavAdapter;

public class ActivityFav extends AppCompatActivity {
    FavAdapter favAdapter;
    ListView listView_Fav;
    ArrayList<String[]> listFav;
    FavoritosDB favDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav);

        // Se obtienen las recetas guardadas en la base de datos
        favDB = FavoritosDB.obtenerInstancia(ActivityFav.this, "favoritosDB.bd");
        listView_Fav = (ListView) findViewById(R.id.fav_list);
        listFav = favDB.verReceta();

        // Se asigna el adapter que mostrara las recetas en el layout
        favAdapter = new FavAdapter(ActivityFav.this, listFav);
        listView_Fav.setAdapter(favAdapter);

        // Permite volver a la activity principal
        ImageView logo = (ImageView)findViewById(R.id.fav_logo);
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int_home = new Intent(ActivityFav.this, MainActivity.class);
                startActivity(int_home);
            }
        });

        // Abre el fragment de opciones
        ImageButton bt_user = (ImageButton) findViewById(R.id.fav_user_bt);
        bt_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                UserFragment fragment = new UserFragment();

                fragmentTransaction.add(R.id.fav_fragContView, fragment);
                fragmentTransaction.commit();
            }
        });

    }

    @Override
    protected void onRestart() {
        super.onRestart();

        // Al volver a esta activity recarga la lista de recetas favoritas mostrada
        listFav = favDB.verReceta();
        favAdapter = new FavAdapter(ActivityFav.this, listFav);
        listView_Fav.setAdapter(favAdapter);

    }
}