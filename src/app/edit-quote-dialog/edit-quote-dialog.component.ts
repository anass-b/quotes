import { Component, Inject } from '@angular/core';
import { AppService } from '../app.service';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material';

@Component({
  selector: 'app-edit-quote-dialog',
  templateUrl: './edit-quote-dialog.component.html',
  styleUrls: ['./edit-quote-dialog.component.scss']
})
export class EditQuoteDialogComponent {
  progress: boolean;
  error: string;
  content: string;

  constructor(
    private readonly appService: AppService,
    private readonly dialogRef: MatDialogRef<EditQuoteDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) {
    this.content = this.data.quote.content;
  }

  onOkClick() {
    if (!this.content) {
      this.error = 'Field required.';
      return;
    }

    this.progress = true;
    this.appService.edit(this.data.quote.id, {content: this.content})
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
