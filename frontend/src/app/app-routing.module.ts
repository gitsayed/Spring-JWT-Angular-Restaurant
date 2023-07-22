import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { ProfileComponent } from './profile/profile.component';
import { BoardUserComponent } from './board-user/board-user.component';
import { BoardModeratorComponent } from './board-moderator/board-moderator.component';
import { BoardAdminComponent } from './board-admin/board-admin.component';
import { InvalidAccessComponent } from './invalid-access/invalid-access.component';
import { AuthGuard } from './_authGuard/auth.guard';
import { SuperAdminModule } from './modules/super-admin/super-admin.module';

const routes: Routes = [
  { path: 'home', component: HomeComponent, },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'profile', component: ProfileComponent },
  { path: 'super-admin', loadChildren:  './modules/super-admin/super-admin.module#SuperAdminModule' ,canActivate: [AuthGuard] },
  { path: 'user', component: BoardUserComponent, canActivate: [AuthGuard], data: {roles: ['ROLE_USER']} },
  { path: 'mod', component: BoardModeratorComponent },
  { path: 'admin', component: BoardAdminComponent, canActivate: [AuthGuard], data: {roles: ['ROLE_ADMIN']} },
  { path: 'invalid-access', component: InvalidAccessComponent },
  { path: '', redirectTo: 'home', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
