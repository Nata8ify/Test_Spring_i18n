package com.test.config;

import java.util.Locale;

import org.springframework.boot.context.embedded.undertow.UndertowBuilderCustomizer;
import org.springframework.boot.context.embedded.undertow.UndertowEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import io.undertow.Undertow.Builder;
import io.undertow.UndertowOptions;


@Configuration
public class BeanConfiguration {
	@Bean
	public LocaleResolver localeResolver() {
	    SessionLocaleResolver slr = new SessionLocaleResolver();
	    slr.setDefaultLocale(Locale.forLanguageTag("TH"));
	    return slr;
	}

	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
	    LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
	    lci.setParamName("ln");
	    return lci;
	}
	
	@Bean
	public UndertowEmbeddedServletContainerFactory containerFactory() {
		UndertowEmbeddedServletContainerFactory containerFactory = new UndertowEmbeddedServletContainerFactory();
		containerFactory.addBuilderCustomizers(new UndertowBuilderCustomizer() {

			@Override
			public void customize(Builder builder) {
				//builder.addHttpListener(8080, "0.0.0.0");
				builder.setServerOption(UndertowOptions.ENABLE_HTTP2, true);
				
			}});
		return containerFactory;
	}
	
}
