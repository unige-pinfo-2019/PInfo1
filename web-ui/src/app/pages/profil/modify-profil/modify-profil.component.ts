import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormArray } from '@angular/forms';
import { PostService } from '../../../services/post.service'
import { CatalogueService } from '../../../services/catalogue.service'
import { Observable, Subscription } from 'rxjs';
import { Router } from '@angular/router'

@Component({
  selector: 'app-modify-profil',
  templateUrl: './modify-profil.component.html',
  styleUrls: ['./modify-profil.component.scss']
})
export class ModifyProfilComponent implements OnInit {

  constructor(private catalogueService: CatalogueService, private postService: PostService, private router: Router) { }

  postForm : FormGroup;
  id : number = 0;
  email : string = "";
  email_boolean : boolean = false;
  password : string = "";
  password_boolean : boolean = false;
  surname : string = "";
  surname_boolean : boolean = false;
  name : string = "";
  name_boolean : boolean = false;
  username : string= "";
  username_boolean : boolean = false;
  report : number = 0;
  grade : number = 0;

  message: string = "1234";
  info_user: any = {"id": 0, "name": "","surname": "","username": "","email": "","report": 0,"grade": 0};

  ngOnInit() {
    this.catalogueService.currentMessage_info_usr.subscribe((res) => {
    this.message = res;
    this.catalogueService.get_user(this.message).subscribe((res: any[]) => {
      this.info_user = res[0];
      console.log("la:"+this.info_user.id);
      this.id = this.info_user.id;
      this.name = this.info_user.name;
      this.surname = this.info_user.surname;
      this.username = this.info_user.username;
      this.email = this.info_user.email;
      this.report = this.info_user.report;
      this.grade = this.info_user.grade;
  })})
}

  newInfo() {
    this.info_user.name = this.name;
    this.info_user.surname = this.surname;
    this.info_user.username = this.username;
    this.info_user.email = this.email;
    console.log(this.info_user);
    this.catalogueService.changeInfo(this.info_user.id);
  }

  set_name(event) {
    this.name = event.target.value;
    if(event.target.value=="0"){
      this.name_boolean=false;
    }else{
      this.name_boolean=true;
    }
  }

  set_surname(event){
    this.surname = event.target.value;
    if(event.target.value=="0"){
      this.surname_boolean=false;
    }else{
      this.surname_boolean=true;
    }
  }

  set_username(event){
    this.username = event.target.value;
    if(event.target.value=="0"){
      this.username_boolean=false;
    }else{
      this.username_boolean=true;
    }
  }

  set_email(event){
    this.email = event.target.value;
    if(event.target.value==""){
      this.email_boolean=false;
    }else{
      this.email_boolean=true;
    }
  }

  onSubmitForm() {
    this.postService.modifyUser(this.id, this.email, this.surname, this.name, this.username, this.report, this.grade);
    this.router.navigate(['../profil/info']);
  }
}
