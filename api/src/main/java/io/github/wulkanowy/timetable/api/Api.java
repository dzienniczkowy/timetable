package io.github.wulkanowy.timetable.api;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class Api {

    public Document getPageByUrl(String url) throws IOException {
        return Jsoup.connect(url)
                .followRedirects(true)
                .get();
    }
}
