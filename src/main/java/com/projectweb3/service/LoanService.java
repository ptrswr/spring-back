package com.projectweb3.service;

import com.projectweb3.domain.Loan;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Loan}.
 */
public interface LoanService {
    /**
     * Save a loan.
     *
     * @param loan the entity to save.
     * @return the persisted entity.
     */
    Loan save(Loan loan);

    /**
     * Partially updates a loan.
     *
     * @param loan the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Loan> partialUpdate(Loan loan);

    /**
     * Get all the loans.
     *
     * @return the list of entities.
     */
    List<Loan> findAll();

    /**
     * Get the "id" loan.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Loan> findOne(Long id);

    /**
     * Delete the "id" loan.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
