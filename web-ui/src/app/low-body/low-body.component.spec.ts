import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LowBodyComponent } from './low-body.component';

describe('LowBodyComponent', () => {
  let component: LowBodyComponent;
  let fixture: ComponentFixture<LowBodyComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LowBodyComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LowBodyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
