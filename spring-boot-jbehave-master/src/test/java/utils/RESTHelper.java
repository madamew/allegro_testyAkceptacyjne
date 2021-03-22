package utils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class RESTHelper {
    private String acceptType;
    private String accessToken;
    private HttpResponse response;


    public HttpResponse<String> callEndpoint(String endpoint) throws
            URISyntaxException, IOException, InterruptedException {
        HttpRequest httpRequest = HttpRequest.newBuilder(new URI(endpoint))
                .header("Accept", acceptType)
                .header("Authorization", "Bearer " + accessToken)
                .GET()
                .build();
        this.response = HttpClient.newHttpClient().send(httpRequest, HttpResponse.BodyHandlers.ofString());
        return response;
    }

    public void setAccessToken(String accessToken){
        this.accessToken = accessToken;
    }

    public void setAcceptType(String acceptType){
        System.out.println("acceptTypeSet: " + acceptType);
        this.acceptType = acceptType;
    }

    public HttpResponse getResponse(){
        return response;
    }

}
