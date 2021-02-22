import { Component, Inject } from '@angular/core';
import { AppService } from '../app.service';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Quote } from '../dtos/Quote';

@Component({
  selector: 'app-delete-quote-dialog',
  templateUrl: './delete-quote-dialog.component.html',
  styleUrls: ['./delete-quote-dialog.component.scss']
})
export class DeleteQuoteDialogComponent {
  progress: boolean;
  error: string;
  quote: Quote;

  constructor(
    private readonly appService: AppService,
    private readonly dialogRef: MatDialogRef<DeleteQuoteDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) {
    this.quote = this.data.quote;
  }

  onOkClick() {
    this.progress = true;
    this.appService.delete(this.data.quote.id)
      .then(() => {
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
