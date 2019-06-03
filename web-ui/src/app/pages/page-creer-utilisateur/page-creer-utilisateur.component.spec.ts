import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PageCreerUtilisateurComponent } from './page-creer-utilisateur.component';

describe('PageCreerUtilisateurComponent', () => {
  let component: PageCreerUtilisateurComponent;
  let fixture: ComponentFixture<PageCreerUtilisateurComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PageCreerUtilisateurComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PageCreerUtilisateurComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
