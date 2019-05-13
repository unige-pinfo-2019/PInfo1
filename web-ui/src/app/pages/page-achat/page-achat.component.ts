import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-page-achat',
  templateUrl: './page-achat.component.html',
  styleUrls: ['./page-achat.component.scss']
})
export class PageAchatComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit() {
    console.log(this.router.url);
  }

}
