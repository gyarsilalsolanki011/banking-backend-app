package com.gyarsilalsolanki011.banking.cache;

import com.gyarsilalsolanki011.banking.models.entity.ConfigBankingAppEntity;
import com.gyarsilalsolanki011.banking.repository.ConfigBankingAppRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class AppCache {
    public enum keys{
        SMS_API_KEY,
        JWT_SECRET_KEY,
        JWT_EXPIRATION_TIME
    }

    @Autowired
    private ConfigBankingAppRepository configBankingAppRepository;

    public Map<String, String> appCache;

    @PostConstruct
    public void init() {
        appCache = new HashMap<>();
        List<ConfigBankingAppEntity> all = configBankingAppRepository.findAll();
        for (ConfigBankingAppEntity configBankingAppEntity : all) {
            appCache.put(configBankingAppEntity.getConfigKey(), configBankingAppEntity.getConfigValue());
        }
    }

    public void save() {
        ConfigBankingAppEntity entity = new ConfigBankingAppEntity();
        entity.setConfigKey(keys.JWT_EXPIRATION_TIME.toString());
        entity.setConfigValue("86400000");
        configBankingAppRepository.save(entity);
    }
}
