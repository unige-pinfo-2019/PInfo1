import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormArray } from '@angular/forms';
import { CatalogueService } from '../../services/catalogue.service'

import { Observable, Subscription } from 'rxjs';
import { Router } from '@angular/router'

@Component({
  selector: 'app-messagerie',
  templateUrl: './messagerie.component.html',
  styleUrls: ['./messagerie.component.scss']
})
export class MessagerieComponent implements OnInit {
  private list_discussion: any[];


  constructor(private catalogueService: CatalogueService, private router: Router) { }

  ngOnInit() {
    this.catalogueService.get_messenger("1234").subscribe((res: any[]) => {
      this.list_discussion = res;
      while(this.list_discussion.length >4){
          this.list_discussion.pop()
      }
      console.log(this.list_discussion.length)
    })
  }
}

//   constructor() { }
//
//   ngOnInit() {
//   }
//
// }
