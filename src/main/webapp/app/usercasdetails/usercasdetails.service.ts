import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'environments/environment';
import { UserCASDetailsDTO } from 'app/usercasdetails/usercasdetails.model';
import { map } from 'rxjs';
import { transformRecordToMap } from 'app/common/utils';


@Injectable({
  providedIn: 'root',
})
export class UserCASDetailsService {

  http = inject(HttpClient);
  resourcePath = environment.apiPath + '/api/userCASDetails';

  getAllUserCASDetailses() {
    return this.http.get<UserCASDetailsDTO[]>(this.resourcePath);
  }

  getUserCASDetails(id: number) {
    return this.http.get<UserCASDetailsDTO>(this.resourcePath + '/' + id);
  }

  createUserCASDetails(userCASDetailsDTO: UserCASDetailsDTO) {
    return this.http.post<number>(this.resourcePath, userCASDetailsDTO);
  }

  updateUserCASDetails(id: number, userCASDetailsDTO: UserCASDetailsDTO) {
    return this.http.put<number>(this.resourcePath + '/' + id, userCASDetailsDTO);
  }

  deleteUserCASDetails(id: number) {
    return this.http.delete(this.resourcePath + '/' + id);
  }

  getUserInfoValues() {
    return this.http.get<Record<string,number>>(this.resourcePath + '/userInfoValues')
        .pipe(map(transformRecordToMap));
  }

}
