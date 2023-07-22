import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from './_services/token-storage.service';

import {  PrimeNGConfig } from 'primeng-lts/api';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html'
})
export class AppComponent implements OnInit {
  private roles: string[];
  isLoggedIn = false;
  showAdminBoard = false;
  showModeratorBoard = false;
  username: string;

  constructor(private tokenStorageService: TokenStorageService,
    private primengConfig: PrimeNGConfig,

  ) {
 
  }

  ngOnInit(): void {
    this.primengConfig.ripple = true;
    this.isLoggedIn = !!this.tokenStorageService.getToken();

    if (this.isLoggedIn) {
      const user = this.tokenStorageService.getUser();
      this.roles = user.roles;

      this.showAdminBoard = this.roles.includes('ROLE_ADMIN');
      this.showModeratorBoard = this.roles.includes('ROLE_MODERATOR');

      this.username = user.username;
    }
  }

 


  logout(): void {
    this.tokenStorageService.signOut();
    window.location.reload();
  }
}
