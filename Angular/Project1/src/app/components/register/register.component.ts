import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  
  username : string;
  password : string;
  confirmpass : string;
  first : string;
  last : string;
  email : string;

  constructor(private us: UserService, private router: Router) { }

  ngOnInit() {
  }

  newUser() {
    if(this.password != this.confirmpass){
      document.getElementById("warning").innerHTML = "Your password do not match, please try again.";
    } else {
      this.us.newUser(this.username, this.password, this.first, this.last, this.email).subscribe();
      this.router.navigate(['/login']);
    }
  }

}
