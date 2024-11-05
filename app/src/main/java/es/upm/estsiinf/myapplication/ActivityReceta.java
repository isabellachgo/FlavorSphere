package es.upm.estsiinf.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import es.upm.estsiinf.myapplication.Adapters.InfoAdapter;
import es.upm.estsiinf.myapplication.Adapters.SearchAdapter;
import es.upm.estsiinf.myapplication.Listeners.ResponseInfoListener;
import es.upm.estsiinf.myapplication.Models.InfoModels.ExtendedIngredient;
import es.upm.estsiinf.myapplication.Models.InfoModels.InfoResponse;
import es.upm.estsiinf.myapplication.RequestManagers.InfoRequestManager;

public class ActivityReceta extends AppCompatActivity {
    InfoAdapter infoAdapter;

    Integer idReceta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receta);

        Intent intent = getIntent();
        idReceta = (Integer)intent.getIntExtra(SearchAdapter.EXTRA_ID_RECETA, 0);

        // Se crea otro hilo para hacer la conexión a la API
        Thread th = new Thread(new InfoRequestManager(ActivityReceta.this, responseInfoListener, idReceta));
        th.start();
    }

    @Override
    protected void onRestart () {
        super.onRestart();

        // Al volver a esta activity se recarga la lista de recetas favoritas en la base de datos
        FavoritosDB favDB = FavoritosDB.obtenerInstancia(ActivityReceta.this, "favoritosDB.bd");
        ImageButton bt_favorito=(ImageButton)findViewById(R.id.recfav_btn_favorito);
        TextView title = (TextView)findViewById(R.id.recfav_tx_title);
        if( !(favDB.recetaIncliuda(title.getText().toString())) ) { bt_favorito.setColorFilter(getResources().getColor(R.color.fav_off)); }
    }


    public final ResponseInfoListener responseInfoListener = new ResponseInfoListener() {
        @Override
        public void didFetch(InfoResponse response, String message) {

            FavoritosDB favDB = FavoritosDB.obtenerInstancia(ActivityReceta.this, "favoritosDB.bd");

            TextView title, minutos, nPlatos, likes, ingredientes, pasos;
            ImageView img, logo;
            ImageButton bt_compartir, bt_favorito, bt_user;

            // Se muestra la informacion de la respuesta de la API en el layout
            title = (TextView)findViewById(R.id.recfav_tx_title);
            title.setText(response.title);
            minutos = ((TextView)findViewById(R.id.recfav_tx_tiempo));
            minutos.setText(response.readyInMinutes + " min.");
            nPlatos = ((TextView)findViewById(R.id.recfav_tx_personas));
            nPlatos.setText(response.servings + " people");
            likes = ((TextView)findViewById(R.id.recfav_tx_likes));
            likes.setText(response.aggregateLikes + " likes");

            pasos = ((TextView)findViewById(R.id.receta_tx_pasos));
            pasos.setText(response.instructions);
            ingredientes = ((TextView)findViewById(R.id.recfav_tx_ingredientes));
            ArrayList<ExtendedIngredient> ingt = response.extendedIngredients;
            for(int i=0; i<ingt.size(); i++){ ingredientes.append(ingt.get(i).amount + " " + ingt.get(i).name + " : " + ingt.get(i).meta + "\n"); }

            img = (ImageView)findViewById(R.id.receta_img);
            Picasso.get().load(response.image).into(img);

            // Permite volver a la activity principal
            logo = (ImageView)findViewById(R.id.receta_logo);
            logo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent int_home = new Intent(ActivityReceta.this, MainActivity.class);
                    startActivity(int_home);
                }
            });

            // Permite compartir la receta
            bt_compartir=(ImageButton)findViewById(R.id.recfav_bt_compartir);
            bt_compartir.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bitmap b= BitmapFactory.decodeResource(getResources(),R.drawable.propaganda);
                    Intent intShare =  new Intent(Intent.ACTION_SEND);
                    intShare.setType("image/png");
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    b.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream);

                    String rutaImagen = MediaStore.Images.Media.insertImage(getContentResolver(),b,"Check FlavorSphere",null);
                    Uri uriImagen = Uri.parse(rutaImagen);

                    intShare.putExtra(Intent.EXTRA_STREAM, uriImagen);

                    intShare.putExtra(Intent.EXTRA_TEXT, title.getText().toString() + "\n\n Ingredients: \n"
                            + ingredientes.getText().toString() + "\n\n Steps: \n"+ pasos.getText().toString() + "\n\n\nDownload FlavorSphere: \n " + "https://play.google.com/store/search?q=flavorsphere&c=apps&hl=es_419&gl=US&pli=1" );

                    intShare.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

                    startActivity(intShare);
                }
            });

            // Añade o elimina la receta a la base de datos de favoritos donde puede ser accedida offline
            bt_favorito=(ImageButton)findViewById(R.id.recfav_btn_favorito);
            if( (favDB.recetaIncliuda(title.getText().toString())) ) { bt_favorito.setColorFilter(getResources().getColor(R.color.fav_on)); }
            bt_favorito.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    // Si la receta no estaba guardada se añade
                    if( !(favDB.recetaIncliuda(title.getText().toString())) ) {
                        favDB.guardarReceta(title.getText().toString(),
                                ingredientes.getText().toString(),
                                pasos.getText().toString(),
                                minutos.getText().toString(),
                                nPlatos.getText().toString(),
                                likes.getText().toString()
                        );

                        // Cambia el aspecto del imagebutton
                        bt_favorito.setColorFilter(getResources().getColor(R.color.fav_on));

                        Toast.makeText(ActivityReceta.this, "The recipe has been added to Favorites", Toast.LENGTH_SHORT).show();

                    } else {
                        // Si la receta estaba guardada se elimina
                        favDB.eliminarReceta(title.getText().toString());

                        // Cambia el aspecto del imagebutton
                        bt_favorito.setColorFilter(getResources().getColor(R.color.fav_off));

                        Toast.makeText(ActivityReceta.this, "The recipe has been eliminated from Favorites", Toast.LENGTH_SHORT).show();

                    }

                }
            });

            // Abre fragment de opciones
            bt_user = (ImageButton) findViewById(R.id.receta_user_bt);
            bt_user.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    UserFragment fragment = new UserFragment();

                    fragmentTransaction.add(R.id.recfav_fragContView, fragment);
                    fragmentTransaction.commit();
                }
            });

        }

        @Override
        public void didError(String message) {

        }
    };


}