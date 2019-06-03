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

  httpOptions = {
    headers: new HttpHeaders({
        'Authorization': 'Client-ID 4fb3f8deeec4486',}),
  };



  onFIleSelected(event){
      this.selectedFile = event.target.files[0]
      console.log(this.selectedFile);
  }

  onUpload(){
    const fd = new FormData();
    fd.append('image', this.selectedFile, this.selectedFile.name)
    this.httpClient.post('https://api.imgur.com/3/image',fd, this.httpOptions).subscribe((res)=>{
      console.log('Saved ! ');
      console.log(res);
      },(error) => {console.log('Erreur  ! : '+ error);}
    );
  }



  constructor(private router: Router, private httpClient: HttpClient) {  }

  ngOnInit() {
  }

}
