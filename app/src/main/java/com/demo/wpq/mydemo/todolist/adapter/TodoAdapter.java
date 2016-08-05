package com.demo.wpq.mydemo.todolist.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.demo.wpq.mydemo.R;
import com.demo.wpq.mydemo.todolist.model.Todo;
import com.demo.wpq.mydemo.todolist.model.TodoList;

/**
 * Desc:
 * Created by wpq on 16/8/5.
 */
public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.ViewHolder>{

    public interface OnTodoListener{
        void onItemChecked(Todo todo);
        void onItemDelete(Todo todo);
    }

    private OnTodoListener onTodoListener;

    public void setOnTodoListener(OnTodoListener onTodoListener){
        this.onTodoListener = onTodoListener;
    }

    private Context context;
    private TodoList todoList;

    public TodoAdapter(Context context, TodoList todoList){
        this.context = context;
        this.todoList = todoList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.todo_item, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Todo todo = todoList.getDisplayList().get(position);
        holder.tvTitle.setText(todo.getTitle());
        holder.checkBox.setChecked(todo.isFinished());
    }

    @Override
    public int getItemCount() {
        return todoList.getDisplayList().size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        CheckBox checkBox;
        TextView tvTitle;
        ImageView ivDelete;

        public ViewHolder(View itemView) {
            super(itemView);

            checkBox = (CheckBox) itemView.findViewById(R.id.checkbox);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            ivDelete = (ImageView) itemView.findViewById(R.id.img_delete);

            checkBox.setOnClickListener(this);
            ivDelete.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(null == onTodoListener){
                return;
            }
            switch (v.getId()){
                case R.id.checkbox:{
                    onTodoListener.onItemChecked(todoList.getDisplayList().get(getAdapterPosition()));
                    break;
                }
                case R.id.img_delete:{
                    onTodoListener.onItemDelete(todoList.getDisplayList().get(getAdapterPosition()));
                    break;
                }
            }
        }
    }
}
