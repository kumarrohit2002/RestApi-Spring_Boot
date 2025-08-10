package com.example.restApis.service;

import com.example.restApis.dto.AddStudentRequestDto;
import com.example.restApis.dto.StudentDto;

import java.util.List;

public interface StudentService {
    List<StudentDto> getAllStudents();

    StudentDto getStudentById(long id);

    StudentDto createNewStudent(AddStudentRequestDto addStudentRequestDto);

    void deleteStudentById(long id);
}
