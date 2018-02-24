package homework.geocoding;

import beans.YandexGeoAnswers;
import beans.YandexSpellerAnswer;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

import static core.YandexSpellerConstants.YANDEX_SPELLER_API_URI;
import static homework.geocoding.constants.Constants.YANDEX_GEO_API_URL;
import static org.hamcrest.Matchers.lessThan;

public class YandexGeoApi {

    private YandexGeoApi() {
    }
    private HashMap<String, String> params = new HashMap<>();

    public static class ApiBuilder {

        YandexGeoApi geoApi;

        private ApiBuilder(YandexGeoApi yandexGeoApi) {
            geoApi = yandexGeoApi;
        }

        public ApiBuilder geocode(String location) {
            geoApi.params.put("geocode", location);
            return this;
        }

        public Response callApi() {
            return RestAssured.with()
                    .queryParams(geoApi.params)
                    .log().all()
                    .get(YANDEX_GEO_API_URL)
                    .prettyPeek();
        }
    }

    public static ApiBuilder with() {
        YandexGeoApi api = new YandexGeoApi();
        return new ApiBuilder(api);
    }

    public static List<YandexGeoAnswers> getYandexGeoAnswers(Response response) {
        return new Gson().fromJson(response.asString(), new TypeToken<List<YandexGeoAnswers>>() {}.getType());
    }

    public static ResponseSpecification successResponse(){
        return new ResponseSpecBuilder()
                .expectContentType(ContentType.XML)
                .expectHeader("Connection", "keep-alive")
                .expectResponseTime(lessThan(20000L))
                .expectStatusCode(HttpStatus.SC_OK)
                .build();
    }

    public static RequestSpecification baseRequestConfiguration(){
        return new RequestSpecBuilder()
                .setAccept(ContentType.XML)
                .addHeader("custom header2", "header2.value")
                .addQueryParam("requestID", new Random().nextLong())
                .setBaseUri("https://geocode-maps.yandex.ru/1.x/?")
                .build();
    }
}
