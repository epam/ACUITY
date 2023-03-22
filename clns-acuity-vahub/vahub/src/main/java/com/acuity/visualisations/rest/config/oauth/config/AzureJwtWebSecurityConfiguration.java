/*
 * Copyright 2021 The University of Manchester
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.acuity.visualisations.rest.config.oauth.config;

import com.acuity.visualisations.rest.config.oauth.jwt.AzureJwtAuthenticationTokenFilter;
import com.acuity.visualisations.rest.config.oauth.jwt.JwtAuthenticationProvider;
import com.acuity.va.security.auth.azure.AzureSecurityAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Collections;

@Order(97)
@Configuration
@EnableOAuth2Client
@Profile("azure-sso")
public class AzureJwtWebSecurityConfiguration extends AzureSecurityAutoConfiguration {

    @Autowired
    private JwtAuthenticationProvider authenticationProvider;

    @Bean
    @Override
    public AuthenticationManager authenticationManager() {
        return new ProviderManager(Collections.singletonList(authenticationProvider));
    }

    private AzureJwtAuthenticationTokenFilter azureJwtAuthenticationTokenFilter() {
        AzureJwtAuthenticationTokenFilter authenticationTokenFilter = new AzureJwtAuthenticationTokenFilter();
        authenticationTokenFilter.setAuthenticationManager(authenticationManager());
        return authenticationTokenFilter;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        http.addFilterBefore(azureJwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
