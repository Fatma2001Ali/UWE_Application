package com.example.universityapp;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toolbar;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;

@RequiresApi(api = Build.VERSION_CODES.O)

public class searchFragment extends Fragment {

    private LocalDate showedDate;
    private ArrayList<Task> tasks;
    private final DateTimeFormatter mainDate = DateTimeFormatter.ofPattern("EEEE dd/MM");
    private Database database;
    private TextView date;
    private ListAdapter listAdapter;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_search, container, false);
        date = v.findViewById(R.id.date);
        ImageView left = v.findViewById(R.id.left);
        ImageView right = v.findViewById(R.id.right);
        ListView listview = v.findViewById(R.id.listview);
        LinearLayout add =v.findViewById(R.id.add);

        tasks = new ArrayList<>();
        listAdapter = new ListAdapter();
        listview.setAdapter(listAdapter);
        database = new Database(getContext());

        showedDate = LocalDate.now();
        RefreshData();

        date.setOnClickListener(r-> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), (datePicker, year, month, day) -> {
                showedDate = LocalDate.of(year, month+1, day);
                RefreshData();
            }, showedDate.getYear(), showedDate.getMonthValue()-1, showedDate.getDayOfMonth());
            datePickerDialog.show();
        });

        left.setOnClickListener(r-> {
            showedDate = showedDate.minusDays(1);
            RefreshData();
        });

        right.setOnClickListener(r-> {
            showedDate = showedDate.plusDays(1);
            RefreshData();
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();

                TaskEditor taskEditorFragment = new TaskEditor();
                Bundle args = new Bundle();
                args.putString("Date", showedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                taskEditorFragment.setArguments(args);

                transaction.replace(R.id.framLatOut, taskEditorFragment);
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });
//        add.setOnClickListener(r-> {
//            Intent intent = new Intent(getContext(), TaskEditor.class);
//            intent.putExtra("Date", showedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
//            startActivity(intent);
//        });
        return  v;
    }

    @Override
    public void onResume() {
        super.onResume();
        RefreshData();
    }

    private void RefreshData() {
        date.setText(showedDate.format(mainDate));
        ArrayList<Task> ts = database.getAllTasks(showedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        Collections.sort(ts);
        tasks = ts;
        listAdapter.notifyDataSetChanged();
    }

    public class ListAdapter extends BaseAdapter {

        public ListAdapter() {

        }

        @Override
        public int getCount() {
            return tasks.size();
        }

        @Override
        public Task getItem(int i) {
            return tasks.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            LayoutInflater inflater = getLayoutInflater();
            @SuppressLint({"InflateParams", "ViewHolder"})
            View v = inflater.inflate(R.layout.fragment_task, null);

            TextView from = v.findViewById(R.id.from);
            TextView to = v.findViewById(R.id.to);
            TextView task = v.findViewById(R.id.task);

            Task t = tasks.get(i);

            from.setText(t.getFromToString());
            to.setText(t.getToToString());
            task.setText(t.getTask());

            GradientDrawable backDrawable = (GradientDrawable) task.getBackground();
            backDrawable.setColor(t.getColorID(getContext()));

            task.setOnLongClickListener(v2-> {
                Intent intent = new Intent(getContext(), TaskEditor.class);
                intent.putExtra("ID", t.getID());
                intent.putExtra("Task", t.getTask());
                intent.putExtra("From", t.getFromToString());
                intent.putExtra("To", t.getToToString());
                intent.putExtra("Color", t.getColor());
                intent.putExtra("Date", showedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                startActivity(intent);
                return true;
            });

            return v;
        }
    }



}