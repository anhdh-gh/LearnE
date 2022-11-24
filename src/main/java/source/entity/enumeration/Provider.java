package source.entity.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Provider {

    QUESTION_BANK("QUESTION_BANK"),
    STUDYSET("STUDYSET");

    private final String value;
}