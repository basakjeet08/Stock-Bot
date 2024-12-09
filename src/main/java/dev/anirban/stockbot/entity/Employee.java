package dev.anirban.stockbot.entity;


import dev.anirban.stockbot.dto.common.EmployeeDto;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "EMPLOYEE")
public class Employee implements UserDetails {

    public enum EmployeeRole {
        OWNER, MANAGER, CASHIER, STAFF
    }

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "salary", nullable = false)
    private Integer salary;

    @Column(name = "joining_date", nullable = false)
    private Timestamp joiningDate;

    @Column(name = "roles", nullable = false)
    private EmployeeRole roles;

    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;

    @Column(name = "updated_at", nullable = false)
    private Timestamp updatedAt;

    @OneToMany(
            mappedBy = "requestedBy",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST}
    )
    private Set<Restock> restockList;

    public void addRestock(Restock restock) {
        restockList.add(restock);
        restock.setRequestedBy(this);
    }

    public EmployeeDto toEmployeeDto() {
        return EmployeeDto
                .builder()
                .id(id)
                .name(name)
                .username(username)
                .salary(salary)
                .joiningDate(joiningDate)
                .roles(roles)
                .build();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(roles.name()));
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