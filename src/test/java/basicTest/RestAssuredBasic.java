package basicTest;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class RestAssuredBasic {

    /**
     * given() -->  configuration
     * when() --> request action -->URL --Method
     * then() --> verificaciones
     */

    @Test
    public void createProject(){
        given()
                .header("Authorization","Basic dWNiQHVjYjIwMjEuY29tOjEyMzQ1")
                .body("{\n" +
                        "  \"Content\":\"UCB_Ibeth\",\n" +
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
    public void createProject2(){
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
}
