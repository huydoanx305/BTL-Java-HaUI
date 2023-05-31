package com.hit.admission.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hit.admission.model.base.DateAuditing;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

/**
 *
 * @author Huy Doan
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "subjects")
public class Subject extends DateAuditing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Nationalized
    @Column(name = "name", nullable = false)
    private String name;
    
    //link to table Block
    @ManyToMany(mappedBy = "subjects", cascade = {CascadeType.PERSIST})
    @JsonIgnore
    private Set<Block> blocks = new HashSet<>();
    
}
