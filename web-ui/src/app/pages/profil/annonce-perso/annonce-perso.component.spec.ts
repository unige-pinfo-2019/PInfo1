import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AnnoncePersoComponent } from './annonce-perso.component';

describe('AnnoncePersoComponent', () => {
  let component: AnnoncePersoComponent;
  let fixture: ComponentFixture<AnnoncePersoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AnnoncePersoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AnnoncePersoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
