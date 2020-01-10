import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ImageService {
  

  constructor(private http: HttpClient) {}


  uploadReceipt(id: number, receiptDataURL: string | ArrayBuffer): Observable<void> {

    let body = {
      id:id,
      receiptDataURL:receiptDataURL
    }
    
    
    return this.http.post<void>('http://localhost:8080/project-1/receipt', body);
    
  }

}
