package by.bsuir.enums;

import lombok.Getter;

@Getter
public enum RequestParamsEnum {
    USER("user"),
    DEF_SUC_URL("defaultSuccessUrl");
    private final String value;

    RequestParamsEnum(String value) {
        this.value = value;
    }

}

