package basicTest;

import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class CrudProjectTest {
    @Test
    public void crudProjectRestApi() {
        //Creacion
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Content","UCB_Ibeth_CRUD");
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
                .body("Content",equalTo("UCB_Ibeth_CRUD"))
                .body("Icon",equalTo(10))
                .log()
                .all();

        int id = response.then().extract().path("Id");

        //Actualizacion

        jsonObject.put("Content","UCB_Ibeth_CrudUpdate");
        jsonObject.put("Icon",4);

        response = given()
                .auth()
                .preemptive()
                .basic("ucb@ucb2021.com","12345")
                .body(jsonObject.toString())
                .log()
                .all()
                .when()
                .put("http://todo.ly/api/projects/"+id+".json");

        response.then()
                .statusCode(200)
                .body("Content",equalTo("UCB_Ibeth_CrudUpdate"))
                .body("Icon",equalTo(4))
                .log()
                .all();
        //Get

        response=given()
                .auth()
                .preemptive()
                .basic("ucb@ucb2021.com","12345")
                .log()
                .all()
                .when()
                .get("http://todo.ly/api/projects/"+id+".json");

        response.then()
                .statusCode(200)
                .body("Content", equalTo("UCB_Ibeth_CrudUpdate"))
                .body("Icon",equalTo(4))
                .log()
                .all();
        //Delete

        response = given()
                .auth()
                .preemptive()
                .basic("ucb@ucb2021.com","12345")
                .body(jsonObject.toString())
                .log()
                .all()
                .when()
                .delete("http://todo.ly/api/projects/"+id+".json");

        response.then()
                .statusCode(200)
                .body("Content",equalTo("UCB_Ibeth_CrudUpdate"))
                .body("Icon",equalTo(4))
                .body("Deleted",equalTo(true))
                .log()
                .all();

    }

}
