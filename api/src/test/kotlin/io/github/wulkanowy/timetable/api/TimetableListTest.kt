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

    private val timetableSelect = TimetableList("http://faketable.wulkanowy/lista-form.html", mock {
        on { getPageByUrl(anyString()) } doReturn Jsoup.parse(readFile("lista-form.html"))
    })

    private val timetableUl = TimetableList("http://faketable.wulkanowy/lista-unordered.html", mock {
        on { getPageByUrl(anyString()) } doReturn Jsoup.parse(readFile("lista-unordered.html"))
    })

    private val timetableExpand = TimetableList("http://faketable.wulkanowy/lista-expandable.html", mock {
        on { getPageByUrl(anyString()) } doReturn Jsoup.parse(readFile("lista-expandable.html"))
    })

    @Test
    fun testGetTimetableListTypes() {
        assertThat(timetableSelect.getTimetableList(), instanceOf(TimetableList.Data::class.java))
        assertThat(timetableUl.getTimetableList(), instanceOf(TimetableList.Data::class.java))
        assertThat(timetableExpand.getTimetableList(), instanceOf(TimetableList.Data::class.java))
    }

    @Test
    fun testClassDataSelect() {
        assertEquals(2, timetableSelect.getTimetableList().classes.size)
        assertEquals("1Tc", timetableSelect.getTimetableList().classes[0].name)
        assertEquals("1", timetableSelect.getTimetableList().classes[0].value)
        assertEquals("1Ti", timetableSelect.getTimetableList().classes[1].name)
        assertEquals("2", timetableSelect.getTimetableList().classes[1].value)
    }

    @Test
    fun testClassDataUl() {
        assertEquals(2, timetableUl.getTimetableList().classes.size)
        assertEquals("1Tc", timetableUl.getTimetableList().classes[0].name)
        assertEquals("1", timetableUl.getTimetableList().classes[0].value)
        assertEquals("1Ti", timetableUl.getTimetableList().classes[1].name)
        assertEquals("2", timetableUl.getTimetableList().classes[1].value)
    }

    @Test
    fun testClassDataExpandable() {
        assertEquals(2, timetableExpand.getTimetableList().classes.size)
        assertEquals("1Tc", timetableExpand.getTimetableList().classes[0].name)
        assertEquals("1", timetableExpand.getTimetableList().classes[0].value)
        assertEquals("1Ti", timetableExpand.getTimetableList().classes[1].name)
        assertEquals("2", timetableExpand.getTimetableList().classes[1].value)
    }

    @Test
    fun testTeachersDataSelect() {
        assertEquals(2, timetableSelect.getTimetableList().teachers.size)
        assertEquals("I.Ochocki (Io)", timetableSelect.getTimetableList().teachers[0].name)
        assertEquals("1", timetableSelect.getTimetableList().teachers[0].value)
        assertEquals("M.Oleszkiewicz (Mo)", timetableSelect.getTimetableList().teachers[1].name)
        assertEquals("3", timetableSelect.getTimetableList().teachers[1].value)
    }

    @Test
    fun testTeachersDataUl() {
        assertEquals(2, timetableUl.getTimetableList().teachers.size)
        assertEquals("I.Ochocki (Io)", timetableUl.getTimetableList().teachers[0].name)
        assertEquals("1", timetableUl.getTimetableList().teachers[0].value)
        assertEquals("M.Oleszkiewicz (Mo)", timetableUl.getTimetableList().teachers[1].name)
        assertEquals("3", timetableUl.getTimetableList().teachers[1].value)
    }

    @Test
    fun testTeachersDataExpandable() {
        assertEquals(2, timetableExpand.getTimetableList().teachers.size)
        assertEquals("I.Ochocki (Io)", timetableExpand.getTimetableList().teachers[0].name)
        assertEquals("1", timetableExpand.getTimetableList().teachers[0].value)
        assertEquals("M.Oleszkiewicz (Mo)", timetableExpand.getTimetableList().teachers[1].name)
        assertEquals("3", timetableExpand.getTimetableList().teachers[1].value)
    }

    @Test
    fun testRoomDataSelect() {
        assertEquals(2, timetableSelect.getTimetableList().rooms.size)
        assertEquals("16 prac. geograficzna", timetableSelect.getTimetableList().rooms[0].name)
        assertEquals("1", timetableSelect.getTimetableList().rooms[0].value)
        assertEquals("17 prac. fizyczna", timetableSelect.getTimetableList().rooms[1].name)
        assertEquals("2", timetableSelect.getTimetableList().rooms[1].value)
    }

    @Test
    fun testRoomDataUl() {
        assertEquals(2, timetableUl.getTimetableList().rooms.size)
        assertEquals("16 prac. geograficzna", timetableUl.getTimetableList().rooms[0].name)
        assertEquals("1", timetableUl.getTimetableList().rooms[0].value)
        assertEquals("17 prac. fizyczna", timetableUl.getTimetableList().rooms[1].name)
        assertEquals("2", timetableUl.getTimetableList().rooms[1].value)
    }

    @Test
    fun testRoomDataExpandable() {
        assertEquals(2, timetableExpand.getTimetableList().rooms.size)
        assertEquals("16 prac. geograficzna", timetableExpand.getTimetableList().rooms[0].name)
        assertEquals("1", timetableExpand.getTimetableList().rooms[0].value)
        assertEquals("17 prac. fizyczna", timetableExpand.getTimetableList().rooms[1].name)
        assertEquals("2", timetableExpand.getTimetableList().rooms[1].value)
    }
}
