package com.petiteestudio.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Daniel on 20/07/2017.
 */
@Entity
public class Cliente implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_persona")
    private Persona persona;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }
}
