import { NgModule } from "@angular/core";
import { CommonPrimeNgModule } from "src/app/common/common.module";
import { AdminComponent } from './admin/admin.component';
import { AdminRoutes } from "./admin.routes";
import { RouterModule } from "@angular/router";
import { UserViewComponent } from './user-view/user-view.component';
import { WaitingUserComponent } from './waiting-user/waiting-user.component';

@NgModule({
    imports: [
        RouterModule.forChild(AdminRoutes),
        CommonPrimeNgModule
    ],
    declarations: [
    AdminComponent,
    UserViewComponent,
    WaitingUserComponent],
  
    providers: [
    ],
    
  })
  export class AdminModule { }