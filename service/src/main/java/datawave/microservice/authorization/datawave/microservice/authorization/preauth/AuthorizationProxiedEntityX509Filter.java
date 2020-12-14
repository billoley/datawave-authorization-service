package datawave.microservice.authorization.datawave.microservice.authorization.preauth;

import datawave.microservice.authorization.config.AuthorizationAllowedCallersFilter;
import datawave.microservice.authorization.preauth.ProxiedEntityPreauthPrincipal;
import datawave.microservice.authorization.preauth.ProxiedEntityX509Filter;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class AuthorizationProxiedEntityX509Filter extends ProxiedEntityX509Filter {

    public AuthorizationProxiedEntityX509Filter(boolean useTrustedSubjectHeaders, boolean requireProxiedEntities, boolean requireIssuers, AuthenticationEntryPoint authenticationEntryPoint) {
        super(useTrustedSubjectHeaders, requireProxiedEntities, requireIssuers, authenticationEntryPoint);
    }

//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        if (AuthorizationAllowedCallersFilter.enforceAllowedCallersForRequest((HttpServletRequest) request)) {
//            super.doFilter(request, response, chain);
//        }
//    }

    @Override
    protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
        ProxiedEntityPreauthPrincipal principal = (ProxiedEntityPreauthPrincipal) super.getPreAuthenticatedPrincipal(request);
        return new AuthorizationProxiedEntityPreauthPrincipal(principal.getCallerPrincipal(),
                principal.getProxiedEntities(), request);

    }
}