package com.vti.blogapp.configuration;

import org.apache.catalina.authenticator.SpnegoAuthenticator;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.Arrays;
import java.util.Locale;

public class i18nConfiguration {
    @Bean
    public LocaleResolver localeResolver() {
        var resolver = new AcceptHeaderLocaleResolver();
        var locale = Arrays.asList(Locale.ENGLISH, new Locale("vi"));
        resolver.setSupportedLocales(locale);
        resolver.setDefaultLocale(Locale.ENGLISH);
        return resolver;
    }
}
