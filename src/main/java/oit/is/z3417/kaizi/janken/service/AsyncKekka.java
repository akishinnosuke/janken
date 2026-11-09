package oit.is.z3417.kaizi.janken.service;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import oit.is.z3417.kaizi.janken.model.Match;
import oit.is.z3417.kaizi.janken.model.MatchMapper;

@Service
public class AsyncKekka {
    private boolean dbUpdated = false;
    private final Logger logger = LoggerFactory.getLogger(AsyncKekka.class);

    @Autowired
    private MatchMapper matchMapper;

    @Async
    public void syncKekka(SseEmitter emitter) {
        dbUpdated = true;
        try {
            while (true) {
                // DBを監視して試合結果（isActive=true）があれば取得する
                Match match = matchMapper.selectActiveMatch();
                if (match != null) {
                    // クライアントに非同期で送信
                    emitter.send(SseEmitter.event().name("result").data(match));
                    
                    // 送信後、同じ結果を何度も処理しないように dbUpdated を false に変更後、isActiveを更新
                    dbUpdated = false;
                    match.setIsActive(false);
                    matchMapper.updateIsActive(match);
                }
                TimeUnit.SECONDS.sleep(1);
            }
        } catch (Exception e) {
            logger.warn("Exception: " + e.getClass().getName() + ":" + e.getMessage());
            emitter.completeWithError(e);
        } finally {
            emitter.complete();
        }
    }
}
