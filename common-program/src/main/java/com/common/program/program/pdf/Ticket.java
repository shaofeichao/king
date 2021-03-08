package com.common.program.program.pdf;

public class Ticket {
    private String ticketId;
    private String homesId;
    private String ticketCreateTime;
    private String ticketCompany;
    private String sysName;
    private String moneyLittle;
    private String moneyBig;
    private String accountCompany;
    private String bedNumber;
    private String username;
    private String password;

    public Ticket() {
        super();
    }

    public Ticket(String ticketId, String homesId, String ticketCreateTime,
                  String ticketCompany, String sysName, String moneyLittle,
                  String moneyBig, String accountCompany, String bedNumber,
                  String username, String password) {
        this.ticketId = ticketId;
        this.homesId = homesId;
        this.ticketCreateTime = ticketCreateTime;
        this.ticketCompany = ticketCompany;
        this.sysName = sysName;
        this.moneyLittle = moneyLittle;
        this.moneyBig = moneyBig;
        this.accountCompany = accountCompany;
        this.bedNumber = bedNumber;
        this.username = username;
        this.password = password;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getHomesId() {
        return homesId;
    }

    public void setHomesId(String homesId) {
        this.homesId = homesId;
    }

    public String getTicketCreateTime() {
        return ticketCreateTime;
    }

    public void setTicketCreateTime(String ticketCreateTime) {
        this.ticketCreateTime = ticketCreateTime;
    }

    public String getTicketCompany() {
        return ticketCompany;
    }

    public void setTicketCompany(String ticketCompany) {
        this.ticketCompany = ticketCompany;
    }

    public String getSysName() {
        return sysName;
    }

    public void setSysName(String sysName) {
        this.sysName = sysName;
    }

    public String getMoneyLittle() {
        return moneyLittle;
    }

    public void setMoneyLittle(String moneyLittle) {
        this.moneyLittle = moneyLittle;
    }

    public String getMoneyBig() {
        return moneyBig;
    }

    public void setMoneyBig(String moneyBig) {
        this.moneyBig = moneyBig;
    }

    public String getAccountCompany() {
        return accountCompany;
    }

    public void setAccountCompany(String accountCompany) {
        this.accountCompany = accountCompany;
    }

    public String getBedNumber() {
        return bedNumber;
    }

    public void setBedNumber(String bedNumber) {
        this.bedNumber = bedNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
