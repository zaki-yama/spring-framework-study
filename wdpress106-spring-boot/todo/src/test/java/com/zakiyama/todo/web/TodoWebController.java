package com.zakiyama.todo.web;

import java.time.LocalDate;
import java.util.List;

import com.zakiyama.todo.domain.Task;
import com.zakiyama.todo.form.TaskForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TodoWebController {

  private static final String TASKS = "tasks";

  private static final String REDIRECT_TO = "redirect:/" + TASKS;

  @Autowired
  TodoService todoService;

  // タスクを全件取得
  @GetMapping(value = "/tasks")
  public ModelAndView readAllTasks() {
    TaskForm form = createInitialForm();

    ModelAndView modelAndView = toTasksPage();
    modelAndView.addObject("form", form);
    List<Task> tasks = todoService.findAllTasks();  
    modelAndView.addObject(TASKS, tasks);
    return modelAndView;
  }

  private ModelAndView toTasksPage() {
    return new ModelAndView(TASKS);
  }

  private TaskForm createInitialForm() {
    String formSubject = "";
    LocalDate formDeadLine = LocalDate.now();
    Boolean isNewTask = true;
    Boolean hasDone = false;

    return new TaskForm(
      formSubject,
      formDeadLine,
      hasDone,
      isNewTask);
  }
}