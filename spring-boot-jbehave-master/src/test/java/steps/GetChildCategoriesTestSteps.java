package steps;

import org.jbehave.core.annotations.Then;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import utils.JWTHelper;
import utils.RESTHelper;
import java.io.IOException;
import java.net.http.HttpResponse;


public class GetChildCategoriesTestSteps {
    private String jsonResponse;
    private HttpResponse<String> httpResponse;
    private JSONArray jsonArray;
    @Autowired
    private JWTHelper jwtHelper;
    @Autowired
    private RESTHelper callRest;


    @Then("System prezentuje listę podkategorii")
    public void getListOfCategories() throws IOException {
        this.httpResponse = callRest.getResponse();
        this.jsonResponse = httpResponse.body();
        this.jsonArray = new JSONObject(jsonResponse).getJSONArray("categories");
        assert jsonArray != null;
    }

}
