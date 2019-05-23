import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InfoPersoComponent } from './info-perso.component';

describe('InfoPersoComponent', () => {
  let component: InfoPersoComponent;
  let fixture: ComponentFixture<InfoPersoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InfoPersoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InfoPersoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
