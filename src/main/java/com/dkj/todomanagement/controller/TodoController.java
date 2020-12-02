package com.dkj.todomanagement.controller;

import com.dkj.todomanagement.model.Todo;
import com.dkj.todomanagement.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Date;

@Controller
@SessionAttributes("name")
public class TodoController {

    @Autowired
    TodoService todoService;

    @RequestMapping(value="/list-todos", method = RequestMethod.GET)
    public String showTodos(ModelMap model){
        String name = (String) model.get("name");
        model.put("todos", todoService.retrieveTodos(name));
        return "todos-list";
    }

    @RequestMapping(value="/add-todo", method = RequestMethod.GET)
    public String showAddTodos(ModelMap model){
        model.addAttribute("todo", new Todo(0, (String) model.get("name"), "Default Desc",
                new Date(), false));
        return "todo";
    }

    @RequestMapping(value="/add-todo", method = RequestMethod.POST)
    public String addTodos(ModelMap model, Todo todo){
        String name = (String) model.get("name");
        todoService.addTodo(name,todo.getDesc(),new Date(),false);
        return "redirect:/list-todos";
    }

    @RequestMapping(value="/delete-todo", method = RequestMethod.GET)
    public String deleteTodos(ModelMap model, @RequestParam  int id){
        todoService.deleteTodo(id);
        return "redirect:/list-todos";
    }
}
