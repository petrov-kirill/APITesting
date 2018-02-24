//package sandbox;
//
//import io.restassured.RestAssured;
//import io.restassured.http.ContentType;
//import io.restassured.http.Headers;
//import io.restassured.response.Response;
//import org.apache.http.HttpStatus;
//import org.hamcrest.Matchers;
//import org.junit.Test;
//
//public class Sandbox {
//
//    private String localHost = "http://localhost:3000/posts";
//
//    @Test
//    public void pathetic() {
//        Response response = RestAssured.given()
//                .params("id", "4", "name", "kirill", "lastName", "petrov")
//                .post(localHost);
//        Headers headers = response.headers();
//        System.out.println(headers);
//    }
//
//    @Test
//    public void getTest() {
//        System.out.println(RestAssured.get(localHost).asString());
//    }
//
//    @Test
//    public void primitive() {
//        RestAssured
//                .given()
//                .queryParam("text", "word")
//                .params("geocode", "37.611,55.758")
//                .accept(ContentType.JSON)
//                .header("headerName", "headerValue")
//                .and()
//                .body("some body info")
//                .log()
//                .everything()
//                .when()
//                .get(YandexMapsApiConstants.YANDEX_SPELLER_API_URI)
//                .prettyPeek()
//                .then()
//                .assertThat()
//                .statusCode(HttpStatus.SC_OK)
//                .body(Matchers.contains("Москва"))
//                .time(Matchers.lessThan(20000L));
//    }
//}
