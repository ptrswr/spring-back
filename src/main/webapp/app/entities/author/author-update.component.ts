import { Component, Vue, Inject } from 'vue-property-decorator';

import { IAuthor, Author } from '@/shared/model/author.model';
import AuthorService from './author.service';

const validations: any = {
  author: {
    name: {},
    surname: {},
    country: {},
  },
};

@Component({
  validations,
})
export default class AuthorUpdate extends Vue {
  @Inject('authorService') private authorService: () => AuthorService;
  public author: IAuthor = new Author();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.authorId) {
        vm.retrieveAuthor(to.params.authorId);
      }
    });
  }

  created(): void {
    this.currentLanguage = this.$store.getters.currentLanguage;
    this.$store.watch(
      () => this.$store.getters.currentLanguage,
      () => {
        this.currentLanguage = this.$store.getters.currentLanguage;
      }
    );
  }

  public save(): void {
    this.isSaving = true;
    if (this.author.id) {
      this.authorService()
        .update(this.author)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = 'A Author is updated with identifier ' + param.id;
          return this.$root.$bvToast.toast(message.toString(), {
            toaster: 'b-toaster-top-center',
            title: 'Info',
            variant: 'info',
            solid: true,
            autoHideDelay: 5000,
          });
        });
    } else {
      this.authorService()
        .create(this.author)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = 'A Author is created with identifier ' + param.id;
          this.$root.$bvToast.toast(message.toString(), {
            toaster: 'b-toaster-top-center',
            title: 'Success',
            variant: 'success',
            solid: true,
            autoHideDelay: 5000,
          });
        });
    }
  }

  public retrieveAuthor(authorId): void {
    this.authorService()
      .find(authorId)
      .then(res => {
        this.author = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {}
}
