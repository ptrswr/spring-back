<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="book">
        <h2 class="jh-entity-heading" data-cy="bookDetailsHeading"><span>Book</span> {{ book.id }}</h2>
        <dl class="row jh-entity-details">
          <dt>
            <span>Title</span>
          </dt>
          <dd>
            <span>{{ book.title }}</span>
          </dd>
          <dt>
            <span>Genre</span>
          </dt>
          <dd>
            <span>{{ book.genre }}</span>
          </dd>
          <dt>
            <span>Pages</span>
          </dt>
          <dd>
            <span>{{ book.pages }}</span>
          </dd>
          <dt>
            <span>Author</span>
          </dt>
          <dd>
            <div v-if="book.author">
              <router-link :to="{ name: 'AuthorView', params: { authorId: book.author.id } }">{{
                book.author.name + ' ' + book.author.surname
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span> Back</span>
        </button>
        <router-link v-if="book.id" :to="{ name: 'BookEdit', params: { bookId: book.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span> Edit</span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./book-details.component.ts"></script>
