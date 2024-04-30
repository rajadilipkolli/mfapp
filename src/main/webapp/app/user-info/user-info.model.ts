export class UserInfoDTO {

  constructor(data:Partial<UserInfoDTO>) {
    Object.assign(this, data);
  }

  id?: number|null;
  email?: string|null;
  name?: string|null;
  mobile?: string|null;
  address?: string|null;

}
