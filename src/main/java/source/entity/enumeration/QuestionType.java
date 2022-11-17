package source.entity.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum QuestionType {

    VOCABULARY("VOCABULARY"),

    PHOTOGRAPHS("PHOTOGRAPHS"),

    QUESTION_RESPONSE("QUESTION_RESPONSE"),

    CONVERSATIONS("CONVERSATIONS"),

    TALKS("TALKS"),

    INCOMPLETE_SENTENCES("INCOMPLETE_SENTENCES"),

    TEXT_COMPLETION("TEXT_COMPLETION"),

    SINGLE_OR_MULTIPLE_PASSAGES("SINGLE_OR_MULTIPLE_PASSAGES"),

    FILE("FILE");

    private final String value;
}