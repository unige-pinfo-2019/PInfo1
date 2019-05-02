import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BodyCatalogueComponent } from './body-catalogue.component';

describe('BodyCatalogueComponent', () => {
  let component: BodyCatalogueComponent;
  let fixture: ComponentFixture<BodyCatalogueComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BodyCatalogueComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BodyCatalogueComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
