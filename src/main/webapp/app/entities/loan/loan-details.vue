<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="loan">
        <h2 class="jh-entity-heading" data-cy="loanDetailsHeading"><span>Loan</span> {{ loan.id }}</h2>
        <dl class="row jh-entity-details">
          <dt>
            <span>Return Date</span>
          </dt>
          <dd>
            <span>{{ loan.return_date | formatDate }}</span>
          </dd>
          <dt>
            <span>Returned</span>
          </dt>
          <dd>
            <span>{{ loan.returned }}</span>
          </dd>
          <dt>
            <span>Book</span>
          </dt>
          <dd>
            <div v-if="loan.book">
              <router-link :to="{ name: 'BookView', params: { bookId: loan.book.id } }">{{ loan.book.id }}</router-link>
            </div>
          </dd>
          <dt>
            <span>User</span>
          </dt>
          <dd>
            {{ loan.user ? loan.user.login : '' }}
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span> Back</span>
        </button>
        <router-link v-if="loan.id" :to="{ name: 'LoanEdit', params: { loanId: loan.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span> Edit</span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./loan-details.component.ts"></script>
