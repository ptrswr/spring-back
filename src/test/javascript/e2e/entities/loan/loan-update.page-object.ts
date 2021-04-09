import { by, element, ElementFinder } from 'protractor';

import AlertPage from '../../page-objects/alert-page';

export default class LoanUpdatePage extends AlertPage {
  title: ElementFinder = element(by.id('springLabWebApp.loan.home.createOrEditLabel'));
  footer: ElementFinder = element(by.id('footer'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));

  return_dateInput: ElementFinder = element(by.css('input#loan-return_date'));

  returnedInput: ElementFinder = element(by.css('input#loan-returned'));
  bookSelect = element(by.css('select#loan-book'));

  userSelect = element(by.css('select#loan-user'));
}
