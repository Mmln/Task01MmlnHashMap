package org.stepup.stream6;

import junit.framework.Assert;
import org.junit.jupiter.api.Test;
import org.stepup.stream6.entities.CurTypes;

public class CurTypesTest {
    @Test
    void values() {
        Assert.assertNotNull(CurTypes.USD.values());
    }

    @Test
    void valueOf() {
        Assert.assertEquals(CurTypes.USD,CurTypes.USD.valueOf("USD"));
    }
}
