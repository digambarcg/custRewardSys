package com.example.CustRewardSystem.controller;

import java.time.Month;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.CustRewardSystem.Exception.InvalidInputException;
import com.example.CustRewardSystem.model.Transaction;
import com.example.CustRewardSystem.service.RewardService;

@RestController
@RequestMapping("/rewards")
public class RewardController {

	@Autowired
	private RewardService rewardService;
	
	@PostMapping("/addTransaction")
	public String addTrans(@RequestBody Transaction tran) throws InvalidInputException {
		rewardService.addTrans(tran);
		return "Transaction added successfully.";
	}
	
	@GetMapping("/totalRewards")
	public Map<String, Integer> getTotalRewards() {
		return rewardService.calcTotalRewardPoints();
	}
	
	@GetMapping("/MonthlyRewards")
	public Map<String, Map<Month, Integer>> getMonthlyRewards() {
		return rewardService.calcRewardPoints();
	}
}
