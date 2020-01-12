import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { Router } from '@angular/router';
import { ReimbursementService } from 'src/app/services/reimbursement.service';
import { Reimbursement } from 'src/app/models/reimbursement';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  amount: number = 0;
  description: string = '';
  type: number = 0;

  reimbursements: Reimbursement[];
  currentUser: User;
  selectedFile: any = null;
  constructor(private rs: ReimbursementService, private router: Router) { }
  
  

  ngOnInit() {
    let userString: string = sessionStorage.getItem('currentUser');
    if(!userString){
      this.router.navigate(['/login']);
    } else {
      this.currentUser = JSON.parse(userString);
    }

    

  }


  sendReim() {
    this.rs.submitReim(this.currentUser.id, this.amount, this.description, this.type).subscribe(
      (response:void) => { console.log("reimbursement sent")}
    );

  }

  getUserReim() {
    this.rs.getUserReim(this.currentUser.id).subscribe(
      (response:Reimbursement[]) => {
       this.reimbursements=response;
      }
    )
  }

  processFile(imageInput: any) {
    const file: File = imageInput.files[0];
    this.selectedFile = file;
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

}
