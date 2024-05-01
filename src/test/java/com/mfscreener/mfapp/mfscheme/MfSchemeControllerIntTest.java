package com.mfscreener.mfapp.mfscheme;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import com.mfscreener.mfapp.common.AbstractIntegrationTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MfSchemeControllerIntTest extends AbstractIntegrationTest {

    @BeforeAll
    void setUp() {
        RestAssured.port = serverPort;
    }

    @Test
    void shouldLoadDataWhenSchemeFound() {
        given().pathParam("schemeCode", "120503")
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
                .body("schemeType", is("Open Ended Schemes(Equity Scheme - ELSS)"));
    }
}
