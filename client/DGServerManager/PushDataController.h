//
//  PushDataController.h
//  DGServerManager
//
//  Created by apple on 2018/3/22.
//  Copyright © 2018年 apple. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface PushDataController : UIViewController

@property (weak, nonatomic) IBOutlet UITableView *tablView;

- (void)initWithCode:(NSString*)code;

@end
