package com.jantsa.task.manager.TaskManager.entity;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Table(name = "Report")
@Entity
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "header")
    private String header;

    @Column(name = "body")
    private String body;

    @Column(name = "talep_tarihi")
    private LocalDateTime talep_date;

    @Column(name = "atanma_tarihi")
    private LocalDateTime atanma_date;

    @Column(name = "iptal_tarihi")
    private LocalDateTime iptal_date;

    @Column(name = "kabul_tarihi")
    private LocalDateTime kabul_date;

    @Column(name = "bitis_tarihi")
    private LocalDateTime bitis_date;

    @Column(name = "company_id")
    private Integer companyId;

    @Column(name = "status")
    private String status;

    @Column(name = "personal_id")
    private Integer personalId;

    @Column(name = "personal_name")
    private String personal_name;


    public Report(Integer id,
                  String header,
                  String body,
                  LocalDateTime talep_date,
                  LocalDateTime atanma_date,
                  LocalDateTime iptal_date,
                  LocalDateTime kabul_date,
                  LocalDateTime bitis_date,
                  Integer companyId,
                  String status,
                  Integer personalId,
                  String personal_name)
    {
        this.id = id;
        this.header = header;
        this.body = body;
        this.talep_date = talep_date;
        this.atanma_date = atanma_date;
        this.iptal_date = iptal_date;
        this.kabul_date = kabul_date;
        this.bitis_date = bitis_date;
        this.companyId = companyId;
        this.status = status;
        this.personalId = personalId;
        this.personal_name = personal_name;
    }

    public Integer getPersonalId() {
        return personalId;
    }

    public void setPersonalId(Integer personalId) {
        this.personalId = personalId;
    }

    public String getPersonal_name() {
        return personal_name;
    }

    public void setPersonal_name(String personal_name) {
        this.personal_name = personal_name;
    }

    public Report() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public LocalDateTime getTalep_date() {
        return talep_date;
    }

    public void setTalep_date(LocalDateTime talep_date) {
        this.talep_date = talep_date;
    }

    public LocalDateTime getAtanma_date() {
        return atanma_date;
    }

    public void setAtanma_date(LocalDateTime atanma_date) {
        this.atanma_date = atanma_date;
    }

    public LocalDateTime getIptal_date() {
        return iptal_date;
    }

    public void setIptal_date(LocalDateTime iptal_date) {
        this.iptal_date = iptal_date;
    }

    public LocalDateTime getKabul_date() {
        return kabul_date;
    }

    public void setKabul_date(LocalDateTime kabul_date) {
        this.kabul_date = kabul_date;
    }

    public LocalDateTime getBitis_date() {
        return bitis_date;
    }

    public void setBitis_date(LocalDateTime bitis_date) {
        this.bitis_date = bitis_date;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

