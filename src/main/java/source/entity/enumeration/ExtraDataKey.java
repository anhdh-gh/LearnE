package source.entity.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ExtraDataKey {

    TARGET("TARGET"),
    REQUEST("REQUEST");

    private final String value;
}