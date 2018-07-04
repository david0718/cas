package org.apereo.cas.support.pac4j.config;

import lombok.val;

import com.github.scribejava.core.model.OAuth1RequestToken;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

/**
 * This is Pac4jJacksonTests.
 *
 * @author sbearcsiro
 * @since 5.3.0
 */
public class Pac4jJacksonTests {

    @Test
    public void serialiseDeserialiseOAuth1RequestToken() {

        val sessionStoreCookieSerializer = new Pac4jDelegatedAuthenticationConfiguration().pac4jDelegatedSessionStoreCookieSerializer();

        val key = "requestToken";
        val requestToken = new OAuth1RequestToken("token", "secret", true, "token=token&secret=secret");

        final Map<String, Object> session = new HashMap<>();
        session.put(key, requestToken);
        val serialisedSession = sessionStoreCookieSerializer.toString(session);

        assertThat(session).isNotEmpty();

        val deserialisedSession = sessionStoreCookieSerializer.from(serialisedSession);

        assertThat(deserialisedSession).isEqualTo(session);
    }
}
