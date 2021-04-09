import { by, element, ElementFinder } from 'protractor';

import AlertPage from '../../page-objects/alert-page';

export default class AuthorUpdatePage extends AlertPage {
  title: ElementFinder = element(by.id('springLabWebApp.author.home.createOrEditLabel'));
  footer: ElementFinder = element(by.id('footer'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));

  nameInput: ElementFinder = element(by.css('input#author-name'));

  surnameInput: ElementFinder = element(by.css('input#author-surname'));

  countryInput: ElementFinder = element(by.css('input#author-country'));
}
