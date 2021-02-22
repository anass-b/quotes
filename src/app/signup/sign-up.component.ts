import { Component } from '@angular/core';
import { AppService } from '../app.service';
import { Router } from '@angular/router';

class SignUpModel {
  fullName: string;
  username: string;
  password: string;
}

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.scss']
})
export class SignUpComponent {
  model = new SignUpModel();
  progress = false;

  constructor(
      private readonly appService: AppService,
      private readonly router: Router) {
  }

  onSubmit(): void {
    this.progress = true;
    this.appService.signUp({
      fullName: this.model.fullName,
      username: this.model.username,
      password: this.model.password
    }).then(() => {
      this.progress = false;
      this.router.navigate(['/signin']);
    }).catch(() => {
      this.progress = false;
    });
  }

  signIn(): void {
    this.router.navigate(['/signin']);
  }
}
