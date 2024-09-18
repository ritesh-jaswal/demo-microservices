package com.project.user.service.UserService.configs;

import com.project.user.service.UserService.configs.interceptor.RestTemplateInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProvider;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProviderBuilder;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class UserConfig
{
    @Autowired
    ClientRegistrationRepository registrationRepository;

    @Autowired
    OAuth2AuthorizedClientRepository clientRepository;

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate()
    {
        RestTemplate restTemplate = new RestTemplate();

        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        interceptors.add(new RestTemplateInterceptor
                (manager
                        (
                                registrationRepository,
                                clientRepository
                        )
                ));

        restTemplate.setInterceptors(interceptors);

        return restTemplate;
    }

    @Bean
    public OAuth2AuthorizedClientManager manager
            (ClientRegistrationRepository registrationRepository,
             OAuth2AuthorizedClientRepository clientRepository)
    {

        OAuth2AuthorizedClientProvider provider = OAuth2AuthorizedClientProviderBuilder.builder().clientCredentials().build();

        DefaultOAuth2AuthorizedClientManager manager = new DefaultOAuth2AuthorizedClientManager(registrationRepository,clientRepository);
        manager.setAuthorizedClientProvider(provider);

        return manager;
    }
}
