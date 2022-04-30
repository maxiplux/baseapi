package io.api.base.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @SequenceGenerator(name="customer_seq",  sequenceName="customer_seq",       allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,        generator="customer_seq")
    private Long id;
    private String name;
    private String email;

}
