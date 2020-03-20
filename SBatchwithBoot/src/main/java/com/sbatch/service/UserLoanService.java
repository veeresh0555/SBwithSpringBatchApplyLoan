package com.sbatch.service;

import java.util.List;
import java.util.Optional;

import com.sbatch.model.LoanAccountDetails;
import com.sbatch.model.User;

public interface UserLoanService {

	public List<User> getAllUsers();

	public Optional<User> getuserById(Long uid);

	public Optional<User> saveloan(LoanAccountDetails loanaccount);

	public Double emicalcualtor(Long uid, Long noyears);

}
