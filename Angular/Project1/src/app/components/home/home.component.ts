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
        console.log(response);
       this.reimbursements=response;
      }
    )
    console.log(this.reimbursements)
  }

  processFile(imageInput: any) {
    const file: File = imageInput.files[0];
    this.selectedFile = file;
    console.log(this.selectedFile);
  }

  showRec(e){
    for(let r of this.reimbursements){
      if (e.target.attributes.value.value == r.id){
        console.log(r.receipt);
        // let iarr = new Uint8Array(new ArrayBuffer(r.receipt.length));
        // for (let i =0; i < r.receipt.length; i++){
        //   iarr[i] = r.receipt.charCodeAt(i);
        // }
        // let blob = new Blob([r.receipt], {type: "image/jpeg"});
        // console.log(blob)
        // const blobURL = URL.createObjectURL(blob);
        // console.log(blobURL);
        // window.open(blobURL);
        let image = new Image();
        image.src = r.receipt;
        let w = window.open("");
        w.document.write(image.outerHTML);


      }
      
    }
  }

}
