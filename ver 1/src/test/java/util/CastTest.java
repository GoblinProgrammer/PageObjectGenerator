package util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static util.Cast.cast;

class CastTest {

    @Test
    void shouldProperlyCastToOptional() {
        var str = "xd";
        var number = 1;

        assertTrue(cast(str, String.class).isPresent());
        assertTrue(cast(str, Integer.class).isEmpty());

        assertTrue(cast(number, Integer.class).isPresent());
        assertTrue(cast(number, Number.class).isPresent());
    }
}