package util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Cast {

    public static <T> Optional<T> cast(Object toCast, @NonNull Class<T> target) {
        return Optional.ofNullable(toCast)
                .filter(t -> target.isAssignableFrom(t.getClass()))
                .map(target::cast);
    }

}
