import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ModifItemComponent } from './modif-item.component';

describe('ModifItemComponent', () => {
  let component: ModifItemComponent;
  let fixture: ComponentFixture<ModifItemComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ModifItemComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ModifItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
