package com.neu.demo.entity;

public class Project {
    String project_id;
    String project_name;
    String project_info;
    String begin;
    String ddl;
    String company;
    String status;
<<<<<<< HEAD
    String begin;
=======
    String auditStatus;
    String auditSuggest;
>>>>>>> 4141cb604101d3c7e96a66696b9c9e10931d0c48
    int step;
    double ufp_num;
    double dfp_num;
    double s_num;
    int ei_num;
    int eo_num;
    int eq_num;
    int ilf_num;
    int elf_num;

    public String getBegin() {
        return begin;
    }

    public void setBegin(String begin) {
        this.begin = begin;
    }
    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getAuditSuggest() {
        return auditSuggest;
    }

    public void setAuditSuggest(String auditSuggest) {
        this.auditSuggest = auditSuggest;
    }

    public Project() {
    }

    public String getProject_id() {
        return project_id;
    }

    public void setProject_id(String project_id) {
        this.project_id = project_id;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getProject_info() {
        return project_info;
    }

    public void setProject_info(String project_info) {
        this.project_info = project_info;
    }

    public String getDdl() {
        return ddl;
    }

    public void setDdl(String ddl) {
        this.ddl = ddl;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public double getUfp_num() {
        return ufp_num;
    }

    public void setUfp_num(double ufp_num) {
        this.ufp_num = ufp_num;
    }

    public double getDfp_num() {
        return dfp_num;
    }

    public void setDfp_num(double dfp_num) {
        this.dfp_num = dfp_num;
    }

    public double getS_num() {
        return s_num;
    }

    public void setS_num(double s_num) {
        this.s_num = s_num;
    }

    public int getEi_num() {
        return ei_num;
    }

    public void setEi_num(int ei_num) {
        this.ei_num = ei_num;
    }

    public int getEo_num() {
        return eo_num;
    }

    public void setEo_num(int eo_num) {
        this.eo_num = eo_num;
    }

    public String getBegin() {
        return begin;
    }

    public void setBegin(String begin) {
        this.begin = begin;
    }

    public int getEq_num() {
        return eq_num;
    }

    public void setEq_num(int eq_num) {
        this.eq_num = eq_num;
    }

    public int getIlf_num() {
        return ilf_num;
    }

    public void setIlf_num(int ilf_num) {
        this.ilf_num = ilf_num;
    }

    public int getElf_num() {
        return elf_num;
    }

    public void setElf_num(int elf_num) {
        this.elf_num = elf_num;
    }
}