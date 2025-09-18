package com.gyarsilalsolanki011.banking.seeder;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.nio.file.Files;

@Slf4j
@Component
@Profile("dev")
@AllArgsConstructor
public class BankingSchemaLoader implements CommandLineRunner {

    private final JdbcTemplate jdbcTemplate;
    @Override
    public void run(String... args) throws Exception {

        log.info("🏦 Checking and creating schema if not exists...");

        String schemaSql = Files.readString(new ClassPathResource("setup/schema.sql").getFile().toPath());

        for (String statement : schemaSql.split(";")) {
            if (!statement.trim().isEmpty()) {
                jdbcTemplate.execute(statement);
            }
        }

        log.info("✅ Schema created or already exists.");
    }
}
