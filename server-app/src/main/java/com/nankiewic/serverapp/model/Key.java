package com.nankiewic.serverapp.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "keys_table")
public class Key {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String keyVal;
}
