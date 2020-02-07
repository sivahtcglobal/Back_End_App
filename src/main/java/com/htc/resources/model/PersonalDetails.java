package com.htc.resources.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by giris on 27/1/20.
 */
@Entity
@Table
public class PersonalDetails {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int personalDetailsId;

    @Column
    private String officialEmailAddr;

    @Column
    private String EmailAddr;

    @Column
    private int extensionNumber;

    @Column
    private long mobileNumber;


    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "address_id")
    private Address address;


    public PersonalDetails() {
    }

    public String getOfficialEmailAddr() {
        return officialEmailAddr;
    }

    public void setOfficialEmailAddr(String officialEmailAddr) {
        this.officialEmailAddr = officialEmailAddr;
    }

    public String getEmailAddr() {
        return EmailAddr;
    }

    public void setEmailAddr(String emailAddr) {
        EmailAddr = emailAddr;
    }

    public int getExtensionNumber() {
        return extensionNumber;
    }

    public void setExtensionNumber(int extensionNumber) {
        this.extensionNumber = extensionNumber;
    }

    public long getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public int getPersonalDetailsId() {
        return personalDetailsId;
    }

    public void setPersonalDetailsId(int personalDetailsId) {
        this.personalDetailsId = personalDetailsId;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
