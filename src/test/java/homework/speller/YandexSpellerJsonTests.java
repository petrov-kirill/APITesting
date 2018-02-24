package homework.speller;

import beans.YandexSpellerAnswer;
import core.YandexSpellerApi;
import core.YandexSpellerConstants;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static homework.speller.enums.Languages.EN;
import static homework.speller.enums.Languages.RU;
import static homework.speller.enums.Options.*;
import static homework.speller.enums.Params.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class YandexSpellerJsonTests {

    @Test
    public void wrongTextWithOptionsTest() {
        RestAssured
                .given(YandexSpellerApi.baseRequestConfiguration())
                .param(TEXT.param, "Hello123")
                .when()
                .get()
                .prettyPeek()
                .then()
                .specification(YandexSpellerApi.successResponse())
                .assertThat()
                .body(not(isEmptyString()));
    }

    @Test
    public void wrongEnglishTextWithoutOptions() {
        List<YandexSpellerAnswer> result = YandexSpellerApi.getYandexSpellerAnswers(
                YandexSpellerApi.with()
                        .language(YandexSpellerConstants.Languages.EN)
                        .text("t3ext")
                        .callApi()
        );
        assertThat(result.size(), equalTo(1));
    }

    @Test
    public void repeatingRussianWords() {
        List<YandexSpellerAnswer> result = YandexSpellerApi.getYandexSpellerAnswers(
                YandexSpellerApi.with()
                        .language(YandexSpellerConstants.Languages.RU)
                        .options("8")
                        .text("повтор повтор")
                        .callApi()
        );
        assertThat(result.size(), equalTo(0));
    }

    @Test
    public void wrongEnglishTextWithIgnoredDigits() {
        List<YandexSpellerAnswer> resultList = YandexSpellerApi.getYandexSpellerAnswers(
                YandexSpellerApi.with()
                        .options(String.valueOf(IGNORE_DIGITS.number))
                        .text("Hello123")
                        .language(YandexSpellerConstants.Languages.EN)
                        .callApi()
        );
        assertThat(resultList.size(), equalTo(0));
    }

    @Test
    public void wrongEnglishTextWithIgnoredURL() {
        RestAssured
                .given(YandexSpellerApi.baseRequestConfiguration())
                .accept(ContentType.JSON)
                .param(OPTIONS.param, IGNORE_URLS.number)
                .param(TEXT.param, "Hello user@mail.com World!")
                .param(LANG.param, EN.text)
                .when()
                .get()
                .prettyPeek()
                .then()
                .specification(YandexSpellerApi.successResponse());
    }

    @Test
    public void repeatingWordsTest() {
        RestAssured
                .given(YandexSpellerApi.baseRequestConfiguration())
                .param(TEXT.param, "hey hey")
                .param(OPTIONS.param, IGNORE_CAPITALIZATION.number)
                .param(LANG.param, EN.text)
                .when()
                .get()
                .prettyPeek()
                .then()
                .specification(YandexSpellerApi.successResponse());
    }

    @Test
    public void capitalisationEnglishTest() {
        RestAssured
                .given(YandexSpellerApi.baseRequestConfiguration())
                .param(TEXT.param, "dubai")
                .param(OPTIONS.param, IGNORE_CAPITALIZATION.number)
                .param(LANG.param, EN.text)
                .when()
                .get()
                .prettyPeek()
                .then()
                .specification(YandexSpellerApi.successResponse())
                .assertThat()
                .body(not(isEmptyString()));
    }

    @Test
    public void capitalizationRussianTest() {
        RestAssured
                .given(YandexSpellerApi.baseRequestConfiguration())
                .param(TEXT.param, "москва")
                .param(OPTIONS.param, IGNORE_CAPITALIZATION.number)
                .param(LANG.param, RU.text)
                .when()
                .get()
                .prettyPeek()
                .then()
                .specification(YandexSpellerApi.successResponse());
    }

    @Test
    public void repeatingRussianWordsTest() {
        RestAssured
                .given(YandexSpellerApi.baseRequestConfiguration())
                .param(TEXT.param, "привет привет")
                .param(OPTIONS.param, FIND_REPEAT_WORDS.number)
                .param(LANG.param, RU.text)
                .when()
                .get()
                .prettyPeek()
                .then()
                .specification(YandexSpellerApi.successResponse());
    }

    @Test
    public void russianWordStartsWithNumber() {
        RestAssured
                .given(YandexSpellerApi.baseRequestConfiguration())
                .param(TEXT.param, "123Привет")
                .param(OPTIONS.param, IGNORE_DIGITS.number)
                .param(LANG.param, EN.text)
                .when()
                .get()
                .prettyPeek()
                .then()
                .specification(YandexSpellerApi.successResponse());
    }

    @Test
    public void wrongRussianWordTest() {
        RestAssured
                .given(YandexSpellerApi.baseRequestConfiguration())
                .param(TEXT.param, "Прывет")
                .param(LANG.param, RU.text)
                .when()
                .get()
                .prettyPeek()
                .then()
                .specification(YandexSpellerApi.successResponse())
                .assertThat()
                .body(not(isEmptyString()));
    }

    @Test
    public void russianWordStartsWithDigit() {
                YandexSpellerApi.with()
                .options("2")
                .text("1давай давай")
                .callApi()
                .then()
                .specification(YandexSpellerApi.successResponse());
    }
}
