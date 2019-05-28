
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
  private list_message: any[];
  msg : string = "";
  msg_boolean : boolean = false;


  constructor(private postService: PostService, private catalogueService: CatalogueService, private router: Router) { }

  ngOnInit() {
    this.catalogueService.get_discussion("1234", "1235").subscribe((res: any[]) => {
      this.list_message = res;
      while(this.list_message.length >4){
          this.list_message.pop()
      }
      console.log(this.list_message.length)
    })

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
    this.msg = event.target.value;
    if(event.target.value==""){
      this.msg_boolean=false;
    }else{
      this.msg_boolean=true;
    }
  }


  onSubmitForm() {
        this.postService.addMessage(this.msg);
        // //this.catalogueService.post_user("salut");
        //
        this.router.navigate(['/discussion']);
}
}

//   constructor() { }
//
//   ngOnInit() {
//   }
//
// }
