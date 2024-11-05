package es.upm.estsiinf.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.HashMap;
//import es.upm.estsiinf.myapplication.Utils.JobUtil;

public class ActivityLogin extends AppCompatActivity {

    private static String ATTR_API_USER = "api_user";
    private static String ATTR_API_KEY = "api_key";
    private static String FILENAME_CONECTION_PREFERENCES = "preferecias_conexion";

    // Informacion de sesion correcta
    private static String U = "Olivia";
    private static String K = "1234Abc";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        SharedPreferences prefConexion = getSharedPreferences(FILENAME_CONECTION_PREFERENCES, MODE_PRIVATE);
        String apiUsu = prefConexion.getString(ATTR_API_USER, null);
        String apiKey = prefConexion.getString(ATTR_API_KEY, null);

        // Si se hace logout borrar informacion de sesion
        Intent int_logout = getIntent();
        Integer v = int_logout.getIntExtra(UserFragment.EXTRA_LOGOUT, 0);
        if(v==1){
            SharedPreferences.Editor edit = prefConexion.edit();
            edit.putString(ATTR_API_USER, null);
            edit.putString(ATTR_API_KEY, null);
            edit.commit();
            v=0;
        }

        RadioButton log_check = ((RadioButton)findViewById(R.id.log_check));

        apiUsu = prefConexion.getString(ATTR_API_USER, null);
        apiKey = prefConexion.getString(ATTR_API_KEY, null);

        // Si la informacion de sesion no esta guardada se pide hacer login
        if(apiKey == null || apiKey.equals("") || apiUsu == null || apiUsu.equals("")){
            //LOGIN:
            Button login =(Button)findViewById(R.id.log_btn_login);
            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String apiUsu =((EditText)findViewById(R.id.log_text_usuario)).getText().toString();
                    String apiKey =((EditText)findViewById(R.id.log_text_contraseña)).getText().toString();

                    if(apiUsu.trim().equals("") || apiKey.trim().equals("")){
                        Toast.makeText(ActivityLogin.this, "You must fill the fildes 'User' and 'Password'", Toast.LENGTH_SHORT).show();
                    }
                    else if (apiUsu.trim().equals(U) && apiKey.trim().equals(K)){
                        // Si se selecciona la opcion 'Remember me' se guarda la informacion de sesion
                        if(log_check.isChecked()) {
                            SharedPreferences.Editor edit = prefConexion.edit();
                            edit.putString(ATTR_API_USER, apiUsu);
                            edit.putString(ATTR_API_KEY, apiKey);
                            edit.commit();
                        }

                        Toast.makeText(ActivityLogin.this, "Welcome", Toast.LENGTH_SHORT).show();

                        Intent int_entrar_app = new Intent(ActivityLogin.this, MainActivity.class);
                        startActivity(int_entrar_app, null);
                    }
                    else{
                        Toast.makeText(ActivityLogin.this, "The username or password is incorrect", Toast.LENGTH_SHORT).show();
                    }

                }
            });

        }
        else{
            // Si la informacion de sesion esta guardada pasar a la activity principal de la app
            SharedPreferences.Editor edit = prefConexion.edit();
            edit.putString(ATTR_API_USER, apiUsu);
            edit.putString(ATTR_API_KEY, apiKey);
            edit.commit();

            Intent int_entrar_app = new Intent(this, MainActivity.class);
            startActivity(int_entrar_app, null);
        }

    }

}