package fr.haizen.utils;

import fr.haizen.type.CourseState;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

public class Utils {

    // CREDIT : CHATGPT
    public static void sendRestRequest(String endpoint) {
        String baseURL = "https://api.application.com/" + endpoint;
        try {
            URL url = new URL(baseURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuilder response = new StringBuilder();

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                reader.close();

                System.out.println(response);
            } else {
                System.out.println("Erreur : " + responseCode);
            }

            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void handleRest(CourseState courseState, UUID courseID) {
        if (courseState.getRestEndpoint().isEmpty()) return;

        String endpoint = courseState.getRestEndpoint().replace("XXX", courseID.toString());
        Utils.sendRestRequest(endpoint);
    }
}
