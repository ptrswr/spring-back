package com.projectweb3.repository;

import com.projectweb3.domain.Loan;
import java.util.List;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Loan entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
    @Query("select loan from Loan loan where loan.user.login = ?#{principal.username}")
    List<Loan> findByUserIsCurrentUser();
}
