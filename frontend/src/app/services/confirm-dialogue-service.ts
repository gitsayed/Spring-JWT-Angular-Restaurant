import { Injectable } from "@angular/core";
import { BaseService } from "../_services/base-services";
import { ConfirmationService } from "primeng/api";











@Injectable({
    providedIn: 'root'
})
export class ConfirmDialogueService  {


    constructor(  private confirmationService: ConfirmationService ){
       

    }

    public sendForConfirmation(acceptFunction: Function, rejectFunction?: Function, customMessage?: string|null){
        this.confirmationService.confirm({
            message: customMessage ? customMessage : 'Are you sure that you want to perform this action?',
            header: 'Action Confirmation Dialogue.',
            icon: 'fa fa-question-circle',
            key: 'common',
            accept: () => {
              acceptFunction();
            },
            reject: () => {
              if (rejectFunction) {
                rejectFunction();
              }
            }
          });


    }

}

