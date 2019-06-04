
import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormArray } from '@angular/forms';
import { PostService } from '../../services/post.service'
import { CatalogueService } from '../../services/catalogue.service'

import { Observable, Subscription } from 'rxjs';
import { Router } from '@angular/router'

@Component({
  selector: 'app-page-discussion',
  templateUrl: './page-discussion.component.html',
  styleUrls: ['./page-discussion.component.scss']
})
export class PageDiscussionComponent implements OnInit {
  private list_Mymessage: any[];
  private list_message: any[];
  private destinataire: any = {"id": 0, "name": "","surname": "","username": "","email": "","report": 0,"grade": 0};



  msg : string = "";
  msg_boolean : boolean = false;
  myId: string = "";
  hisId: string = "";


  constructor(private postService: PostService, private catalogueService: CatalogueService, private router: Router) { }

  ngOnInit() {
    var str = this.router.url;
    this.hisId = str.split("/",9).pop();
    console.log(this.hisId)
    this.myId = "1234";
    this.catalogueService.get_user(this.hisId).subscribe((res: any[]) => {
      if (Array.isArray(res) && res.length) {
        this.destinataire = res[0];
        console.log("HAHHAHAHAHHA"+this.destinataire.name);
    // array exists and is not empty
    }
  });
    this.catalogueService.get_discussion(this.myId, this.hisId).subscribe((res: any[]) => {
      this.list_message = res;
      // while(this.list_message.length >4){
      //     this.list_message.pop()
      // }
      console.log(this.list_Mymessage.length)
    });

    // this.catalogueService.get_discussion(this.hisId,this.myId).subscribe((res: any[]) => {
    //   this.list_Hismessage = res;
    //   while(this.list_Hismessage.length >4){
    //       this.list_Hismessage.pop()
    //   }
    //   console.log(this.list_Hismessage.length)
    // });


    // var str = this.router.url;
    // this.id = str.split("/",9).pop();
    // console.log(this.id);
    // this.catalogueService.get_discussion(this.id).subscribe((res: any[]) => {
    //   this.items = res[0];
    //   console.log(this.items);
    //   // this.catalogueService.get_user(this.items.usrId).subscribe((res: any[]) => {
    //   //   if (Array.isArray(res) && res.length) {
    //   //     this.user = res[0];
    //   //     console.log(this.user);
    //   // // array exists and is not empty
    //   // }
    //   // })
    // })

  }

  set_msg(event){
    console.log(this.msg)
    console.log(this.msg_boolean)
    this.msg = event.target.value;
    if(event.target.value==""){
      this.msg_boolean=false;
    }else{
      this.msg_boolean=true;
    }
  }


  sendMesage() {
      console.log("envoye")
        this.postService.addMessage(this.msg,this.myId, this.hisId);
        // //this.catalogueService.post_user("salut");
        //
        this.router.navigate(['/']);
}
}

//   constructor() { }
//
//   ngOnInit() {
//   }
//
// }
