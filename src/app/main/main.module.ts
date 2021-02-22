import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { MainComponent } from './main.component';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatDialogModule } from '@angular/material/dialog';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatListModule } from '@angular/material/list';
import { MatMenuModule } from '@angular/material/menu';
import { MatSelectModule } from '@angular/material/select';
import { MatTabsModule } from '@angular/material/tabs';
import { MatToolbarModule } from '@angular/material/toolbar';
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
