import { Component, OnInit } from '@angular/core';
import { AuthService } from '../_services/auth.service';
import { TokenStorageService } from '../_services/token-storage.service';
import { Router } from '@angular/router';
import { MessageService } from 'primeng/api';
import { ToastService } from '../_services/toast.services';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  form: any = {
    username: null,
    password: null
  };
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  roles: string[] = [];

  constructor(
    private router: Router,
    private toast: ToastService,
    private authService: AuthService, 
    private tokenStorage: TokenStorageService) { }

  ngOnInit(): void {
    if(this.tokenStorage.getIsLoggedIn()){
       this.router.navigate(['/home']);  
    }else{
      localStorage.clear();
      sessionStorage.clear();
    }
  
   
    if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;
      this.roles = this.tokenStorage.getUser().roles;
    }
  }

  onSubmit(): void {
    const { username, password } = this.form;

    this.authService.login(username, password).subscribe(
      data => {
        this.tokenStorage.saveToken(data.accessToken);
        this.tokenStorage.saveUser(data);
        this.tokenStorage.setIsLoggedIn(true);

        this.isLoginFailed = false;
        this.isLoggedIn = true;
        this.roles = this.tokenStorage.getUser().roles;
        this.reloadPage();

      },
      err => {
        this.errorMessage = err.error.message;
        this.toast.error(err.error.message);
        this.isLoginFailed = true;
        this.tokenStorage.setIsLoggedIn(false);
      }
    );
  }

  reloadPage(): void {
    window.location.reload();
   
  }

}
