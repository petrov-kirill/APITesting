package homework.speller.enums;

public enum Format {
    PLAIN("plain"),
    HTML("html");

    public String text;

    Format(String text) {
        this.text = text;
    }
}
