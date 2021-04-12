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
          <div class="container mt-5">
            <div class="row">
              <div class="col mt-2">
                <div class="card" style="width: 18rem">
                  <div class="card-body">
                    <h5 class="card-title">Authors</h5>
                    <p class="card-text">Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
                    <router-link to="/author">
                      <button type="button" class="btn btn-primary">
                        <div class="mr-1">
                          <font-awesome-icon icon="user"></font-awesome-icon>
                        </div>
                        Browse authors
                      </button>
                    </router-link>
                  </div>
                </div>
              </div>
              <div class="col mt-2">
                <div class="card" style="width: 18rem">
                  <div class="card-body">
                    <h5 class="card-title">Books</h5>
                    <p class="card-text">Nulla varius blandit neque sed euismod. Donec quis tortor.</p>
                    <router-link to="/book">
                      <button type="button" class="btn btn-primary">
                        <div class="mr-1">
                          <font-awesome-icon icon="book"></font-awesome-icon>
                        </div>
                        Browse books
                      </button>
                    </router-link>
                  </div>
                </div>
              </div>
              <div class="col mt-2">
                <div class="card" style="width: 18rem">
                  <div class="card-body">
                    <h5 class="card-title">Loans</h5>
                    <p class="card-text">Etiam accumsan lectus risus, non elementum augue vehicula eu.</p>
                    <router-link to="/loan">
                      <button type="button" class="btn btn-primary">
                        <div class="mr-1">
                          <font-awesome-icon icon="book"></font-awesome-icon>
                        </div>
                        Loan a book
                      </button>
                    </router-link>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./home.component.ts"></script>

<style scoped></style>
