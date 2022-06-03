package com.example.test2.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "blocked_ip_table")
public class BlockedIPTable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "REQUEST_NUMBER")
    private String requestNumber;

    @Column(name = "IP")
    private String ip;

    @Column(name = "COMMENT")
    private String comment;
}
