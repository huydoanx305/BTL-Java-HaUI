package com.hit.admission.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hit.admission.model.base.DateAuditing;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Huy Doan
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "blocks")
public class Block extends DateAuditing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "code", nullable = false, unique = true)
    private String code;

    //link to table Admission
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "block")
    @JsonIgnore
    private Set<Admission> admissions = new HashSet<>();

    //link to table Major
    @ManyToMany(mappedBy = "blocks", cascade = {CascadeType.PERSIST})
    @JsonIgnore
    private Set<Major> majors = new HashSet<>();

}
