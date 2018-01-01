package io.github.wulkanowy.timetable.api

import com.nhaarman.mockito_kotlin.doReturn
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.jsoup.Jsoup
import org.junit.Assert.assertEquals

import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import com.nhaarman.mockito_kotlin.mock

class TimetableListTest : BaseTest() {

    private val timetableList = TimetableList("http://faketable.wulkanowy/index.html", mock {
        on { getPageByUrl(anyString()) } doReturn Jsoup.parse(readFile("lista.html"))
    })

    @Test
    fun testGetTimetableListTypes() {
        assertThat(timetableList.getTimetableList(),
                instanceOf(TimetableList.Data::class.java))
    }

    @Test
    fun testClassData() {
        assertEquals("1Tc", timetableList.getTimetableList().classes[0].name)
        assertEquals("1", timetableList.getTimetableList().classes[0].value)
        assertEquals("1Ti", timetableList.getTimetableList().classes[1].name)
        assertEquals("2", timetableList.getTimetableList().classes[1].value)
    }

    @Test
    fun testTeachersData() {
        assertEquals("I.Ochocki (Io)", timetableList.getTimetableList().teachers[0].name)
        assertEquals("1", timetableList.getTimetableList().teachers[0].value)
        assertEquals("M.Oleszkiewicz (Mo)", timetableList.getTimetableList().teachers[1].name)
        assertEquals("3", timetableList.getTimetableList().teachers[1].value)
    }

    @Test
    fun testRoomData() {
        assertEquals("16 prac. geograficzna", timetableList.getTimetableList().rooms[0].name)
        assertEquals("1", timetableList.getTimetableList().rooms[0].value)
        assertEquals("17 prac. fizyczna", timetableList.getTimetableList().rooms[1].name)
        assertEquals("2", timetableList.getTimetableList().rooms[1].value)
    }
}
