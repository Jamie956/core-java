package org.example;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

// https://www.baeldung.com/aspectj
public class AccountTest {
    private Account account;

    @Before
    public void before() {
        account = new Account();
    }

    @Test
    public void test1() {
        assertTrue(account.withdraw(5));
    }

    @Test
    public void test2() {
        assertFalse(account.withdraw(100));
    }
}
