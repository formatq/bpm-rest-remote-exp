package com.aplana.bpm.common;

import org.kie.api.task.TaskService;
import org.kie.api.task.model.*;

import java.util.List;
import java.util.Map;

/**
 * Класс облочка которая содержит только поддерживаемые RemoteRest методы TaskService
 *
 * @author aivanov
 */
public class TaskServiceCut {

    private TaskService taskService;

    public TaskServiceCut(TaskService taskService) {
        this.taskService = taskService;
    }

    public void activate(long taskId, String userId) {
        taskService.activate(taskId, userId);
    }

    public void claim(long taskId, String userId) {
        taskService.claim(taskId, userId);
    }

    public void claimNextAvailable(String userId, String language) {
        taskService.claimNextAvailable(userId, language);
    }

    public void complete(long taskId, String userId, Map<String, Object> data) {
        taskService.complete(taskId, userId, data);
    }

    public void delegate(long taskId, String userId, String targetUserId) {
        taskService.delegate(taskId, userId, targetUserId);
    }

    public void exit(long taskId, String userId) {
        taskService.exit(taskId, userId);
    }

    public void fail(long taskId, String userId, Map<String, Object> faultData) {
        taskService.fail(taskId, userId, faultData);
    }

    public void forward(long taskId, String userId, String targetEntityId) {
        taskService.forward(taskId, userId, targetEntityId);
    }

    public Task getTaskByWorkItemId(long workItemId) {
        return taskService.getTaskByWorkItemId(workItemId);
    }

    public Task getTaskById(long taskId) {
        return taskService.getTaskById(taskId);
    }

    public List<TaskSummary> getTasksAssignedAsBusinessAdministrator(String userId, String language) {
        return taskService.getTasksAssignedAsBusinessAdministrator(userId, language);
    }

    public List<TaskSummary> getTasksAssignedAsPotentialOwner(String userId, String language) {
        return taskService.getTasksAssignedAsPotentialOwner(userId, language);
    }

    public List<TaskSummary> getTasksAssignedAsPotentialOwnerByStatus(String userId, List<Status> status, String language) {
        return taskService.getTasksAssignedAsPotentialOwnerByStatus(userId, status, language);
    }

    public List<TaskSummary> getTasksOwned(String userId, String language) {
        return taskService.getTasksOwned(userId, language);
    }

    public List<TaskSummary> getTasksOwnedByStatus(String userId, List<Status> status, String language) {
        return taskService.getTasksOwnedByStatus(userId, status, language);
    }

    public List<TaskSummary> getTasksByStatusByProcessInstanceId(long processInstanceId, List<Status> status, String language) {
        return taskService.getTasksByStatusByProcessInstanceId(processInstanceId, status, language);
    }

    public List<Long> getTasksByProcessInstanceId(long processInstanceId) {
        return taskService.getTasksByProcessInstanceId(processInstanceId);
    }

    public long addTask(Task task, Map<String, Object> params) {
        return taskService.addTask(task, params);
    }

    public void release(long taskId, String userId) {
        taskService.release(taskId, userId);
    }

    public void resume(long taskId, String userId) {
        taskService.resume(taskId, userId);
    }

    public void skip(long taskId, String userId) {
        taskService.skip(taskId, userId);
    }

    public void start(long taskId, String userId) {
        taskService.start(taskId, userId);
    }

    public void stop(long taskId, String userId) {
        taskService.stop(taskId, userId);
    }

    public void suspend(long taskId, String userId) {
        taskService.suspend(taskId, userId);
    }

    public void nominate(long taskId, String userId, List<OrganizationalEntity> potentialOwners) {
        taskService.nominate(taskId, userId, potentialOwners);
    }

    public Content getContentById(long contentId) {
        return taskService.getContentById(contentId);
    }

    public Attachment getAttachmentById(long attachId) {
        return taskService.getAttachmentById(attachId);
    }

    public Map<String, Object> getTaskContent(long taskId) {
        return taskService.getTaskContent(taskId);
    }

    public List<TaskSummary> getTasksByVariousFields(String userId, List<Long> workItemIds, List<Long> taskIds, List<Long> procInstIds, List<String> busAdmins, List<String> potOwners, List<String> taskOwners, List<Status> status, List<String> languages, boolean union) {
        return taskService.getTasksByVariousFields(userId, workItemIds, taskIds, procInstIds, busAdmins, potOwners, taskOwners, status, languages, union);
    }

    public List<TaskSummary> getTasksAssignedAsPotentialOwner(String userId, List<String> groupIds, String language, int firstResult, int maxResults) {
        return taskService.getTasksAssignedAsPotentialOwner(userId, groupIds, language, firstResult, maxResults);
    }
}
