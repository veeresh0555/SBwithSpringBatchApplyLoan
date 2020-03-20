package com.sbatch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sbatch.model.LoanAccountDetails;

@Repository
public interface LoanAccountRepository extends JpaRepository<LoanAccountDetails, Long> {

}
