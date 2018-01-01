package io.github.wulkanowy.timetable.api

import org.jsoup.nodes.Element
import org.jsoup.select.Elements

class Table(private val url: String, private val client: Client) {

    data class Week(val days: MutableList<Day> = mutableListOf())

    data class Day(val name: String, val hours: MutableList<Hour> = mutableListOf())

    data class Hour(
            val number: String,
            val start: String,
            val end: String,
            val lessons: MutableList<Lesson> = mutableListOf()
    )

    data class Lesson(
            val teacher: String = "",
            val room: String = "",
            val subject: String = "",
            val className: String = "",
            val alt: String = ""
    )

    fun getClassTable(number: String): Week {
        return getTable("o" + number)
    }

    fun getTeacherTable(number: String): Week {
        return getTable("n" + number)
    }

    fun getRoomTable(number: String): Week {
        return getTable("s" + number)
    }

    fun getTable(name: String): Week {
        val table = client.getPageByUrl(url + name).select(".tabela").first()

        val days: MutableList<Day> = getDays(table.select("tr th"))

        setLessonHoursToDays(table, days)

        return Week(days = days)
    }

    private fun getDays(tableHeaderCells: Elements): MutableList<Day> {
        val days = mutableListOf<Day>()

        tableHeaderCells.drop(2).mapTo(days) {
            Day(name = it.html())
        }

        return days
    }

    private fun setLessonHoursToDays(table: Element, days: MutableList<Day>) {
        for (row in table.select("tr").drop(1)) {
            val rowCells: Elements = row.select("td")

            // fill hours in day
            for (i in 2 until rowCells.size) {
                days[i - 2].hours.add(getHourWithLessons(rowCells, i))
            }
        }
    }

    private fun getHourWithLessons(rowCells: Elements, index: Int): Hour {
        val hours = rowCells[1].text().split("-")
        val hour = Hour(number = rowCells[0].text(), start = hours[0].trim(), end = hours[1].trim())

        val current = rowCells[index]
        if (current.select("> .p").size != 0) {
            hour.lessons.add(getLesson(current))
        }

        val groups = current.select("span[style]")
        for (i in 0 until groups.size) {
            hour.lessons.add(getLesson(groups[i]))
        }

        if (hour.lessons.size == 0 && current.text().trim() != "") {
            hour.lessons.add(Lesson(alt = current.text()))
        }

        return hour
    }

    private fun getLesson(cell: Element) : Lesson {
        return Lesson(
                teacher = cell.select("> .n").text(),
                subject = cell.select("> .p").text(),
                room = cell.select("> .s").text(),
                className = cell.select("> .o").text()
        )
    }
}
