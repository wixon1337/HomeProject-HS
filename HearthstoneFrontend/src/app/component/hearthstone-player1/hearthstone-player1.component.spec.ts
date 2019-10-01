import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HearthstonePlayer1Component } from './hearthstone-player1.component';

describe('HearthstonePlayer1Component', () => {
  let component: HearthstonePlayer1Component;
  let fixture: ComponentFixture<HearthstonePlayer1Component>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HearthstonePlayer1Component ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HearthstonePlayer1Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
