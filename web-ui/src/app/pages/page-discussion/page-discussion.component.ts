import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormArray } from '@angular/forms';
import { CatalogueService } from '../../services/catalogue.service'

import { Observable, Subscription } from 'rxjs';
import { Router } from '@angular/router'

@Component({
  selector: 'app-page-discussion',
  templateUrl: './page-discussion.component.html',
  styleUrls: ['./page-discussion.component.scss']
})
export class PageDiscussionComponent implements OnInit {
  private list_annonce: any[];


  constructor(private catalogueService: CatalogueService, private router: Router) { }

  ngOnInit() {
    this.catalogueService.get_annonce().subscribe((res: any[]) => {
      this.list_annonce = res;
      while(this.list_annonce.length >4){
          this.list_annonce.pop()
      }
      console.log(this.list_annonce.length)
    })
  }
}

//   constructor() { }
//
//   ngOnInit() {
//   }
//
// }
