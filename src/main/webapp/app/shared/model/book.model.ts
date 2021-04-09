import { IAuthor } from '@/shared/model/author.model';

export interface IBook {
  id?: number;
  title?: string | null;
  genre?: string | null;
  pages?: number | null;
  author?: IAuthor | null;
}

export class Book implements IBook {
  constructor(
    public id?: number,
    public title?: string | null,
    public genre?: string | null,
    public pages?: number | null,
    public author?: IAuthor | null
  ) {}
}
