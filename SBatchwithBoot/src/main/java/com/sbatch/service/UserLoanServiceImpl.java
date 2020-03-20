package com.sbatch.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sbatch.model.LoanAccountDetails;
import com.sbatch.model.User;
import com.sbatch.repository.LoanAccountRepository;
import com.sbatch.repository.UserRepository;

@Service
@Transactional
public class UserLoanServiceImpl implements UserLoanService {
	
	@Autowired
	UserRepository userrepository;

	
	@Autowired
	LoanAccountRepository loanrepository;
	
	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return userrepository.findAll();
	}

	@Override
	public Optional<User> getuserById(Long uid) {
		// TODO Auto-generated method stub
		 Optional<User> user=userrepository.findById(uid);
		return user;
	}

	@Override
	public Optional<User> saveloan(LoanAccountDetails loanaccount) {
		Optional<User> userdet=userrepository.findById(loanaccount.getUser().getUid());
		Optional<User> userloanresult = null;
		if (userdet.isPresent()) { 
		  User user=userdet.get();
		  loanaccount.setUser(user); 
		  }
		loanrepository.save(loanaccount);
		Optional<LoanAccountDetails> loandetById=loanrepository.findById(userdet.get().getUid());
		
		if(loandetById.isPresent()) {
			userloanresult=userrepository.findById(loanaccount.getUser().getUid());
		}
		return userloanresult;
	}

	@Override
	public Double emicalcualtor(Long uid, Long noyears) {
		 double emi = 0;
		 Float rate = 12.0f;
		 Optional<LoanAccountDetails> userloanresult=loanrepository.findById(uid);
		 if(userloanresult.isPresent()) {
			 LoanAccountDetails loan=userloanresult.get();
	        rate=rate/(12*100);
	        noyears=noyears*12;
	        
	        /*List<LoanAccountDetails> loand=loan.getLoanacc();
	       
	        loand.stream().forEach(l->System.out.println(""+l.getAmount())
	        		
	        		);*/
	        
	        
	        
	        
	        
	        emi= (loan.getAmount().doubleValue()*rate*Math.pow(1+rate,noyears))/(Math.pow(1+rate,noyears)-1);
		 }
	        
		return emi;
	}
	
	
	
}
