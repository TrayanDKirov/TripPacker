import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GroupToPack } from './group-to-pack';

describe('GroupToPack', () => {
  let component: GroupToPack;
  let fixture: ComponentFixture<GroupToPack>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GroupToPack]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GroupToPack);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
