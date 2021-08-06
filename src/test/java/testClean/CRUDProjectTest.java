package testClean;

import factoryRequest.FactoryRequest;
import factoryRequest.RequestInformation;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.ConfigAPI;
import util.GetProperties;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.equalTo;

public class CRUDProjectTest {

    @BeforeEach
    public void before() throws IOException {
        new GetProperties().readProperties();
    }

    @Test
    public void verifyCRUDProject(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Content","UCB_Ibeth");
        jsonObject.put("Icon",10);

        RequestInformation requestInformation = new RequestInformation(ConfigAPI.CREATE_PROJECT,jsonObject.toString());
        Response response = FactoryRequest.make(FactoryRequest.POST).send(requestInformation);

        response.then()
                .statusCode(200)
                .body("Content",equalTo("UCB_Ibeth"));

        int id = response.then().extract().path("Id");
        //Actualizar

        requestInformation = new RequestInformation(ConfigAPI.UPDATE_PROJECT.replace("ID",String.valueOf(id)),jsonObject.toString());
        response = FactoryRequest.make(FactoryRequest.PUT).send(requestInformation);

        response.then()
                .statusCode(200)
                .body("Content",equalTo("UCB_Ibeth"));

        requestInformation = new RequestInformation(ConfigAPI.READ_PROJECT.replace("ID",String.valueOf(id)),jsonObject.toString());
        response = FactoryRequest.make(FactoryRequest.GET).send(requestInformation);

        response.then()
                .statusCode(200)
                .body("Content",equalTo("UCB_Ibeth"));

        requestInformation = new RequestInformation(ConfigAPI.DELETE_PROJECT.replace("ID",String.valueOf(id)),jsonObject.toString());
        response = FactoryRequest.make(FactoryRequest.DELETE).send(requestInformation);

        response.then()
                .statusCode(200)
                .body("Deleted",equalTo(true));
    }
}
