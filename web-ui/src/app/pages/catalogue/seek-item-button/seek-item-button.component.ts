import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router'

@Component({
  selector: 'app-seek-item-button',
  templateUrl: './seek-item-button.component.html',
  styleUrls: ['./seek-item-button.component.scss']
})
export class SeekItemButtonComponent implements OnInit {

  constructor(private router: Router) { }

  posterAnnonce(){
    this.router.navigate(['ajouterAnnonce']);
  }

  ngOnInit() {
  }

}
