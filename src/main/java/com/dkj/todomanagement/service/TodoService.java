package com.dkj.todomanagement.service;

import com.dkj.todomanagement.model.Todo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class TodoService {

    private static List<Todo> todos = new ArrayList<Todo>();
    private static int todoCount = 3;

    static {
        todos.add(new Todo(1, "dkj", "Learn Spring MVC", new Date(),
                "New"));
        todos.add(new Todo(2, "dkj", "Learn Struts", new Date(), "New"));
        todos.add(new Todo(3, "dkj", "Learn Hibernate", new Date(),
                "New"));
        todos.add(new Todo(3, "bkj", "Learn hindi ", new Date(),
                "New"));
    }

    public List<Todo> retrieveTodos(String user) {
        List<Todo> filteredTodos = new ArrayList<Todo>();
        for (Todo todo : todos) {
            if (todo.getUser().equals(user)) {
                filteredTodos.add(todo);
            }
        }
        return filteredTodos;
    }

    public void addTodo(String name, String desc, Date targetDate,
                        String status) {
        todos.add(new Todo(++todoCount, name, desc, targetDate, status));
    }

    public void deleteTodo(int id) {
        Iterator<Todo> iterator = todos.iterator();
        while (iterator.hasNext()) {
            Todo todo = iterator.next();
            if (todo.getId() == id) {
                iterator.remove();
            }
        }
    }
}
