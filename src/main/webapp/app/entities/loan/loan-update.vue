<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2 id="springLabWebApp.loan.home.createOrEditLabel" data-cy="LoanCreateUpdateHeading">Create or edit a Loan</h2>
        <div>
          <div class="form-group" v-if="loan.id">
            <label for="id">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="loan.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="loan-return_date">Return Date</label>
            <div class="d-flex">
              <input
                id="loan-return_date"
                data-cy="return_date"
                type="datetime-local"
                class="form-control"
                name="return_date"
                :class="{ valid: !$v.loan.return_date.$invalid, invalid: $v.loan.return_date.$invalid }"
                :value="convertDateTimeFromServer($v.loan.return_date.$model)"
                @change="updateInstantField('return_date', $event)"
              />
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="loan-returned">Returned</label>
            <input
              type="checkbox"
              class="form-check"
              name="returned"
              id="loan-returned"
              data-cy="returned"
              :class="{ valid: !$v.loan.returned.$invalid, invalid: $v.loan.returned.$invalid }"
              v-model="$v.loan.returned.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="loan-book">Book</label>
            <select class="form-control" id="loan-book" data-cy="book" name="book" v-model="loan.book">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="loan.book && bookOption.id === loan.book.id ? loan.book : bookOption"
                v-for="bookOption in books"
                :key="bookOption.id"
              >
                {{ bookOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="loan-user">User</label>
            <select class="form-control" id="loan-user" data-cy="user" name="user" v-model="loan.user">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="loan.user && userOption.id === loan.user.id ? loan.user : userOption"
                v-for="userOption in users"
                :key="userOption.id"
              >
                {{ userOption.login }}
              </option>
            </select>
          </div>
        </div>
        <div>
          <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
            <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span>Cancel</span>
          </button>
          <button
            type="submit"
            id="save-entity"
            data-cy="entityCreateSaveButton"
            :disabled="$v.loan.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span>Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./loan-update.component.ts"></script>
