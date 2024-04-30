import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'environments/environment';
import { UserInfoDTO } from 'app/user-info/user-info.model';


@Injectable({
  providedIn: 'root',
})
export class UserInfoService {

  http = inject(HttpClient);
  resourcePath = environment.apiPath + '/api/userInfos';

  getAllUserInfoes() {
    return this.http.get<UserInfoDTO[]>(this.resourcePath);
  }

  getUserInfo(id: number) {
    return this.http.get<UserInfoDTO>(this.resourcePath + '/' + id);
  }

  createUserInfo(userInfoDTO: UserInfoDTO) {
    return this.http.post<number>(this.resourcePath, userInfoDTO);
  }

  updateUserInfo(id: number, userInfoDTO: UserInfoDTO) {
    return this.http.put<number>(this.resourcePath + '/' + id, userInfoDTO);
  }

  deleteUserInfo(id: number) {
    return this.http.delete(this.resourcePath + '/' + id);
  }

}
