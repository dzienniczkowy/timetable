package io.github.wulkanowy.timetable.api

import org.jsoup.nodes.Document

class TimetableList(private val url: String, private val client: Client) {

    data class Data(val classes: List<Class>, val teachers: List<Teacher>, val rooms: List<Room>)

    data class Class(val name: String, val value: String)

    data class Teacher(val name: String, val value: String)

    data class Room(val name: String, val value: String)

    fun getTimetableList(): Data {
        val doc: Document = client.getPageByUrl(
                client.getPageByUrl(url).select("frame[name=list]").attr("abs:src")
        )

        return when {
            doc.select("form").isNotEmpty() -> getTimetableSelectListType(doc)
            doc.select("body table").isNotEmpty() -> getTimetableExpandableListType(doc)
            else -> getTimetableUnorderedListType(doc)
        }
    }

    private fun getTimetableSelectListType(doc: Document): Data {
        val classes: MutableList<Class> = mutableListOf()
        val teachers: MutableList<Teacher> = mutableListOf()
        val rooms: MutableList<Room> = mutableListOf()

        doc.select("[name=oddzialy] option").drop(1).mapTo(classes) {
            Class(it.text(), it.attr("value"))
        }

        doc.select("[name=nauczyciele] option").drop(1).mapTo(teachers) {
            Teacher(it.text(), it.attr("value"))
        }

        doc.select("[name=sale] option").drop(1).mapTo(rooms) {
            Room(it.text(), it.attr("value"))
        }

        return Data(classes = classes, teachers = teachers, rooms = rooms)
    }

    private fun getTimetableUnorderedListType(doc: Document): Data {
        return getTimetableUrlSubType(doc,
                "ul:first-of-type a", "ul:nth-of-type(2) a", "ul:nth-of-type(3) a")
    }

    private fun getTimetableExpandableListType(doc: Document): Data {
        return getTimetableUrlSubType(doc,
                "#oddzialy a", "#nauczyciele a", "#sale a")
    }

    private fun getTimetableUrlSubType(doc: Document, classQ: String, teachersQ: String, roomsQ: String): Data {
        val classes: MutableList<Class> = mutableListOf()
        val teachers: MutableList<Teacher> = mutableListOf()
        val rooms: MutableList<Room> = mutableListOf()

        doc.select(classQ).mapTo(classes) {
            Class(it.text(), it.attr("href")
                    .removePrefix("plany/o").removeSuffix(".html"))
        }

        doc.select(teachersQ).mapTo(teachers) {
            Teacher(it.text(), it.attr("href")
                    .removePrefix("plany/n").removeSuffix(".html"))
        }

        doc.select(roomsQ).mapTo(rooms) {
            Room(it.text(), it.attr("href")
                    .removePrefix("plany/s").removeSuffix(".html"))
        }

        return Data(classes = classes, teachers = teachers, rooms = rooms)
    }
}
