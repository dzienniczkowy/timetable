package io.github.wulkanowy.timetable;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import java.util.LinkedHashMap;

public class InputFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_input, container, false);
        final AutoCompleteTextView schoolView = view.findViewById(R.id.school);

        // Get the string array
        String[] timetables = view.getResources().getStringArray(R.array.timetables_schools);
        // Create the adapter and set it to the AutoCompleteTextView
        ArrayAdapter<String> adapter = new ArrayAdapter<>
                (getActivity(), android.R.layout.simple_list_item_1, timetables);
        schoolView.setAdapter(adapter);

        Button button = view.findViewById(R.id.action_save);
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                String timetableUrl = schoolView.getText().toString();

                String[] keys = getResources().getStringArray(R.array.timetables_schools);
                String[] values = getResources().getStringArray(R.array.timetables_urls);
                LinkedHashMap<String, String> map = new LinkedHashMap<>();

                for (int i = 0; i < Math.min(keys.length, values.length); ++i) {
                    map.put(keys[i], values[i]);
                }

                if (map.containsKey(timetableUrl)) {
                    timetableUrl = map.get(timetableUrl);
                }

                Snackbar.make(getActivity().findViewById(R.id.container),
                        timetableUrl, Snackbar.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
