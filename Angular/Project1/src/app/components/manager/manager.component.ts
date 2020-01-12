import { Component, OnInit } from '@angular/core';
import { Reimbursement } from 'src/app/models/reimbursement';
import { User } from 'src/app/models/user';
import { ReimbursementService } from 'src/app/services/reimbursement.service';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-manager',
  templateUrl: './manager.component.html',
  styleUrls: ['./manager.component.css']
})
export class ManagerComponent implements OnInit {
  status: number = 1;
  reimId: number;
  approval: number;

  reimbursements: Reimbursement[];
  currentUser: User;


  constructor(private rs: ReimbursementService, private router: Router) { }

  ngOnInit() {
    let userString: string = sessionStorage.getItem('currentUser');

    if(!userString){
      this.router.navigate(['/login']);
    } else {
      this.currentUser = JSON.parse(userString);
    }
  }

  getReim() {
    this.rs.getReim(this.status).subscribe(
      (response:Reimbursement[]) => {
       this.reimbursements=response;
      }
    )
  }


  showRec(e){
    for(let r of this.reimbursements){
      if (e.target.attributes.value.value == r.id){
        let image = new Image();
        image.src = r.receipt;
        let w = window.open("");
        w.document.write(image.outerHTML);
      }
    }
  }

  reimApp(){
    this.rs.reimApp(this.reimId, this.approval, this.currentUser.id).subscribe()
  };
}
