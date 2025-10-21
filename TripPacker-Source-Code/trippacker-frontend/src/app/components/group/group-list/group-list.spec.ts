import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GroupOverviewList } from './group-list.component';

describe('GroupOverviewList', () => {
  let component: GroupOverviewList;
  let fixture: ComponentFixture<GroupOverviewList>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GroupOverviewList]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GroupOverviewList);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
