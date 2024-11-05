package es.upm.estsiinf.myapplication.RequestManagers;

import es.upm.estsiinf.myapplication.FavoritosDB;
import es.upm.estsiinf.myapplication.Interfaces.CallSearchRecipes;
import es.upm.estsiinf.myapplication.Listeners.ResponseSearchListener;
import es.upm.estsiinf.myapplication.MainActivity;
import es.upm.estsiinf.myapplication.R;
import es.upm.estsiinf.myapplication.Models.SearchModels.SearchResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchRequestManager implements Runnable{
    MainActivity context;
    ResponseSearchListener listener;
    String query;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.spoonacular.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public SearchRequestManager(MainActivity context, ResponseSearchListener listener, String query){
        this.context=context;
        this.listener=listener;
        this.query=query;
    }

    @Override
    public void run() {
        // Llamada a la API para el conjunto de recetas a mostrar
        CallSearchRecipes callRecipes = retrofit.create(CallSearchRecipes.class);
        Call<SearchResponse> call = callRecipes.callSearchRecipes(context.getString(R.string.api_key), "10", query);
        call.enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                if(!response.isSuccessful()) {
                    listener.didError(response.message());
                    return;
                }

                // Si la llamada va correctamente se llama a 'didFetch' para mostrar la respuesta
                try {
                    listener.didFetch(response.body(), response.message());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {
                listener.didError(t.getMessage());
            }
        });

    }
}