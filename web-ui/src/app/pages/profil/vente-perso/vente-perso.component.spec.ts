import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VentePersoComponent } from './vente-perso.component';

describe('VentePersoComponent', () => {
  let component: VentePersoComponent;
  let fixture: ComponentFixture<VentePersoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VentePersoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VentePersoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
