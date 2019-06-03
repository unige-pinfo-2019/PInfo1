import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router'
import { HttpHeaders, HttpClient ,HttpParams } from '@angular/common/http';



@Component({
  selector: 'app-load-image',
  templateUrl: './load-image.component.html',
  styleUrls: ['./load-image.component.scss']
})
export class LoadImageComponent implements OnInit {
  selectedFile = null;

  onFIleSelected(event){
    this.selectedFile = event.target.files[0]

    const fd = new FormData();
    fd.append('image', this.selectedFile, this.selectedFile.name);
    this.selectedFile = event.target.files[0]
    console.log(this.selectedFile);
    console.log(fd);
  }

  onUpload(){

  }

  constructor(private http: HttpClient, private router: Router) { }


  ngOnInit() {
  }

}
