package com.aplana.bpm.testapi;

import com.aplana.bpm.common.KieSessionCut;
import com.aplana.bpm.common.RemoteRestClient;
import com.aplana.bpm.common.TaskServiceCut;
import org.kie.api.runtime.manager.audit.AuditService;
import org.kie.api.runtime.manager.audit.ProcessInstanceLog;
import org.kie.api.runtime.process.ProcessInstance;
import org.kie.api.task.model.Task;
import org.kie.remote.jaxb.gen.GetProcessInstancesCommand;


import java.util.Collection;
import java.util.List;

/**
 * Created by aivanov on 05.08.2015.
 */
public class Main {

    public static void main(String[] args) {
        test("superuser", "XSW@zaq1", "http://localhost:8080/business-central", "com.aplana.bpm.timesheet:vacations:1.1");
    }

    public static void test(String userId, String password, String url, String deploymentId) {
        RemoteRestClient remoteRestClient = new RemoteRestClient(userId, password, url, deploymentId);

//        TaskService taskService = runtimeEngine.getTaskService();
//        List<TaskSummary> tasks = taskService.getTasksOwned("user1", "en-UK");
//        if (tasks.size() == 0) {
//            System.out.printf("No tasks for user \"%s\" as owner...\n", "user1");
//        } else {
//            System.out.printf("Tasks where user \"%s\" is a an owner...\n", "user1");
//            for (TaskSummary t : tasks) {
//                System.out.printf("ID: %d\n", t.getId());
//                System.out.printf("Name: %s\n", t.getName());
//                System.out.printf("Actual Owner: %s\n", t.getActualOwner());
//                System.out.printf("Created by: %s\n", t.getCreatedBy());
//                System.out.printf("Created on: %s\n", t.getCreatedOn());
//                System.out.printf("Status: %s\n", t.getStatus());
//                System.out.printf("Description: %s\n", t.getDescription());
//                System.out.println("---------------");
//            }
//        }
//    }

        KieSessionCut ksession = remoteRestClient.getKieSession();
        TaskServiceCut taskService = remoteRestClient.getTaskService();
        AuditService auditService = remoteRestClient.getAuditService();

        System.out.println("ksession " + ksession.execute(new GetProcessInstancesCommand()));

        System.out.println("ksession.getFactCount() " + ksession.getFactCount());

        Collection<ProcessInstance> processInstances = ksession.getProcessInstances();


        System.out.println("Get started processes: " + processInstances.size());
        //for (ProcessInstance processInstance : processInstances) {
        //System.out.println("ProcessInstance " + processInstance.getProcessName());

        List<Long> tasksByProcessInstanceId = taskService.getTasksByProcessInstanceId(50);
        System.out.println("Get started tasks: " + tasksByProcessInstanceId.size());
        for (Long aLong : tasksByProcessInstanceId) {
            Task taskById = taskService.getTaskById(aLong);
            System.out.println("Task " + taskById + ". Task Owner " + taskById.getPeopleAssignments().getPotentialOwners());
        }

        //}


        List<? extends ProcessInstanceLog> processInstances1 = auditService.findProcessInstances();
        for (ProcessInstanceLog processInstanceLog : processInstances1) {
            System.out.println(processInstanceLog.getProcessName() + "(" + processInstanceLog.getParentProcessInstanceId() + ")      " + processInstanceLog.getStart() + "   " + processInstanceLog.getEnd());
        }
    }

}
