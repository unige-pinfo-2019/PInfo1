import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PageAnnonceComponent } from './page-annonce.component';

describe('PageAnnonceComponent', () => {
  let component: PageAnnonceComponent;
  let fixture: ComponentFixture<PageAnnonceComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PageAnnonceComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PageAnnonceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
