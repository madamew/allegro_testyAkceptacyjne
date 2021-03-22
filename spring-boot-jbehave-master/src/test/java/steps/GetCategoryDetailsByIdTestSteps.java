package steps;

import org.jbehave.core.annotations.Then;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import utils.RESTHelper;
import java.io.IOException;
import java.net.http.HttpResponse;

public class GetCategoryDetailsByIdTestSteps {
    private String jsonResponse;
    private HttpResponse<String> httpResponse;
    @Autowired
    private RESTHelper callRest;


    @Then("System prezentuje szczegóły kategorii")
    public void getDetailsOfCategory() throws IOException {
        this.httpResponse = callRest.getResponse();
        this.jsonResponse = httpResponse.body();

        assert new JSONObject(jsonResponse).get("name").equals("Dom i Ogród");
        assert new JSONObject(jsonResponse).get("parent") != null;
        assert new JSONObject(jsonResponse).get("options") != null;
    }

}
