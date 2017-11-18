package io.github.wulkanowy.timetable.api;

import org.junit.Test;
import org.junit.Assert;

public class TimetableTest {

    @Test
    public void addition() throws Exception {
        Assert.assertEquals(4, new Timetable().addition(2, 2));
    }
}
