import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { AppService } from './app.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  constructor(public appService: AppService, public router: Router) {
  }

  canActivate(): boolean {
    if (!this.appService.getUser()) {
      this.router.navigate(['/signin']);
      return false;
    }
    return true;
  }
}
