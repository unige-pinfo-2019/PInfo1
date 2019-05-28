import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PageDiscussionComponent } from './page-discussion.component';

describe('PageDiscussionComponent', () => {
  let component: PageDiscussionComponent;
  let fixture: ComponentFixture<PageDiscussionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PageDiscussionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PageDiscussionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
