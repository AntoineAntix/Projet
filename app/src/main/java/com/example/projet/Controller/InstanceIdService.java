package com.example.projet.Controller;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Classe créée par LUCAS Antoine pour le 20/12/2019.
 * Permet de gérer le serveur google pour les notifications.
 */

public class InstanceIdService extends FirebaseInstanceIdService{

    public InstanceIdService() {
        super();
    }

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        String token = FirebaseInstanceId.getInstance().getToken();

        sendToServer(token);
    }

    private void sendToServer(String token) {

        try {
            URL url = new URL("https://www.whatsthatlambda.com/store");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setDoOutput(true);
            connection.setDoInput(true);

            connection.setRequestMethod("POST");

            DataOutputStream dos = new DataOutputStream(connection.getOutputStream());

            dos.writeBytes("token=" + token);

            connection.connect();

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
