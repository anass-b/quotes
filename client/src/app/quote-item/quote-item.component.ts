import { Component, ElementRef, Input, OnInit, ViewChild } from '@angular/core';
import { Quote } from '../dtos/Quote';
import { AppService } from '../app.service';
import { Chart } from 'chart.js';
import { VoteHistoryItem } from '../dtos/VoteHistoryItem';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { EditQuoteDialogComponent } from '../edit-quote-dialog/edit-quote-dialog.component';
import { DeleteQuoteDialogComponent } from '../delete-quote-dialog/delete-quote-dialog.component';

@Component({
  selector: 'app-quote-item',
  templateUrl: './quote-item.component.html',
  styleUrls: ['./quote-item.component.scss']
})
export class QuoteItemComponent implements OnInit {
  @Input() quote: Quote;
  @ViewChild('chartCanvas') private chartCanvas: ElementRef;
  chartUnit = 'second';
  chartLimit = 50;
  chart: Chart;
  private editQuoteDialogRef: MatDialogRef<EditQuoteDialogComponent>;
  private deleteQuoteDialogRef: MatDialogRef<DeleteQuoteDialogComponent>;

  constructor(
      private readonly appService: AppService,
      private readonly dialog: MatDialog) {
  }

  ngOnInit(): void {
    this.loadChart();
  }

  loadChart(): void {
    this.appService.voteHistory(this.quote.id, this.chartUnit, this.chartLimit)
        .then((history: VoteHistoryItem[]) => {
          const config = {
            type: 'line',
            data: {
              labels: history.map(e => new Date(e.createdAt).toLocaleString()),
              datasets: [
                {
                  data: history.map(e => e.upvotes),
                  label: 'Upvotes',
                  borderColor: '#32CD32',
                  fill: false
                },
                {
                  data: history.map(e => e.downvotes),
                  label: 'Downvotes',
                  borderColor: '#B22222',
                  fill: false
                },
                {
                  data: history.map(e => e.delta),
                  label: 'Score',
                  borderColor: '#3169b2',
                  fill: false
                }
              ]
            },
            options: {
              responsive: true,
              maintainAspectRatio: false,
            }
          };

          if (!this.chart) {
            this.chart = new Chart(this.chartCanvas.nativeElement.getContext('2d'), config);
          } else {
            this.chart.data = config.data;
            this.chart.update();
          }
        });
  }

  downvote(event: MouseEvent, quoteId: string): void {
    event.stopPropagation();
    this.appService.downvote(quoteId);
  }

  upvote(event: MouseEvent, quoteId: string): void {
    event.stopPropagation();
    this.appService.upvote(quoteId);
  }

  editQuote(quote: Quote): void {
    this.editQuoteDialogRef = this.dialog.open(EditQuoteDialogComponent, {
      width: '350px',
      data: {
        quote
      }
    });
  }

  deleteQuote(quote: Quote): void {
    this.deleteQuoteDialogRef = this.dialog.open(DeleteQuoteDialogComponent, {
      width: '350px',
      data: {
        quote
      }
    });
  }

  get canEdit(): boolean {
    return this.quote.user === this.appService.getUser().profile.username;
  }

  get canDelete(): boolean {
    return this.quote.user === this.appService.getUser().profile.username;
  }
}
