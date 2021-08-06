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

public class CRUDItemsTest {

    @BeforeEach
    public void before() throws IOException {
        new GetProperties().readProperties();
    }

    @Test
    public void verifyCRUDItems(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Content","Item_Ibeth");

        RequestInformation requestInformation = new RequestInformation(ConfigAPI.CREATE_ITEM,jsonObject.toString());
        Response response = FactoryRequest.make(FactoryRequest.POST).send(requestInformation);

        response.then()
                .statusCode(200)
                .body("Content",equalTo("Item_Ibeth"));

        int id = response.then().extract().path("Id");

        //Actualizar
        jsonObject = new JSONObject();
        jsonObject.put("Checked",true);

        requestInformation = new RequestInformation(ConfigAPI.UPDATE_ITEM.replace("ID",String.valueOf(id)),jsonObject.toString());
        response = FactoryRequest.make(FactoryRequest.PUT).send(requestInformation);

        response.then()
                .statusCode(200)
                .body("Checked",equalTo(true));

        //GET
        requestInformation = new RequestInformation(ConfigAPI.READ_ITEM.replace("ID",String.valueOf(id)),jsonObject.toString());
        response = FactoryRequest.make(FactoryRequest.GET).send(requestInformation);

        response.then()
                .statusCode(200)
                .body("Content",equalTo("Item_Ibeth"));

        //DELETE
        requestInformation = new RequestInformation(ConfigAPI.DELETE_ITEM.replace("ID",String.valueOf(id)),jsonObject.toString());
        response = FactoryRequest.make(FactoryRequest.DELETE).send(requestInformation);

        response.then()
                .statusCode(200)
                .body("Content",equalTo("Item_Ibeth"));
    }
}
