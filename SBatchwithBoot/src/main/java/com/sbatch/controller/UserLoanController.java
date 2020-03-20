package com.sbatch.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sbatch.model.LoanAccountDetails;
import com.sbatch.model.User;
import com.sbatch.service.UserLoanService;

@RestController
@RequestMapping("/api/users")
public class UserLoanController {
	
	@Autowired
	UserLoanService userloanservice;
	
	@GetMapping
	public Optional<List<User>> getAllUsers(){
		List<User> userlist=userloanservice.getAllUsers();
		return Optional.of(userlist);
	}
	
	@GetMapping("/{uid}")
	public Optional<?> getuserById(@PathVariable("uid") Long uid){
		Optional<User> userById=userloanservice.getuserById(uid);
		return Optional.of(userById);
	}
	
	@PostMapping
	public Optional<Optional<User>> saveUser(LoanAccountDetails loanaccount){//@RequestBody 
		Optional<User> loan=userloanservice.saveloan(loanaccount);
		return Optional.of(loan);
	}
	
	@GetMapping("/{uid}/{noyears}")
	public Optional<?> calculateemi(@PathVariable("uid") Long uid,@PathVariable("noyears") Long noyears) {
		Double emi=userloanservice.emicalcualtor(uid,noyears);
		return Optional.of(emi);
		
	}
	
	
 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
