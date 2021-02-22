import { Component } from '@angular/core';
import { AppService } from '../app.service';
import { Router } from '@angular/router';

class SigninModel {
  username: string;
  password: string;
}

@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.scss']
})
export class SigninComponent {
  model = new SigninModel();
  progress = false;

  constructor(
    private readonly appService: AppService,
    private readonly router: Router) { }

  onSubmit() {
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

  signup() {
    this.router.navigate(['/signup']);
  }
}
