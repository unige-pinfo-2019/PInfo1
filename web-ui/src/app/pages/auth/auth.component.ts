import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router'
import { AuthService } from '../../services/auth.service'


@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.scss']
})





export class AuthComponent implements OnInit {

    //@Input() id: number;
    listStatus: any[];
    authStatus: boolean;

    constructor(private authService: AuthService, private router: Router) {}

    ngOnInit() {
      this.authStatus = this.authService.isAuth;
      this.listStatus = this.authService.list;
      //console.log("id: " + this.id);
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
