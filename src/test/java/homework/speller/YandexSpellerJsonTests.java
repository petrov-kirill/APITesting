package homework.speller;

import beans.YandexSpellerAnswer;
import core.YandexSpellerApi;
import core.YandexSpellerConstants;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Task: write 10 REST tests for YandexSpeller.
 */
public class YandexSpellerJsonTests {

    @Test
    public void wrongTextWithOptionsTest() {
        ValidatableResponse response = RestAssured
                .given(YandexSpellerApi.baseRequestConfiguration())
                .param("text", "Hello123")
                .get().prettyPeek()
                .then()
                .specification(YandexSpellerApi.successResponse())
                .assertThat()
                .body(not(isEmptyString()));
    }

    @Test
    public void wrongTextWithoutOptionsTest() {
        List<YandexSpellerAnswer> result = YandexSpellerApi.getYandexSpellerAnswers(
                YandexSpellerApi.with()
                        .text("t3ext")
                        .callApi()
        );
        assertThat(result.size(), equalTo(1));
    }

    @Test
    public void ignoreDigitsOptionTest() {
        List<YandexSpellerAnswer> resultList = YandexSpellerApi.getYandexSpellerAnswers(
                YandexSpellerApi.with()
                        .options("2")
                        .text("Hello123")
                        .language(YandexSpellerConstants.Languages.EN)
                        .callApi()
        );
        assertThat(resultList.size(), equalTo(0));
    }
}
