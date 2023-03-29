package sk.fmfi.feeservice;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.Header;
import org.junit.jupiter.api.Test;
import sk.fmfi.resource.dto.FeeDTO;

import java.math.BigDecimal;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class FeeResourceIT {

    @Test
    public void testCreateAndFetchFee() {
        FeeDTO feeDTO = new FeeDTO();
        feeDTO.setAcno("acno");
        feeDTO.setAmount(new BigDecimal(1000));
        feeDTO.setTransactionId("id");
        given().body(feeDTO).header("Content-Type","application/json")
                .when().post("/fee")
                .then()
                .statusCode(200);

        given().body(feeDTO).header("Content-Type","application/json")
                .when().get("/fee")
                .then()
                .statusCode(200)
                .body(is("[]"));
    }
}
