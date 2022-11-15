package source.dto.response.get_course_detail_for_user;

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
    SINGLE_OR_MULTIPLE_PASSAGES("SINGLE_OR_MULTIPLE_PASSAGES");

    private final String value;
}