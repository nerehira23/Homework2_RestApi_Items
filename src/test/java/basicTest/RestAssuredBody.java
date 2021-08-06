package basicTest;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class RestAssuredBody {

    /**
     * given() -->  configuration
     * when() --> request action -->URL --Method
     * then() --> verificaciones
     */

    @Test
    public void createProjectString(){
        given()
                .auth()
                .preemptive()
                .basic("ucb@ucb2021.com","12345")
                .body("{\n" +
                        "  \"Content\":\"UCB_Ibeth_2\",\n" +
                        "  \"Icon\":\"10\"\n" +
                        "}")
                .log()
                .all()
                .when()
                .post("http://todo.ly/api/projects.json")
                .then()
                .log()
                .all();
    }

    @Test
    public void createProjectExternalFile(){
        given()
                .auth()
                .preemptive()
                .basic("ucb@ucb2021.com","12345")
                .body(new File("C:\\Users\\nerehira\\Desktop\\Diplomado\\Modulo 3\\rest_api\\src\\test\\resources\\projectBody.json"))
                .log()
                .all()
                .when()
                .post("http://todo.ly/api/projects.json")
                .then()
                .log()
                .all();
    }

    @Test
    public void createProjectJSONObject(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Content","UCB_Ibeth_Json");
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
                .log()
                .all();
    }
}
