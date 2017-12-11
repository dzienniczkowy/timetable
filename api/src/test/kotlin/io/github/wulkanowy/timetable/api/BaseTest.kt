package io.github.wulkanowy.timetable.api

abstract class BaseTest {

    protected fun readFile(filename: String) : String {
        return this.javaClass.getResource(filename)?.readText()
                ?: throw IllegalStateException("File $filename not found")
    }
}
