package io.api.base.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Properties specific to Baseapi.
 * <p>
 * Properties are configured in the {@code application-quartz.properties} file.
 * See {@link tech.jhipster.config.JHipsterProperties} for a good example.
 */
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {
}
