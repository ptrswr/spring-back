<template>
  <div>
    <h2 id="page-heading" data-cy="LoanHeading">
      <span id="loan-heading">Loans</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon> <span>Refresh List</span>
        </button>
        <router-link :to="{ name: 'LoanCreate' }" custom v-slot="{ navigate }">
          <button @click="navigate" id="jh-create-entity" data-cy="entityCreateButton" class="btn btn-primary jh-create-entity create-loan">
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span> Create a new Loan </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && loans && loans.length === 0">
      <span>No loans found</span>
    </div>
    <div class="table-responsive" v-if="loans && loans.length > 0">
      <table class="table table-striped" aria-describedby="loans">
        <thead>
          <tr>
            <th scope="row"><span>ID</span></th>
            <th scope="row"><span>Return Date</span></th>
            <th scope="row"><span>Returned</span></th>
            <th scope="row"><span>Book</span></th>
            <th scope="row"><span>User</span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="loan in loans" :key="loan.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'LoanView', params: { loanId: loan.id } }">{{ loan.id }}</router-link>
            </td>
            <td>{{ loan.return_date | formatDate }}</td>
            <td>{{ loan.returned }}</td>
            <td>
              <div v-if="loan.book">
                <router-link :to="{ name: 'BookView', params: { bookId: loan.book.id } }">{{ loan.book.id }}</router-link>
              </div>
            </td>
            <td>
              {{ loan.user ? loan.user.login : '' }}
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'LoanView', params: { loanId: loan.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline">View</span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'LoanEdit', params: { loanId: loan.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(loan)"
                  variant="danger"
                  class="btn btn-sm"
                  data-cy="entityDeleteButton"
                  v-b-modal.removeEntity
                >
                  <font-awesome-icon icon="times"></font-awesome-icon>
                  <span class="d-none d-md-inline">Delete</span>
                </b-button>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <b-modal ref="removeEntity" id="removeEntity">
      <span slot="modal-title"
        ><span id="springLabWebApp.loan.delete.question" data-cy="loanDeleteDialogHeading">Confirm delete operation</span></span
      >
      <div class="modal-body">
        <p id="jhi-delete-loan-heading">Are you sure you want to delete this Loan?</p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-loan"
          data-cy="entityConfirmDeleteButton"
          v-on:click="removeLoan()"
        >
          Delete
        </button>
      </div>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./loan.component.ts"></script>
