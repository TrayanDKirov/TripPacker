import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PackingGroupView } from './packing-group-view.component';

describe('PackingGroupView', () => {
  let component: PackingGroupView;
  let fixture: ComponentFixture<PackingGroupView>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PackingGroupView]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PackingGroupView);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
