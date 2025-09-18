package com.gyarsilalsolanki011.banking.seeder;

import com.gyarsilalsolanki011.banking.util.PasswordUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Slf4j
@Component
@Profile("dev")
@AllArgsConstructor
public class BankingDataLoader implements CommandLineRunner {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... args) throws Exception {
        Integer count = null;
        try {
            count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM users", Integer.class);
        } catch (Exception e) {
            log.warn("⚠️ Users table not found, skipping data load.");
        }

        if (count > 0) {
            log.info("✅ Users table already contains data ({} rows), skipping sample data load.", count);
            return;
        }

        // Load SQL file from resources
        var resource = new ClassPathResource("setup/sample-data.sql");
        String sql = new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);

        // Replace passwords with encoded ones using PasswordUtil
        sql = sql.replace("'Tiger@123'", "'" + PasswordUtil.encode("Tiger@123") + "'")
                .replace("'Kamlesh@123'", "'" + PasswordUtil.encode("Kamlesh@123") + "'")
                .replace("'Vasu@123'", "'" + PasswordUtil.encode("Vasu@123") + "'")
                .replace("'Tiger@123'", "'" + PasswordUtil.encode("Tiger@123") + "'")
                .replace("'Nitin@123'", "'" + PasswordUtil.encode("Nitin@123") + "'")
                .replace("'Akshay@123'", "'" + PasswordUtil.encode("Akshay@123") + "'")
                .replace("'New@123'", "'" + PasswordUtil.encode("New@123") + "'");


        for (String statement : sql.split(";")) {
            if (!statement.trim().isEmpty()) {
                jdbcTemplate.execute(statement.trim());
            }
        }

        log.info("✅ Sample banking data loaded successfully from setup/banking_data.sql");
    }
}
