import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../models/user';


@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  login(username:string, password:string): Observable<User> {
    let body: any =
    {
      username: username,
      password: password
    };

    

    return this.http.post<User>(('http://localhost:8080/project-1/login'), body);

    
  }

  logout(){
    return this.http.post<void>("http://localhost:8080/project-1/logout", {});
  }

  newUser(username: string, password: string, first: string, last: string, email: string){
    let body: any = {
      name: username,
      password: password,
      firstName: first,
      lastName: last,
      email: email
    }
    return this.http.post<void>("http://localhost:8080/project-1/register", body);
  }


}
