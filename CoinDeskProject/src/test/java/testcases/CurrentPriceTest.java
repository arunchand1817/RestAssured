package testcases;

import api.coindesk.CoinDesk;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class CurrentPriceTest {

    Response response;
    public static final String MISMATCH_ERROR = "Value Mismatch";

    @Test
    public void testGetCurrentPrice() {
        Set<String> expectedCurrencies = new HashSet<>();
        expectedCurrencies.add("EUR");
        expectedCurrencies.add("GBP");
        expectedCurrencies.add("USD");

        response = CoinDesk.getCurrentPrice();
        JsonPath path = response.jsonPath();
        HashMap<String, HashMap<String, String>> map = path.get("bpi");
        String actualValue = path.get("bpi.GBP.description").toString();
        Set<String> actualCurrencies = map.keySet();

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK, MISMATCH_ERROR);
        Assert.assertEqualsDeep(expectedCurrencies, actualCurrencies, MISMATCH_ERROR);
        Assert.assertEquals(actualValue, "British Pound Sterling", MISMATCH_ERROR);

    }
}
