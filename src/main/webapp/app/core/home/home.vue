<template>
  <div class="home row justify-content-center">
    <div class="col-md-9 text-center">
      <h1 class="display-3">Welcome to Library</h1>
      <p class="lead">Loan your book today!</p>

      <div>
        <div class="alert alert-success" v-if="authenticated">
          <span v-if="username">You are logged in "{{ username }}"</span>
        </div>

        <div class="alert alert-warning" v-if="!authenticated">
          <span>Please </span>
          <a class="alert-link" v-on:click="openLogin()">sign in</a>
          <span>to get a full Library experience:<br /></span>
        </div>
        <div v-if="authenticated">
          <h1 class="display-4">Available actions</h1>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./home.component.ts"></script>
