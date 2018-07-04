package org.apereo.cas.web.view;

import lombok.val;

import org.apereo.cas.configuration.CasConfigurationProperties;
import org.apereo.cas.util.MockWebServer;
import org.junit.Test;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.thymeleaf.IEngineConfiguration;

import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * This is {@link RestfulUrlTemplateResolverTests}.
 *
 * @author Misagh Moayyed
 * @since 5.3.0
 */
public class RestfulUrlTemplateResolverTests {
    @Test
    public void verifyAction() {
        try (var webServer = new MockWebServer(9294,
            new ByteArrayResource("template".getBytes(StandardCharsets.UTF_8), "REST Output"), MediaType.APPLICATION_JSON_VALUE)) {
            webServer.start();

            val props = new CasConfigurationProperties();
            props.getView().getRest().setUrl("http://localhost:9294");
            val r = new RestfulUrlTemplateResolver(props);
            val res = r.resolveTemplate(mock(IEngineConfiguration.class), "cas",
                "template", new LinkedHashMap<>());
            assertNotNull(res);
        } catch (final Exception e) {
            throw new AssertionError(e.getMessage(), e);
        }

    }
}
