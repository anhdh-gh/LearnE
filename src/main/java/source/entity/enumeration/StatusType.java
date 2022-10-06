package source.entity.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter

public enum StatusType {

    UNFINISHED("UNFINISHED"),
    PROCESSING("PROCESSING"),
    FINISHED("FINISHED"),
    UNKNOWN(null)
    ;

    private final String value;
}