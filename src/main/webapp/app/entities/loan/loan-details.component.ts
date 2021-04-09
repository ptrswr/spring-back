import { Component, Vue, Inject } from 'vue-property-decorator';

import { ILoan } from '@/shared/model/loan.model';
import LoanService from './loan.service';

@Component
export default class LoanDetails extends Vue {
  @Inject('loanService') private loanService: () => LoanService;
  public loan: ILoan = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.loanId) {
        vm.retrieveLoan(to.params.loanId);
      }
    });
  }

  public retrieveLoan(loanId) {
    this.loanService()
      .find(loanId)
      .then(res => {
        this.loan = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
