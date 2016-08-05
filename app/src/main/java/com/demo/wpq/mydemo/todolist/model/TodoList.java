package com.demo.wpq.mydemo.todolist.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Desc:
 * Created by wpq on 16/8/5.
 */
public class TodoList {

    private List<Todo> list = new ArrayList<>();
    private boolean allFinished = false;
    private boolean showFinished = true;

    public List<Todo> getDisplayList() {
        if (showFinished) {
            return Collections.unmodifiableList(list);
        } else {
            List<Todo> listFinished = new ArrayList<>();
            for (Todo todo : list) {
                if (!todo.isFinished()) {
                    listFinished.add(todo);
                }
            }
            return listFinished;
        }
    }

    public void add(String title) {
        list.add(new Todo(title));
    }

    public void update(Todo todo) {
        todo.setFinished(!todo.isFinished());
        updateAllFinishedState();
    }

    public boolean isAllFinished() {
        return allFinished;
    }

    public void setAllFinished(boolean allFinished) {
        this.allFinished = allFinished;
    }

    public void updateAll(boolean allFinished) {
        for (Todo todo : list) {
            todo.setFinished(allFinished);
        }
    }

    /**
     * 每次选中一个或者删除一个都要判断剩下的是不是都处于选中状态，
     * 如果是则需要更新全选中标记
     */
    private void updateAllFinishedState() {
        boolean hasUnFinished = false;
        for (Todo t : list) {
            if (!t.isFinished()) {
                hasUnFinished = true;
                break;
            }
        }
        setAllFinished(!hasUnFinished);
    }

    public void remove(Todo todo) {
        list.remove(todo);
        updateAllFinishedState();
    }

    public void removeFinished() {
        List<Todo> listUnFinished = new ArrayList<>();
        for (Todo todo : list) {
            if (!todo.isFinished()) {
                listUnFinished.add(todo);
            }
        }
        this.list = listUnFinished;
    }

    public boolean isShowFinished() {
        return showFinished;
    }

    public void setShowFinished(boolean showFinished) {
        this.showFinished = showFinished;
    }

    @Override
    public String toString() {
        return list.toString();
    }
}
