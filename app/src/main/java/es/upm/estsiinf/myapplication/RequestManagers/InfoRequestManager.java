package es.upm.estsiinf.myapplication.RequestManagers;

import android.content.Context;

import es.upm.estsiinf.myapplication.FavoritosDB;
import es.upm.estsiinf.myapplication.Models.InfoModels.InfoResponse;
import es.upm.estsiinf.myapplication.Interfaces.CallRecipeInfo;
import es.upm.estsiinf.myapplication.Listeners.ResponseInfoListener;
import es.upm.estsiinf.myapplication.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InfoRequestManager implements Runnable{
    Context context;
    ResponseInfoListener listener;
    int recipeId;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.spoonacular.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public InfoRequestManager(Context context, ResponseInfoListener listener, int recipeId){
        this.context=context;
        this.listener=listener;
        this.recipeId=recipeId;
    }

    @Override
    public void run() {
        // Llamada a la API para conseguir la informacion de una receta especifica
        CallRecipeInfo callRecipes = retrofit.create(CallRecipeInfo.class);
        Call<InfoResponse> call = callRecipes.callRecipeInfo(recipeId, context.getString(R.string.api_key));
        call.enqueue(new Callback<InfoResponse>() {
            @Override
            public void onResponse(Call<InfoResponse> call, Response<InfoResponse> response) {
                if(!response.isSuccessful()) {
                    listener.didError(response.message());
                    return;
                }

                // Si la llamada va correctamente se llama a 'didFetch' para mostrar la respuesta
                listener.didFetch(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<InfoResponse> call, Throwable t) {
                listener.didError(t.getMessage());
            }
        });
    }
}
