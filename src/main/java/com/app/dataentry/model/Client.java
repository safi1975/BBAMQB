package com.app.dataentry.model;

import javax.persistence.*;

@Entity
@Table(name = "client")
public class Client implements BaseEntity {

    @Id
    @SequenceGenerator(sequenceName = "client_seq", name="client_seq_id", initialValue = 20, allocationSize = 10)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_seq_id")
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "mobile_no")
    private String mobileNo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }
}
