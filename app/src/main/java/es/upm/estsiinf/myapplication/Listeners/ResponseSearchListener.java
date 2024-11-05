package es.upm.estsiinf.myapplication.Listeners;

import java.util.List;

import es.upm.estsiinf.myapplication.FavoritosDB;
import es.upm.estsiinf.myapplication.Models.SearchModels.Result;
import es.upm.estsiinf.myapplication.Models.SearchModels.SearchResponse;

public interface ResponseSearchListener {
    void didFetch(SearchResponse response, String message) throws Exception;
    void didError(String message);
}
