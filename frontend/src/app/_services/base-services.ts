import { HttpHeaders, HttpParams } from "@angular/common/http";






export class BaseService {

    headers = new HttpHeaders({ 'Content-Type': 'application/json' });

    public mapToHttpParams(map: Map<string, any>) {
        let params = '?';
        if (map != null && map.size > 0) {
            map.forEach(( v, k) => {
                params += k.toString() + '=' + v.toString() + '&'
                console.log(params);
            });
        }
        return params;
    }

    public create(a: string, b: string): string {

        return a + b;
    }
}

