package datawave.microservice.authorization;

import datawave.security.authorization.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.EnableCaching;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import static datawave.security.authorization.DatawaveUser.UserType.USER;
import static java.util.stream.Collectors.toList;

@EnableCaching
@CacheConfig(cacheNames = "datawaveUsers-IT")
public class AuthorizationTestUserService implements CachedDatawaveUserService {
    
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private Map<SubjectIssuerDNPair,DatawaveUser> userMap;
    private boolean createUser;
    
    public AuthorizationTestUserService(Map<SubjectIssuerDNPair,DatawaveUser> userMap, boolean createUser) {
        this.userMap = userMap;
        this.createUser = createUser;
    }
    
    @Override
    public Collection<DatawaveUser> lookup(Collection<SubjectIssuerDNPair> dns) throws AuthorizationException {
        logger.debug("AuthorizationTestUserService.lookup called");
        return dns.stream().map(dn -> {
            DatawaveUser user = this.userMap.get(dn);
            if (user == null && createUser) {
                user = new DatawaveUser(dn, USER, null, null, null, null, -1L);
            }
            return user;
            
        }).collect(toList());
    }
    
    @Override
    public Collection<DatawaveUser> reload(Collection<SubjectIssuerDNPair> dns) throws AuthorizationException {
        return null;
    }
    
    @Override
    public DatawaveUser list(String name) {
        try {
            return lookup(Collections.singleton(SubjectIssuerDNPair.of(name))).stream().findFirst().orElse(null);
        } catch (AuthorizationException e) {
            return null;
        }
    }
    
    @Override
    public Collection<? extends DatawaveUserInfo> listAll() {
        return null;
    }
    
    @Override
    public Collection<? extends DatawaveUserInfo> listMatching(String substring) {
        return null;
    }
    
    @Override
    public String evict(String name) {
        return null;
    }
    
    @Override
    public String evictMatching(String substring) {
        return null;
    }
    
    @Override
    public String evictAll() {
        return null;
    }
}
