package dev.paie.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("dev.paie.service, dev.paie.util, dev.paie.config")
public class ServicesConfig {

}
