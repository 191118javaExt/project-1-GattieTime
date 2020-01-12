import { Component, OnInit } from '@angular/core';
import { ImageService } from 'src/app/services/image.service';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user';


@Component({
  selector: 'app-receipt',
  templateUrl: './receipt.component.html',
  styleUrls: ['./receipt.component.css']
})
export class ReceiptComponent {
  receiptDataURL: string | ArrayBuffer;
  ReceiptReimId: number;
  currentUser: User;


  constructor(private is: ImageService, private router: Router) { }

  ngOnInit() {
    let userString: string = sessionStorage.getItem('currentUser');
    if(!userString){
      this.router.navigate(['/login']);
    } else {
      this.currentUser = JSON.parse(userString);
    }
  }

  processFile(e) {
    let image = e.target.files[0];
    let read = new FileReader();
    read.readAsDataURL(image)
    read.onloadend = () => {
      this.receiptDataURL=read.result;
    }


  }

  sendReceipt() {
    this.is.uploadReceipt(this.ReceiptReimId, this.receiptDataURL).subscribe()
  }

  
}