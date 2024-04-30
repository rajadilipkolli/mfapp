import { Component, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { ReactiveFormsModule, FormControl, FormGroup, Validators } from '@angular/forms';
import { InputRowComponent } from 'app/common/input-row/input-row.component';
import { UserCASDetailsService } from 'app/user-c-a-s-details/user-c-a-s-details.service';
import { UserCASDetailsDTO } from 'app/user-c-a-s-details/user-c-a-s-details.model';
import { ErrorHandler } from 'app/common/error-handler.injectable';
import { updateForm } from 'app/common/utils';


@Component({
  selector: 'app-user-c-a-s-details-edit',
  standalone: true,
  imports: [CommonModule, RouterLink, ReactiveFormsModule, InputRowComponent],
  templateUrl: './user-c-a-s-details-edit.component.html'
})
export class UserCASDetailsEditComponent implements OnInit {

  userCASDetailsService = inject(UserCASDetailsService);
  route = inject(ActivatedRoute);
  router = inject(Router);
  errorHandler = inject(ErrorHandler);

  userInfoValues?: Map<number,string>;
  currentId?: number;

  editForm = new FormGroup({
    id: new FormControl({ value: null, disabled: true }),
    casType: new FormControl(null),
    fileType: new FormControl(null),
    userInfo: new FormControl(null, [Validators.required])
  }, { updateOn: 'submit' });

  getMessage(key: string, details?: any) {
    const messages: Record<string, string> = {
      updated: $localize`:@@userCASDetails.update.success:UserCAS Details was updated successfully.`,
      USER_CASDETAILS_USER_INFO_UNIQUE: $localize`:@@Exists.userCASDetails.userInfo:This User Info is already referenced by another UserCAS Details.`
    };
    return messages[key];
  }

  ngOnInit() {
    this.currentId = +this.route.snapshot.params['id'];
    this.userCASDetailsService.getUserInfoValues()
        .subscribe({
          next: (data) => this.userInfoValues = data,
          error: (error) => this.errorHandler.handleServerError(error.error)
        });
    this.userCASDetailsService.getUserCASDetails(this.currentId!)
        .subscribe({
          next: (data) => updateForm(this.editForm, data),
          error: (error) => this.errorHandler.handleServerError(error.error)
        });
  }

  handleSubmit() {
    window.scrollTo(0, 0);
    this.editForm.markAllAsTouched();
    if (!this.editForm.valid) {
      return;
    }
    const data = new UserCASDetailsDTO(this.editForm.value);
    this.userCASDetailsService.updateUserCASDetails(this.currentId!, data)
        .subscribe({
          next: () => this.router.navigate(['/userCASDetailss'], {
            state: {
              msgSuccess: this.getMessage('updated')
            }
          }),
          error: (error) => this.errorHandler.handleServerError(error.error, this.editForm, this.getMessage)
        });
  }

}
