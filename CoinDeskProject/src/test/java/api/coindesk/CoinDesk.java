package api.coindesk;

import endpoints.Urls;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class CoinDesk {

    public static Response response;

    public static Response getCurrentPrice() {
        response = given().accept(ContentType.JSON)
                .when().get(Urls.CURRENCY_PATH);
        return response;
    }

}
