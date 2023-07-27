import { Component, OnInit } from '@angular/core';
import { AuthService } from '../_services/auth.service';
import { ToastService } from '../_services/toast.services';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit{
  form: any = {
    username: null,
    email: null,
    password: null,
    category:null
  };
  isSuccessful = false;
  isSignUpFailed = false;
  errorMessage = '';

roleCategory=[{label:'Select Category ', value:null},{label:'Restaurant', value:'restaurant'}, {label: 'Customer', value: 'customer'}];
  categoryError: boolean=false;

  constructor(
    private router: Router,
    private toast: ToastService,
    private authService: AuthService) { }
  ngOnInit(): void {
    localStorage.clear();
    sessionStorage.clear();
  
  }

  onSubmit(): void {
    this.categoryError=false;
    const { username, email, password, category } = this.form;
    if(category.value==null){
      this.categoryError=true;
      this.toast.error('Form Invalid : Select Category.');
      return;
    }
    this.authService.register(username, email, password, category.value).subscribe({
     
      next: data => {
        console.log(data);
        this.isSuccessful = true;
        this.isSignUpFailed = false;
        this.toast.success("Signup Successful.\n"+data.message);
        this.router.navigate(['/login']); 
      },
      error: err => {
        this.errorMessage = err.error.message;
        this.isSignUpFailed = true;
      }
    });
  }

  reloadPage(): void {
    window.location.reload();
  }
}
