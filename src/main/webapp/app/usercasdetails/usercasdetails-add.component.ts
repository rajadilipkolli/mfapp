import { Component, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterLink } from '@angular/router';
import { ReactiveFormsModule, FormControl, FormGroup, Validators } from '@angular/forms';
import { InputRowComponent } from 'app/common/input-row/input-row.component';
import { UserCASDetailsService } from 'app/usercasdetails/usercasdetails.service';
import { UserCASDetailsDTO } from 'app/usercasdetails/usercasdetails.model';
import { ErrorHandler } from 'app/common/error-handler.injectable';


@Component({
  selector: 'app-usercasdetails-add',
  standalone: true,
  imports: [CommonModule, RouterLink, ReactiveFormsModule, InputRowComponent],
  templateUrl: './usercasdetails-add.component.html'
})
export class UserCASDetailsAddComponent implements OnInit {

  userCASDetailsService = inject(UserCASDetailsService);
  router = inject(Router);
  errorHandler = inject(ErrorHandler);

  userInfoValues?: Map<number,string>;

  addForm = new FormGroup({
    casType: new FormControl(null),
    fileType: new FormControl(null),
    userInfo: new FormControl(null, [Validators.required])
  }, { updateOn: 'submit' });

  getMessage(key: string, details?: any) {
    const messages: Record<string, string> = {
      created: $localize`:@@userCASDetails.create.success:UserCAS Details was created successfully.`,
      USER_CASDETAILS_USER_INFO_UNIQUE: $localize`:@@Exists.userCASDetails.userInfo:This User Info is already referenced by another UserCAS Details.`
    };
    return messages[key];
  }

  ngOnInit() {
    this.userCASDetailsService.getUserInfoValues()
        .subscribe({
          next: (data) => this.userInfoValues = data,
          error: (error) => this.errorHandler.handleServerError(error.error)
        });
  }

  handleSubmit() {
    window.scrollTo(0, 0);
    this.addForm.markAllAsTouched();
    if (!this.addForm.valid) {
      return;
    }
    const data = new UserCASDetailsDTO(this.addForm.value);
    this.userCASDetailsService.createUserCASDetails(data)
        .subscribe({
          next: () => this.router.navigate(['/userCASDetails'], {
            state: {
              msgSuccess: this.getMessage('created')
            }
          }),
          error: (error) => this.errorHandler.handleServerError(error.error, this.addForm, this.getMessage)
        });
  }

}
