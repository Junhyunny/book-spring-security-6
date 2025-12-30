package com.example.demo.controller;

import com.example.demo.domain.TodoCommand;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@Controller
public class MainController {

    private final Queue<String> todos = new ConcurrentLinkedQueue<>();

    @GetMapping
    public ModelAndView index() {
        var mav = new ModelAndView("index");
        mav.addObject("todos", todos);
        return mav;
    }

    @GetMapping("/todos")
    public ModelAndView getTodos() {
        var mav = new ModelAndView("index::#todoList");
        mav.addObject("todos", todos);
        return mav;
    }

    @ResponseBody
    @PostMapping("/todos")
    public void createTodo(
            @RequestBody TodoCommand command
    ) {
        todos.add(command.value());
    }
}
