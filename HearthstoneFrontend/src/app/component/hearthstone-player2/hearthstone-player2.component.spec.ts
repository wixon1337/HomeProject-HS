import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HearthstonePlayer2Component } from './hearthstone-player2.component';

describe('HearthstonePlayer2Component', () => {
  let component: HearthstonePlayer2Component;
  let fixture: ComponentFixture<HearthstonePlayer2Component>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HearthstonePlayer2Component ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HearthstonePlayer2Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
