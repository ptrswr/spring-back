package com.projectweb3.web.rest;

import com.projectweb3.domain.Book;
import com.projectweb3.domain.Loan;
import com.projectweb3.domain.User;
import com.projectweb3.repository.LoanRepository;
import com.projectweb3.repository.UserRepository;
import com.projectweb3.service.BookService;
import com.projectweb3.service.LoanService;
import com.projectweb3.service.UserService;
import com.projectweb3.service.dto.LoanDTO;
import com.projectweb3.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import liquibase.pro.packaged.L;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.projectweb3.domain.Loan}.
 */
@RestController
@RequestMapping("/api")
public class LoanResource {

    private final Logger log = LoggerFactory.getLogger(LoanResource.class);

    private static final String ENTITY_NAME = "loan";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final LoanService loanService;

    private final LoanRepository loanRepository;
    private final UserRepository userRepository;
    private final BookService bookService;

    public LoanResource(LoanService loanService, LoanRepository loanRepository, UserRepository userRepository, BookService bookService) {
        this.loanService = loanService;
        this.loanRepository = loanRepository;
        this.userRepository = userRepository;
        this.bookService = bookService;
    }

    @PostMapping("loans/{bookId}")
    public ResponseEntity<Loan> createLoanWithLoggedUser(@PathVariable("bookId") Long bookId) throws URISyntaxException {
        Loan loan = new Loan();
        User user = userRepository.findCurrentUser();
        loan.setUser(user);
        Book book = bookService.findOne(bookId).get();
        loan.setBook(book);
        loan.setReturned(false);
        loan.setReturn_date(Instant.now().plus(7, ChronoUnit.DAYS));
        Loan result = loanService.save(loan);
        return ResponseEntity
            .created(new URI("/api/loans/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /loans/:id} : Updates an existing loan.
     *
     * @param id the id of the loan to save.
     * @param loan the loan to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated loan,
     * or with status {@code 400 (Bad Request)} if the loan is not valid,
     * or with status {@code 500 (Internal Server Error)} if the loan couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/loans/{id}")
    public ResponseEntity<Loan> updateLoan(@PathVariable(value = "id", required = false) final Long id, @RequestBody Loan loan)
        throws URISyntaxException {
        log.debug("REST request to update Loan : {}, {}", id, loan);
        if (loan.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, loan.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!loanRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Loan result = loanService.save(loan);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, loan.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /loans/:id} : Partial updates given fields of an existing loan, field will ignore if it is null
     *
     * @param id the id of the loan to save.
     * @param loan the loan to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated loan,
     * or with status {@code 400 (Bad Request)} if the loan is not valid,
     * or with status {@code 404 (Not Found)} if the loan is not found,
     * or with status {@code 500 (Internal Server Error)} if the loan couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/loans/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<Loan> partialUpdateLoan(@PathVariable(value = "id", required = false) final Long id, @RequestBody Loan loan)
        throws URISyntaxException {
        log.debug("REST request to partial update Loan partially : {}, {}", id, loan);
        if (loan.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, loan.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!loanRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Loan> result = loanService.partialUpdate(loan);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, loan.getId().toString())
        );
    }

    /**
     * {@code GET  /loans} : get all the loans.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of loans in body.
     */
    @GetMapping("/loans")
    public List<Loan> getAllLoans() {
        log.debug("REST request to get all Loans");
        return loanService.findAll();
    }

    /**
     * {@code GET  /loans/:id} : get the "id" loan.
     *
     * @param id the id of the loan to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the loan, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/loans/{id}")
    public ResponseEntity<Loan> getLoan(@PathVariable Long id) {
        log.debug("REST request to get Loan : {}", id);
        Optional<Loan> loan = loanService.findOne(id);
        return ResponseUtil.wrapOrNotFound(loan);
    }

    /**
     * {@code DELETE  /loans/:id} : delete the "id" loan.
     *
     * @param id the id of the loan to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/loans/{id}")
    public ResponseEntity<Void> deleteLoan(@PathVariable Long id) {
        log.debug("REST request to delete Loan : {}", id);
        loanService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
