/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';

import * as config from '@/shared/config/config';
import LoanComponent from '@/entities/loan/loan.vue';
import LoanClass from '@/entities/loan/loan.component';
import LoanService from '@/entities/loan/loan.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('b-badge', {});
localVue.directive('b-modal', {});
localVue.component('b-button', {});
localVue.component('router-link', {});

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  describe('Loan Management Component', () => {
    let wrapper: Wrapper<LoanClass>;
    let comp: LoanClass;
    let loanServiceStub: SinonStubbedInstance<LoanService>;

    beforeEach(() => {
      loanServiceStub = sinon.createStubInstance<LoanService>(LoanService);
      loanServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<LoanClass>(LoanComponent, {
        store,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          loanService: () => loanServiceStub,
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      loanServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllLoans();
      await comp.$nextTick();

      // THEN
      expect(loanServiceStub.retrieve.called).toBeTruthy();
      expect(comp.loans[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      loanServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      comp.removeLoan();
      await comp.$nextTick();

      // THEN
      expect(loanServiceStub.delete.called).toBeTruthy();
      expect(loanServiceStub.retrieve.callCount).toEqual(1);
    });
  });
});
