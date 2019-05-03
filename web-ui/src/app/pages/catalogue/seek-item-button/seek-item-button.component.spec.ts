import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SeekItemButtonComponent } from './seek-item-button.component';

describe('SeekItemButtonComponent', () => {
  let component: SeekItemButtonComponent;
  let fixture: ComponentFixture<SeekItemButtonComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SeekItemButtonComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SeekItemButtonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
