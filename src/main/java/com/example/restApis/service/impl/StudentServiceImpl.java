package com.example.restApis.service.impl;

import com.example.restApis.dto.AddStudentRequestDto;
import com.example.restApis.dto.StudentDto;
import com.example.restApis.entity.Student;
import com.example.restApis.repository.StudentRepository;
import com.example.restApis.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<StudentDto> getAllStudents() {
        List<Student> students=studentRepository.findAll();

        return students
                .stream()
                .map(student->modelMapper.map(student,StudentDto.class))
                .toList();
    }

    @Override
    public StudentDto getStudentById(long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found with id " + id));
        return modelMapper.map(student, StudentDto.class);
    }

    @Override
    public StudentDto createNewStudent(AddStudentRequestDto addStudentRequestDto) {
        Student student=modelMapper.map(addStudentRequestDto,Student.class);
        Student newStudent=studentRepository.save(student);

        return modelMapper.map(student,StudentDto.class);
    }

    @Override
    public void deleteStudentById(long id) {
        if(!studentRepository.existsById(id)){
            throw new IllegalArgumentException("Student not exists with id = "+id);
        }
        studentRepository.deleteById(id);
    }

}
