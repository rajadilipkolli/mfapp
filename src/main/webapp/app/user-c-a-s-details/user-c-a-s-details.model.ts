export class UserCASDetailsDTO {

  constructor(data:Partial<UserCASDetailsDTO>) {
    Object.assign(this, data);
  }

  id?: number|null;
  casType?: string|null;
  fileType?: string|null;
  userInfo?: number|null;

}
