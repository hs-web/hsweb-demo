package org.hsweb.demo.service;

import org.hsweb.web.bean.po.logger.LoggerInfo;
import org.hsweb.web.bean.po.user.User;
import org.hsweb.web.core.logger.AccessLoggerPersisting;
import org.hsweb.web.socket.cmd.CMD;
import org.hsweb.web.socket.cmd.support.AbstractCmdProcessor;
import org.hsweb.web.socket.message.WebSocketMessage;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author zhouhao
 */
@Component
public class PVMonitor extends AbstractCmdProcessor implements AccessLoggerPersisting {
    private AtomicLong  counter  = new AtomicLong(0);
    private Set<String> userList = new HashSet<>();
    @Override
    public String getName() {
        return "pv";
    }

    @Override
    public void exec(CMD cmd) throws Exception {
        User user = getUser(cmd.getSession());
        webSocketMessageManager.subscribe(getName(), user.getId(), cmd.getSession());
        userList.add(user.getId());
    }

    @Override
    @PostConstruct
    public void init() throws Exception {
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                    for (String userId : userList) {
                        WebSocketMessage message = new WebSocketMessage();
                        message.setCallBack("pvGet");
                        message.setContent(counter.get());
                        message.setType("pv");
                        message.setTo(userId);
                        message.setFrom("system");
                        webSocketMessageManager.publish(message);
                    }
                } catch (Exception e) {
                } finally {
                    counter.getAndSet(0);
                }
            }
        }).start();
    }

    @Override
    public void onSessionConnect(WebSocketSession session) throws Exception {

    }

    @Override
    public void onSessionClose(WebSocketSession session) throws Exception {
        User user = getUser(session);
        userList.remove(user.getId());
        webSocketMessageManager.deSubscribe(getName(), user.getId(), session);
    }

    @Override
    public void save(LoggerInfo loggerInfo) {
        counter.addAndGet(1);
    }
}
