package com.hit.admission.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hit.admission.constants.RoleConstant;
import com.hit.admission.model.base.DateAuditing;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Huy Doan
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User extends DateAuditing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    @JsonIgnore
    private String password;

    @Column(name = "role_name", nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleConstant roleName;

    //link to table Student
    @OneToOne
    @JoinColumn(name = "student_id", foreignKey = @ForeignKey(name = "FK_USER_STUDENT"))
    private Student student;

}
