package com.crud.tasks.service;

import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.domain.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class DbService {
    @Autowired
    private TaskRepository repository;

    public List<Task> getAllTasks(){
        return repository.findAll();
    }

    public Task findById(Long id) {
        Task task;
        try {
            task = repository.findById(id).get();
        } catch (NoSuchElementException e) {
            System.out.println("enity ID " + id + " not found");
            return new Task(0L, "no Task found", "no Task found");
        }
        return task;
    }

    public Task saveTask(final Task task) {
        return repository.save(task);
    }


}
