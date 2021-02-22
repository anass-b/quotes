import { Component, OnInit } from '@angular/core';
import { AppService } from '../app.service';
import { Router } from '@angular/router';

class SignupModel {
  fullName: string;
  username: string;
  password: string;
}

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss']
})
export class SignupComponent {
  model = new SignupModel();
  progress = false;

  constructor(
    private readonly appService: AppService,
    private readonly router: Router) { }

  onSubmit() {
    this.progress = true;
    this.appService.signup({
      fullName: this.model.fullName,
      username: this.model.username,
      password: this.model.password
    })
      .then(() => {
        this.progress = false;
        this.router.navigate(['/signin']);
      })
      .catch(() => {
        this.progress = false;
      });
  }

  signin() {
    this.router.navigate(['/signin']);
  }
}
