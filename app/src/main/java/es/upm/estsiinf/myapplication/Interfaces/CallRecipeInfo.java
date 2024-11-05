package es.upm.estsiinf.myapplication.Interfaces;

import es.upm.estsiinf.myapplication.Models.InfoModels.InfoResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CallRecipeInfo {
    @GET("recipes/{id}/information")
    Call<InfoResponse> callRecipeInfo(
            @Path("id") int recipeId,
            @Query("apiKey") String apiKey
    );
}
