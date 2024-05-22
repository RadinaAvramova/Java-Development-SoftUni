package bank;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class BankTests {
    @Test(expected = NullPointerException.class)
    public void testSetNameOnNull() {
        new Bank(null, 10);
    }

    @Test(expected = NullPointerException.class)
    public void testSetNameOnWhitespaces() {
        new Bank("      ", 10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCapacityOnLessThanZero() {
        new Bank("Unicredit", -3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddClientOnFullBank() {
        Bank bank = new Bank("DSKBank", 1);
        bank.addClient(new Client("Client"));
        bank.addClient(new Client("Client11"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveUnknownClient() {
        Bank bank = new Bank("DSK", 1);
        bank.removeClient("client");
    }

    @Test
    public void testGetName() {
        Bank bank = new Bank("DSK", 1);
        assertEquals("DSK", bank.getName());
    }

    @Test
    public void testGetCapacity() {
        Bank bank = new Bank("DSK", 6);
        assertEquals(6, bank.getCapacity());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testClientForLoanWithdrawal() {
        Bank bank = new Bank("DSK", 1);
        bank.loanWithdrawal("Client");
    }

    @Test
    public void testIsApprovedForLoan() {
        Bank bank = new Bank("DSK", 1);
        Client client = new Client("client");
        bank.addClient(client);
        bank.loanWithdrawal("client");

        assertFalse(client.isApprovedForLoan());
    }

    @Test
    public void testGetCountAfterAdding() {
        Bank bank = new Bank("DSK", 1);
        Client client = new Client("Client");
        bank.addClient(client);

        assertEquals(1, bank.getCount());
    }

    @Test
    public void testGetCountAfterRemoving() {
        Bank bank = new Bank("DSK", 3);
        Client client = new Client("Client");
        Client client1 = new Client("Client1");
        Client client2 = new Client("Client2");
        bank.addClient(client);
        bank.addClient(client1);
        bank.addClient(client2);
        bank.removeClient("Client1");
        bank.removeClient("Client2");

        assertEquals(1, bank.getCount());
    }

    @Test
    public void testStatistics() {
        Bank bank = new Bank("DSK", 3);
        bank.addClient(new Client("Client"));
        bank.addClient(new Client("Client1"));
        bank.addClient(new Client("Client2"));

        String expected = "The client Client, Client1, Client2 is at the DSK bank!";

        assertEquals(expected, bank.statistics());
    }
}
