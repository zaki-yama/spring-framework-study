package com.zakiyama.todo.form;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskForm {

  String subject;

  @DateTimeFormat(pattern="yyyy-MM-dd")
  LocalDate deadLine;

  Boolean hasDone;

  Boolean isNewTask;
}