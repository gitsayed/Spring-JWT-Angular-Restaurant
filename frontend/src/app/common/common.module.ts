import { NgModule } from "@angular/core";

import { ButtonModule } from "primeng/button";
import { CardModule } from "primeng/card";
import { ToastModule } from 'primeng/toast';
import { DropdownModule } from 'primeng/dropdown';


@NgModule({
    declarations: [
    
    ],
    imports: [
   ButtonModule,
   CardModule,
   ToastModule,
   DropdownModule
  
    ],
    exports: [
   ButtonModule,
   CardModule,
   ToastModule,
   DropdownModule
    ],
    providers: [],
    
  })
  export class CommonPrimeNgModule { }