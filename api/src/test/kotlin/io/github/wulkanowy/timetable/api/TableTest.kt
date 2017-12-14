package io.github.wulkanowy.timetable.api

import com.nhaarman.mockito_kotlin.*
import org.jsoup.Jsoup
import org.junit.Assert.assertEquals

import org.junit.Test
import org.mockito.ArgumentMatchers.anyString

class TableTest : BaseTest() {

    private val table = mock<Table> {
        on { getPageByUrl(anyString()) } doReturn Jsoup.parse(readFile("oddzial.html"))
    }

    private val tableRoom = mock<Table> {
        on { getPageByUrl(anyString()) } doReturn Jsoup.parse(readFile("sala.html"))
    }

    @Test
    fun getClassTableTest() {
        assertEquals(5, table.getClassTable("1").days.size)
        assertEquals(9, table.getRoomTable("2").days[2].hours.size)
        assertEquals(9, table.getTeacherTable("2").days[2].hours.size)
    }

    @Test
    fun getTableDaysTest() {
        assertEquals("Poniedziałek", table.getTable("").days[0].name)
        assertEquals("Piątek", table.getTable("").days[4].name)
    }

    @Test
    fun getTableHoursSizeTest() {
        assertEquals(0, table.getTable("").days[1].hours[0].lessons.size)
        assertEquals(1, table.getTable("").days[1].hours[4].lessons.size)
        assertEquals(2, table.getTable("").days[2].hours[3].lessons.size)
        assertEquals(2, table.getTable("").days[4].hours[7].lessons.size)
    }

    @Test
    fun getTableHoursNumberTest() {
        assertEquals("1", table.getTable("").days[0].hours[0].number)
        assertEquals("3", table.getTable("").days[1].hours[2].number)
        assertEquals("5", table.getTable("").days[2].hours[4].number)
        assertEquals("7", table.getTable("").days[3].hours[6].number)
        assertEquals("9", table.getTable("").days[4].hours[8].number)
    }

    @Test
    fun getTableHoursStartTest() {
        assertEquals("8:00", table.getTable("").days[0].hours[0].start)
        assertEquals("9:40", table.getTable("").days[1].hours[2].start)
        assertEquals("11:30", table.getTable("").days[2].hours[4].start)
        assertEquals("13:10", table.getTable("").days[3].hours[6].start)
        assertEquals("14:50", table.getTable("").days[4].hours[8].start)
    }

    @Test
    fun getTableHoursEndTest() {
        assertEquals("8:45", table.getTable("").days[0].hours[0].end)
        assertEquals("10:25", table.getTable("").days[1].hours[2].end)
        assertEquals("12:15", table.getTable("").days[2].hours[4].end)
        assertEquals("13:55", table.getTable("").days[3].hours[6].end)
        assertEquals("15:35", table.getTable("").days[4].hours[8].end)
    }

    @Test
    fun getTableLessonTeacherTest() {
        assertEquals("PR", table.getTable("").days[0].hours[0].lessons[0].teacher)
        assertEquals("Dr", table.getTable("").days[0].hours[0].lessons[1].teacher)
        assertEquals("Ho", table.getTable("").days[0].hours[4].lessons[0].teacher)
        assertEquals("", table.getTable("").days[1].hours[8].lessons[0].teacher)
        assertEquals("Oż", table.getTable("").days[1].hours[8].lessons[1].teacher)
    }

    @Test
    fun getTableLessonRoomTest() {
        assertEquals("33", table.getTable("").days[0].hours[0].lessons[0].room)
        assertEquals("35", table.getTable("").days[0].hours[0].lessons[1].room)
        assertEquals("21", table.getTable("").days[0].hours[4].lessons[0].room)
        assertEquals("19", table.getTable("").days[1].hours[8].lessons[0].room)
        assertEquals("32", table.getTable("").days[1].hours[8].lessons[1].room)
    }

    @Test
    fun getTableLessonSubjectTest() {
        assertEquals("użytk.peryf-1/2", table.getTable("").days[0].hours[0].lessons[0].subject)
        assertEquals("sieci.komput-2/2", table.getTable("").days[0].hours[0].lessons[1].subject)
        assertEquals("u_hist.i sp.", table.getTable("").days[0].hours[4].lessons[0].subject)
        assertEquals("r_fizyka #3fi", table.getTable("").days[1].hours[8].lessons[0].subject)
        assertEquals("naprawa.komp-1/2", table.getTable("").days[1].hours[8].lessons[1].subject)
    }

    @Test
    fun getTableLessonClassNameTest() {
        assertEquals("2Tc", tableRoom.getTable("").days[0].hours[0].lessons[0].className)
        assertEquals("4Tp", tableRoom.getTable("").days[1].hours[0].lessons[0].className)
    }
}
