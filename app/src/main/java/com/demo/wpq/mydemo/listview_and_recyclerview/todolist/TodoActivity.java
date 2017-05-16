package com.demo.wpq.mydemo.listview_and_recyclerview.todolist;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.demo.wpq.mydemo.R;
import com.demo.wpq.mydemo.base.BaseAppCompatActivity;
import com.demo.wpq.mydemo.constant.Constants;
import com.demo.wpq.mydemo.listview_and_recyclerview.todolist.adapter.TodoAdapter;
import com.demo.wpq.mydemo.listview_and_recyclerview.todolist.model.Todo;
import com.demo.wpq.mydemo.listview_and_recyclerview.todolist.model.TodoList;
import com.demo.wpq.mydemo.listview_and_recyclerview.util.DividerItemDecoration;

/**
 * Desc:
 * Created by wpq on 16/8/5.
 */
public class TodoActivity extends BaseAppCompatActivity implements View.OnClickListener, TodoAdapter.OnTodoListener {

    private EditText editText;
    private Button btnAdd;
    private RecyclerView recyclerView;
    private CheckBox cbCheckAll;
    private Button btnHideFinished;
    private Button btnRemoveFinished;

    // intent data
    private String title;

    private TodoAdapter adapter;
    private TodoList todoList = new TodoList();

    public static Intent newIntent(Context context, String title) {
        Intent intent = new Intent(context, TodoActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(Constants.TITLE, title);
        intent.putExtras(bundle);
        return intent;
    }

    @Override
    public void getBundleExtras(Bundle bundle) {
        title = bundle.getString(Constants.TITLE);
    }

    @Override
    public int getContentViewLayoutID() {
        return R.layout.activity_todolist;
    }

    @Override
    public String getToolBarTitle() {
        return title;
    }

    @Override
    public void init(@Nullable Bundle savedInstanceState) {
        editText = (EditText) findViewById(R.id.edittext);
        btnAdd = (Button) findViewById(R.id.btn_add);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        cbCheckAll = (CheckBox) findViewById(R.id.cb_checkAll);
        btnHideFinished = (Button) findViewById(R.id.btn_hideFinished);
        btnRemoveFinished = (Button) findViewById(R.id.btn_removeFinished);

        btnAdd.setOnClickListener(this);
        cbCheckAll.setOnClickListener(this);
        btnHideFinished.setOnClickListener(this);
        btnRemoveFinished.setOnClickListener(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL, Color.GREEN, 5));
        recyclerView.setAdapter(adapter = new TodoAdapter(this, todoList));
        adapter.setOnTodoListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add: {
                String title = editText.getText().toString().trim();
                if (TextUtils.isEmpty(title)) {
                    editText.setError("not null");
                    return;
                }
                todoList.add(title);
                todoList.setAllFinished(false);
                cbCheckAll.setChecked(todoList.isAllFinished());
                break;
            }
            case R.id.cb_checkAll: {
                todoList.setAllFinished(!todoList.isAllFinished());
                todoList.updateAll(todoList.isAllFinished());
                cbCheckAll.setChecked(todoList.isAllFinished());
                break;
            }
            case R.id.btn_hideFinished: {
                todoList.setShowFinished(!todoList.isShowFinished());
                btnHideFinished.setText(todoList.isShowFinished()? "hide finished" : "show finished");
                break;
            }
            case R.id.btn_removeFinished: {
                todoList.removeFinished();
                cbCheckAll.setChecked(todoList.isAllFinished());
                break;
            }
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemChecked(Todo todo) {
        todoList.update(todo);
        adapter.notifyDataSetChanged();
        cbCheckAll.setChecked(todoList.isAllFinished());
    }

    @Override
    public void onItemDelete(Todo todo) {
        todoList.remove(todo);
        adapter.notifyDataSetChanged();
        cbCheckAll.setChecked(todoList.isAllFinished());
    }

}
