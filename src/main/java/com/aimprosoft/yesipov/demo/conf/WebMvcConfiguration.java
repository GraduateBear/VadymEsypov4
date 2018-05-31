package com.aimprosoft.yesipov.demo.conf;

import net.sf.oval.Validator;
import net.sf.oval.integration.spring.SpringValidator;
import net.sf.oval.localization.message.ResourceBundleMessageResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;
import java.util.ResourceBundle;

@Configuration
//@EnableWebMvc
public class WebMvcConfiguration {

    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
        localeResolver.setDefaultLocale(new Locale("ru"));
        return localeResolver;
    }

    @Bean
    public SpringValidator springValidator() {
        return new SpringValidator(new Validator());
    }

    @Bean
    public ResourceBundleMessageResolver resourceBundleMessageResolver() {
        ResourceBundleMessageResolver resolver = (ResourceBundleMessageResolver) Validator.getMessageResolver();
        resolver.addMessageBundle(ResourceBundle.getBundle("resources"));
        return resolver;
    }
//
//    src/main/resources/META-INF/resources/WEB-INF/jsp/
//    @Override
//    public void configureViewResolvers(ViewResolverRegistry registry) {
//        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
//        resolver.setPrefix("classpath:/templates/");
//        resolver.setSuffix(".jsp");
//        resolver.setViewClass(JstlView.class);
//        registry.viewResolver(resolver);
//    }
}
