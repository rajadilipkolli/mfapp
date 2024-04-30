import { Component, inject, OnDestroy, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NavigationEnd, Router, RouterLink } from '@angular/router';
import { Subscription } from 'rxjs';
import { ErrorHandler } from 'app/common/error-handler.injectable';
import { UserInfoService } from 'app/user-info/user-info.service';
import { UserInfoDTO } from 'app/user-info/user-info.model';


@Component({
  selector: 'app-user-info-list',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './user-info-list.component.html'})
export class UserInfoListComponent implements OnInit, OnDestroy {

  userInfoService = inject(UserInfoService);
  errorHandler = inject(ErrorHandler);
  router = inject(Router);
  userInfoes?: UserInfoDTO[];
  navigationSubscription?: Subscription;

  getMessage(key: string, details?: any) {
    const messages: Record<string, string> = {
      confirm: $localize`:@@delete.confirm:Do you really want to delete this element? This cannot be undone.`,
      deleted: $localize`:@@userInfo.delete.success:User Info was removed successfully.`,
      'userInfo.userCASDetails.userInfo.referenced': $localize`:@@userInfo.userCASDetails.userInfo.referenced:This entity is still referenced by UserCAS Details ${details?.id} via field User Info.`
    };
    return messages[key];
  }

  ngOnInit() {
    this.loadData();
    this.navigationSubscription = this.router.events.subscribe((event) => {
      if (event instanceof NavigationEnd) {
        this.loadData();
      }
    });
  }

  ngOnDestroy() {
    this.navigationSubscription!.unsubscribe();
  }
  
  loadData() {
    this.userInfoService.getAllUserInfoes()
        .subscribe({
          next: (data) => this.userInfoes = data,
          error: (error) => this.errorHandler.handleServerError(error.error)
        });
  }

  confirmDelete(id: number) {
    if (confirm(this.getMessage('confirm'))) {
      this.userInfoService.deleteUserInfo(id)
          .subscribe({
            next: () => this.router.navigate(['/userInfos'], {
              state: {
                msgInfo: this.getMessage('deleted')
              }
            }),
            error: (error) => {
              if (error.error?.code === 'REFERENCED') {
                const messageParts = error.error.message.split(',');
                this.router.navigate(['/userInfos'], {
                  state: {
                    msgError: this.getMessage(messageParts[0], { id: messageParts[1] })
                  }
                });
                return;
              }
              this.errorHandler.handleServerError(error.error)
            }
          });
    }
  }

}
