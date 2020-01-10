import { Injectable, ɵNOT_FOUND_CHECK_ONLY_ELEMENT_INJECTOR } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Reimbursement } from '../models/reimbursement';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ReimbursementService {
  

  constructor(private http: HttpClient) { }

  submitReim(userId:number, amount:number, description:string, type:number){
    let body: any = {
      userId: userId,
      amount: amount,
      description: description,
      type: type,
    }

    return this.http.post<void>(('http://localhost:8080/project-1/newreim'), body);
  }

  getUserReim(id: number): Observable<Reimbursement[]> {
    let body = {
      id: id
    }
    return this.http.post<Reimbursement[]>(('http://localhost:8080/project-1/getuserreim'), body);
  }

  


}
