import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterLink } from '@angular/router';
import { ReactiveFormsModule, FormControl, FormGroup, Validators } from '@angular/forms';
import { InputRowComponent } from 'app/common/input-row/input-row.component';
import { UserInfoService } from 'app/user-info/user-info.service';
import { UserInfoDTO } from 'app/user-info/user-info.model';
import { ErrorHandler } from 'app/common/error-handler.injectable';


@Component({
  selector: 'app-user-info-add',
  standalone: true,
  imports: [CommonModule, RouterLink, ReactiveFormsModule, InputRowComponent],
  templateUrl: './user-info-add.component.html'
})
export class UserInfoAddComponent {

  userInfoService = inject(UserInfoService);
  router = inject(Router);
  errorHandler = inject(ErrorHandler);

  addForm = new FormGroup({
    email: new FormControl(null, [Validators.maxLength(255)]),
    name: new FormControl(null, [Validators.maxLength(255)]),
    mobile: new FormControl(null, [Validators.maxLength(255)]),
    address: new FormControl(null, [Validators.maxLength(255)])
  }, { updateOn: 'submit' });

  getMessage(key: string, details?: any) {
    const messages: Record<string, string> = {
      created: $localize`:@@userInfo.create.success:User Info was created successfully.`
    };
    return messages[key];
  }

  handleSubmit() {
    window.scrollTo(0, 0);
    this.addForm.markAllAsTouched();
    if (!this.addForm.valid) {
      return;
    }
    const data = new UserInfoDTO(this.addForm.value);
    this.userInfoService.createUserInfo(data)
        .subscribe({
          next: () => this.router.navigate(['/userInfos'], {
            state: {
              msgSuccess: this.getMessage('created')
            }
          }),
          error: (error) => this.errorHandler.handleServerError(error.error, this.addForm, this.getMessage)
        });
  }

}
