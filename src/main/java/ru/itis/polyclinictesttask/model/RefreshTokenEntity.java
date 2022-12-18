package ru.itis.polyclinictesttask.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.Instant;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "refresh_token")
public class RefreshTokenEntity extends AbstractEntity {

    @Column(name = "token")
    private String token;

    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private AccountEntity account;

    @Column(nullable = false, name = "expiry_date")
    private Instant expiryDate;
}