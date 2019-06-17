package cn.zedongw.transaction;

import org.junit.Test;

import static org.junit.Assert.*;

public class TransactionDemoTest {

    @Test
    public void transfer() {
        TransactionDemo transactionDemo = new TransactionDemo();
        transactionDemo.transfer();
    }
}