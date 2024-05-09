package entities;

import java.util.*;

import jakarta.persistence.*;

@Entity(name = "Company")
@Table(name = "companies")
public class Company {
    @Id
    @Column(name = "CompanyId")
    private String companyId;
    
    @Column(name = "CompanyName")
    private String companyName;
    
    public Company() {
    }

    public Company(String companyId, String companyName) {
        super();
        this.companyId = companyId;
        this.companyName = companyName;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
