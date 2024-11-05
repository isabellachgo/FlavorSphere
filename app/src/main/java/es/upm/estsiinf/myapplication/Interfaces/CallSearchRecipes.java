package es.upm.estsiinf.myapplication.Interfaces;

import java.util.List;

import es.upm.estsiinf.myapplication.Models.SearchModels.Result;
import es.upm.estsiinf.myapplication.Models.SearchModels.SearchResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CallSearchRecipes {
    @GET("recipes/complexSearch")
    Call<SearchResponse> callSearchRecipes(
            @Query("apiKey") String apiKey,
            @Query("number") String number,
            @Query("query") String query
    );
}
