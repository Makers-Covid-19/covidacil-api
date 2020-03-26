package com.rfbsoft.model;


import javax.persistence.*;

@Entity

@Table()


public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Column
    private String no;

    @OneToOne
    private Category category;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "neighborhood_id", updatable = false)
    private Neighborhood neighborhood;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "province_id", updatable = false)
    private Province province;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "distric_id", updatable = false)
    private District district;


    public Neighborhood getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(Neighborhood neighborhood) {
        this.neighborhood = neighborhood;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }


    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
