package it.unibo.bank.impl;

import it.unibo.bank.api.AccountHolder;
import it.unibo.bank.api.BankAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;

/**
 * Test class for the {@link StrictBankAccount} class.
 */
class TestStrictBankAccount {

    // Create a new AccountHolder and a StrictBankAccount for it each time tests are executed.
    private AccountHolder mRossi;
    private BankAccount bankAccount;

    private static final int ACCEPTABLE_MESSAGE_LENGTH = 10;
    /**
     * Prepare the tests.
     */
    @BeforeEach
    public void setUp() {
        this.mRossi = new AccountHolder("Mario", "Rossi", 1);
        this.bankAccount = new StrictBankAccount(mRossi, 0);
    }

    /**
     * Test the initial state of the StrictBankAccount.
     */
    @Test
    public void testInitialization() {
        assertEquals(0.0, bankAccount.getBalance());
        assertEquals(0, bankAccount.getTransactionsCount());
        assertEquals(mRossi, bankAccount.getAccountHolder());
    }

    /**
     * Perform a deposit of 100€, compute the management fees, and check that the balance is correctly reduced.
     */
    @Test
    public void testManagementFees() {
        this.bankAccount.deposit(1, 100);
        this.bankAccount.chargeManagementFees(1);
        assertEquals(94.9, this.bankAccount.getBalance());
    }

    /**
     * Test that withdrawing a negative amount causes a failure.
     */
    @Test
    public void testNegativeWithdraw() {
        try{
            this.bankAccount.withdraw(1, -10);
            Assertions.fail("Withdrawing a negative amount was possible, but should have thrown an exception");
        } catch (IllegalArgumentException ex) {
            assertEquals(0, this.bankAccount.getBalance());
            assertEquals(0, this.bankAccount.getTransactionsCount());
            assertFalse(ex.getMessage().isBlank());
            assertTrue(ex.getMessage().length() >= ACCEPTABLE_MESSAGE_LENGTH);
        }
        
    }

    /**
     * Test that withdrawing more money than it is in the account is not allowed.
     */
    @Test
    public void testWithdrawingTooMuch() {
        try {
            this.bankAccount.withdraw(1, 100);
            Assertions.fail("Withdrawing too much money was possible, but should have thrown an exception");
        } catch (IllegalArgumentException ex) {
            assertEquals(0, this.bankAccount.getBalance());
            assertEquals(0, this.bankAccount.getTransactionsCount());
            assertFalse(ex.getMessage().isBlank());
            assertTrue(ex.getMessage().length() >= ACCEPTABLE_MESSAGE_LENGTH);
        }
        
    }
}
