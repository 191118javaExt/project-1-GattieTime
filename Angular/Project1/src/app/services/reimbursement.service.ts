import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

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
      type: type
    }

    return this.http.post<void>(('http://localhost:8080/project-1/newreim'), body);

    
  }


}
