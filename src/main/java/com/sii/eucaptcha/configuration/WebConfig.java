package com.sii.eucaptcha.configuration;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.util.Locale;

/**
 * @author mousab.aidoud
 * @version 1.0
 * Web MVC configuration.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.sii.eucaptcha"})
public class WebConfig implements WebMvcConfigurer {

    /**
     *
     * @return the created resolver
     */
    @Bean
    protected InternalResourceViewResolver resolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setViewClass(JstlView.class);
        resolver.setPrefix("/WEB-INF/pages/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    /**
     *
     * @return localResolver.
     */
    @Bean
    protected LocaleResolver localeResolver() {
        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
        localeResolver.setDefaultLocale(Locale.ENGLISH);
        return localeResolver;
    }

    /**
     *
     * @return localChangeInterceptor
     */
    @Bean
    protected LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        return localeChangeInterceptor;
    }

    /**
     *
     * @return the message source created
     */
    @Bean
    protected MessageSource messageSource() {
        ResourceBundleMessageSource source = new ResourceBundleMessageSource();
        source.setDefaultEncoding("UTF-8");
        source.setBasename("messages");
        return source;
    }

    /**
     *
     * @param registry the registry to add the interceptor to change the language
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }

    /**
     *
     * @return validator
     */
    @Override
    public Validator getValidator() {
        LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
        validator.setValidationMessageSource(messageSource());
        return validator;
    }

    /**
     *
     * @param registry the registry to add the resource handlers
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/css/**")
                .addResourceLocations("/WEB-INF/pages/css/");
        registry.addResourceHandler("/js/**")
                .addResourceLocations("/WEB-INF/pages/js/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations(
                        "classpath:/META-INF/resources/webjars/");

    }

    /**
     *
     * @return result
     */
    private String[] getStaticLocations() {
        String[] result = new String[5];
        result[0] = "/";
        result[1] = "classpath:/META-INF/resources/";
        result[2] = "classpath:/resources/";
        result[3] = "classpath:/static/";
        result[4] = "classpath:/public/";
        return result;
    }
}