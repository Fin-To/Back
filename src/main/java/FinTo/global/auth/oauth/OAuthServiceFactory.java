package FinTo.global.auth.oauth;

import FinTo.domain.member.domain.OAuthProvider;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class OAuthServiceFactory {
    private final Map<OAuthProvider, OAuthService> serviceMap;

    public OAuthServiceFactory(List<OAuthService> services) {
        this.serviceMap = services.stream()
                .collect(Collectors.toMap(OAuthService::getProvider, s -> s));
    }

    public OAuthService get(OAuthProvider provider) {
        return serviceMap.get(provider);
    }
}
