package io.github.wulkanowy.timetable.api

import org.jsoup.Jsoup
import org.jsoup.nodes.Document

import java.io.IOException

class Api {

    @Throws(IOException::class)
    fun getPageByUrl(url: String): Document {
        return Jsoup.connect(url)
                .followRedirects(true)
                .get()
    }
}
