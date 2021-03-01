import { Component, OnDestroy, OnInit } from '@angular/core';
import { Quote } from '../dtos/Quote';
import { Subscription } from 'rxjs';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { AddQuoteDialogComponent } from '../add-quote-dialog/add-quote-dialog.component';
import { AppService } from '../app.service';
import { User } from '../User';
import { Router } from '@angular/router';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.scss']
})
export class MainComponent implements OnInit, OnDestroy {
  top10: Quote[];
  flop10: Quote[];
  latest: Quote;
  user: User;
  private top10Subscription: Subscription;
  private flop10Subscription: Subscription;
  private latestSubscription: Subscription;
  private userSubscription: Subscription;
  private addQuoteDialogRef: MatDialogRef<AddQuoteDialogComponent>;

  constructor(
      private readonly appService: AppService,
      private readonly dialog: MatDialog,
      private readonly router: Router) {
  }

  ngOnInit(): void {
    this.top10Subscription = this.appService.getTop10Observable()
        .subscribe(top10 => this.top10 = top10);

    this.flop10Subscription = this.appService.getFlop10Observable()
        .subscribe(flop10 => this.flop10 = flop10);

    this.latestSubscription = this.appService.getLatestObservable()
        .subscribe(latest => this.latest = latest);

    this.userSubscription = this.appService.getUserObservable()
        .subscribe(user => this.user = user);

    this.fetchAll();
    this.appService.fetchUser();
  }

  ngOnDestroy(): void {
    this.top10Subscription.unsubscribe();
    this.flop10Subscription.unsubscribe();
    this.latestSubscription.unsubscribe();
    this.userSubscription.unsubscribe();
  }

  reload(): void {
    this.fetchAll();
  }

  addQuote(): void {
    this.addQuoteDialogRef = this.dialog.open(AddQuoteDialogComponent, {
      width: '350px'
    });
  }

  signOut(): void {
    this.appService.signOut()
        .then(() => {
          this.router.navigate(['/signin']);
        });
  }

  private fetchAll(): void {
    this.appService.fetchTop10();
    this.appService.fetchFlop10();
    this.appService.fetchLatest();
  }
}
