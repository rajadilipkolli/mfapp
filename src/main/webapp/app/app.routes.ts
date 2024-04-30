import { Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { UserCASDetailsListComponent } from './usercasdetails/usercasdetails-list.component';
import { UserCASDetailsAddComponent } from './usercasdetails/usercasdetails-add.component';
import { UserCASDetailsEditComponent } from './usercasdetails/usercasdetails-edit.component';
import { UserInfoListComponent } from './userinfo/userinfo-list.component';
import { UserInfoAddComponent } from './userinfo/userinfo-add.component';
import { UserInfoEditComponent } from './userinfo/userinfo-edit.component';
import { ErrorComponent } from './error/error.component';


export const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
    title: $localize`:@@home.index.headline:Welcome to your new app!`
  },
  {
    path: 'userCASDetailss',
    component: UserCASDetailsListComponent,
    title: $localize`:@@userCASDetails.list.headline:UserCAS Detailses`
  },
  {
    path: 'userCASDetailss/add',
    component: UserCASDetailsAddComponent,
    title: $localize`:@@userCASDetails.add.headline:Add UserCAS Details`
  },
  {
    path: 'userCASDetailss/edit/:id',
    component: UserCASDetailsEditComponent,
    title: $localize`:@@userCASDetails.edit.headline:Edit UserCAS Details`
  },
  {
    path: 'userInfos',
    component: UserInfoListComponent,
    title: $localize`:@@userInfo.list.headline:User Infoes`
  },
  {
    path: 'userInfos/add',
    component: UserInfoAddComponent,
    title: $localize`:@@userInfo.add.headline:Add User Info`
  },
  {
    path: 'userInfos/edit/:id',
    component: UserInfoEditComponent,
    title: $localize`:@@userInfo.edit.headline:Edit User Info`
  },
  {
    path: 'error',
    component: ErrorComponent,
    title: $localize`:@@error.headline:Error`
  },
  {
    path: '**',
    component: ErrorComponent,
    title: $localize`:@@notFound.headline:Page not found`
  }
];
