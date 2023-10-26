//
//  ExitApp.m
//  pecportal
//
//  Created by 刘利军 on 2020/12/23.
//

#import "ExitApp.h"

@implementation ExitApp

// To export a module named CalendarManager
RCT_EXPORT_MODULE();

// This would name the module AwesomeCalendarManager instead
// RCT_EXPORT_MODULE(AwesomeCalendarManager);
RCT_EXPORT_METHOD(exitApp)
{
    exit(0);
};

@end
