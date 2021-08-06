package basicTest;

import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import static org.hamcrest.CoreMatchers.equalTo;
import static io.restassured.RestAssured.given;

public class RestAssuredExtract {

    @Test
    public void restAssuredVerification(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Content","UCB_Ibeth_Check");
        jsonObject.put("Icon",10);

        given()
                .auth()
                .preemptive()
                .basic("ucb@ucb2021.com","12345")
                .body(jsonObject.toString())
                .log()
                .all()
        .when()
                .post("http://todo.ly/api/projects.json")
        .then()
                .statusCode(200)
                .body("Content",equalTo("UCB_Ibeth_Check"))
                .body("Icon",equalTo(10))
                .log()
                .all();
    }

    @Test
    public void restAssuredExtractor(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Content","UCB_Ibeth_Check");
        jsonObject.put("Icon",10);

        Response response = given()
                .auth()
                .preemptive()
                .basic("ucb@ucb2021.com","12345")
                .body(jsonObject.toString())
                .log()
                .all()
        .when()
                .post("http://todo.ly/api/projects.json");

        response.then()
                .statusCode(200)
                .body("Content",equalTo("UCB_Ibeth_Check"))
                .body("Icon",equalTo(10))
                .log()
                .all();

        int id = response.then().extract().path("Id");
        System.out.println("****Valor ID del Response: "+id);
    }
}
