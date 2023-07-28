import { Component, Input, OnInit } from '@angular/core';
import { AdminService } from '../services/admin-service';
import { UserStatusEnum } from '../services/user-status-enum';
import { ToastService } from 'src/app/_services/toast.services';
import { ConfirmationService, LazyLoadEvent } from 'primeng/api';
import { ConfirmDialogueService } from 'src/app/services/confirm-dialogue-service';

@Component({
  selector: 'app-waiting-user',
  templateUrl: './waiting-user.component.html'
})
export class WaitingUserComponent implements OnInit {


  loading: boolean = true;
  pageSizeOptions = [10, 25, 50, 100];

  contents = [];
  totalRecords = 0;

  users = [];
  openDailogue = false;



  constructor(
    private adminService: AdminService,
    private toast: ToastService,
    private confirmationService: ConfirmationService,
    private confirmDialogueService: ConfirmDialogueService


  ) { }
  ngOnInit(): void {
    const map = new Map<string, string>();
  }


  contentsLazyLoads(event: LazyLoadEvent) {

    const { first, rows } = event;
    console.log('contentsLazyLoads', event);
    let map = new Map<string, any>();
    map.set('status', UserStatusEnum.IN_ACTIVE);
    if (first != null && rows != null) {
      map.set('page', (+first / rows));
    }
    map.set('size', rows);
    this.adminService.fetchUserWithStatus(map).subscribe({
      next: res => {
        this.contents = res.content;
        this.totalRecords = +res.totalElements;


      },
      error: err => {
        this.toast.error(err.message);
      }
    });
  }

  changeUserStatus(event: any) {


    this.confirmationService.confirm({
      message: 'Do you want to Acitvate user :' + event.username + ' ?',
      header: 'Acitvate Confirmation',
      accept: () => {
        let obj = { id: event.id, status: UserStatusEnum.ACTIVE };
        this.adminService.changeUserStatus(obj).subscribe({
          next: res => {
            console.log(res);
            this.toast.success('User Activated Successfully.');
            this.contentsLazyLoads({ first: 0, rows: 10 });

          },
          error: err => {
            this.toast.error(err.message)
          }
        });
      },
      reject: () => {
        this.toast.warn('!Cancelled.')
      }
    });



  }

  openNewDialogue($event: MouseEvent) {
   this.openDailogue=true;
    }





  getSeverity(status: any): string {
    switch (status) {
      case 'IN_ACTIVE':
        return 'danger';

      case 'ACTIVE':
        return 'success';

      default: return '';
    }
  }
}
