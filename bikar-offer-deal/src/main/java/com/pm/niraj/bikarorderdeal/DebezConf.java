package com.pm.niraj.bikarorderdeal;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@ComponentScan("com.pm.niraj.customdebezium")
@Profile("!test")
//@ConditionalOnClass(name = {"com.pm.niraj.customdebezium.DebeziumOrder"})
public class DebezConf {
}
