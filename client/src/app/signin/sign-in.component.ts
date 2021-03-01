import { Component } from '@angular/core';
import { AppService } from '../app.service';
import { Router } from '@angular/router';

class SignInModel {
  username: string;
  password: string;
}

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.scss']
})
export class SignInComponent {
  model = new SignInModel();
  progress = false;

  constructor(
      private readonly appService: AppService,
      private readonly router: Router) {
  }

  onSubmit(): void {
    this.progress = true;
    this.appService.signIn(this.model.username, this.model.password)
        .then(() => {
          this.progress = false;
          this.router.navigate(['/']);
        })
        .catch(() => {
          this.progress = false;
        });
  }

  signUp(): void {
    this.router.navigate(['/signup']);
  }
}
