import { Routes } from "@angular/router";
import { AdminComponent } from "./admin/admin.component";
import { UserViewComponent } from "./user-view/user-view.component";

export const AdminRoutes: Routes = [
    {
        path: '', 
        component: AdminComponent
    },
    {
        path: 'user-view', 
        component: UserViewComponent
    },
];