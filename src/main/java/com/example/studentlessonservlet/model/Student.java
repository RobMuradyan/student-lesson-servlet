package com.example.studentlessonservlet.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
   private int id;
   private String name;
   private String surname;
   private String email;
   private int age;
  private Lesson lesson;
  private  String picName;
}
