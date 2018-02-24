package beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class YandexGeoAnswers {

    @SerializedName("found")
    @Expose
    public Integer found;

    @SerializedName("results")
    @Expose
    public Integer results;

    @Override
    public String toString() {
        return "YandexGeoAnswers{" +
                "found=" + found +
                ", results=" + results +
                '}';
    }
}
