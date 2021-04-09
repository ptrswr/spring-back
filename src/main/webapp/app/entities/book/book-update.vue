<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2 id="springLabWebApp.book.home.createOrEditLabel" data-cy="BookCreateUpdateHeading">Create or edit a Book</h2>
        <div>
          <div class="form-group" v-if="book.id">
            <label for="id">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="book.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="book-title">Title</label>
            <input
              type="text"
              class="form-control"
              name="title"
              id="book-title"
              data-cy="title"
              :class="{ valid: !$v.book.title.$invalid, invalid: $v.book.title.$invalid }"
              v-model="$v.book.title.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="book-genre">Genre</label>
            <input
              type="text"
              class="form-control"
              name="genre"
              id="book-genre"
              data-cy="genre"
              :class="{ valid: !$v.book.genre.$invalid, invalid: $v.book.genre.$invalid }"
              v-model="$v.book.genre.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="book-pages">Pages</label>
            <input
              type="number"
              class="form-control"
              name="pages"
              id="book-pages"
              data-cy="pages"
              :class="{ valid: !$v.book.pages.$invalid, invalid: $v.book.pages.$invalid }"
              v-model.number="$v.book.pages.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="book-author">Author</label>
            <select class="form-control" id="book-author" data-cy="author" name="author" v-model="book.author">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="book.author && authorOption.id === book.author.id ? book.author : authorOption"
                v-for="authorOption in authors"
                :key="authorOption.id"
              >
                {{ authorOption.id }}
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
            :disabled="$v.book.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span>Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./book-update.component.ts"></script>
