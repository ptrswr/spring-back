import { mixins } from 'vue-class-component';

import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { ILoan } from '@/shared/model/loan.model';

import LoanService from './loan.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class Loan extends Vue {
  @Inject('loanService') private loanService: () => LoanService;
  private removeId: number = null;

  public loans: ILoan[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllLoans();
  }

  public clear(): void {
    this.retrieveAllLoans();
  }

  public retrieveAllLoans(): void {
    this.isFetching = true;

    this.loanService()
      .retrieve()
      .then(
        res => {
          this.loans = res.data;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
        }
      );
  }

  public handleSyncList(): void {
    this.clear();
  }

  public prepareRemove(instance: ILoan): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeLoan(): void {
    this.loanService()
      .delete(this.removeId)
      .then(() => {
        const message = 'A Loan is deleted with identifier ' + this.removeId;
        this.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Info',
          variant: 'danger',
          solid: true,
          autoHideDelay: 5000,
        });
        this.removeId = null;
        this.retrieveAllLoans();
        this.closeDialog();
      });
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
