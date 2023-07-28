import { Component, OnInit } from '@angular/core';
import { UserService } from '../_services/user.service';
import { TokenStorageService } from '../_services/token-storage.service';
import { ToastService } from '../_services/toast.services';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  isLoggedIn: boolean=false;
  content: string='';

  constructor(
    private toast: ToastService,
    private storageService: TokenStorageService,
    private userService: UserService) { }

  ngOnInit(): void {
   this.isLoggedIn=this.storageService.getIsLoggedIn();

   if(this.isLoggedIn){
   
   }
  }

}
