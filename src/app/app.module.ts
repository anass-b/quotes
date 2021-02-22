import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MainModule } from './main/main.module';
import { SigninModule } from './signin/signin.module';
import { SignupModule } from './signup/signup.module';
import { NotFoundComponent } from './not-found/not-found.component';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {path: '404', component: NotFoundComponent},
  {path: '**', redirectTo: '/404'}
];

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(routes),
    BrowserAnimationsModule,
    MainModule,
    SigninModule,
    SignupModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
