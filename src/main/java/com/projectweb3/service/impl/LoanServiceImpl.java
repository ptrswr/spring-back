package com.projectweb3.service.impl;

import com.projectweb3.domain.Loan;
import com.projectweb3.repository.LoanRepository;
import com.projectweb3.service.LoanService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Loan}.
 */
@Service
@Transactional
public class LoanServiceImpl implements LoanService {

    private final Logger log = LoggerFactory.getLogger(LoanServiceImpl.class);

    private final LoanRepository loanRepository;

    public LoanServiceImpl(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    @Override
    public Loan save(Loan loan) {
        log.debug("Request to save Loan : {}", loan);
        return loanRepository.save(loan);
    }

    @Override
    public Optional<Loan> partialUpdate(Loan loan) {
        log.debug("Request to partially update Loan : {}", loan);

        return loanRepository
            .findById(loan.getId())
            .map(
                existingLoan -> {
                    if (loan.getReturn_date() != null) {
                        existingLoan.setReturn_date(loan.getReturn_date());
                    }
                    if (loan.getReturned() != null) {
                        existingLoan.setReturned(loan.getReturned());
                    }

                    return existingLoan;
                }
            )
            .map(loanRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Loan> findAll() {
        log.debug("Request to get all Loans");
        return loanRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Loan> findOne(Long id) {
        log.debug("Request to get Loan : {}", id);
        return loanRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Loan : {}", id);
        loanRepository.deleteById(id);
    }
}
