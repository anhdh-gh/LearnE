package source.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Gender {
    MALE("MALE"),
    FEMALE("FEMALE");
    private final String value;
}
