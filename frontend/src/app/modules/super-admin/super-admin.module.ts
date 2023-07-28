import { NgModule } from "@angular/core";
import { CommonPrimeNgModule } from "src/app/common/common.module";
import { SuperAdminComponent } from './super-admin/super-admin.component';
import { SuperAdminRoutes } from "./super-admin.routes";
import { RouterModule } from "@angular/router";

@NgModule({
    imports: [
        RouterModule.forChild(SuperAdminRoutes),
        CommonPrimeNgModule
    ],
    declarations: [
    
    SuperAdminComponent],
  
    providers: [],
    
  })
  export class SuperAdminModule { }