package com.crud.tasks.controller;

import com.crud.tasks.domain.TaskDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/task")
public class TaskController {

    @RequestMapping(method = RequestMethod.GET, value = "/getTasks")
    public List<TaskDto> getTasks() {
        return new ArrayList<>();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getTask/{taskId}")
    public TaskDto getTask(@PathVariable("taskId") String taskId) {
        Long id = 0L;
        try {
            id = new Long(taskId);
        } catch (Exception nfe) {
            //noting
        }
        return new TaskDto(id, "test title", "content");
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteTask/{taskId}")
    public void deleteTask(@PathVariable("taskId") String taskId) {
        Long id = 0L;
        try {
            id = new Long(taskId);
        } catch (NumberFormatException nfe) {
            // do nothing
        }

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
    public TaskDto createTaskDirectly(@RequestParam() String taskId)
{
        Long newID = new Long(taskId);
        return new TaskDto(newID, "title", "content ok");

    }


}
