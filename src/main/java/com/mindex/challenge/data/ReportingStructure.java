package com.mindex.challenge.data;

public class ReportingStructure {

    private Employee employee;
    private int numberOfReports;

    public int getNumberOfReports() {
        return numberOfReports;
    }

    public void setNumberOfReports(int numberOfReports) {
        this.numberOfReports = numberOfReports;
    }

    public com.mindex.challenge.data.Employee getEmployee() {
        return employee;
    }

    public void setEmployee(com.mindex.challenge.data.Employee employee) {
        this.employee = employee;
    }
}
