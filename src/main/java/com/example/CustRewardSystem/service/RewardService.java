package com.example.CustRewardSystem.service;

import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.CustRewardSystem.Exception.InvalidInputException;
import com.example.CustRewardSystem.model.Transaction;

@Service
public class RewardService {
	private List<Transaction> trans = new ArrayList<>();
	
	public void addTrans(Transaction tran) throws InvalidInputException {
		checkTrans(tran);
		trans.add(tran);
	}
	
	public Map<String, Map<Month, Integer>> calcRewardPoints() {
		Map<String, Map<Month, Integer>> cust_rewards = new HashMap<>();
		
		for (Transaction tran : trans) {
			String cust_id = tran.getCust_id();
			Month month = tran.getTrans_date().getMonth();
			int points = calcPoints(tran.getTrans_amt());
			
			cust_rewards.computeIfAbsent(cust_id, k -> new HashMap<>()).merge(month, points, Integer::sum);
		}
		return cust_rewards;
	}
	
	public  Map<String, Integer> calcTotalRewardPoints(){
		Map<String, Integer> totalRewards = new HashMap<>();
		
		for (Transaction tran : trans) {
			String cust_id = tran.getCust_id();
			int points = calcPoints(tran.getTrans_amt());
			
			totalRewards.merge(cust_id, points, Integer::sum);
		}
		return totalRewards;
		
	}
	
	private int calcPoints(double trans_amt) {
		int points = 0;
		if (trans_amt > 100) {
			points += (trans_amt - 100) * 2;
		}
		if (trans_amt > 50) {
			points += (Math.min(trans_amt, 100) - 50);
		}
		return points;
		
	}
	
	private void checkTrans(Transaction trans) throws InvalidInputException {
		if (trans.getCust_id() == null || trans.getCust_id().isEmpty()) {
			throw new InvalidInputException("Customer ID can not be null or empty.");
		}
		if (trans.getTrans_amt() < 0 ) {
			throw new InvalidInputException("Transaction amount can not be less than 0/negative.");
		}
		if (trans.getTrans_date() == null || trans.getTrans_date().equals(" ")) {
			throw new InvalidInputException("Transaction date can not be null or empty.");
		}
	}
}
