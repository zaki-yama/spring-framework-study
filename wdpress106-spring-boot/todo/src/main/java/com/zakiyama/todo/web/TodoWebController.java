package com.zakiyama.todo.web;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.zakiyama.todo.domain.Task;
import com.zakiyama.todo.form.TaskForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

  // タスクを1件作成
  @PostMapping(value = "/tasks")
  public ModelAndView createOneTask(
      @ModelAttribute TaskForm form) {
    createTaskFromForm(form);
    return new ModelAndView(REDIRECT_TO);
  }

  private void createTaskFromForm(TaskForm form) {
    String subject = form.getSubject();
    LocalDate deadLine = form.getDeadLine();
    Boolean hasDone = form.getHasDone();
    Task task = new Task(subject, deadLine, hasDone);
    todoService.createTask(task);
  }

  // タスクを1件取得
  @GetMapping(value = "tasks/{id}")
  public ModelAndView readOneTask(
      // アノテーションをつけ、URL 中の変数名と揃えることで
      // URL のパラメータから id の値を取得できる
      @PathVariable Integer id) {
    Optional<TaskForm> form = readTaskFromId(id);
    if (!form.isPresent()) {
      return new ModelAndView(REDIRECT_TO);
    }
    ModelAndView modelAndView = toTasksPage();
    modelAndView.addObject("taskId", id);
    modelAndView.addObject("form", form.get());
    List<Task> tasks = todoService.findAllTasks();
    modelAndView.addObject(TASKS, tasks);
    return modelAndView;
  }

  private Optional<TaskForm> readTaskFromId(Integer id) {
    Optional<Task> task = todoService.findOneTask(id);
    if (!task.isPresent()) {
      return Optional.ofNullable(null);
    }

    String formSubject = task.get().getSubject();
    LocalDate formDeadLine = task.get().getDeadLine();
    Boolean hasDone = task.get().getHasDone();
    Boolean isNewTask = false;
    TaskForm form = new TaskForm(
      formSubject,
      formDeadLine,
      hasDone,
      isNewTask);
    return Optional.ofNullable(form);
  }
}