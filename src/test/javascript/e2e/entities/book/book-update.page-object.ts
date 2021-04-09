import { by, element, ElementFinder } from 'protractor';

import AlertPage from '../../page-objects/alert-page';

export default class BookUpdatePage extends AlertPage {
  title: ElementFinder = element(by.id('springLabWebApp.book.home.createOrEditLabel'));
  footer: ElementFinder = element(by.id('footer'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));

  titleInput: ElementFinder = element(by.css('input#book-title'));

  genreInput: ElementFinder = element(by.css('input#book-genre'));

  pagesInput: ElementFinder = element(by.css('input#book-pages'));

  authorSelect = element(by.css('select#book-author'));
}
