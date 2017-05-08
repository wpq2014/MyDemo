package com.demo.wpq.mydemo.todolist.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.demo.wpq.mydemo.R;
import com.demo.wpq.mydemo.todolist.model.Todo;
import com.demo.wpq.mydemo.todolist.model.TodoList;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Desc:
 * Created by wpq on 16/8/5.
 */
public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.ViewHolder> {

    private Context context;
    private TodoList todoList;
    private OnTodoListener onTodoListener;

    public interface OnTodoListener {
        void onItemChecked(Todo todo);

        void onItemDelete(Todo todo);
    }

    public void setOnTodoListener(OnTodoListener onTodoListener) {
        this.onTodoListener = onTodoListener;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        SimpleDraweeView avatar1;
        CheckBox checkBox;
        TextView tvTitle;
        ImageView ivDelete;

        public ViewHolder(View itemView) {
            super(itemView);

            avatar1 = (SimpleDraweeView) itemView.findViewById(R.id.avatar1);
            checkBox = (CheckBox) itemView.findViewById(R.id.checkbox);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            ivDelete = (ImageView) itemView.findViewById(R.id.img_delete);
        }

    }

    public TodoAdapter(Context context, TodoList todoList) {
        this.context = context;
        this.todoList = todoList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.todo_item, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.avatar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "monkey " + position, Toast.LENGTH_SHORT).show();
            }
        });
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onTodoListener.onItemChecked(todoList.getDisplayList().get(position));
            }
        });
        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onTodoListener.onItemDelete(todoList.getDisplayList().get(position));
            }
        });

        Todo todo = todoList.getDisplayList().get(position);
        holder.tvTitle.setText(todo.getTitle());
        holder.checkBox.setChecked(todo.isFinished());
    }

    @Override
    public int getItemCount() {
        return todoList.getDisplayList().size();
    }

}
