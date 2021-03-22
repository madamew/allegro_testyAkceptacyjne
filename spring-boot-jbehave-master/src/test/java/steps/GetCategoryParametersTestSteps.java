package steps;

import org.jbehave.core.annotations.Then;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import utils.RESTHelper;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class GetCategoryParametersTestSteps {
    private String jsonResponse;
    private HttpResponse<String> httpResponse;
    public JSONArray jsonArray;
    @Autowired
    private RESTHelper callRest;


    @Then("System prezentuje listę parametrów dla zadanej kategorii")
    public void getParametersOfCategory() throws IOException {
        this.httpResponse = callRest.getResponse();
        this.jsonResponse = httpResponse.body();
        this.jsonArray = new JSONObject(jsonResponse).getJSONArray("parameters");
        assert jsonArray != null;
        assert jsonArray.length()==3;
    }

}
