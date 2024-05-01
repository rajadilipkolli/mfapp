import { Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { UserInfoListComponent } from './userinfo/userinfo-list.component';
import { UserInfoAddComponent } from './userinfo/userinfo-add.component';
import { UserInfoEditComponent } from './userinfo/userinfo-edit.component';
import { ErrorComponent } from './error/error.component';
import { MfschemeListComponent } from './mfscheme/mfscheme-list.component';


export const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
    title: $localize`:@@home.index.headline:Welcome to your new app!`
  },
  {
    path: 'mfschemes',
    component: MfschemeListComponent,
    title: $localize`@@mfschemes.list.headline:MfSchemes`
  },
  {
    path: 'mfschemes/load/:id',
    component: MfschemeListComponent,
    title: $localize`:@@mfschemes.load.headline:MfScheme Info`
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
