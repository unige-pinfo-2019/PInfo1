import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HighBodyComponent } from './high-body.component';

describe('HighBodyComponent', () => {
  let component: HighBodyComponent;
  let fixture: ComponentFixture<HighBodyComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HighBodyComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HighBodyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
