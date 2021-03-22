package steps;

import org.jbehave.core.annotations.Alias;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import utils.JWTHelper;
import utils.RESTHelper;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SharedSteps {
    @Autowired
    JWTHelper jwtHelper;
    @Autowired
    RESTHelper callRest;


    @Given("Użytkownik pobiera token JWT")
    public void userGetsToken(){
        callRest.setAccessToken(jwtHelper.getAccessToken());
        assert jwtHelper.getAccessToken() != null;
    }

    @Given("Accept type jest ustawiony na $acceptType")
    public void setAcceptType(String acceptType) throws IOException {
        callRest.setAcceptType(acceptType);
    }

    @When("Użytkownik odpytuje $endpoint endpoint")
    public void userMakeARequest(String endpoint) throws IOException {
        try {
            callRest.callEndpoint(endpoint);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Then("System prezentuje błąd")
    public void errorOccuder() {
        HttpResponse<String> httpResponse = callRest.getResponse();
        String jsonResponse = httpResponse.body();
        assert httpResponse.statusCode() != 200;
        assert new JSONObject(jsonResponse).getJSONArray("errors").length() != 0;

    }

    @Then("Kategoria z id= $cat nie istnieje")
    public void categoryNotFound(String cat) {
        HttpResponse<String> httpResponse = callRest.getResponse();
        assert httpResponse.statusCode() != 200;
        JSONArray errors = new JSONObject(httpResponse.body()).getJSONArray("errors");
        assert errors.getJSONObject(0).get("message").equals("Category '"+cat+"' not found");
        assert errors.length() != 0;
    }

    @Then("Widoczne są tylko kategorie dla których $param = $status")
    public void checkParams(String param, String status) throws IOException {
        HttpResponse<String> httpResponse = callRest.getResponse();
        String jsonResponse = httpResponse.body();
        JSONArray jsonArray = new JSONObject(jsonResponse).getJSONArray("categories");

        List<String> listOfParams = IntStream.range(0, jsonArray.length())
                .mapToObj(index -> ((JSONObject) jsonArray.get(index)).optString(param))
                .collect(Collectors.toList());

        if(status.equals("null")){
            listOfParams.forEach(el -> {
                assert el.isEmpty();
            });
        } else {
            listOfParams.forEach(el -> {
                assert el.contains(status);
            });
        }


    }

}
