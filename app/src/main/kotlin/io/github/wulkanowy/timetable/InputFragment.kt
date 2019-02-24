package io.github.wulkanowy.timetable

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_input.*
import java.util.*

class InputFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_input, container, false)

        // Get the string array
        val timetables = view.resources.getStringArray(R.array.timetables_schools)
        // Create the adapter and set it to the AutoCompleteTextView
        val adapter = ArrayAdapter(activity!!, android.R.layout.simple_list_item_1, timetables)
        school.setAdapter(adapter)

        actionSave.setOnClickListener {
            var timetableUrl = school.text.toString()

            val keys = resources.getStringArray(R.array.timetables_schools)
            val values = resources.getStringArray(R.array.timetables_urls)
            val map = LinkedHashMap<String, String>()

            for (i in 0 until Math.min(keys.size, values.size)) {
                map[keys[i]] = values[i]
            }

            if (map.containsKey(timetableUrl)) {
                timetableUrl = map[timetableUrl] as String
            }

            Snackbar.make(activityContainer, timetableUrl, Snackbar.LENGTH_SHORT).show()
        }

        return view
    }
}
