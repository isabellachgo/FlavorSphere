package es.upm.estsiinf.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import es.upm.estsiinf.myapplication.Adapters.SearchAdapter;
import es.upm.estsiinf.myapplication.Listeners.ResponseSearchListener;
import es.upm.estsiinf.myapplication.Models.SearchModels.SearchResponse;
//import es.upm.estsiinf.myapplication.Notifications.NotificationHandler;                       // !!!!!!!!!!!!!!!
import es.upm.estsiinf.myapplication.RequestManagers.SearchRequestManager;

public class MainActivity extends AppCompatActivity {

    SearchAdapter searchAdapter;
    ListView listView;
    NotificationHandler notifHandler;
    Button bt_buscar;
    ImageButton main_bt_user;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView= findViewById(R.id.fav_list);

        // Creacion y lanzamiento de notificacion de bienvenida
        notifHandler = new NotificationHandler(this);
        Notification.Builder notifBuilder = notifHandler.crearNotificationBuilder("Welcome back!", "Explore a world full of new recipes :)");
        notifHandler.getManager().notify(1, notifBuilder.build());
        notifHandler.publishGroup();

        bt_buscar=(Button)findViewById(R.id.button_btn_buscar);
        bt_buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query=  ((EditText)findViewById(R.id.main_txt_buscador)).getText().toString();

                // Se crea un segundo hilo para hacer la conexión a la API
                Thread th = new Thread(new SearchRequestManager(MainActivity.this, responseSearchListener, query));
                th.start();
            }
        });

        main_bt_user=(ImageButton) findViewById(R.id.fav_user_bt);
        main_bt_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crea y abre el fragment de opciones
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                UserFragment fragment = new UserFragment();

                fragmentTransaction.add(R.id.fav_fragContView, fragment);
                fragmentTransaction.commit();
            }
        });


    }

    private final ResponseSearchListener responseSearchListener = new ResponseSearchListener() {
        @Override
        public void didFetch(SearchResponse response, String message) throws Exception {
            // LLama al adaptador para mostrar la información de respuesta de la API o muestra un toast si no hay informacion para la búsqueda
            searchAdapter = new SearchAdapter(MainActivity.this, response);
            if(searchAdapter.getCount() == 0) Toast.makeText(MainActivity.this, "We have no recipes matching your search", Toast.LENGTH_SHORT).show();
            listView.setAdapter(searchAdapter);
        }

        @Override
        public void didError(String message) {
            Toast.makeText(MainActivity.this, "Sorry, a technical issue has ocurred", Toast.LENGTH_SHORT).show();
        }
    };
}
