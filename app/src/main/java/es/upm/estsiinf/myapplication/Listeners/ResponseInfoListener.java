package es.upm.estsiinf.myapplication.Listeners;

import es.upm.estsiinf.myapplication.FavoritosDB;
import es.upm.estsiinf.myapplication.Models.InfoModels.InfoResponse;

public interface ResponseInfoListener {
    void didFetch(InfoResponse response, String message);
    void didError(String message);
}
