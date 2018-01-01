package io.github.wulkanowy.timetable.api

import org.jsoup.nodes.Document

open class TimetableList(private val url: String, private val client: Client) {

    data class Data(val classes: List<Class>, val teachers: List<Teacher>, val rooms: List<Room>)

    data class Class(val name: String, val value: String)

    data class Teacher(val name: String, val value: String)

    data class Room(val name: String, val value: String)

    fun getTimetableList(): Data {
        val doc: Document = client.getPageByUrl(url)

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
}
