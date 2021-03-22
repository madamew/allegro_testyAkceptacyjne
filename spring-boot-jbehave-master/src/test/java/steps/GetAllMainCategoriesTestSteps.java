package steps;

import org.jbehave.core.annotations.*;
import org.jbehave.core.model.ExamplesTable;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import utils.JWTHelper;
import utils.RESTHelper;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GetAllMainCategoriesTestSteps {
    private String jsonResponse;
    private HttpResponse<String> httpResponse;
    public List mainCatNamesList;
    public JSONArray jsonArray;

    @Autowired
    private RESTHelper callRest;


    @Given("Główne kategorie: $catNames")
    public void checkCategoriesNames(ExamplesTable catNames) throws IOException {
        this.mainCatNamesList = new ArrayList();
        for (Map<String,String> row : catNames.getRows()) {
            String name = row.get("names");
            mainCatNamesList.add(name);
        }
    }

    @Then("System prezentuje listę głównych kategorii Allegro")
    public void getListOfCategories() throws IOException {
        this.httpResponse = callRest.getResponse();
        this.jsonResponse = httpResponse.body();
        this.jsonArray = new JSONObject(jsonResponse).getJSONArray("categories");
        assert jsonArray != null;
    }

    @Then("Widocznych jest $length głównych kategorii")
    public void arrayLength(int length) throws IOException {
        assert jsonArray.length() == length;
    }


    @Then("Widoczne są tylko nazwy z podanej listy głównych kategorii")
    public void checkCategoriesNames() throws IOException {
        List<String> listOfCatNamesFromResponse = IntStream.range(0, jsonArray.length())
                .mapToObj(index -> ((JSONObject) jsonArray.get(index)).optString("name"))
                .collect(Collectors.toList());

        mainCatNamesList.forEach(mainCategory ->{
            assert listOfCatNamesFromResponse.contains(mainCategory);
        });
        assert listOfCatNamesFromResponse.size() == mainCatNamesList.size();
    }
}
