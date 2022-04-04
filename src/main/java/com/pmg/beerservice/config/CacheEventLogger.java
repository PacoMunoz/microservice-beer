package com.pmg.beerservice.config;

import lombok.extern.slf4j.Slf4j;
import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;

@Slf4j
public class CacheEventLogger implements CacheEventListener <Object, Object> {

    @Override
    public void onEvent(CacheEvent cacheEvent) {
        // log.info("Type: " + cacheEvent.getType() + "Key: " + cacheEvent.getKey() + " Old Value: " + cacheEvent.getOldValue() + " New Value: " + cacheEvent.getNewValue());
        log.info("Event '{}' fire for key '{}' with value '{}'", cacheEvent.getType(), cacheEvent.getKey(), cacheEvent.getNewValue());
    }
}
