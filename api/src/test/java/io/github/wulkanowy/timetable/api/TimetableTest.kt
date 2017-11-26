package io.github.wulkanowy.timetable.api

import org.junit.Assert
import org.junit.Test

class TimetableTest {

    @Test
    @Throws(Exception::class)
    fun addition() {
        Assert.assertEquals(4, Timetable().addition(2, 2).toLong())
    }
}
