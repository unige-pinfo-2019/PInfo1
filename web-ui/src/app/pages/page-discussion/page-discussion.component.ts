
import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormArray } from '@angular/forms';
import { PostService } from '../../services/post.service'
import { CatalogueService } from '../../services/catalogue.service';
import { KeycloakService } from '../../services/keycloak/keycloak.service';
import { User } from '../../models/Item.model';



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
  private destinataire: User = {"id": "", "name": "","surname": "","email": "","report": 0, "image":"", "userReport": ""};


  image: string = "Acq7kkx";
  msg : string = "";
  msg_boolean : boolean = false;
  myId: string = "";
  hisId: string = "";


  constructor(private postService: PostService, private catalogueService: CatalogueService, private router: Router, public keycloak: KeycloakService) { }

  ngOnInit() {
    var str = this.router.url;
    this.hisId = str.split("/",9).pop();
    this.myId = this.keycloak.getKeycloakAuth().subject;
    this.catalogueService.get_user(this.hisId).subscribe((res: User) => {
        this.destinataire = res;
        if (res.image != "" && res.image != null){
          this.image = res.image;
        }
  });
    this.catalogueService.get_discussion(this.myId, this.hisId).subscribe((res: any[]) => {
      this.list_message = res;
    });

  }

  set_msg(event){
    this.msg = event.target.value;
    if(event.target.value==""){
      this.msg_boolean=false;
    }else{
      this.msg_boolean=true;
    }
  }


  sendMesage() {
        this.postService.addMessage(this.msg,this.myId, this.hisId);
        this.router.navigate(['/discussion/'+this.hisId]);
        window.location.reload();
}
}
