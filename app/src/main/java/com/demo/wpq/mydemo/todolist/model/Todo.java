package com.demo.wpq.mydemo.todolist.model;

/**
 * Desc:
 * Created by wpq on 16/8/5.
 */
public class Todo {

    private String title;
    private boolean finished;

    public Todo(String title){
        this(title, false);
    }

    public Todo(String title, boolean finished) {
        this.title = title;
        this.finished = finished;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "title='" + title + '\'' +
                ", finished=" + finished +
                '}';
    }
}
