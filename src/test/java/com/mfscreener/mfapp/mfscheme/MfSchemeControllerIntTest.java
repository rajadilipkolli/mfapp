package com.mfscreener.mfapp.mfscheme;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import com.mfscreener.mfapp.common.AbstractIntegrationTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

class MfSchemeControllerIntTest extends AbstractIntegrationTest {

    @Test
    void shouldLoadDataWhenSchemeFound() {
        RestAssured.given()
                .pathParam("schemeCode", "120503")
                .when()
                .get("/api/schemes/{schemeCode}")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body("schemeCode", is(120503))
                .body("payout", is("INF846K01EW2"))
                .body("schemeName", is("Axis ELSS Tax Saver Fund - Direct Plan - Growth Option"))
                .body("nav", notNullValue(String.class))
                .body("date", notNullValue(String.class))
                .body("schemeType", is("Axis ELSS Tax Saver Fund - Direct Plan - Growth Option"));
    }
}
