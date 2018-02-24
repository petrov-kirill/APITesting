package homework.geocoding;

import org.junit.Test;

import static homework.geocoding.constants.Constants.MOSCOW_TVERSKAY_SEVEN_REVERSED_CODING;
import static homework.geocoding.constants.Constants.MOSCOW_TVERSKAY_SEVEN_STRAIGHT_CODING;

public class YandexGeoApiTests {

    @Test
    public void basicRequest() {
        YandexGeoApi.with()
                .geocode(MOSCOW_TVERSKAY_SEVEN_STRAIGHT_CODING)
                .callApi()
                .then()
                .specification(YandexGeoApi.successResponse());
    }

    @Test
    public void basicRequestReversedCoding() {
        YandexGeoApi.with()
                .geocode(MOSCOW_TVERSKAY_SEVEN_REVERSED_CODING)
                .callApi()
                .then()
                .specification(YandexGeoApi.successResponse());
    }

}
