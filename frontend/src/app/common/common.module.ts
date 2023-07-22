import { NgModule } from "@angular/core";
import { authInterceptorProviders } from "../_helpers/auth.interceptor";
import { ButtonModule } from "primeng-lts/button";

@NgModule({
    declarations: [
    
    ],
    imports: [
   ButtonModule
    ],
    exports: [
   ButtonModule
    ],
    providers: [],
    
  })
  export class CommonPrimeNgModule { }