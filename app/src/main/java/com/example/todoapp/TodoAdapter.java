package com.example.todoapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoViewHolder> {

    private ArrayList<TodoItem> dataList;

    // ViewHolder class provides reference to the views for each item
    public static class TodoViewHolder extends RecyclerView.ViewHolder {
        // for now view item is just a string
        public View todoView;
        public TodoViewHolder(View view) {
            super(view);
            todoView = view;
        }
    }

    // Provide a suitable constructor (depends on the data the each item will hold)
    public TodoAdapter() {
        dataList = new ArrayList<>();
    }

    // Create new views (invoked by the layout manager)
    @Override
    public TodoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create new view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_view, parent, false);

        TodoViewHolder vh = new TodoViewHolder(view);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(TodoViewHolder holder, int position) {
        // - get element from the dataset at this position
        // - replace the contents of the view with that element
        ((TextView)holder.todoView.findViewById(R.id.todoItemTitle)).setText(dataList.get(position).getName());
        ((TextView)holder.todoView.findViewById(R.id.todoDueDateText)).setText("Due: " + dataList.get(position).getDueDate());
        ((TextView)holder.todoView.findViewById(R.id.todoDueTimeText)).setText(dataList.get(position).getDueTime());
    }

    // Return the size of the dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public ArrayList<TodoItem> getDataList() {
        return dataList;
    }

    public void upDateDataList(TodoItem newData) {
        dataList.add(newData);
    }

}
