package com.revolut.services;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
public class BankingServiceTest {

    private BankingService bankingService;

    @Before
    public void setUp() throws Exception {
        bankingService = BankingServiceImpl.getBankingService();
    }

    @After
    public void tearDown() throws Exception {
        bankingService = null;
    }

    @Test
    public void creditMoneyIntoAccount() {
        bankingService.creditMoneyIntoAccount(null);
    }

    @Test
    public void debitMoneyFromAccount() {
    }

    @Test
    public void viewBalanceOfAccount() {
    }
}