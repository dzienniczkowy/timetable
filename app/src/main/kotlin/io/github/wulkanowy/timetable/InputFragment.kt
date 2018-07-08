package io.github.wulkanowy.timetable

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button

import java.util.LinkedHashMap

class InputFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_input, container, false)
        val schoolView = view.findViewById<AutoCompleteTextView>(R.id.school)

        // Get the string array
        val timetables = view.resources.getStringArray(R.array.timetables_schools)
        // Create the adapter and set it to the AutoCompleteTextView
        val adapter = ArrayAdapter(activity!!, android.R.layout.simple_list_item_1, timetables)
        schoolView.setAdapter(adapter)

        val button = view.findViewById<Button>(R.id.action_save)
        button.setOnClickListener {
            var timetableUrl = schoolView.text.toString()

            val keys = resources.getStringArray(R.array.timetables_schools)
            val values = resources.getStringArray(R.array.timetables_urls)
            val map = LinkedHashMap<String, String>()

            for (i in 0 until Math.min(keys.size, values.size)) {
                map[keys[i]] = values[i]
            }

            if (map.containsKey(timetableUrl)) {
                timetableUrl = map[timetableUrl] as String
            }

            Snackbar.make(activity!!.findViewById(R.id.container),
                    timetableUrl, Snackbar.LENGTH_SHORT).show()
        }

        return view
    }
}
