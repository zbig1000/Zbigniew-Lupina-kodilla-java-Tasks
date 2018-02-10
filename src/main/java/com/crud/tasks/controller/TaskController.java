package com.crud.tasks.controller;

import com.crud.tasks.domain.TaskDto;

import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/task")
public class TaskController {
    @Autowired
    private DbService service;
    @Autowired
    private TaskMapper taskMapper;

    @RequestMapping(method = RequestMethod.GET, value = "/getTasks")
    public List<TaskDto> getTasks() {
        return taskMapper.mapToTaskDtoList(service.getAllTasks());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getTask/{taskId}")
    public TaskDto getTask(@PathVariable("taskId") Long taskId) {
        return new TaskDto(taskId, "test title", "content");
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteTask/{taskId}")
    public void deleteTask(@PathVariable("taskId") Long taskId) {

    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateTask")
    public TaskDto updateTask(@RequestBody TaskDto taskDto) {
        return new TaskDto(taskDto.getId(), taskDto.getTitle(), taskDto.getContent());
    }

    @RequestMapping(method = RequestMethod.POST, value = "createTask")
    public void createTask(@RequestBody TaskDto taskDto) {

    }

    @RequestMapping(
            method = RequestMethod.POST,
            value = "createTaskDirectly",
            params = {"taskId"}
    )
    public TaskDto createTaskDirectly(@RequestParam() Long taskId) {
        return new TaskDto(taskId, "title", "content ok");
    }
}
