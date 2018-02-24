package homework.speller.enums;

public enum Params {
    TEXT("text"),
    LANG("lang"),
    OPTIONS("options"),
    FORMAT("format"),
    CALLBACK("callback");

    public String param;

    Params(String param) {
        this.param = param;
    }
}
