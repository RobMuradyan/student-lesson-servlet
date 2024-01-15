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
    int id;
    String name;
    String surname;
    String email;
    int age;
   Lesson lesson;
}
