import { Component, inject, OnDestroy, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NavigationEnd, Router, RouterLink } from '@angular/router';
import { Subscription } from 'rxjs';
import { ErrorHandler } from 'app/common/error-handler.injectable';
import { MfSchemeService } from './mfscheme.service';
import { MfSchemeDTO } from './mfscheme.model';

@Component({
  selector: 'app-mfscheme',
  standalone: true,
  imports: [],
  templateUrl: './mfscheme-list.component.html'
})
export class MfschemeListComponent implements OnInit, OnDestroy {

  mfSchemeService = inject(MfSchemeService);
  errorHandler = inject(ErrorHandler);
  router = inject(Router);
  mfschemes?: MfSchemeDTO[];
  navigationSubscription?: Subscription;

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
    this.mfSchemeService.getAllMfSchemes()
      .subscribe({
        next: (data) => this.mfschemes = data,
        error: (error) => this.errorHandler.handleServerError(error.error)
      });
  }

  loadMfSchemeByCode(schemeCode: number) {
    this.mfSchemeService.getMfSchemeInfo(schemeCode)
      .subscribe({
        next: (data) => data,
        error: (error) => this.errorHandler.handleServerError(error.error)
      });
  }

}
