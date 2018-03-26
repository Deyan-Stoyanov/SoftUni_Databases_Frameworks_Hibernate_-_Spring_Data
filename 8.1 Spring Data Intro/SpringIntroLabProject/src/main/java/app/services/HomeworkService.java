package app.services;

import app.model.Homework;

import java.util.List;

public interface HomeworkService {
    Homework findById(Long id);
    List<Homework> findAll();
    void save(Homework homework);
    void remove(Homework homework);
}
