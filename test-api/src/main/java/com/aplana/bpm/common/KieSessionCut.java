package com.aplana.bpm.common;

import org.kie.api.command.Command;
import org.kie.api.runtime.*;
import org.kie.api.runtime.process.ProcessInstance;
import org.kie.api.runtime.process.WorkItemManager;

import java.util.Collection;
import java.util.Map;

/**
 * Облочка которая содержит только поддерживаемые RemoteRest методы KieSession
 * @author aivanov
 */
public class KieSessionCut {

    private KieSession kieSession;

    public KieSessionCut(KieSession kieSession) {
        this.kieSession = kieSession;
    }


    public int fireAllRules() {
        return kieSession.fireAllRules();
    }

    public int fireAllRules(int max) {
        return kieSession.fireAllRules(max);
    }

    public <T> T execute(Command<T> command) {
        return kieSession.execute(command);
    }

    public void setGlobal(String identifier, Object value) {
        kieSession.setGlobal(identifier, value);
    }

    public Object getGlobal(String identifier) {
        return kieSession.getGlobal(identifier);
    }

    public long getFactCount() {
        return kieSession.getFactCount();
    }


    public ProcessInstance startProcess(String processId) {
        return kieSession.startProcess(processId);
    }


    public ProcessInstance startProcess(String processId, Map<String, Object> parameters) {
        return kieSession.startProcess(processId, parameters);
    }


    public void signalEvent(String type, Object event) {
        kieSession.signalEvent(type, event);
    }


    public void signalEvent(String type, Object event, long processInstanceId) {
        kieSession.signalEvent(type, event, processInstanceId);
    }


    public Collection<ProcessInstance> getProcessInstances() {
        return kieSession.getProcessInstances();
    }


    public ProcessInstance getProcessInstance(long processInstanceId) {
        return kieSession.getProcessInstance(processInstanceId);
    }


    public ProcessInstance getProcessInstance(long processInstanceId, boolean readonly) {
        return kieSession.getProcessInstance(processInstanceId, readonly);
    }

    public void abortProcessInstance(long processInstanceId) {
        kieSession.abortProcessInstance(processInstanceId);
    }

    public WorkItemManager getWorkItemManager() {
        return kieSession.getWorkItemManager();
    }

    public void dispose() {
        kieSession.dispose();
    }

}
