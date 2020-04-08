package com.rfbsoft.v0.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class District implements Comparable<District>{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String name;


    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "district")
    @JsonIgnore
    private List<Neighborhood> neighborhoods = new ArrayList<>();


    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "district")
    @JsonIgnore
    private List<Phone> phones = new ArrayList<>();


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "province_id", updatable = false)
    @JsonBackReference
    private Province province;


    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Neighborhood> getNeighborhoods() {
        return neighborhoods;
    }

    public void setNeighborhoods(List<Neighborhood> neighborhoods) {
        this.neighborhoods = neighborhoods;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    @Override
    public int compareTo(District o) {
        return this.getName().compareTo(o.getName());
    }
}
