import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormArray } from '@angular/forms';
import { PostService } from '../../services/post.service'
import { CatalogueService } from '../../services/catalogue.service'
import { HttpHeaders, HttpClient ,HttpParams } from '@angular/common/http';
import { Image } from '../../models/Item.model'
import { KeycloakService } from '../../services/keycloak/keycloak.service';


import { Observable, Subscription } from 'rxjs';
import { Router } from '@angular/router'

@Component({
  selector: 'app-page-vente',
  templateUrl: './page-vente.component.html',
  styleUrls: ['./page-vente.component.scss']
})
export class PageVenteComponent implements OnInit {
  postForm : FormGroup;
  name : string = "";
  image: string = "Acq7kkx";
  name_boolean : boolean = false;
  description : string = "";
  description_boolean : boolean = false;
  price : number = 0;
  price_boolean : boolean = false;
  categorie : string = "";
  categorie_boolean : boolean = false;
  etat : number = 1;
  etat_boolean : boolean = false;
  first_name: string =  this.keycloak.getFirstName();
  last_name: string =  this.keycloak.getLastName();

  message: any[];
  selectedFile = null;

  httpOptions = {
    headers: new HttpHeaders({
        'Authorization': 'Client-ID 4fb3f8deeec4486',}),
  };

  public imagePath;
  imgURL: any;
  public mes: string;

  preview(files) {
    if (files.length === 0)
      return;

    var mimeType = files[0].type;
    if (mimeType.match(/image\/*/) == null) {
      this.mes = "Only images are supported.";
      return;
    }

    var reader = new FileReader();
    this.imagePath = files;
    reader.readAsDataURL(files[0]);
    reader.onload = (_event) => {
      this.imgURL = reader.result;
    }
    this.selectedFile = files[0];

  }

  onFIleSelected(event){
      this.selectedFile = event.target.files[0]
  }

  onUpload(){
    const fd = new FormData();
    fd.append('image', this.selectedFile, this.selectedFile.name)
    this.httpClient.post('https://api.imgur.com/3/image',fd, this.httpOptions).subscribe((res: Image)=>{
      this.image = res.data.id;
      },(error) => {console.log('Erreur  ! : '+ error);}
    );
  }

  constructor(private postService: PostService,private catalogueService: CatalogueService, private router: Router, private httpClient: HttpClient, public keycloak: KeycloakService) { }

  ngOnInit() {
  }


  set_name(event){
    this.name = event.target.value;
    if(event.target.value==""){
      this.name_boolean=false;
    }else{
      this.name_boolean=true;
    }
  }

  selectChangeHandlerState(event: any) {
    this.etat = event.target.value;
    if(event.target.value=="0"){
      this.etat_boolean=false;
    }else{
      this.etat_boolean=true;
    }
  }

  selectChangeHandlerCat(event: any) {
    this.categorie = event.target.value;
    if(event.target.value=="all"){
      this.categorie_boolean=false;
    }else{
      this.categorie_boolean=true;
    }
  }

  set_description(event){
    this.description = event.target.value;
    if(event.target.value==""){
      this.description_boolean=false;
    }else{
      this.description_boolean=true;
    }
  }

  set_prix(event){
    this.price = event.target.value;
    if(event.target.value==""){
      this.price_boolean=false;
    }else{
      this.price_boolean=true;
    }

  }

  test() {
    console.log("envoyé")
  }

  onSubmitForm() {
<<<<<<< HEAD

        this.postService.addPost(this.name, this.price, this.categorie, this.description, this.etat, this.image, this.first_name, this.last_name);
=======
    if (this.selectedFile != null){
      const fd = new FormData();
      fd.append('image', this.selectedFile, this.selectedFile.name)
      this.httpClient.post('https://api.imgur.com/3/image',fd, this.httpOptions).subscribe((res: Image)=>{
        console.log('image Saved ! ');
        console.log(res);
        console.log(res.data.id);
        this.image = res.data.id;
        this.postService.addPost(this.name, this.price, this.categorie, this.description, this.etat, this.image);
>>>>>>> 86d6d4ce5b583eccd156296d492f4b6f91237f2a
        this.router.navigate(['/profil/vente']);
        },(error) => {console.log('Erreur  ! : '+ error);
      alert("Error l'image n'est pas conforme !!");}
      );
    } else {
      this.postService.addPost(this.name, this.price, this.categorie, this.description, this.etat, this.image);
      this.router.navigate(['/profil/vente']);
    }

}
}
