import { IBook } from '@/shared/model/book.model';
import { IUser } from '@/shared/model/user.model';

export interface ILoan {
  id?: number;
  return_date?: Date | null;
  returned?: boolean | null;
  book?: IBook | null;
  user?: IUser | null;
}

export class Loan implements ILoan {
  constructor(
    public id?: number,
    public return_date?: Date | null,
    public returned?: boolean | null,
    public book?: IBook | null,
    public user?: IUser | null
  ) {
    this.returned = this.returned ?? false;
  }
}
