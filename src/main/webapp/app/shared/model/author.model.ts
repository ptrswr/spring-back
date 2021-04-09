export interface IAuthor {
  id?: number;
  name?: string | null;
  surname?: string | null;
  country?: string | null;
}

export class Author implements IAuthor {
  constructor(public id?: number, public name?: string | null, public surname?: string | null, public country?: string | null) {}
}
