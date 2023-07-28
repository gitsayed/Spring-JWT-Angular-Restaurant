import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { baseURL } from "src/app/constants";
import { Observable } from "rxjs";
import { BaseService } from "src/app/_services/base-services";




const FETCH_USER_WITH_STATUS = baseURL+"/api/v1/admin/user-status";
const CHANGE_USER_STATUS = baseURL+"/api/v1/admin/user-status";


@Injectable({
    providedIn: 'root'
})
export class AdminService extends BaseService{


   

    constructor(private http: HttpClient) {
      super();
    }

    public fetchUserWithStatus(map: Map<string, any>): Observable<any> {

      let params= this.mapToHttpParams(map);
      let url= this.create(FETCH_USER_WITH_STATUS, params);
    
        return this.http.get(url, {  headers:this.headers }  );
      }



      changeUserStatus(obj:any) {

        let url= CHANGE_USER_STATUS+'/'+obj.id;
      
        return this.http.put(url, obj );
        }
}





























