package stepDefinition;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import utility.configReader;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class stepDefinition {

    private String endpoint;
    private Response response;
    private JSONObject requestJson;

    @Given("verify get request endpoint")
    public void verify_get_request_endpoint() {
        String baseURL = configReader.getProperty("baseUrl");
        this.endpoint=baseURL+"/posts/1";
    }
    @When("Send the get request")
    public void send_the_get_request() {
        response= given().when().get(endpoint);
    }
    @Then("verify the status code")
    public void verify_the_status_code() {
        Assert.assertEquals(200,response.getStatusCode());
        System.out.println(response.getStatusCode());
        System.out.println(response.body().asString());

    }

    @Given("verify post request endpoint")
    public void verifyPostRequestEndpoint() {
        String baseURL=configReader.getProperty("baseUrl");
        this.endpoint=baseURL+"/posts";
        
    }

    @When("send the post request")
    public void sendThePostRequest(DataTable dataTable) {
        Map<String, String> data = dataTable.asMap(String.class, String.class);
        requestJson = new JSONObject();
        for (Map.Entry<String, String> entry : data.entrySet()) {
            // Try parsing value as integer if it's numeric
            String key = entry.getKey();
            String value = entry.getValue();
            try {
                int intValue = Integer.parseInt(value);
                requestJson.put(key, intValue);
            } catch (NumberFormatException e) {
                requestJson.put(key, value);
            }
            response = given()
                    .contentType(ContentType.JSON)
                    .body(requestJson.toString())
                    .when()
                    .post(endpoint);
        }
    }

    @Then("verify the status code for post request")
    public void verifyTheStatusCodeForPostRequest() {
        System.out.println(response.getStatusCode());
    }
}
