import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PageAchatComponent } from './page-achat.component';

describe('PageAchatComponent', () => {
  let component: PageAchatComponent;
  let fixture: ComponentFixture<PageAchatComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PageAchatComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PageAchatComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
