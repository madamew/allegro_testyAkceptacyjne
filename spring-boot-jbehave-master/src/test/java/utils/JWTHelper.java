package utils;

import lombok.Builder;
import lombok.Getter;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
public class JWTHelper {
    public String url;
    public String clientId;
    public String clientSecret;
    public String accessToken;

    public JWTHelper() throws IOException {
        this.url = "https://allegro.pl/auth/oauth/token?grant_type=client_credentials";
        this.clientId = "ca323f5c717149359147f1d43b43215d";
        this.clientSecret = "glXrS6r2YizHXjg32n0r2S9LWlzf6n41Dcd0eQkS6tszNp9MHT1vzRu7fknRdOSD";
        this.accessToken = getToken();
    }

    public String getToken() throws IOException {
        String jsonResponse = "";
        try {
            String encoding = Base64.getEncoder().encodeToString((clientId + ":" + clientSecret).getBytes(StandardCharsets.UTF_8));
            HttpRequest httpRequest = HttpRequest.newBuilder(new URI("https://allegro.pl/auth/oauth/token?grant_type=client_credentials"))
                    .header("Accept", "application/json")
                    .header("Authorization", "Basic " + encoding)
                    .header("Content-Type", "application/json")
                    .GET()
                    .build();

            HttpResponse<String> httpResponse = HttpClient.newHttpClient().send(httpRequest, HttpResponse.BodyHandlers.ofString());
            jsonResponse = httpResponse.body();


        } catch (URISyntaxException | InterruptedException e) {
            e.printStackTrace();
        }

        return new JSONObject(jsonResponse).get("access_token").toString();
    }

    public String getAccessToken() {
        return accessToken;
    }
}
