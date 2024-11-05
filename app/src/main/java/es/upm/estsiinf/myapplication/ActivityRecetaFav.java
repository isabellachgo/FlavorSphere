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
import java.io.ByteArrayOutputStream;

import es.upm.estsiinf.myapplication.Adapters.FavAdapter;

public class ActivityRecetaFav extends AppCompatActivity {
    String[] receta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receta_fav);

        Intent intent = getIntent();
        receta = (String[]) intent.getStringArrayExtra(FavAdapter.EXTRA_FAV_RECETA);

        FavoritosDB favDB = FavoritosDB.obtenerInstancia(ActivityRecetaFav.this, "favoritosDB.bd");

        TextView title, minutos, nPlatos, likes, ingredientes, pasos;
        ImageButton bt_compartir, bt_favorito, bt_user;

        // Muestra la informacion de la receta en el layout
        title = (TextView)findViewById(R.id.recfav_tx_title);
        title.setText(receta[0]);
        pasos = ((TextView)findViewById(R.id.receta_tx_pasos));
        pasos.setText(receta[2]);
        ingredientes = ((TextView)findViewById(R.id.recfav_tx_ingredientes));
        ingredientes.setText(receta[1]);
        minutos = ((TextView)findViewById(R.id.recfav_tx_tiempo));
        minutos.setText(receta[3]);
        nPlatos = ((TextView)findViewById(R.id.recfav_tx_personas));
        nPlatos.setText(receta[4]);
        likes = ((TextView)findViewById(R.id.recfav_tx_likes));
        likes.setText(receta[5]);

        // Permite volver a la activity principal
        ImageView logo = (ImageView)findViewById(R.id.receta_logo);
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int_home = new Intent(ActivityRecetaFav.this, MainActivity.class);
                startActivity(int_home);
            }
        });

        // Permite compartir la receta (en caso de tener conexion)
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

        // Añade o elimina la receta a la base de datos de favoritos
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

                    Toast.makeText(ActivityRecetaFav.this, "The recipe has been added to Favorites", Toast.LENGTH_SHORT).show();

                } else {
                    // Si la receta estaba guardada se elimina
                    favDB.eliminarReceta(title.getText().toString());

                    // Cambia el aspecto del imagebutton
                    bt_favorito.setColorFilter(getResources().getColor(R.color.fav_off));

                    Toast.makeText(ActivityRecetaFav.this, "The recipe has been eliminated from Favorites", Toast.LENGTH_SHORT).show();

                }

            }
        });

        // Abre el fragment de opciones
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

}
