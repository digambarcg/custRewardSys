package com.example.CustRewardSystem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.CustRewardSystem.Exception.InvalidInputException;
import com.example.CustRewardSystem.model.Transaction;
import com.example.CustRewardSystem.service.RewardService;

@SpringBootTest
class CustRewardSystemApplicationTests {

	private RewardService rewardService;
	private List<Transaction> transactions;
	
	
	
	@BeforeEach
	void setUp() {
        LocalDate localDate1 = LocalDate.parse("2025-01-01");
        LocalDate localDate2 = LocalDate.parse("2025-01-15");
        LocalDate localDate3 = LocalDate.parse("2025-02-10");
        LocalDate localDate4 = LocalDate.parse("2025-02-20");
        transactions.add(new Transaction("Cust1",120.0, localDate1));
        transactions.add(new Transaction("Cust1",200.0, localDate2));
        transactions.add(new Transaction("Cust1",80.0, localDate3));
        transactions.add(new Transaction("Cust1",150.0, localDate4));
	}
	
	@Test
	void testEmptyTransaction() {
		assertThrows(InvalidInputException.class, () -> {
			rewardService.checkTrans();
		});
	}
	
	@Test
	void testInvalidTransaction() {
		LocalDate localDate1 = LocalDate.parse("2025-01-01");
		Transaction invalidTransactions = new Transaction("Cust1",120.0, localDate1);
		
		assertThrows(InvalidInputException.class, () -> {
			rewardService.checkTrans(invalidTransactions);
		});
	}
	
	@Test
	void testCalculateRewards() {
	Transaction trans;
	Map<String, Integer> result = rewardService.calcTotalRewardPoints();
	
	Integer monthlyRewards = result.get("monthlyRewards");
	assertEquals(2, monthlyRewards);
	assertEquals(110, result.get("2025-01"));
	assertEquals(150, result.get("2025-02"));
	
	int totalRewards = (int) result.get("totalRewards");
	assertEquals(260, totalRewards);
	}
}
