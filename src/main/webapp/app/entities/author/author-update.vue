<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2 id="springLabWebApp.author.home.createOrEditLabel" data-cy="AuthorCreateUpdateHeading">Create or edit a Author</h2>
        <div>
          <div class="form-group" v-if="author.id">
            <label for="id">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="author.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="author-name">Name</label>
            <input
              type="text"
              class="form-control"
              name="name"
              id="author-name"
              data-cy="name"
              :class="{ valid: !$v.author.name.$invalid, invalid: $v.author.name.$invalid }"
              v-model="$v.author.name.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="author-surname">Surname</label>
            <input
              type="text"
              class="form-control"
              name="surname"
              id="author-surname"
              data-cy="surname"
              :class="{ valid: !$v.author.surname.$invalid, invalid: $v.author.surname.$invalid }"
              v-model="$v.author.surname.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="author-country">Country</label>
            <input
              type="text"
              class="form-control"
              name="country"
              id="author-country"
              data-cy="country"
              :class="{ valid: !$v.author.country.$invalid, invalid: $v.author.country.$invalid }"
              v-model="$v.author.country.$model"
            />
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
            :disabled="$v.author.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span>Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./author-update.component.ts"></script>
