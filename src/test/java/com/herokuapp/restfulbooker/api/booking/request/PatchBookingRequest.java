package com.herokuapp.restfulbooker.api.booking.request;

import com.herokuapp.restfulbooker.BaseRequest;
import com.herokuapp.restfulbooker.api.booking.response.PatchBookingResponse;
import com.herokuapp.restfulbooker.url.BookingUrl;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.http.HttpStatus;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PatchBookingRequest extends BaseRequest {

    public static PatchBookingResponse partialUpdateBooking(JSONObject payload, int bookingId, String token) {

        return given()
                .spec(requestSetup())
                .body(payload.toString())
                .when()
                .patch(BookingUrl.BOOKING + "/" + bookingId)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .log().ifError()
                .extract()
                .response()
                .getBody()
                .as(PatchBookingResponse.class);
    }
}