import { Component, Vue, Inject } from 'vue-property-decorator';

import dayjs from 'dayjs';
import { DATE_TIME_LONG_FORMAT } from '@/shared/date/filters';

import BookService from '@/entities/book/book.service';
import { IBook } from '@/shared/model/book.model';

import UserService from '@/admin/user-management/user-management.service';

import { ILoan, Loan } from '@/shared/model/loan.model';
import LoanService from './loan.service';

const validations: any = {
  loan: {
    return_date: {},
    returned: {},
  },
};

@Component({
  validations,
})
export default class LoanUpdate extends Vue {
  @Inject('loanService') private loanService: () => LoanService;
  public loan: ILoan = new Loan();

  @Inject('bookService') private bookService: () => BookService;

  public books: IBook[] = [];

  @Inject('userService') private userService: () => UserService;

  public users: Array<any> = [];
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.loanId) {
        vm.retrieveLoan(to.params.loanId);
      }
      vm.initRelationships();
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
    if (this.loan.id) {
      this.loanService()
        .update(this.loan)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = 'A Loan is updated with identifier ' + param.id;
          return this.$root.$bvToast.toast(message.toString(), {
            toaster: 'b-toaster-top-center',
            title: 'Info',
            variant: 'info',
            solid: true,
            autoHideDelay: 5000,
          });
        });
    } else {
      this.loanService()
        .create(this.loan)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = 'A Loan is created with identifier ' + param.id;
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

  public convertDateTimeFromServer(date: Date): string {
    if (date && dayjs(date).isValid()) {
      return dayjs(date).format(DATE_TIME_LONG_FORMAT);
    }
    return null;
  }

  public updateInstantField(field, event) {
    if (event.target.value) {
      this.loan[field] = dayjs(event.target.value, DATE_TIME_LONG_FORMAT);
    } else {
      this.loan[field] = null;
    }
  }

  public updateZonedDateTimeField(field, event) {
    if (event.target.value) {
      this.loan[field] = dayjs(event.target.value, DATE_TIME_LONG_FORMAT);
    } else {
      this.loan[field] = null;
    }
  }

  public retrieveLoan(loanId): void {
    this.loanService()
      .find(loanId)
      .then(res => {
        res.return_date = new Date(res.return_date);
        this.loan = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.bookService()
      .retrieve()
      .then(res => {
        this.books = res.data;
      });
    this.userService()
      .retrieve()
      .then(res => {
        this.users = res.data;
      });
  }
}
