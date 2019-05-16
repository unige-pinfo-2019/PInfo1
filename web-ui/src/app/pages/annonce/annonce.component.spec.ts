import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AnnonceComponent } from './annonce.component';

describe('AnnonceComponent', () => {
  let component: AnnonceComponent;
  let fixture: ComponentFixture<AnnonceComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AnnonceComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AnnonceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
