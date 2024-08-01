package com.braja.rxjava.configuration;

import lombok.AllArgsConstructor;
import org.jpos.iso.ISOException;
import org.jpos.iso.packager.GenericPackager;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;

@AllArgsConstructor
@Configuration
public class GenericPackagerConfiguration {

    private ResourceLoader resourceLoader;

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public GenericPackager genericPackager() throws ISOException, IOException {
        var sourcePackage = resourceLoader.getResource(ResourceLoader.CLASSPATH_URL_PREFIX + "/iso87ascii.xml").getInputStream();
        var packager = new GenericPackager(sourcePackage);
        sourcePackage.close();
        return packager;
    }

}
