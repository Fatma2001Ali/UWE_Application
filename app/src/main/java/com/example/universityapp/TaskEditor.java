package com.example.universityapp;

import android.annotation.SuppressLint;
import android.app.TimePickerDialog;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.time.LocalTime;


public class TaskEditor extends Fragment {
    private Task t;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public TaskEditor() {

    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_task_editor, container, false);

        EditText task = v.findViewById(R.id.task);
        TextView from = v.findViewById(R.id.from);
        TextView to = v.findViewById(R.id.to);
        Spinner color =v.findViewById(R.id.color);
        Button submit = v.findViewById(R.id.submit);
        TextView delete = v.findViewById(R.id.delete);

        Database database = new Database(getContext()); // Use getContext() instead of this
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            t = new Task();
        }

        if (getArguments() != null && getArguments().containsKey("Date")) {
            String date = getArguments().getString("Date");
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                t.setID(database.getNextID(date));
            }
        }

        String[] colors = {"Rose", "Blue", "Green", "Red", "Yellow", "Orange", "Purple", "Grey"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(),
                android.R.layout.simple_spinner_item, colors);
        color.setAdapter(adapter);

        if (getArguments() != null && getArguments().containsKey("Task")) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                t.setID(getArguments().getInt("ID"));
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                t.setTask(getArguments().getString("Task"));
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                t.setFrom(String.valueOf(LocalTime.parse(getArguments().getString("From"))));
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                t.setTo(String.valueOf(LocalTime.parse(getArguments().getString("To"))));
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                t.setColor(getArguments().getString("Color"));
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                color.setSelection(adapter.getPosition(t.getColor()));
            }
            GradientDrawable background = (GradientDrawable) color.getBackground();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                background.setColor(t.getColorID(requireContext()));
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                task.setText(t.getTask());
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                from.setText(t.getFromToString());
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                to.setText(t.getToToString());
            }

            submit.setOnClickListener(view -> {
                if (task.getText().toString().isEmpty()) {
                    task.setError("Task cannot be empty");
                    return;
                }
                if (from.getText().equals("Click Here")) {
                    Toast.makeText(getContext(), "Select Time: From", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (to.getText().equals("Click Here")) {
                    Toast.makeText(getContext(), "Select Time: To", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    t.setTask(task.getText().toString());
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    database.updateTask(t, getArguments().getString("Date"));
                }
                Toast.makeText(getContext(), "Task updated successfully", Toast.LENGTH_SHORT).show();
               // requireActivity().finish();
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();


                FragmentTransaction transaction = fragmentManager.beginTransaction();


                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    transaction.replace(R.id.framLatOut, new searchFragment());
                }

                transaction.addToBackStack(null);

                transaction.commit();
            });

            delete.setOnClickListener(view -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    database.deleteTask(t.getID(), getArguments().getString("Date"));
                }
                Toast.makeText(getContext(), "Task deleted successfully", Toast.LENGTH_SHORT).show();
                //requireActivity().finish();
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();


                FragmentTransaction transaction = fragmentManager.beginTransaction();


                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    transaction.replace(R.id.framLatOut, new searchFragment());
                }

                transaction.addToBackStack(null);

                transaction.commit();
            });

        } else {
            submit.setOnClickListener(view -> {
                if (task.getText().toString().isEmpty()) {
                    task.setError("Task cannot be empty");
                    return;
                }
                if (from.getText().equals("Click Here")) {
                    Toast.makeText(getContext(), "Select Time: From", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (to.getText().equals("Click Here")) {
                    Toast.makeText(getContext(), "Select Time: To", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    t.setTask(task.getText().toString());
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    database.addTask(t, getArguments().getString("Date"));
                }
                Toast.makeText(getContext(), "Task added successfully", Toast.LENGTH_SHORT).show();
                //requireActivity().finish();
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();


                FragmentTransaction transaction = fragmentManager.beginTransaction();


                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    transaction.replace(R.id.framLatOut, new searchFragment());
                }

                transaction.addToBackStack(null);

                transaction.commit();
            });

            delete.setVisibility(View.GONE);
        }

        from.setOnClickListener(view -> {
            TimePickerDialog timePickerDialog = null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                timePickerDialog = new TimePickerDialog(requireContext(), (timePicker, hh, mm) -> {
                    String formattedTime = String.format("%02d:%02d", hh, mm);
                    from.setText(formattedTime);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        t.setFrom(String.valueOf(LocalTime.parse(formattedTime)));
                    }
                }, t.getFrom().getHour(), t.getFrom().getMinute(), true);
            }
            timePickerDialog.show();
        });

        to.setOnClickListener(view -> {
            TimePickerDialog timePickerDialog = null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                timePickerDialog = new TimePickerDialog(requireContext(), (timePicker, hh, mm) -> {
                    String formattedTime = String.format("%02d:%02d", hh, mm);
                    to.setText(formattedTime);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        t.setTo(String.valueOf(LocalTime.parse(formattedTime)));
                    }
                }, t.getTo().getHour(), t.getTo().getMinute(), true);
            }
            timePickerDialog.show();
        });

        color.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    t.setColor(color.getSelectedItem().toString());
                }
                GradientDrawable background = (GradientDrawable) color.getBackground();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    background.setColor(t.getColorID(requireContext()));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        return v;
    }
}