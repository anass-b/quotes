import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { MainComponent } from './main.component';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule } from '@angular/forms';
import {
  MatButtonModule, MatCardModule, MatDialogModule,
  MatExpansionModule,
  MatFormFieldModule,
  MatIconModule, MatInputModule,
  MatListModule, MatSelectModule,
  MatTabsModule,
  MatToolbarModule,
  MatMenuModule
} from '@angular/material';
import { AddQuoteDialogComponent } from '../add-quote-dialog/add-quote-dialog.component';
import { QuoteItemComponent } from '../quote-item/quote-item.component';
import { EditQuoteDialogComponent } from '../edit-quote-dialog/edit-quote-dialog.component';
import { DeleteQuoteDialogComponent } from '../delete-quote-dialog/delete-quote-dialog.component';
import { TruncatePipe } from '../truncate.pipe';

const routes: Routes = [
  {
    path: '',
    component: MainComponent
  }
];

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forRoot(routes),
    HttpClientModule,
    BrowserAnimationsModule,
    FormsModule,
    MatButtonModule,
    MatListModule,
    MatToolbarModule,
    MatTabsModule,
    MatExpansionModule,
    MatIconModule,
    MatFormFieldModule,
    MatDialogModule,
    MatInputModule,
    MatSelectModule,
    MatCardModule,
    MatMenuModule
  ],
  entryComponents: [
    AddQuoteDialogComponent,
    EditQuoteDialogComponent,
    DeleteQuoteDialogComponent
  ],
  declarations: [
    MainComponent,
    AddQuoteDialogComponent,
    EditQuoteDialogComponent,
    DeleteQuoteDialogComponent,
    QuoteItemComponent,
    TruncatePipe
  ]
})
export class MainModule { }
