/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import LoanDetailComponent from '@/entities/loan/loan-details.vue';
import LoanClass from '@/entities/loan/loan-details.component';
import LoanService from '@/entities/loan/loan.service';
import router from '@/router';

const localVue = createLocalVue();
localVue.use(VueRouter);

config.initVueApp(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('Loan Management Detail Component', () => {
    let wrapper: Wrapper<LoanClass>;
    let comp: LoanClass;
    let loanServiceStub: SinonStubbedInstance<LoanService>;

    beforeEach(() => {
      loanServiceStub = sinon.createStubInstance<LoanService>(LoanService);

      wrapper = shallowMount<LoanClass>(LoanDetailComponent, { store, localVue, router, provide: { loanService: () => loanServiceStub } });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundLoan = { id: 123 };
        loanServiceStub.find.resolves(foundLoan);

        // WHEN
        comp.retrieveLoan(123);
        await comp.$nextTick();

        // THEN
        expect(comp.loan).toBe(foundLoan);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundLoan = { id: 123 };
        loanServiceStub.find.resolves(foundLoan);

        // WHEN
        comp.beforeRouteEnter({ params: { loanId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.loan).toBe(foundLoan);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        comp.previousState();
        await comp.$nextTick();

        expect(comp.$router.currentRoute.fullPath).toContain('/');
      });
    });
  });
});
