package stepDefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import io.restassured.response.Response;
import org.junit.Assert;
import utility.configReader;

import static io.restassured.RestAssured.given;

public class stepDefinition {

    private String endpoint;
    private Response response;

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

}
