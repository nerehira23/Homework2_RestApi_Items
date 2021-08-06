package factoryRequest;

import io.restassured.response.Response;
import util.ConfigEnv;

import static io.restassured.RestAssured.given;

public class RequestPUT implements IRequest{

    @Override
    public Response send(RequestInformation information) {
        Response response = given()
                            .auth()
                            .preemptive()
                            .basic(ConfigEnv.user,ConfigEnv.password)
                            .body(information.getBody())
                            .log()
                            .all()
                    .when()
                    .put(information.getUrl());

        response.then()
                .log()
                .all();
        return response;
    }
}
