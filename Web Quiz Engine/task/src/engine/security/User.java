package engine.security;

import engine.Question;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
public class User implements UserDetails {

    @Getter
    @Setter
    @SequenceGenerator(name = "user_generator", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "user_generator")
    private long id;

    @Email(regexp = ".+\\..+")
    @NotBlank(message = "Email is required")
    @Getter
    @Setter
    private String email;

    @NotEmpty(message = "Password is required")
    @Size(min = 5, message = "At least 5 characters are required")
    @Getter
    @Setter
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @Getter
    private List<Question> quizzes;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
