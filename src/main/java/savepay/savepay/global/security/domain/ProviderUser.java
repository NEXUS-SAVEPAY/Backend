package savepay.savepay.global.security.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.List;
import java.util.Map;

public interface ProviderUser extends OAuth2User {

    public String getId();
    public String getUsername();
    public String getPassword();
    public String getEmail();
    public String getProvider();
    public List<? extends GrantedAuthority> getAuthorities();
    public Map<String, Object> getAttributes();

}
