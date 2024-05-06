/*
 * Copyright 2008-2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.vn.osp.notarialservices;

import com.vn.osp.notarialservices.utils.config.CORSFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.Filter;

/**
 * Created by longtran on 19/10/2016.
 */
@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.vn.osp.notarialservices.*.repository")
@EnableTransactionManagement
public class NotarialServicesAPIApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(NotarialServicesAPIApplication.class);
    }

    @Bean
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setMaxUploadSize(52428800); // Cấu hình kích thước tối đa cho tệp tin tải lên (ở đây là 50MB)
        return resolver;
    }
    @Bean
    public Filter corsFilter() {
        return new CORSFilter();
    }
    public static void main(String[] args) {
        SpringApplication.run(NotarialServicesAPIApplication.class, args);
    }
}