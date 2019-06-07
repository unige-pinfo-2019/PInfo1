import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormArray } from '@angular/forms';
import { CatalogueService } from '../../../services/catalogue.service'

import { Observable, Subscription } from 'rxjs';
import { Router } from '@angular/router'

@Component({
  selector: 'app-annonce-bottom',
  templateUrl: './annonce-bottom.component.html',
  styleUrls: ['./annonce-bottom.component.scss']
})

export class AnnonceBottomComponent implements OnInit {
  private list_annonce: any[];
    longueur: number = 0;


  constructor(private catalogueService: CatalogueService, private router: Router) { }

  ngOnInit() {
    this.catalogueService.get_annonce().subscribe((res: any[]) => {
      this.list_annonce = res;
      this.longueur = this.list_annonce.length;
      while(this.list_annonce.length >4){
          this.list_annonce.pop()
      }
    })
  }
}
