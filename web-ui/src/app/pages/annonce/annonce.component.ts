import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormArray } from '@angular/forms';
import { CatalogueService } from '../../services/catalogue.service'

import { Observable, Subscription } from 'rxjs';
import { Router } from '@angular/router'

@Component({
  selector: 'app-annonce',
  templateUrl: './annonce.component.html',
  styleUrls: ['./annonce.component.scss']
})
export class AnnonceComponent implements OnInit {
  private list_annonce: any[];


  constructor(private catalogueService: CatalogueService, private router: Router) { }

  ngOnInit() {
    this.catalogueService.get_annonce().subscribe((res: any[]) => {
      this.list_annonce = res;
    })
  }
}

//   constructor() { }
//
//   ngOnInit() {
//   }
//
// }
