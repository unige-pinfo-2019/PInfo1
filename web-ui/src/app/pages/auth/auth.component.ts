import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';
import { Router } from '@angular/router'
import { AuthService } from '../../services/auth.service'
import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';
import { Observable, Subscription } from 'rxjs';


@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.scss']
})





export class AuthComponent implements OnInit {

    email: boolean = false
    password: boolean = false
    res_input: boolean = this.email && this.password
    //@Input() id: number;
    listStatus: any[];
    authStatus: boolean;
    form=new FormGroup({
      connection: new FormControl('', Validators.required)
    })

    constructor(private authService: AuthService, private router: Router) {}

    createUser(){
      this.router.navigate(['creerUtilisateur']);
    }

    ngOnInit() {
      this.authStatus = this.authService.isAuth;
      this.listStatus = this.authService.list;
      //console.log("id: " + this.id);
    }


    set_email(event){
      //this.email = event.target.value;
      if(event.target.value==""){
        this.email=false;
      }else{
        this.email=true;
      }
    }

    set_password(event){
      if(event.target.value==""){
        this.password=false;
      }else{
        this.password=true;
      }
    }

    onSignIn() {
      this.authService.signIn().then(
        () => {
          console.log('Sign in successful !');
          this.authStatus = this.authService.isAuth;
          console.log(this.authStatus);
          //this.router.navigate(['home']);

        }
      );
    }

    onSignOut() {
      this.authService.signOut();
      this.authStatus = this.authService.isAuth;
    }

    onDisplay() {
      this.authService.display();
      this.listStatus = this.authService.list;
      console.log(this.listStatus);
      return this.listStatus;
    }

}
