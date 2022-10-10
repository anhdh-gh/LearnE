package source.entity.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum StatusType {

    UNFINISHED("UNFINISHED"),
    PROCESSING("PROCESSING"),
    FINISHED("FINISHED");

    private final String value;
}