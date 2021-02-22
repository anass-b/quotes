import { Component } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { Quote } from '../dtos/Quote';
import { AppService } from '../app.service';

export interface AddQuoteDialogData {
  quote: Quote;
}

@Component({
  selector: 'app-add-quote-dialog',
  templateUrl: './add-quote-dialog.component.html',
  styleUrls: ['./add-quote-dialog.component.scss']
})
export class AddQuoteDialogComponent {
  content: string;
  progress: boolean;
  error: string;

  constructor(
      private readonly appService: AppService,
      private readonly dialogRef: MatDialogRef<AddQuoteDialogComponent>) {
  }

  onOkClick() {
    if (!this.content) {
      this.error = 'Field required.';
      return;
    }

    this.progress = true;
    this.appService.create({content: this.content})
        .then((quote: Quote) => {
          this.progress = false;
          this.dialogRef.close();
        })
        .catch(() => {
          this.progress = false;
          this.error = 'An error occurred.';
        });
  }

  onNoClick(): void {
    this.dialogRef.close();
  }
}
