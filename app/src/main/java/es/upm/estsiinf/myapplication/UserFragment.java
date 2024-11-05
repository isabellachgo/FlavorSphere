package es.upm.estsiinf.myapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class UserFragment extends Fragment {

    public static final String EXTRA_LOGOUT ="logout";

    public UserFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Asigna el layout al fragment
        View root = inflater.inflate(R.layout.fragment_user, container, false);

        // Cierra el fragment de opciones
        ImageButton bt_user = ((ImageButton) root.findViewById(R.id.frg_bt_user));
        bt_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().remove(UserFragment.this).commit();
            }
        });

        // Abre el activity de la lista de recetas favoritas
        LinearLayout fav = ((LinearLayout) root.findViewById(R.id.frg_layout_fav));
        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int_fav = new Intent(getActivity(), ActivityFav.class);
                startActivity(int_fav);
            }
        });

        // Cierra sesion y vuelve al activity de login
        LinearLayout logout = ((LinearLayout) root.findViewById(R.id.frg_layout_logout));
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int_logout = new Intent(getActivity(), ActivityLogin.class);
                int_logout.putExtra(EXTRA_LOGOUT, 1);
                startActivity(int_logout);
            }
        });

        return root;
    }
}