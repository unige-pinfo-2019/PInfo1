import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ReviewPersoComponent } from './review-perso.component';

describe('ReviewPersoComponent', () => {
  let component: ReviewPersoComponent;
  let fixture: ComponentFixture<ReviewPersoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ReviewPersoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ReviewPersoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
