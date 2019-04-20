import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AnnonceBottomComponent } from './annonce-bottom.component';

describe('AnnonceBottomComponent', () => {
  let component: AnnonceBottomComponent;
  let fixture: ComponentFixture<AnnonceBottomComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AnnonceBottomComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AnnonceBottomComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
