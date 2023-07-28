import { NgModule } from "@angular/core";

import { ButtonModule } from "primeng/button";
import { CardModule } from "primeng/card";
import { ToastModule } from 'primeng/toast';
import { DropdownModule } from 'primeng/dropdown';
import { TreeModule } from 'primeng/tree';
import { CommonModule } from "@angular/common";
import { ToolbarModule } from 'primeng/toolbar';
import { TableModule } from 'primeng/table';
import { TagModule } from 'primeng/tag';
import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { RippleModule } from 'primeng/ripple';
import { DialogModule } from 'primeng/dialog';

@NgModule({
    declarations: [
    
    ],
    imports: [
   ButtonModule,
   CardModule,
   ToastModule,
   TreeModule, 
   DropdownModule,
   CommonModule,
   ToolbarModule,
   DialogModule,
   TagModule,
   TableModule,
   ConfirmDialogModule,
   RippleModule
  
    ],
    exports: [
   ButtonModule,
   CardModule,
   ToastModule,
   TreeModule, 
   DropdownModule,
   TableModule,
   CommonModule,
   ToolbarModule,
   ConfirmDialogModule,
   DialogModule,
   TagModule,
   RippleModule

    ],
    providers: [],
    
  })
  export class CommonPrimeNgModule { }