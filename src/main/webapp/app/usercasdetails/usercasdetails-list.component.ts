import { Component, inject, OnDestroy, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NavigationEnd, Router, RouterLink } from '@angular/router';
import { Subscription } from 'rxjs';
import { ErrorHandler } from 'app/common/error-handler.injectable';
import { UserCASDetailsService } from 'app/usercasdetails/usercasdetails.service';
import { UserCASDetailsDTO } from 'app/usercasdetails/usercasdetails.model';


@Component({
  selector: 'app-usercasdetails-list',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './usercasdetails-list.component.html'})
export class UserCASDetailsListComponent implements OnInit, OnDestroy {

  userCASDetailsService = inject(UserCASDetailsService);
  errorHandler = inject(ErrorHandler);
  router = inject(Router);
  userCASDetailses?: UserCASDetailsDTO[];
  navigationSubscription?: Subscription;

  getMessage(key: string, details?: any) {
    const messages: Record<string, string> = {
      confirm: $localize`:@@delete.confirm:Do you really want to delete this element? This cannot be undone.`,
      deleted: $localize`:@@userCASDetails.delete.success:UserCAS Details was removed successfully.`,
      'userCASDetails.userFolioDetails.userCASDetails.referenced': $localize`:@@userCASDetails.userFolioDetails.userCASDetails.referenced:This entity is still referenced by User Folio Details ${details?.id} via field UserCAS Details.`
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
    this.userCASDetailsService.getAllUserCASDetailses()
        .subscribe({
          next: (data) => this.userCASDetailses = data,
          error: (error) => this.errorHandler.handleServerError(error.error)
        });
  }

  confirmDelete(id: number) {
    if (confirm(this.getMessage('confirm'))) {
      this.userCASDetailsService.deleteUserCASDetails(id)
          .subscribe({
            next: () => this.router.navigate(['/userCASDetails'], {
              state: {
                msgInfo: this.getMessage('deleted')
              }
            }),
            error: (error) => {
              if (error.error?.code === 'REFERENCED') {
                const messageParts = error.error.message.split(',');
                this.router.navigate(['/userCASDetails'], {
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
