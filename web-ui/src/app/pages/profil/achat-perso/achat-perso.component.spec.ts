import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AchatPersoComponent } from './achat-perso.component';

describe('AchatPersoComponent', () => {
  let component: AchatPersoComponent;
  let fixture: ComponentFixture<AchatPersoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AchatPersoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AchatPersoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
