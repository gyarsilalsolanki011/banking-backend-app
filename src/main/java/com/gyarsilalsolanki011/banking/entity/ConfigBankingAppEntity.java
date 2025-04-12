package com.gyarsilalsolanki011.banking.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "config_banking_app")
@Data
@Entity
@NoArgsConstructor
public class ConfigBankingAppEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Column(name = "config_key", nullable = false, unique = true)
    private String configKey;

    @Getter
    @Column(name = "config_value", nullable = false)
    private String configValue;
}
