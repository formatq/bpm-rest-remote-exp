package com.aplana.bpm.common;

import org.kie.api.runtime.manager.audit.AuditService;
import org.kie.remote.client.api.RemoteRestRuntimeEngineFactory;
import org.kie.services.client.api.command.RemoteConfiguration;
import org.kie.services.client.api.command.RemoteRuntimeEngine;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Оберка для RemoteRuntimeEngine и основных интерефесов.
 * Скрывает методы которые не поддерживаются в Rest реализиции общий интерфейсов KieSession и т.д.
 *
 * @author aivanov
 */
public class RemoteRestClient {

    private KieSessionCut kieSessionCut;
    private TaskServiceCut taskServiceCut;

    private RemoteRuntimeEngine engine;

    private RemoteConfiguration remoteConfiguration;

    public RemoteRestClient(RemoteConfiguration conf) {
        this.remoteConfiguration = conf;

        if (remoteConfiguration.getServerBaseUrl() == null) {
            throw new IllegalArgumentException("RemoteConfiguration must have ServerBaseRestUrl argument");
        }

        engine = getEngine(remoteConfiguration);
    }

    public RemoteRestClient(String userId, String password, String url, String deploymentId) {
        RemoteConfiguration remoteConfiguration = null;
        try {
            URL _url = new URL(url);
            remoteConfiguration = new RemoteConfiguration(deploymentId, _url, userId, password);
            // Устанавливем так потому что в в контсрукторе урл не проставляется (бага редхата)
            remoteConfiguration.setServerBaseRestUrl(_url);
        } catch (MalformedURLException e) {
            throw new IllegalStateException("This URL is always expected to be valid!", e);
        }
        this.remoteConfiguration = remoteConfiguration;

        engine = getEngine(remoteConfiguration);
    }

    public KieSessionCut getKieSession() {
        if (kieSessionCut == null) {
            kieSessionCut = new KieSessionCut(engine.getKieSession());
        }
        return kieSessionCut;
    }

    public TaskServiceCut getTaskService() {
        if (taskServiceCut == null) {
            taskServiceCut = new TaskServiceCut(engine.getTaskService());
        }
        return taskServiceCut;
    }

    public AuditService getAuditService() {
        return engine.getAuditService();
    }

    private RemoteRuntimeEngine getEngine(RemoteConfiguration conf) {
        RemoteRestRuntimeEngineFactory remoteRestRuntimeEngineFactory = new RemoteRestRuntimeEngineFactory(conf);
        return remoteRestRuntimeEngineFactory.newRuntimeEngine();
    }


}
