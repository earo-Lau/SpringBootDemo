package com.earo.test.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;

/**
 * Created by lauearo on 02/05/2017.
 */
@Configuration
@EnableConfigurationProperties(HttpEncodingProperties.class)    //enable properties injection
@ConditionalOnClass(CharacterEncodingFilter.class)  //only CharacterEncodingFilter exist in classpath, this configuration will auto register as a configuration Bean
@ConditionalOnProperty(prefix = "spring.http.encoding", value = "enabled", matchIfMissing = true)   //only property "spring.http.encoding" is true, this configuration will be auto register as a Bean
public class HttpEncodingAutoConfiguration {
    @Autowired
    private HttpEncodingProperties httpEncodingProperties;

    @Bean
    @ConditionalOnMissingBean(CharacterEncodingFilter.class)    //if CharacterEncodingFilter.class do not exist in classpath, create a Bean.
    public CharacterEncodingFilter characterEncodingFilter(){
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding(this.httpEncodingProperties.getCharset().name());
        filter.setForceEncoding(this.httpEncodingProperties.isForce());
        return filter;
    }

}
