package com.stalight.crystal.bean;

import org.springframework.stereotype.Component;

/**
 * <p>银行账户属性</p>
 * @author Crystal
 * @version 1.0
 * @since 1.0
 */
@Component
public class Account {
    private Integer id;
    private String account;
    private Double balance;

    public Account() {
    }

    public Account(Integer id, String account, Double balance) {
        this.id = id;
        this.account = account;
        this.balance = balance;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Integer getId() {
        return id;
    }

    public String getAccount() {
        return account;
    }

    public Double getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", balance=" + balance +
                '}';
    }
}
