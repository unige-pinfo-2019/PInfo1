import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ModifAnnonceComponent } from './modif-annonce.component';

describe('ModifAnnonceComponent', () => {
  let component: ModifAnnonceComponent;
  let fixture: ComponentFixture<ModifAnnonceComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ModifAnnonceComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ModifAnnonceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
