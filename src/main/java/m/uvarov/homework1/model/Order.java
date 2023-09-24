package m.uvarov.homework1.model;


import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

@Entity
@Table(name = "orders")

public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Getter
    private long id;

    @Getter
    @Setter
    @Column(name = "user_id")
    private long userId;

    @Getter
    @Setter
    @Column(name = "request_id")
    private String requestId;

    @Getter
    @Setter
    @Column(name = "goods")
    private String goods;

    @Getter
    @Setter
    @Column(name = "amount")
    private Long amount;

    @Getter
    @Setter
    @Column(name = "quantity")
    private Long quantity;

}
